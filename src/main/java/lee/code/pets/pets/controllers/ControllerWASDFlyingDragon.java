package lee.code.pets.pets.controllers;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class ControllerWASDFlyingDragon extends ControllerWASD {

  public ControllerWASDFlyingDragon(Mob mob, UUID owner, float speed) {
    super(mob, owner, speed);
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
    final float strafe = (float) riddenInput.x * 0.25F;
    if (forward <= 0.0F) forward *= 0.5F;

    final float yaw = rider.getBukkitYaw();
    final float pitch = rider.getXRot();

    // Calculate the direction based on yaw and pitch
    final double motionX = forward * -Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));
    double motionY = forward * -Math.sin(Math.toRadians(pitch));
    final double motionZ = forward * Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch));

    // Add upward motion when jumping
    if (rider.jumping) {
      motionY += 0.4D;
    }

    // Set the mob's motion
    mob.setDeltaMovement(new Vec3(motionX, motionY, motionZ));
    mob.setYRot(yaw + 180.0f);
    mob.setSpeed(speed);
    mob.setZza(forward);
    mob.setXxa(0.0F);
  }
}
