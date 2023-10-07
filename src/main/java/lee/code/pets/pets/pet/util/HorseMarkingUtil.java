package lee.code.pets.pets.pet.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Markings;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;

@AllArgsConstructor
public enum HorseMarkingUtil {
  WHITE(Markings.WHITE),
  BLACK_DOTS(Markings.BLACK_DOTS),
  WHITE_DOTS(Markings.WHITE_DOTS),
  WHITE_FIELD(Markings.WHITE_FIELD),
  NONE(Markings.NONE)
  ;
  @Getter private final Markings marking;

  public static HorseMarkingUtil getMarking(Entity entity) {
    if (((CraftEntity) entity).getHandle() instanceof Horse horse) return HorseMarkingUtil.valueOf(horse.getMarkings().name());
    return HorseMarkingUtil.NONE;
  }
}
