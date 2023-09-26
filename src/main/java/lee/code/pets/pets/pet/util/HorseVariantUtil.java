package lee.code.pets.pets.pet.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Variant;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;

@AllArgsConstructor
public enum HorseVariantUtil {
  WHITE(Variant.WHITE),
  BLACK(Variant.BLACK),
  BROWN(Variant.BROWN),
  DARK_BROWN(Variant.DARK_BROWN),
  CREAMY(Variant.CREAMY),
  CHESTNUT(Variant.CHESTNUT),
  GRAY(Variant.GRAY)
  ;
  @Getter private final Variant horseVariant;

  public static HorseVariantUtil getVariant(Entity entity) {
    if (((CraftEntity) entity).getHandle() instanceof Horse horse) return HorseVariantUtil.valueOf(horse.getVariant().name());
    return HorseVariantUtil.WHITE;
  }
}
