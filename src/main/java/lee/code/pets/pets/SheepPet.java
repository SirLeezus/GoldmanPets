package lee.code.pets.pets;

import lee.code.pets.pets.controllers.ControllerLookFlying;
import lee.code.pets.pets.goals.FollowOwnerGoal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

public class SheepPet extends Mob {

  public SheepPet(Player player, Location loc, String name) {
    super(EntityType.BEE, ((CraftWorld) loc.getWorld()).getHandle());
    this.setPos(loc.getX(), loc.getY(), loc.getZ());
    this.setInvulnerable(true);
    this.setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(name)));
    this.setCustomNameVisible(true);
    this.collides = false;
    this.setPersistenceRequired(true);
    this.setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    this.moveControl = new ControllerLookFlying(this, player.getUniqueId());
    setAttributes();
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(0, new FollowOwnerGoal(this, 2));
  }

  private void setAttributes() {
    this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(15.0D);
    this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(10);
    this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
  }

  @Override
  public boolean save(CompoundTag compoundTag) {
    return false;
  }

  @Override
  public void load(CompoundTag compoundTag) {
  }
}
