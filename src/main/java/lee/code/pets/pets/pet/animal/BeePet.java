package lee.code.pets.pets.pet.animal;

import lee.code.pets.pets.controllers.ControllerWASDFlying;
import lee.code.pets.pets.goals.FollowOwnerGoal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

public class BeePet extends Animal {

  public BeePet(Player player, boolean baby, String name) {
    super(EntityType.BEE, ((CraftWorld) player.getLocation().getWorld()).getHandle());
    setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    setInvulnerable(true);
    setCustomNameVisible(true);
    setPersistenceRequired(true);
    setNoGravity(true);
    collides = false;
    setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(name)));
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerWASDFlying(this, player.getUniqueId());
    if (baby) setBaby(true);
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FollowOwnerGoal(this, 2));
  }

  public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
    return null;
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
