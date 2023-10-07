package lee.code.pets.pets.pet.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.world.entity.animal.Rabbit;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;

@AllArgsConstructor
public enum RabbitVariantUtil {
  WHITE(Rabbit.Variant.WHITE),
  BROWN(Rabbit.Variant.BROWN),
  GOLD(Rabbit.Variant.GOLD),
  BLACK(Rabbit.Variant.BLACK),
  SALT(Rabbit.Variant.SALT),
  WHITE_SPLOTCHED(Rabbit.Variant.WHITE_SPLOTCHED),
  EVIL(Rabbit.Variant.EVIL)
  ;
  @Getter private final Rabbit.Variant rabbitVariant;

  public static RabbitVariantUtil getVariant(Entity entity) {
    if (((CraftEntity) entity).getHandle() instanceof Rabbit rabbit) return RabbitVariantUtil.valueOf(rabbit.getVariant().name());
    return RabbitVariantUtil.WHITE;
  }
}
