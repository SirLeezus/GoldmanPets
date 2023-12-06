package lee.code.pets.pets.goals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class FollowOwnerWaterGoal extends Goal {
  private final Mob mob;
  private final double speed;
  private final float minDistance;
  private final float maxDistance;
  private final boolean landSupport;

  public FollowOwnerWaterGoal(Mob mob, double speed, float minDistance, float maxDistance, boolean landSupport) {
    this.mob = mob;
    this.speed = speed;
    this.minDistance = minDistance;
    this.maxDistance = maxDistance;
    this.landSupport = landSupport;
    this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
  }

  @Override
  public boolean canUse() {
    if (!mob.getPassengers().isEmpty() || mob.getTarget() == null) return false;
    final double distanceToOwner = mob.distanceTo(mob.getTarget());
    return distanceToOwner >= minDistance;
  }

  @Override
  public boolean canContinueToUse() {
    if (!mob.getPassengers().isEmpty() || mob.getTarget() == null) return false;
    final double distanceToOwner = mob.distanceTo(mob.getTarget());
    return distanceToOwner >= minDistance;
  }

  @Override
  public void start() {
  }

  @Override
  public void stop() {
  }

  @Override
  public void tick() {
    if (mob.getTarget() == null) return;
    final Vec3 ownerPos = mob.getTarget().position();
    final double dx = ownerPos.x - mob.getX();
    final double dy = ownerPos.y - mob.getY();
    final double dz = ownerPos.z - mob.getZ();
    final double distanceToOwner = Math.sqrt(dx * dx + dy * dy + dz * dz);

    final double speedX = dx / distanceToOwner * speed;
    final double speedY = mob.isInWater() ? dy / distanceToOwner * speed : -0.3;
    final double speedZ = dz / distanceToOwner * speed;

    mob.setDeltaMovement(speedX, speedY, speedZ);
    mob.lookAt(mob.getTarget(), 30.0F, 30.0F);

    if (!mob.isInWater()) {
      if (!landSupport) mob.getJumpControl().jump();
    }

    if (distanceToOwner > maxDistance) {
      mob.setPos(ownerPos.x, ownerPos.y, ownerPos.z);
    }
  }
}
