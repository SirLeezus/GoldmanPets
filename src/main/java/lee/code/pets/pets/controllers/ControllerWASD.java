package lee.code.pets.pets.controllers;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class ControllerWASD extends MoveControl {
  protected final UUID owner;
  protected Player rider;

  public ControllerWASD(Mob mob, UUID owner) {
    super(mob);
    this.owner = owner;
  }

  @Override
  public boolean hasWanted() {
    return rider != null;
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
    float yaw = rider.getBukkitYaw();
    if (strafe != 0) {
      if (forward == 0) {
        yaw += strafe > 0 ? -90 : 90;
        forward = Math.abs(strafe * 2);
      } else {
        yaw += strafe > 0 ? -30 : 30;
        strafe /= 2;
        if (forward < 0) {
          yaw += strafe > 0 ? -110 : 110;
          forward *= -1;
        }
      }
    } else if (forward < 0) {
      yaw -= 180;
      forward *= -1;
    }

    // Set the mob's motion
    mob.setYRot(yaw);
    mob.setSpeed(0.2F);
    mob.setZza(forward);
    mob.setXxa(0.0F);
    if (rider.jumping) jump();
  }

  protected Vec3 getRiddenInput(Player rider) {
    final float f = rider.xxa * 0.5F;
    float f1 = rider.zza;

    if (f1 <= 0.0F) f1 *= 0.25F;
    return new Vec3(f, 0.0D, f1);
  }

  protected void jump() {
    if (!mob.onGround()) return;
    mob.getJumpControl().jump();
  }
}
