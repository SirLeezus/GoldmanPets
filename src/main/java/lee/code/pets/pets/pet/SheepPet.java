package lee.code.pets.pets.pet;

import lee.code.pets.pets.controllers.ControllerWASD;
import lee.code.pets.pets.goals.FollowOwnerGoal;
import lee.code.pets.pets.structure.PetAnimal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.DyeColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.Objects;

public class SheepPet extends PetAnimal {
  private static final EntityDataAccessor<Byte> DATA_WOOL_ID = SynchedEntityData.defineId(SheepPet.class, EntityDataSerializers.BYTE);

  public SheepPet(Player player, Location loc, String name, String color) {
    super(EntityType.SHEEP, ((CraftWorld) loc.getWorld()).getHandle());
    setPos(loc.getX(), loc.getY(), loc.getZ());
    setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(name)));
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerWASD(this, player.getUniqueId());
    setAttributes();
    setWoolColor(DyeColor.valueOf(color));
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FollowOwnerGoal(this, 2));
  }

  protected void setAttributes() {
    Objects.requireNonNull(getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.23000000417232513D);
  }

  @Override
  public void addAdditionalSaveData(CompoundTag nbt) {
    super.addAdditionalSaveData(nbt);
    nbt.putByte("Color", (byte) getColor().getId());
  }

  @Override
  public void readAdditionalSaveData(CompoundTag nbt) {
    super.readAdditionalSaveData(nbt);
    setWoolColor(DyeColor.byId(nbt.getByte("Color")));
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    entityData.define(DATA_WOOL_ID, (byte) 0);
  }

  public DyeColor getColor() {
    return DyeColor.byId(entityData.get(DATA_WOOL_ID) & 15);
  }

  public void setWoolColor(DyeColor color) {
    final byte byteID = entityData.get(DATA_WOOL_ID);
    entityData.set(DATA_WOOL_ID, (byte) (byteID & 240 | color.getId() & 15));
  }
}
