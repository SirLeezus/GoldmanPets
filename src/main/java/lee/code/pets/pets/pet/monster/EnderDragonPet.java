package lee.code.pets.pets.pet.monster;

import lee.code.pets.pets.controllers.ControllerWASDFlyingDragon;
import lee.code.pets.pets.goals.FollowOwnerFlyingDragonGoal;
import lee.code.pets.pets.goals.LookAtOwnerGoal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

public class EnderDragonPet extends Mob {
  private volatile CraftEntity bukkitEntity;

  public EnderDragonPet(Player player, String[] data) {
    super(EntityType.ENDER_DRAGON, ((CraftWorld) player.getLocation().getWorld()).getHandle());
    setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    setInvulnerable(true);
    setCustomNameVisible(true);
    setPersistenceRequired(true);
    setCanPickUpLoot(false);
    setNoGravity(false);
    setMaxUpStep(1.0F);
    collides = false;
    setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(data[1])));
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerWASDFlyingDragon(this, player.getUniqueId(), 0.2F);
    targetSelector.getAvailableGoals().clear();
    getBrain().removeAllBehaviors();
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FollowOwnerFlyingDragonGoal(this, 0.6, 7, 40));
    goalSelector.addGoal(1, new LookAtOwnerGoal(this));
  }

  @Override
  public void load(CompoundTag compoundTag) {
  }

  @Override
  public boolean save(CompoundTag compoundTag) {
    return false;
  }

  // 1.20.4 fix
  @Override
  public CraftEntity getBukkitEntity() {
    if (this.bukkitEntity == null) {
      synchronized (this) {
        if (this.bukkitEntity == null) {
          return this.bukkitEntity = new CraftLivingEntity(this.level().getCraftServer(), this) {};
        }
      }
    }
    return this.bukkitEntity;
  }

  // 1.20.4 fix
  @Override
  public CraftLivingEntity getBukkitLivingEntity() {
    return (CraftLivingEntity) this.getBukkitEntity();
  }
}
