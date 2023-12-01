package lee.code.pets.pets.pet.monster;

import lee.code.pets.pets.controllers.ControllerWASDFlying;
import lee.code.pets.pets.goals.FollowOwnerFlyingGoal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import org.bukkit.craftbukkit.v1_20_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

public class BlazePet extends Monster {
  private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(BlazePet.class, EntityDataSerializers.BYTE);

  public BlazePet(Player player, String[] data) {
    super(EntityType.BLAZE, ((CraftWorld) player.getLocation().getWorld()).getHandle());
    setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    setInvulnerable(true);
    setCustomNameVisible(true);
    setPersistenceRequired(true);
    setCanPickUpLoot(false);
    setNoGravity(true);
    setMaxUpStep(1.0F);
    setCharged(Boolean.parseBoolean(data[2]));
    collides = false;
    setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(data[1])));
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerWASDFlying(this, player.getUniqueId(), 0.3F);
    targetSelector.getAvailableGoals().clear();
    getBrain().removeAllBehaviors();
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return SoundEvents.BLAZE_AMBIENT;
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FollowOwnerFlyingGoal(this, 0.4, 5, 10));
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

  private void setCharged(boolean fireActive) {
    byte b = entityData.get(DATA_FLAGS_ID);
    if (fireActive) b = (byte)(b | 1);
    else b = (byte)(b & -2);
    entityData.set(DATA_FLAGS_ID, b);
  }
}
