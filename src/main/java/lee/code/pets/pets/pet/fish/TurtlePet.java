package lee.code.pets.pets.pet.fish;

import lee.code.pets.pets.controllers.ControllerWASDWater;
import lee.code.pets.pets.goals.FollowOwnerWaterGoal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Turtle;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

public class TurtlePet extends Turtle {

  public TurtlePet(Player player, String[] data) {
    super(EntityType.TURTLE, ((CraftWorld) player.getLocation().getWorld()).getHandle());
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
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerWASDWater(this, player.getUniqueId(), true, false, 0.2F);
    targetSelector.getAvailableGoals().clear();
    getBrain().removeAllBehaviors();
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FollowOwnerWaterGoal(this, 0.4f, 5, 20, true));
  }

  @Override
  public void load(CompoundTag compoundTag) {
  }

  @Override
  public boolean save(CompoundTag compoundTag) {
    return false;
  }
}
