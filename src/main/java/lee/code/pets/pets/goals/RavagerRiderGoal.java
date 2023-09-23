package lee.code.pets.pets.goals;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class RavagerRiderGoal extends Goal {
  private final Mob mob;
  private Player rider;

  public RavagerRiderGoal(Mob mob) {
    this.mob = mob;
    this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
  }

  @Override
  public boolean canUse() {
    // Check if there is a rider and if it's a player
    if (!mob.getPassengers().isEmpty()) {
      Entity passenger = mob.getPassengers().get(0);
      if (passenger instanceof Player player) {
        this.rider = player;
        return true;
      }
    }
    return false;
  }

  @Override
  public void tick() {
    if (rider == null) return;
    // Get rider's input
    final Vec3 input = getRiderInput(rider);

    // Calculate movement based on input
    final double forward = input.z * 0.5;
    final double strafe = input.x * 0.25;
    final double yaw = Math.toRadians(rider.getYRot());

    final double motionX = forward * Math.sin(-yaw) + strafe * Math.cos(-yaw);
    final double motionZ = forward * Math.cos(yaw) - strafe * Math.sin(yaw);

    // Calculate the new position based on the Ravager's current position and input
    final double newX = mob.getX() + motionX;
    final double newY = mob.getY();
    final double newZ = mob.getZ() + motionZ;

    // Update the Ravager's position
    mob.getMoveControl().setWantedPosition(newX, newY, newZ, 3);

    // Handle jumping if the rider is jumping
    if (rider.jumping) {
      mob.getJumpControl().jump();
    }
  }

  private Vec3 getRiderInput(Player rider) {
    final double moveForward = rider.zza;
    final double moveStrafe = rider.xxa;
    return new Vec3(moveStrafe, 0, moveForward);
  }
}
