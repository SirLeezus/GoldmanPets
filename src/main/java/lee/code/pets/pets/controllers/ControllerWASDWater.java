package lee.code.pets.pets.controllers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class ControllerWASDWater extends ControllerWASD {

  public ControllerWASDWater(Mob mob, UUID owner) {
    super(mob, owner);
  }

  @Override
  public void tick() {
    if (mob.getPassengers().isEmpty()) {
      super.tick();
      return;
    }
    if (!(mob.getPassengers().get(0) instanceof Player player)) return;
    if (!player.getUUID().equals(owner)) return;
    this.rider = player;

    final Vec3 riddenInput = getRiddenInput(rider);

    float forward = (float) riddenInput.z * 0.5F;
    float strafe = (float) riddenInput.x * 0.25F;
    if (forward <= 0.0F) forward *= 0.5F;

    // Calculate the player's yaw and pitch
    final float yaw = rider.getBukkitYaw();
    final float pitch = rider.getXRot();

    // Calculate the direction based on yaw and pitch
    double motionX = forward * -Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
    double motionY = forward * -Math.sin(Math.toRadians(pitch)); // Adjust vertical motion based on pitch
    double motionZ = forward * Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));

    // Add upward motion when jumping
    if (rider.jumping) {
      motionY += 0.5D;
    }

    if (!mob.isInWater()) {
      // Limit upward motion if out of water
      motionY = -0.04D;

      // If solid block below entity out of water, slow down
      final BlockPos downPos = mob.blockPosition().relative(Direction.DOWN);
      final BlockState frontBlockState = mob.level().getBlockState(downPos);
      if (!(frontBlockState.getBlock() instanceof LiquidBlock)) {
        mob.getJumpControl().jump();
        return;
      }
    }

    // Set the mob's motion
    mob.setDeltaMovement(new Vec3(motionX, motionY, motionZ));
    mob.setYRot(yaw);
    mob.setSpeed(0.2f);
    mob.setZza(forward);
    mob.setXxa(0.0F);
  }
}
