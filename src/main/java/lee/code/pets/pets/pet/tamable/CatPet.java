package lee.code.pets.pets.pet.tamable;

import lee.code.pets.pets.controllers.ControllerWASD;
import lee.code.pets.pets.goals.FollowOwnerGoal;
import lee.code.pets.pets.pet.util.CatUtil;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.item.DyeColor;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.Objects;

public class CatPet extends Cat {

  public CatPet(Player player, boolean baby, String name, String variant, String collarColor) {
    super(EntityType.CAT, ((CraftWorld) player.getLocation().getWorld()).getHandle());
    setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    setInvulnerable(true);
    setCustomNameVisible(true);
    setPersistenceRequired(true);
    collides = false;
    setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(name)));
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerWASD(this, player.getUniqueId());
    if (baby) setBaby(true);
    setAttributes();
    setVariant(CatUtil.valueOf(variant).getCatVariant());
    setTame(true);
    setCollarColor(DyeColor.valueOf(collarColor));
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FollowOwnerGoal(this, 2));
  }

  protected void setAttributes() {
    Objects.requireNonNull(getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.23000000417232513D);
  }

  @Override
  public void ageUp(int age) {
  }

  @Override
  public void ageUp(int age, boolean overGrow) {
  }

  @Override
  public void load(CompoundTag compoundTag) {
  }

  @Override
  public boolean save(CompoundTag compoundTag) {
    return false;
  }
}
