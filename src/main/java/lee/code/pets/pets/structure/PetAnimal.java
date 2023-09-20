package lee.code.pets.pets.structure;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;

public abstract class PetAnimal extends Animal {

  protected PetAnimal(EntityType<? extends Animal> type, Level world) {
    super(type, world);
    setInvulnerable(true);
    setCustomNameVisible(true);
    setPersistenceRequired(true);
    collides = false;
  }

  protected abstract void registerGoals();

  protected abstract void setAttributes();

  @Override
  public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
    return null;
  }

  @Override
  public void ageUp(int age) {
  }

  @Override
  public void ageUp(int age, boolean overGrow) {
  }

  @Override
  public void load(CompoundTag compoundTag) {

  }

  @Override
  public boolean save(CompoundTag compoundTag) {
    return false;
  }
}
