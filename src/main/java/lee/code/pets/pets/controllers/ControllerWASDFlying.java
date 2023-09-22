package lee.code.pets.pets.controllers;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class ControllerWASDFlying extends ControllerWASD {

  public ControllerWASDFlying(Mob mob, UUID owner) {
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

    final float forward = Math.max(0, (float) riddenInput.z);
    final float pitch = rider.getXRot();
    final float yaw = rider.getYRot();
    final double motionY = forward * -Math.sin(Math.toRadians(pitch));

    // Set the mob's motion
    mob.setYRot(yaw);
    mob.setSpeed(0.2F);
    mob.setZza(forward);
    mob.setXxa(0.0F);
    mob.setYya((float) motionY);
  }
}