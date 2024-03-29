package lee.code.pets.pets.pet.monster;

import lee.code.pets.pets.controllers.ControllerWASDFlying;
import lee.code.pets.pets.goals.FollowOwnerFlyingGoal;
import lee.code.pets.pets.goals.LookAtOwnerGoal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

public class WitherPet extends Monster {
  private volatile CraftEntity bukkitEntity;

  public WitherPet(Player player, String[] data) {
    super(EntityType.WITHER, ((CraftWorld) player.getLocation().getWorld()).getHandle());
    setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    setInvulnerable(true);
    setCustomNameVisible(true);
    setPersistenceRequired(true);
    setCanPickUpLoot(false);
    setNoGravity(true);
    setMaxUpStep(1.0F);
    collides = false;
    setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(data[1])));
    if (Boolean.parseBoolean(data[2])) {
      getAttribute(Attributes.MAX_HEALTH).setBaseValue(0.1);
      setHealth(0.1f);
    }
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerWASDFlying(this, player.getUniqueId(), 0.3F);
    targetSelector.getAvailableGoals().clear();
    getBrain().removeAllBehaviors();
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FollowOwnerFlyingGoal(this, 0.3, 5, 15));
    goalSelector.addGoal(1, new LookAtOwnerGoal(this));
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return SoundEvents.WITHER_AMBIENT;
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
