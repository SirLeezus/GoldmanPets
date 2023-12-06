package lee.code.pets.pets.goals;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class LookAtOwnerGoal extends Goal {
  protected final Mob mob;

  public LookAtOwnerGoal(Mob mob) {
    this.mob = mob;
    this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
  }

  @Override
  public boolean canUse() {
    if (!mob.getPassengers().isEmpty() || mob.getTarget() == null) return false;
    final double distanceToOwner = mob.distanceTo(mob.getTarget());
    return distanceToOwner <= 5;
  }

  @Override
  public boolean canContinueToUse() {
    if (!mob.getPassengers().isEmpty() || mob.getTarget() == null) return false;
    final double distanceToOwner = mob.distanceTo(mob.getTarget());
    return distanceToOwner <= 5;
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
    mob.getLookControl().setLookAt(mob.getTarget());
    mob.lookAt(mob.getTarget(), 30.0F, 30.0F);
  }
}
