package lee.code.pets.pets;

import com.google.common.collect.Maps;
import lee.code.pets.pets.logic.ControllerWASD;
import lee.code.pets.pets.logic.FollowOwnerGoal;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

public class SheepPet extends Mob {
  public SheepPet(Player player, Location loc, String name) {
    super(EntityType.SNIFFER, ((CraftWorld) loc.getWorld()).getHandle());
    this.setPos(loc.getX(), loc.getY(), loc.getZ());
    this.setInvulnerable(true);
    this.setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(name)));
    this.setCustomNameVisible(true);
    this.collides = false;
    this.setPersistenceRequired(true);
    this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    this.setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
    this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    this.moveControl = new ControllerWASD(this, player.getUniqueId());
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(0, new FollowOwnerGoal(this, 1));
  }

  @Override
  public boolean save(CompoundTag compoundTag) {
    return false;
  }

  @Override
  public void load(CompoundTag compoundTag) {
  }
}
