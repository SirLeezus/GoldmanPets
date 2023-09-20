package lee.code.pets.pets.pet.mob;

import lee.code.pets.pets.controllers.ControllerLookFlying;
import lee.code.pets.pets.goals.FollowOwnerGoal;
import lee.code.pets.pets.structure.PetMob;
import lee.code.pets.utils.CoreUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.Objects;

public class PhantomPet extends PetMob {

  public PhantomPet(Player player, String name) {
    super(EntityType.PHANTOM, ((CraftWorld) player.getLocation().getWorld()).getHandle());
    setPos(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
    setCustomName(Component.Serializer.fromJson(CoreUtil.serializeColorComponentJson(name)));
    setTarget(((CraftPlayer) player).getHandle(), EntityTargetEvent.TargetReason.CUSTOM, false);
    moveControl = new ControllerLookFlying(this, player.getUniqueId());
    setAttributes();
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(0, new FollowOwnerGoal(this, 2));
  }

  protected void setAttributes() {
    Objects.requireNonNull(getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.15D);
  }
}
