package lee.code.pets.pets.pet.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.world.entity.animal.Parrot;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;

@AllArgsConstructor
public enum ParrotUtil {
  BLUE(Parrot.Variant.BLUE),
  RED_BLUE(Parrot.Variant.RED_BLUE),
  YELLOW_BLUE(Parrot.Variant.YELLOW_BLUE),
  GREEN(Parrot.Variant.GREEN),
  GRAY(Parrot.Variant.GRAY)
  ;
  @Getter private final Parrot.Variant parrotVariant;

  public static ParrotUtil getVariant(Entity entity) {
    if (((CraftEntity) entity).getHandle() instanceof Parrot parrot) return ParrotUtil.valueOf(parrot.getVariant().name());
    return ParrotUtil.RED_BLUE;
  }
}
