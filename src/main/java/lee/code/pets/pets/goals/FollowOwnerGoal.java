package lee.code.pets.pets.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;
import net.minecraft.world.phys.Vec3;

public class FollowOwnerGoal extends Goal {
  protected final Mob mob;
  protected LivingEntity owner;
  protected final double speed;
  protected double xOffset;
  protected double zOffset;

  public FollowOwnerGoal(Mob mob, double speed) {
    this.mob = mob;
    this.speed = speed;
    this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
  }

  @Override
  public boolean canUse() {
    owner = mob.getTarget();
    if (owner == null) return false;
    if (!mob.getPassengers().isEmpty()) return false;
    final float distance = 15;
    if (owner.distanceToSqr(mob) > (double) (distance * distance)) {
      mob.setPos(owner.getX(), owner.getY(), owner.getZ());
      return false;
    }
    if (mob.distanceTo(owner) < 4.0) return false;
    final Vec3 ownerPos = owner.position();
    final double angle = Math.toRadians(owner.getYRot() - 90);
    final double xOffset = Math.cos(angle) * 2.0;
    final double zOffset = Math.sin(angle) * 2.0;

    this.xOffset = ownerPos.x + xOffset;
    this.zOffset = ownerPos.z + zOffset;
    return true;
  }

  @Override
  public void start() {
    moveTo();
  }

  @Override
  public boolean canContinueToUse() {
    return !mob.getPassengers().isEmpty() && mob.distanceTo(owner) > 4.0;
  }

  @Override
  public void stop() {
  }

  protected void moveTo() {
    mob.getMoveControl().setWantedPosition(xOffset, owner.getY(), zOffset, speed);
  }
}
