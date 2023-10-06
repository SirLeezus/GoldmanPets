package lee.code.pets.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;

@AllArgsConstructor
public enum PettingSound {
  SHEEP(EntityType.SHEEP, Sound.ENTITY_SHEEP_AMBIENT),
  WITHER(EntityType.WITHER, Sound.ENTITY_WITHER_AMBIENT),
  GOAT(EntityType.GOAT, Sound.ENTITY_GOAT_SCREAMING_AMBIENT),
  ENDERMAN(EntityType.ENDERMAN, Sound.ENTITY_ENDERMAN_AMBIENT),


  ;

  private final EntityType entityType;
  @Getter private final Sound sound;
}
