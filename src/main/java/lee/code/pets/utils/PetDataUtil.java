package lee.code.pets.utils;

import lee.code.pets.menus.menu.menudata.options.Option;
import lee.code.pets.pets.pet.util.*;
import org.bukkit.DyeColor;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PetDataUtil {

  public static String getPetData(EntityType entityType, String[] data, Option option) {
    switch (entityType) {
      case ALLAY, BAT, IRON_GOLEM, WANDERING_TRADER, COD, DOLPHIN, ELDER_GUARDIAN, GLOW_SQUID, GUARDIAN, PUFFERFISH, SALMON, SQUID, TADPOLE -> {
        return data[1];
      }
      case COW, CHICKEN, HOGLIN, OCELOT, PIG, POLAR_BEAR, SNIFFER, ZOGLIN -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
        }
      }
      case PARROT, FROG -> {
        switch (option) {
          case NAME -> {return data[1];}
          case VARIANT -> {return data[2];}
        }
      }
      case SNOWMAN -> {
        switch (option) {
          case NAME -> {return data[1];}
          case PUMPKIN -> {return data[2];}
        }
      }
      case CAMEL, SKELETON_HORSE, STRIDER, ZOMBIE_HORSE -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case SADDLE -> {return data[3];}
        }
      }
      case SHEEP -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case COLOR -> {return data[3];}
        }
      }
      case GOAT -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case HORNS -> {return data[3];}
        }
      }
      case FOX, MUSHROOM_COW, RABBIT, AXOLOTL -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case VARIANT -> {return data[3];}
        }
      }
      case DONKEY -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case CHEST -> {return data[3];}
          case SADDLE -> {return data[4];}
        }
      }
      case CAT -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case VARIANT -> {return data[3];}
          case COLOR -> {return data[4];}
        }
      }
      case WOLF -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case COLLAR -> {return data[3];}
          case COLOR -> {return data[4];}
          case ANGRY -> {return data[5];}
        }
      }
      case BEE -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case ANGRY -> {return data[3];}
          case NECTAR -> {return data[4];}
          case STUNG -> {return data[5];}
        }
      }
      case PANDA -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case MAIN_GENE -> {return data[3];}
          case HIDDEN_GENE -> {return data[4];}
        }
      }
      case HORSE -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case SADDLE -> {return data[3];}
          case VARIANT -> {return data[4];}
          case MARKING -> {return data[5];}
        }
      }
      case LLAMA, TRADER_LLAMA -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case CHEST -> {return data[3];}
          case VARIANT -> {return data[4];}
          case SADDLE -> {return data[5];}
          case COLOR -> {return data[6];}
        }
      }
      case VILLAGER -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case TYPE -> {return data[3];}
          case PROFESSION -> {return data[4];}
          case LEVEL -> {return data[5];}
        }
      }
    }
    return null;
  }

  public static String serializePetData(Entity entity) {
    final EntityType entityType = entity.getType();
    final String petName = "&6&l" + CoreUtil.capitalize(entityType.name()) + " Pet";
    final boolean isBaby = entity instanceof Ageable ageable && !ageable.isAdult();
    final String sep = ",";
    final String startingData = entityType.name() + sep + petName;
    switch (entityType) {
      case ALLAY, BAT, IRON_GOLEM, WANDERING_TRADER, COD, DOLPHIN, ELDER_GUARDIAN, GLOW_SQUID, GUARDIAN, PUFFERFISH, SALMON, SQUID, TADPOLE -> {
        return startingData;
      }
      case COW, CHICKEN, HOGLIN, OCELOT, PIG, POLAR_BEAR, SNIFFER, ZOGLIN -> {
        return startingData + sep + isBaby;
      }
      case BEE -> {
        final boolean isMad = entity instanceof Bee bee && bee.getAnger() > 0;
        final boolean hasNectar = entity instanceof Bee bee && bee.hasNectar();
        final boolean hasStung = entity instanceof Bee bee && bee.hasStung();
        return startingData + sep + isBaby + sep + isMad + sep + hasNectar + sep + hasStung;
      }
      case FROG -> {
        final Frog.Variant frogVariant = entity instanceof Frog frog ? frog.getVariant() : Frog.Variant.WARM;
        return startingData + sep + frogVariant;
      }
      case FOX -> {
        final Fox.Type foxType = entity instanceof Fox fox ? fox.getFoxType() : Fox.Type.RED;
        return startingData + sep + isBaby + sep + foxType.name();
      }
      case CAMEL -> {
        final boolean hasSaddle = entity instanceof Camel camel && camel.getInventory().getSaddle() != null;
        return startingData + sep + isBaby + sep + hasSaddle;
      }
      case STRIDER -> {
        final boolean hasSaddle = entity instanceof Strider strider && strider.hasSaddle();
        return startingData + sep + isBaby + sep + hasSaddle;
      }
      case SHEEP -> {
        DyeColor color = entity instanceof Sheep sheep ? sheep.getColor() : DyeColor.WHITE;
        if (color == null) color = DyeColor.WHITE;
        return startingData + sep + isBaby + sep + color.name();
      }
      case GOAT -> {
        final boolean hasHorns = entity instanceof Goat goat && goat.hasLeftHorn() | goat.hasRightHorn();
        return startingData + sep + isBaby + sep + hasHorns;
      }
      case MUSHROOM_COW -> {
        final MushroomCow.Variant cowVariant = entity instanceof MushroomCow mushroomCow ? mushroomCow.getVariant() : MushroomCow.Variant.RED;
        return startingData + sep + isBaby + sep + cowVariant;
      }
      case PARROT -> {
        final ParrotVariantUtil variant = ParrotVariantUtil.getVariant(entity);
        return startingData + sep + variant.name();
      }
      case DONKEY -> {
        final boolean hasChest = entity instanceof Donkey donkey && donkey.isCarryingChest();
        final boolean hasSaddle = entity instanceof Donkey donkey && donkey.getInventory().getSaddle() != null;
        return startingData + sep + isBaby + sep + hasChest + sep + hasSaddle;
      }
      case CAT -> {
        final Cat.Type catType = entity instanceof Cat cat ? cat.getCatType() : Cat.Type.ALL_BLACK;
        final DyeColor color = entity instanceof Cat cat ? cat.getCollarColor() : DyeColor.RED;
        return startingData + sep + isBaby + sep + catType.name() + sep + color.name();
      }
      case RABBIT -> {
        final RabbitVariantUtil rabbitVariant = RabbitVariantUtil.getVariant(entity);
        return startingData + sep + isBaby + sep + rabbitVariant.name();
      }
      case PANDA -> {
        final Panda.Gene mainGene = entity instanceof Panda panda ? panda.getMainGene() : Panda.Gene.NORMAL;
        final Panda.Gene hiddenGene = entity instanceof Panda panda ? panda.getHiddenGene() : Panda.Gene.NORMAL;
        return startingData + sep + isBaby + sep + mainGene.name() + sep + hiddenGene.name();
      }
      case HORSE -> {
        final HorseVariantUtil horseVariant = HorseVariantUtil.getVariant(entity);
        final HorseMarkingUtil horseMarking = HorseMarkingUtil.getMarking(entity);
        final boolean hasSaddle = entity instanceof Horse horse && horse.getInventory().getSaddle() != null;
        return startingData + sep + isBaby + sep + hasSaddle + sep + horseVariant.name() + sep + horseMarking.name();
      }
      case LLAMA, TRADER_LLAMA -> {
        final Llama.Color llamaVariant = entity instanceof Llama llama ? llama.getColor() : Llama.Color.WHITE;
        final boolean hasChest = entity instanceof Llama llama && llama.isCarryingChest();
        final ItemStack saddleItem = entity instanceof Llama llama ? llama.getInventory().getSaddle() : null;
        final boolean hasSaddle = saddleItem != null;
        final DyeColor color = saddleItem == null ? DyeColor.WHITE : DyeColor.valueOf(saddleItem.getType().name().substring(0, saddleItem.getType().name().indexOf('_')));
        return startingData + sep + isBaby + sep + hasChest + sep + llamaVariant + sep + hasSaddle + sep + color.name();
      }
      case SKELETON_HORSE -> {
        final boolean hasSaddle = entity instanceof SkeletonHorse skeletonHorse && skeletonHorse.getInventory().getSaddle() != null;
        return startingData + sep + isBaby + sep + hasSaddle;
      }
      case ZOMBIE_HORSE -> {
        final boolean hasSaddle = entity instanceof ZombieHorse zombieHorse && zombieHorse.getInventory().getSaddle() != null;
        return startingData + sep + isBaby + sep + hasSaddle;
      }
      case SNOWMAN -> {
        final boolean hasPumpkin = entity instanceof Snowman snowman && !snowman.isDerp();
        return startingData + sep + hasPumpkin;
      }
      case VILLAGER -> {
        final VillagerTypeUtil type = VillagerTypeUtil.getType(entity);
        final VillagerProfessionUtil profession = VillagerProfessionUtil.getProfession(entity);
        final int level = entity instanceof Villager villager ? villager.getVillagerLevel() : 1;
        return startingData + sep + isBaby + sep + type.name() + sep + profession.name() + sep + level;
      }
      case WOLF -> {
        final boolean hasCollar = entity instanceof Wolf wolf && wolf.isTamed();
        final DyeColor color = entity instanceof Wolf wolf ? wolf.getCollarColor() : DyeColor.RED;
        final boolean mad = false;
        return startingData + sep + isBaby + sep + hasCollar + sep + color.name() + sep + mad;
      }
      case AXOLOTL -> {
        final Axolotl.Variant variant = entity instanceof Axolotl axolotl ? axolotl.getVariant() : Axolotl.Variant.LUCY;
        return startingData + sep + isBaby + sep + variant;
      }
    }
    return null;
  }

  public static String addNewPetData(EntityType entityType, String[] data, String newData, Option option) {
    final String sep = ",";
    switch (entityType) {
      case ALLAY, BAT, IRON_GOLEM, WANDERING_TRADER, COD, DOLPHIN, ELDER_GUARDIAN, GLOW_SQUID, GUARDIAN, PUFFERFISH, SALMON, SQUID, TADPOLE -> {
        return data[0] + sep + newData;
      }
      case COW, CHICKEN, HOGLIN, OCELOT, PIG, POLAR_BEAR, SNIFFER, ZOGLIN -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData;}
        }
      }
      case FOX, MUSHROOM_COW, RABBIT, AXOLOTL -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2] + sep + data[3];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData + sep + data[3];}
          case VARIANT -> {return data[0] + sep + data[1] + sep + data[2] + sep + newData;}
        }
      }
      case SNOWMAN -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2];}
          case PUMPKIN -> {return data[0] + sep + data[1] + sep + newData;}
        }
      }
      case WOLF -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2] + sep + data[3] + sep + data[4] + sep + data[5];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData + sep + data[3] + sep + data[4] + sep + data[5];}
          case COLLAR -> {return data[0] + sep + data[1] + sep + data[2] + sep + newData + sep + data[4] + sep + data[5];}
          case COLOR -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + newData + sep + data[5];}
          case ANGRY -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + data[4] + sep + newData;}
        }
      }
      case DONKEY -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2] + sep + data[3] + sep + data[4];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData + sep + data[3] + sep + data[4];}
          case CHEST -> {return data[0] + sep + data[1] + sep + data[2] + sep + newData + sep + data[4];}
          case SADDLE -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + newData;}
        }
      }
      case CAT -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2] + sep + data[3] + sep + data[4];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData + sep + data[3] + sep + data[4];}
          case VARIANT -> {return data[0] + sep + data[1] + sep + data[2] + sep + newData + sep + data[4];}
          case COLOR -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + newData;}
        }
      }
      case BEE -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2] + sep + data[3] + sep + data[4] + sep + data[5];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData + sep + data[3] + sep + data[4] + sep + data[5];}
          case ANGRY -> {return data[0] + sep + data[1] + sep + data[2] + sep + newData + sep + data[4] + sep + data[5];}
          case NECTAR -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + newData + sep + data[5];}
          case STUNG -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + data[4] + sep + newData;}
        }
      }
      case PANDA -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2] + sep + data[3] + sep + data[4];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData + sep + data[3] + sep + data[4];}
          case MAIN_GENE -> {return data[0] + sep + data[1] + sep + data[2] + sep + newData + sep + data[4];}
          case HIDDEN_GENE -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + newData;}
        }
      }
      case CAMEL, SKELETON_HORSE, STRIDER, ZOMBIE_HORSE -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2] + sep + data[3];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData + sep + data[3];}
          case SADDLE -> {return data[0] + sep + data[1] + sep + data[2] + sep + newData;}
        }
      }
      case SHEEP -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2] + sep + data[3];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData + sep + data[3];}
          case COLOR -> {return data[0] + sep + data[1] + sep + data[2] + sep + newData;}
        }
      }
      case PARROT, FROG -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2];}
          case VARIANT -> {return data[0] + sep + data[1] + sep + newData;}
        }
      }
      case GOAT -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2] + sep + data[3];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData + sep + data[3];}
          case HORNS -> {return data[0] + sep + data[1] + sep + data[2] + sep + newData;}
        }
      }
      case HORSE -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2] + sep + data[3] + sep + data[4] + sep + data[5];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData + sep + data[3] + sep + data[4] + sep + data[5];}
          case SADDLE -> {return data[0] + sep + data[1] + sep + data[2] + sep + newData + sep + data[4] + sep + data[5];}
          case VARIANT -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + newData + sep + data[5];}
          case MARKING -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + data[4] + sep + newData;}
        }
      }
      case LLAMA, TRADER_LLAMA -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2] + sep + data[3] + sep + data[4] + sep + data[5] + sep + data[6];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData + sep + data[3] + sep + data[4] + sep + data[5] + sep + data[6];}
          case CHEST -> {return data[0] + sep + data[1] + sep + data[2] + sep + newData + sep + data[4] + sep + data[5] + sep + data[6];}
          case VARIANT -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + newData + sep + data[5] + sep + data[6];}
          case SADDLE -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + data[4] + sep + newData + sep + data[6];}
          case COLOR -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + data[4] + sep + data[5] + sep + newData;}
        }
      }
      case VILLAGER -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2] + sep + data[3] + sep + data[4] + sep + data[5];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData + sep + data[3] + sep + data[4] + sep + data[5];}
          case TYPE -> {return data[0] + sep + data[1] + sep + data[2] + sep + newData + sep + data[4] + sep + data[5];}
          case PROFESSION -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + newData + sep + data[5];}
          case LEVEL -> {return data[0] + sep + data[1]+ sep + data[2] + sep + data[3] + sep + data[4] + sep + newData;}
        }
      }
    }
    return null;
  }

  public static String getNextColor(DyeColor dyeColor) {
    final ArrayList<DyeColor> colors = new ArrayList<>(List.of(DyeColor.values()));
    final DyeColor nextColor = dyeColor.ordinal() + 1 < colors.size() ? colors.get(dyeColor.ordinal() + 1) : colors.get(0);
    return nextColor.name();
  }
  public static String getNextVariant(EntityType entityType, String variant) {
    switch (entityType) {
      case PARROT -> {
        final ParrotVariantUtil parrotVariant = ParrotVariantUtil.valueOf(variant);
        final ArrayList<ParrotVariantUtil> variants = new ArrayList<>(List.of(ParrotVariantUtil.values()));
        final ParrotVariantUtil nextVariant = parrotVariant.ordinal() + 1 < variants.size() ? variants.get(parrotVariant.ordinal() + 1) : variants.get(0);
        return nextVariant.name();
      }
      case CAT -> {
        final CatVariantUtil catVariant = CatVariantUtil.valueOf(variant);
        final ArrayList<CatVariantUtil> variants = new ArrayList<>(List.of(CatVariantUtil.values()));
        final CatVariantUtil nextVariant = catVariant.ordinal() + 1 < variants.size() ? variants.get(catVariant.ordinal() + 1) : variants.get(0);
        return nextVariant.name();
      }
      case FOX -> {
        final Fox.Type foxType = Fox.Type.valueOf(variant);
        if (foxType.equals(Fox.Type.RED)) return Fox.Type.SNOW.name();
        else return Fox.Type.RED.name();
      }
      case HORSE -> {
        final HorseVariantUtil horseVariant = HorseVariantUtil.valueOf(variant);
        final ArrayList<HorseVariantUtil> variants = new ArrayList<>(List.of(HorseVariantUtil.values()));
        final HorseVariantUtil nextVariant = horseVariant.ordinal() + 1 < variants.size() ? variants.get(horseVariant.ordinal() + 1) : variants.get(0);
        return nextVariant.name();
      }
      case LLAMA, TRADER_LLAMA -> {
        final Llama.Color llamaVariant = Llama.Color.valueOf(variant);
        final ArrayList<Llama.Color> variants = new ArrayList<>(List.of(Llama.Color.values()));
        final Llama.Color nextVariant = llamaVariant.ordinal() + 1 < variants.size() ? variants.get(llamaVariant.ordinal() + 1) : variants.get(0);
        return nextVariant.name();
      }
      case MUSHROOM_COW -> {
        final MushroomCow.Variant cowVariant = MushroomCow.Variant.valueOf(variant);
        if (cowVariant.equals(MushroomCow.Variant.RED)) return MushroomCow.Variant.BROWN.name();
        else return MushroomCow.Variant.RED.name();
      }
      case RABBIT -> {
        final RabbitVariantUtil rabbitVariant = RabbitVariantUtil.valueOf(variant);
        final ArrayList<RabbitVariantUtil> variants = new ArrayList<>(List.of(RabbitVariantUtil.values()));
        final RabbitVariantUtil nextVariant = rabbitVariant.ordinal() + 1 < variants.size() ? variants.get(rabbitVariant.ordinal() + 1) : variants.get(0);
        return nextVariant.name();
      }
      case AXOLOTL -> {
        final Axolotl.Variant axolotlVariant = Axolotl.Variant.valueOf(variant);
        final ArrayList<Axolotl.Variant> variants = new ArrayList<>(List.of(Axolotl.Variant.values()));
        final Axolotl.Variant nextVariant = axolotlVariant.ordinal() + 1 < variants.size() ? variants.get(axolotlVariant.ordinal() + 1) : variants.get(0);
        return nextVariant.name();
      }
      case FROG -> {
        final Frog.Variant frogVariant = Frog.Variant.valueOf(variant);
        final ArrayList<Frog.Variant> variants = new ArrayList<>(List.of(Frog.Variant.values()));
        final Frog.Variant nextVariant = frogVariant.ordinal() + 1 < variants.size() ? variants.get(frogVariant.ordinal() + 1) : variants.get(0);
        return nextVariant.name();
      }
    }
    return null;
  }

  public static String getNextHorseMarking(String marking) {
    final HorseMarkingUtil horseMarking = HorseMarkingUtil.valueOf(marking);
    final ArrayList<HorseMarkingUtil> variants = new ArrayList<>(List.of(HorseMarkingUtil.values()));
    final HorseMarkingUtil nextVariant = horseMarking.ordinal() + 1 < variants.size() ? variants.get(horseMarking.ordinal() + 1) : variants.get(0);
    return nextVariant.name();
  }

  public static String getNextPandaGene(String gene) {
    final Panda.Gene pandaGene = Panda.Gene.valueOf(gene);
    final ArrayList<Panda.Gene> variants = new ArrayList<>(List.of(Panda.Gene.values()));
    final Panda.Gene nextGene = pandaGene.ordinal() + 1 < variants.size() ? variants.get(pandaGene.ordinal() + 1) : variants.get(0);
    return nextGene.name();
  }

  public static String getNextVillagerType(String type) {
    final VillagerTypeUtil villagerType = VillagerTypeUtil.valueOf(type);
    final ArrayList<VillagerTypeUtil> types = new ArrayList<>(List.of(VillagerTypeUtil.values()));
    final VillagerTypeUtil nextType = villagerType.ordinal() + 1 < types.size() ? types.get(villagerType.ordinal() + 1) : types.get(0);
    return nextType.name();
  }

  public static String getNextVillagerProfession(String profession) {
    final VillagerProfessionUtil villagerProfession = VillagerProfessionUtil.valueOf(profession);
    final ArrayList<VillagerProfessionUtil> professions = new ArrayList<>(List.of(VillagerProfessionUtil.values()));
    final VillagerProfessionUtil nextProfession = villagerProfession.ordinal() + 1 < professions.size() ? professions.get(villagerProfession.ordinal() + 1) : professions.get(0);
    return nextProfession.name();
  }

  public static String getNextVillagerLevel(String level) {
    final int curLevel = Integer.parseInt(level);
    return String.valueOf(curLevel + 1 > 5 ? 1 : curLevel + 1);
  }
}
