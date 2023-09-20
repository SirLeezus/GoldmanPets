package lee.code.pets.pets.goals;


import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;

public class FollowOwnerFlyingGoal extends FollowOwnerGoal {

  public FollowOwnerFlyingGoal(Mob mob, double speed) {
    super(mob, speed);
  }

  @Override
  public void tick() {
    // Get the current position of the mob
    BlockPos mobPos = mob.blockPosition();

    // Get the current position of the owner (player)
    BlockPos ownerPos = owner.blockPosition();

    // Calculate the direction vector from the mob to the owner
    Vec3 direction = new Vec3(ownerPos.getX() - mobPos.getX(), ownerPos.getY() - mobPos.getY(), ownerPos.getZ() - mobPos.getZ()).normalize();

    // Calculate the new position for the mob to move towards the owner
    double newX = mobPos.getX() + direction.x * speed; // Adjust the speed to control the movement speed
    double newY = mobPos.getY() + direction.y * speed;
    double newZ = mobPos.getZ() + direction.z * speed;

    // Update the mob's position
    mob.getMoveControl().setWantedPosition(newX, newY, newZ, speed);
  }
}
