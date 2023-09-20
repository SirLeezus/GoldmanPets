package lee.code.pets.pets.pet.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.world.entity.npc.VillagerType;

@AllArgsConstructor
public enum VillagerTypeUtil {
  DESERT(VillagerType.DESERT),
  JUNGLE(VillagerType.JUNGLE),
  SNOW(VillagerType.SNOW),
  PLAINS(VillagerType.PLAINS),
  SAVANNA(VillagerType.SAVANNA),
  SWAMP(VillagerType.SWAMP),
  TAIGA(VillagerType.TAIGA)

  ;
  @Getter private final VillagerType villagerType;
}
