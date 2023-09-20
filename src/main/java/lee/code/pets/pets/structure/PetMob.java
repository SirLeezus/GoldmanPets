package lee.code.pets.pets.structure;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;

public abstract class PetMob extends Mob {

  protected PetMob(EntityType<? extends Mob> type, Level world) {
    super(type, world);
    setInvulnerable(true);
    setCustomNameVisible(true);
    setPersistenceRequired(true);
    collides = false;
  }

  protected abstract void registerGoals();

  protected abstract void setAttributes();

  @Override
  public void load(CompoundTag compoundTag) {

  }

  @Override
  public boolean save(CompoundTag compoundTag) {
    return false;
  }
}
