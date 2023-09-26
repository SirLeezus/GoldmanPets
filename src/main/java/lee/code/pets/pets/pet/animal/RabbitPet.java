package lee.code.pets.pets.pet.animal;

import lee.code.pets.pets.controllers.ControllerWASD;
import lee.code.pets.pets.goals.FollowOwnerGoal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Rabbit;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.UUID;

public class RabbitPet extends Rabbit {
  //TODO maybe add killer rabbit, would require remaking rabbits

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
    setVariant(Variant.valueOf(data[3]));
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerWASDRabbit(this, player.getUniqueId());
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

  private class ControllerWASDRabbit extends ControllerWASD {
    private double nextJumpSpeed;

    public ControllerWASDRabbit(Mob mob, UUID owner) {
      super(mob, owner);
    }

    @Override
    public void tick() {
      if (mob.onGround() && !mob.jumping && !((Rabbit.RabbitJumpControl) jumpControl).wantJump()) {
        setSpeedModifier(0.0D);
      } else if (hasWanted()) {
        setSpeedModifier(nextJumpSpeed);
      }
      super.tick();
    }

    @Override
    public void setWantedPosition(double x, double y, double z, double speed) {
      if (mob.isInWater()) speed = 1.5D;
      super.setWantedPosition(x, y, z, speed);
      if (speed > 0.0D) nextJumpSpeed = speed;
    }
  }
}
