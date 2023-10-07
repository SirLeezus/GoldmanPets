package lee.code.pets.pets.pet.animal;

import lee.code.pets.pets.controllers.ControllerWASD;
import lee.code.pets.pets.goals.FollowOwnerGoal;
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
import org.bukkit.craftbukkit.v1_20_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

public class SnowGolemPet extends Mob {
  private static final EntityDataAccessor<Byte> DATA_PUMPKIN_ID = SynchedEntityData.defineId(SnowGolemPet.class, EntityDataSerializers.BYTE);

  public SnowGolemPet(Player player, String[] data) {
    super(EntityType.SNOW_GOLEM, ((CraftWorld) player.getLocation().getWorld()).getHandle());
    setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    setInvulnerable(true);
    setCustomNameVisible(true);
    setPersistenceRequired(true);
    setCanPickUpLoot(false);
    setMaxUpStep(1.0F);
    collides = false;
    setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(data[1])));
    setPumpkin(Boolean.parseBoolean(data[2]));
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerWASD(this, player.getUniqueId(), 0.4F);
    targetSelector.getAvailableGoals().clear();
    getBrain().removeAllBehaviors();
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FollowOwnerGoal(this, 2));
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return SoundEvents.SNOW_GOLEM_AMBIENT;
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    entityData.define(DATA_PUMPKIN_ID, (byte) 16);
  }

  public boolean hasPumpkin() {
    return (entityData.get(DATA_PUMPKIN_ID) & 16) != 0;
  }

  public void setPumpkin(boolean hasPumpkin) {
    final byte b0 = entityData.get(DATA_PUMPKIN_ID);
    if (hasPumpkin) {
      this.entityData.set(DATA_PUMPKIN_ID, (byte) (b0 | 16));
    } else {
      this.entityData.set(DATA_PUMPKIN_ID, (byte) (b0 & -17));
    }
  }

  @Override
  public void addAdditionalSaveData(CompoundTag nbt) {
    super.addAdditionalSaveData(nbt);
    nbt.putBoolean("Pumpkin", hasPumpkin());
  }

  @Override
  public void readAdditionalSaveData(CompoundTag nbt) {
    super.readAdditionalSaveData(nbt);
    if (nbt.contains("Pumpkin")) {
      setPumpkin(nbt.getBoolean("Pumpkin"));
    }
  }

  @Override
  public void load(CompoundTag compoundTag) {
  }

  @Override
  public boolean save(CompoundTag compoundTag) {
    return false;
  }
}
