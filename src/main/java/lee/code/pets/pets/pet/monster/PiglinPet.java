package lee.code.pets.pets.pet.monster;

import lee.code.pets.pets.controllers.ControllerWASD;
import lee.code.pets.pets.goals.FollowOwnerGoal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.Piglin;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

public class PiglinPet extends Piglin {
  private final LivingEntity target;

  public PiglinPet(Player player, boolean baby, String name) {
    super(EntityType.PIGLIN, ((CraftWorld) player.getLocation().getWorld()).getHandle());
    setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    setInvulnerable(true);
    setCustomNameVisible(true);
    setPersistenceRequired(true);
    setCanPickUpLoot(false);
    setImmuneToZombification(true);
    collides = false;
    setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(name)));
    target = ((CraftPlayer) player).getHandle();
    setTarget(target, EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerWASD(this, player.getUniqueId());
    setBaby(baby);
    targetSelector.getAvailableGoals().clear();
    getBrain().removeAllBehaviors();
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FollowOwnerGoal(this, 1.5));
  }

  @Override
  public void load(CompoundTag compoundTag) {
  }

  @Override
  public boolean save(CompoundTag compoundTag) {
    return false;
  }

  @Override
  protected void customServerAiStep() {
  }

  // The default target works different with Piglin
  @Override
  public LivingEntity getTarget() {
    return target;
  }
}