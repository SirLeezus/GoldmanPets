package lee.code.pets.pets.pet.animal;

import lee.code.pets.pets.controllers.ControllerWASDFlying;
import lee.code.pets.pets.goals.FollowOwnerFlyingGoal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.animal.Animal;
import org.bukkit.craftbukkit.v1_20_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class BeePet extends Animal implements NeutralMob {
  private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
  private static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(BeePet.class, EntityDataSerializers.BYTE);
  private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(BeePet.class, EntityDataSerializers.INT);

  public BeePet(Player player, String[] data) {
    super(EntityType.BEE, ((CraftWorld) player.getLocation().getWorld()).getHandle());
    setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    setInvulnerable(true);
    setCustomNameVisible(true);
    setPersistenceRequired(true);
    setNoGravity(true);
    setCanPickUpLoot(false);
    setMaxUpStep(1.0F);
    collides = false;
    ageLocked = true;
    setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(data[1])));
    setBaby(Boolean.parseBoolean(data[2]));
    setAnger(Boolean.parseBoolean(data[3]));
    setHasNectar(Boolean.parseBoolean(data[4]));
    setHasStung(Boolean.parseBoolean(data[5]));
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerWASDFlying(this, player.getUniqueId(), 0.2F);
    targetSelector.getAvailableGoals().clear();
    getBrain().removeAllBehaviors();
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FollowOwnerFlyingGoal(this, 0.3, 5, 10));
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return SoundEvents.BEE_POLLINATE;
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
    entityData.define(DATA_FLAGS_ID, (byte) 0);
    entityData.define(DATA_REMAINING_ANGER_TIME, 0);
  }

  @Override
  public void addAdditionalSaveData(CompoundTag nbt) {
    super.addAdditionalSaveData(nbt);
    nbt.putBoolean("HasNectar", hasNectar());
    nbt.putBoolean("HasStung", hasStung());
    addPersistentAngerSaveData(nbt);
  }

  @Override
  public void readAdditionalSaveData(CompoundTag nbt) {
    super.readAdditionalSaveData(nbt);
    setHasNectar(nbt.getBoolean("HasNectar"));
    setHasStung(nbt.getBoolean("HasStung"));
    readPersistentAngerSaveData(level(), nbt);
  }

  @Override
  public int getRemainingPersistentAngerTime() {
    return entityData.get(DATA_REMAINING_ANGER_TIME);
  }

  @Override
  public void setRemainingPersistentAngerTime(int angerTime) {
    entityData.set(DATA_REMAINING_ANGER_TIME, angerTime);
  }

  @Override
  public UUID getPersistentAngerTarget() {
    return null;
  }

  @Override
  public void setPersistentAngerTarget(@Nullable UUID angryAt) {
  }

  @Override
  public void startPersistentAngerTimer() {
    setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(random));
  }

  private void setFlag(int bit, boolean value) {
    if (value) entityData.set(DATA_FLAGS_ID, (byte) (entityData.get(DATA_FLAGS_ID) | bit));
    else entityData.set(DATA_FLAGS_ID, (byte) (entityData.get(DATA_FLAGS_ID) & ~bit));
  }

  public void setAnger(boolean isAnger) {
    if (isAnger) setRemainingPersistentAngerTime(1);
    else setRemainingPersistentAngerTime(0);
  }

  public void setHasNectar(boolean hasNectar) {
    setFlag(8, hasNectar);
  }

  public void setHasStung(boolean hasStung) {
    setFlag(4, hasStung);
  }

  public boolean hasStung() {
    return getFlag(4);
  }

  public boolean hasNectar() {
    return getFlag(8);
  }

  private boolean getFlag(int location) {
    return (entityData.get(DATA_FLAGS_ID) & location) != 0;
  }
}
