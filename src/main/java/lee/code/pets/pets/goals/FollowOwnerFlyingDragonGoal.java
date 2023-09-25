package lee.code.pets.pets.goals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class FollowOwnerFlyingDragonGoal extends Goal {
  private final Mob mob;
  private final double speed;
  private final float minDistance;
  private final float maxDistance;

  public FollowOwnerFlyingDragonGoal(Mob mob, double speed, float minDistance, float maxDistance) {
    this.mob = mob;
    this.speed = speed;
    this.minDistance = minDistance;
    this.maxDistance = maxDistance;
    this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
  }

  @Override
  public boolean canUse() {
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

    // Calculate yaw angle (in degrees) from dragon to player's location
    final double targetYaw = Math.toDegrees(Math.atan2(-dx, dz));

    final double distanceToOwner = Math.sqrt(dx * dx + dy * dy + dz * dz);

    final double speedX = dx / distanceToOwner * speed;
    double speedY = dy / distanceToOwner * speed;
    final double speedZ = dz / distanceToOwner * speed;

    // Check for blocks 5 blocks down from the dragon's current position
    final BlockState blockState = mob.level().getBlockState(mob.blockPosition().offset(0, -5, 0));
    if (!blockState.isAir()) {
      // Apply upward force if a block is detected
      speedY = 0.5; // You can adjust the upward force as needed
    }

    mob.setDeltaMovement(speedX, speedY, speedZ);
    mob.setYRot((float) targetYaw + 180f);

    if (distanceToOwner > maxDistance) {
      mob.setPos(ownerPos.x, ownerPos.y, ownerPos.z);
    }
  }
}
