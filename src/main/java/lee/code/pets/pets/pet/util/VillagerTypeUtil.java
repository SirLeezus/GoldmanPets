package lee.code.pets.pets.pet.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerType;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;

@AllArgsConstructor
public enum VillagerTypeUtil {
  DESERT(VillagerType.DESERT),
  JUNGLE(VillagerType.JUNGLE),
  SNOW(VillagerType.SNOW),
  PLAINS(VillagerType.PLAINS),
  SAVANNA(VillagerType.SAVANNA),
  SWAMP(VillagerType.SWAMP),
  TAIGA(VillagerType.TAIGA),

  ;
  @Getter private final VillagerType villagerType;

  public static VillagerTypeUtil getType(Entity entity) {
    if (((CraftEntity) entity).getHandle() instanceof Villager villager) return VillagerTypeUtil.valueOf(String.valueOf(villager.getVillagerData().getType()).toUpperCase());
    return VillagerTypeUtil.PLAINS;
  }
}
