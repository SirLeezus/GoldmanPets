package lee.code.pets.pets.pet.animal;

import lee.code.pets.pets.controllers.ControllerWASD;
import lee.code.pets.pets.goals.FollowOwnerGoal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.JumpControl;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.UUID;

public class RabbitPet extends Animal implements VariantHolder<Rabbit.Variant> {
  private static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(RabbitPet.class, EntityDataSerializers.INT);
  private int jumpTicks;
  private int jumpDuration;
  private boolean wasOnGround;
  private int jumpDelayTicks;

  public RabbitPet(Player player, String[] data) {
    super(EntityType.RABBIT, ((CraftWorld) player.getLocation().getWorld()).getHandle());
    setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    setInvulnerable(true);
    setCustomNameVisible(true);
    setPersistenceRequired(true);
    setCanPickUpLoot(false);
    setMaxUpStep(1.0F);
    collides = false;
    ageLocked = true;
    setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(data[1])));
    setBaby(Boolean.parseBoolean(data[2]));
    setVariant(Rabbit.Variant.valueOf(data[3]));
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerWASDRabbit(this, player.getUniqueId(), 0.4F);
    jumpControl = new RabbitJumpControl(this);
    targetSelector.getAvailableGoals().clear();
    getBrain().removeAllBehaviors();
  }

  @Override
  public void registerGoals() {
    goalSelector.addGoal(0, new FollowOwnerGoal(this, 3));
  }

  @Override
  public void load(CompoundTag compoundTag) {
  }

  @Override
  public boolean save(CompoundTag compoundTag) {
    return false;
  }

  @Override
  public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
    return null;
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(DATA_TYPE_ID, Rabbit.Variant.BROWN.id());
  }

  @Override
  public Rabbit.Variant getVariant() {
    return Rabbit.Variant.byId(entityData.get(DATA_TYPE_ID));
  }

  @Override
  public void setVariant(Rabbit.Variant variant) {
    this.entityData.set(DATA_TYPE_ID, variant.id());
  }

  @Override
  public void addAdditionalSaveData(CompoundTag nbt) {
    super.addAdditionalSaveData(nbt);
    nbt.putInt("RabbitType", getVariant().id());
  }

  @Override
  public void readAdditionalSaveData(CompoundTag nbt) {
    super.readAdditionalSaveData(nbt);
    this.setVariant(Rabbit.Variant.byId(nbt.getInt("RabbitType")));
  }

  @Override
  public void aiStep() {
    super.aiStep();
    if (jumpTicks != jumpDuration) {
      ++jumpTicks;
    } else if (jumpDuration != 0) {
      jumpTicks = 0;
      jumpDuration = 0;
      setJumping(false);
    }
  }

  private void checkLandingDelay() {
    setLandingDelay();
    disableJumpControl();
  }

  private void enableJumpControl() {
    ((RabbitJumpControl) jumpControl).setCanJump(true);
  }

  private void disableJumpControl() {
    ((RabbitJumpControl) jumpControl).setCanJump(false);
  }

  private void setLandingDelay() {
    if (moveControl.getSpeedModifier() < 2.2D) {
      jumpDelayTicks = 10;
    } else {
      jumpDelayTicks = 1;
    }
  }

  private void facePoint(double x, double z) {
    setYRot((float) (Mth.atan2(z - getZ(), x - getX()) * 57.2957763671875D) - 90.0F);
  }

  @Override
  public void customServerAiStep() {
    if (jumpDelayTicks > 0) {
      --jumpDelayTicks;
    }

    if (onGround()) {
      if (!wasOnGround) {
        setJumping(false);
        checkLandingDelay();
      }
      final RabbitJumpControl entityrabbit_controllerjumprabbit = (RabbitJumpControl) jumpControl;
      if (!entityrabbit_controllerjumprabbit.wantJump()) {
        if (moveControl.hasWanted() && jumpDelayTicks == 0) {
          final Path pathentity = navigation.getPath();
          Vec3 vec3d = new Vec3(moveControl.getWantedX(), moveControl.getWantedY(), moveControl.getWantedZ());
          if (pathentity != null && !pathentity.isDone()) vec3d = pathentity.getNextEntityPos(this);
          facePoint(vec3d.x, vec3d.z);
          startJumping();
        }
      } else if (!entityrabbit_controllerjumprabbit.canJump()) {
        enableJumpControl();
      }
    }
    wasOnGround = onGround();
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return SoundEvents.RABBIT_AMBIENT;
  }

  protected SoundEvent getJumpSound() {
    return SoundEvents.RABBIT_JUMP;
  }

  public void startJumping() {
    setJumping(true);
    jumpDuration = 10;
    jumpTicks = 0;
  }

  @Override
  public void setJumping(boolean jumping) {
    super.setJumping(jumping);
    if (jumping) {
      playSound(getJumpSound(), getSoundVolume(), ((random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
    }
  }

  @Override
  protected void jumpFromGround() {
    super.jumpFromGround();
    final double d0 = this.moveControl.getSpeedModifier();
    if (d0 > 0.0D) {
      final double d1 = this.getDeltaMovement().horizontalDistanceSqr();
      if (d1 < 0.01D) moveRelative(0.1F, new Vec3(0.0D, 0.0D, 1.0D));
    }
    if (!level().isClientSide) level().broadcastEntityEvent(this, (byte) 1);
  }

  private class ControllerWASDRabbit extends ControllerWASD {
    private double nextJumpSpeed;

    public ControllerWASDRabbit(Mob mob, UUID owner, float speed) {
      super(mob, owner, speed);
    }

    @Override
    public void tick() {
      if (mob.onGround() && !mob.jumping && !((RabbitJumpControl) jumpControl).wantJump()) {
        setSpeedModifier(0.0D);
      } else if (hasWanted()) {
        setSpeedModifier(nextJumpSpeed);
      }
      super.tick();
    }

    public void setSpeedModifier(double speed) {
      getNavigation().setSpeedModifier(speed);
      moveControl.setWantedPosition(moveControl.getWantedX(), moveControl.getWantedY(), moveControl.getWantedZ(), speed);
    }

    @Override
    public void setWantedPosition(double x, double y, double z, double speed) {
      if (mob.isInWater()) speed = 1.5D;
      super.setWantedPosition(x, y, z, speed);
      if (speed > 0.0D) nextJumpSpeed = speed;
    }
  }

  public class RabbitJumpControl extends JumpControl {
    private final Mob mob;
    private boolean canJump;

    public RabbitJumpControl(Mob mob) {
      super(mob);
      this.mob = mob;
    }

    public boolean wantJump() {
      return jump;
    }

    public boolean canJump() {
      return canJump;
    }

    public void setCanJump(boolean canJump) {
      this.canJump = canJump;
    }

    @Override
    public void tick() {
      if (jump) {
        startJumping();
        jump = false;
      }
    }
  }
}
