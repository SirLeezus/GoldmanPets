package lee.code.pets.pets.pet.monster;

import lee.code.pets.pets.goals.FollowOwnerFlyingGoal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.DyeColor;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.Optional;

public class ShulkerPet extends Shulker {

  public ShulkerPet(Player player, String[] data) {
    super(EntityType.SHULKER, ((CraftWorld) player.getLocation().getWorld()).getHandle());
    setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    setInvulnerable(true);
    setCustomNameVisible(true);
    setPersistenceRequired(true);
    setCanPickUpLoot(false);
    setNoGravity(true);
    setMaxUpStep(1.0F);
    collides = false;
    setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(data[1])));
    if (Boolean.parseBoolean(data[2])) setVariant(Optional.of(DyeColor.valueOf(data[3])));
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    targetSelector.getAvailableGoals().clear();
    getBrain().removeAllBehaviors();
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new ShulkerPeekGoal());
    goalSelector.addGoal(1, new FollowOwnerFlyingGoal(this, 0.3, 3, 10));
  }

  @Override
  public void load(CompoundTag compoundTag) {
  }

  @Override
  public boolean save(CompoundTag compoundTag) {
    return false;
  }

  @Override
  protected boolean teleportSomewhere() {
    return false;
  }

  private class ShulkerPeekGoal extends Goal {
    private int peekTime;

    @Override
    public boolean canUse() {
      return random.nextInt(reducedTickDelay(40)) == 0;
    }

    @Override
    public boolean canContinueToUse() {
      return peekTime > 0;
    }

    @Override
    public void start() {
      peekTime = adjustedTickDelay(20 * (1 + random.nextInt(3)));
      setRawPeekAmount(30);
    }

    @Override
    public void stop() {
      setRawPeekAmount(0);
    }

    @Override
    public void tick() {
      --peekTime;
    }
  }
}
