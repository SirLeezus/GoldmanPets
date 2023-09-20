package lee.code.pets.pets.pet.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.world.entity.npc.VillagerProfession;

@AllArgsConstructor
public enum VillagerProfessionUtil {
  BUTCHER(VillagerProfession.BUTCHER),
  ARMORER(VillagerProfession.ARMORER),
  CARTOGRAPHER(VillagerProfession.CARTOGRAPHER),
  CLERIC(VillagerProfession.CLERIC),
  FARMER(VillagerProfession.FARMER),
  FISHERMAN(VillagerProfession.FISHERMAN),
  FLETCHER(VillagerProfession.FLETCHER),
  LEATHERWORKER(VillagerProfession.LEATHERWORKER),
  LIBRARIAN(VillagerProfession.LIBRARIAN),
  MASON(VillagerProfession.MASON),
  NITWIT(VillagerProfession.NITWIT),
  WEAPONSMITH(VillagerProfession.WEAPONSMITH),
  TOOLSMITH(VillagerProfession.TOOLSMITH),
  SHEPHERD(VillagerProfession.SHEPHERD),
  NONE(VillagerProfession.NONE),

  ;
  @Getter private final VillagerProfession villagerProfession;
}
