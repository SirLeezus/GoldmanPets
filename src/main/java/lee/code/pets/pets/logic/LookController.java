package lee.code.pets.pets.logic;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.LookControl;

public class LookController extends LookControl {
  private float yawOffset = 0;
  private float pitchOffset = 0;

  public LookController(Mob entity) {
    super(entity);
  }

  public void setYawPitch(float yaw, float pitch) {
  }

  public void setOffsets(float yaw, float pitch) {
    yawOffset = yaw;
    pitchOffset = pitch;
  }


  @Override
  public void tick() {

  }
}
