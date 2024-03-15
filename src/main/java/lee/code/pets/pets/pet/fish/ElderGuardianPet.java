package lee.code.pets.pets.pet.fish;

import lee.code.pets.pets.controllers.ControllerWASDWater;
import lee.code.pets.pets.goals.FollowOwnerWaterGoal;
import lee.code.pets.pets.goals.LookAtOwnerGoal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

public class ElderGuardianPet extends Monster {
  private volatile CraftEntity bukkitEntity;

  public ElderGuardianPet(Player player, String[] data) {
    super(EntityType.ELDER_GUARDIAN, ((CraftWorld) player.getLocation().getWorld()).getHandle());
    setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    setInvulnerable(true);
    setCustomNameVisible(true);
    setPersistenceRequired(true);
    setCanPickUpLoot(false);
    setMaxUpStep(1.0F);
    collides = false;
    setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(data[1])));
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerWASDWater(this, player.getUniqueId(), false, true, 0.2F);
    targetSelector.getAvailableGoals().clear();
    getBrain().removeAllBehaviors();
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FollowOwnerWaterGoal(this, 0.4f, 5, 20, false));
    goalSelector.addGoal(1, new LookAtOwnerGoal(this));
  }

  @Override
  public void load(CompoundTag compoundTag) {
  }

  @Override
  public boolean save(CompoundTag compoundTag) {
    return false;
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return isInWaterOrBubble() ? SoundEvents.ELDER_GUARDIAN_AMBIENT : SoundEvents.ELDER_GUARDIAN_AMBIENT_LAND;
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
