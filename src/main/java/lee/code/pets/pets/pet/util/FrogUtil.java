package lee.code.pets.pets.pet.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.world.entity.animal.FrogVariant;

@AllArgsConstructor
public enum FrogUtil {
  COLD(FrogVariant.COLD),
  WARM(FrogVariant.WARM),
  TEMPERATE(FrogVariant.TEMPERATE)
  ;
  @Getter private final FrogVariant frogVariant;
}
