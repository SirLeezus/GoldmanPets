package lee.code.pets.pets.pet.monster;

import lee.code.pets.pets.controllers.ControllerWASDFlying;
import lee.code.pets.pets.goals.FollowOwnerFlyingGoal;
import lee.code.pets.pets.goals.LookAtOwnerGoal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

public class VexPet extends Mob {
  protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(VexPet.class, EntityDataSerializers.BYTE);

  public VexPet(Player player, String[] data) {
    super(EntityType.VEX, ((CraftWorld) player.getLocation().getWorld()).getHandle());
    setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    setInvulnerable(true);
    setCustomNameVisible(true);
    setPersistenceRequired(true);
    setCanPickUpLoot(false);
    setNoGravity(true);
    setMaxUpStep(1.0F);
    collides = false;
    setIsCharging(Boolean.parseBoolean(data[2]));
    setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(data[1])));
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerWASDFlying(this, player.getUniqueId(), 0.3F);
    targetSelector.getAvailableGoals().clear();
    getBrain().removeAllBehaviors();
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FollowOwnerFlyingGoal(this, 0.3, 5, 20));
    goalSelector.addGoal(1, new LookAtOwnerGoal(this));
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return SoundEvents.VEX_AMBIENT;
  }

  @Override
  public void load(CompoundTag compoundTag) {
  }

  @Override
  public boolean save(CompoundTag compoundTag) {
    return false;
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(DATA_FLAGS_ID, (byte) 0);
  }

  private void setVexFlag(int mask, boolean value) {
    final byte b0 = entityData.get(DATA_FLAGS_ID);
    final int j;
    if (value) j = b0 | mask;
    else j = b0 & ~mask;
    entityData.set(DATA_FLAGS_ID, (byte) (j & 255));
  }

  public void setIsCharging(boolean charging) {
    setVexFlag(1, charging);
  }
}
