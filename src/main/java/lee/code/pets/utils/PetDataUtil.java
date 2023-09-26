package lee.code.pets.utils;

import lee.code.pets.menus.menu.menudata.options.Option;
import lee.code.pets.pets.pet.util.CatVariantUtil;
import lee.code.pets.pets.pet.util.HorseMarkingUtil;
import lee.code.pets.pets.pet.util.HorseVariantUtil;
import lee.code.pets.pets.pet.util.ParrotVariantUtil;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PetDataUtil {

  public static String getPetData(EntityType entityType, String[] data, Option option) {
    switch (entityType) {
      case ALLAY, BAT, IRON_GOLEM -> {
        return data[1];
      }
      case COW, BEE, CHICKEN, HOGLIN -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
        }
      }
      case PARROT -> {
        switch (option) {
          case NAME -> {return data[1];}
          case VARIANT -> {return data[2];}
        }
      }
      case CAMEL -> {
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
      case FOX -> {
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
      case HORSE -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case SADDLE -> {return data[3];}
          case VARIANT -> {return data[4];}
          case MARKING -> {return data[5];}
        }
      }
      case LLAMA -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case CHEST -> {return data[3];}
          case VARIANT -> {return data[4];}
          case SADDLE -> {return data[5];}
          case COLOR -> {return data[6];}
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
      case ALLAY, BAT, IRON_GOLEM -> {
        return startingData;
      }
      case COW, BEE, CHICKEN, HOGLIN -> {
        return startingData + sep + isBaby;
      }
      case FOX -> {
        final Fox.Type foxType = entity instanceof Fox fox ? fox.getFoxType() : Fox.Type.RED;
        return startingData + sep + isBaby + sep + foxType.name();
      }
      case CAMEL -> {
        final boolean hasSaddle = entity instanceof Camel camel && camel.getInventory().getSaddle() != null;
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
      case HORSE -> {
        final HorseVariantUtil horseVariant = HorseVariantUtil.getVariant(entity);
        final HorseMarkingUtil horseMarking = HorseMarkingUtil.getMarking(entity);
        final boolean hasSaddle = entity instanceof Horse horse && horse.getInventory().getSaddle() != null;
        return startingData + sep + isBaby + sep + hasSaddle + sep + horseVariant.name() + sep + horseMarking.name();
      }
      case LLAMA -> {
        final Llama.Color llamaVariant = entity instanceof Llama llama ? llama.getColor() : Llama.Color.WHITE;
        final boolean hasChest = entity instanceof Llama llama && llama.isCarryingChest();
        final ItemStack saddleItem = entity instanceof Llama llama ? llama.getInventory().getSaddle() : null;
        final boolean hasSaddle = saddleItem != null;
        final DyeColor color = saddleItem == null ? DyeColor.WHITE : DyeColor.valueOf(saddleItem.getType().name().substring(0, saddleItem.getType().name().indexOf('_')));
        return startingData + sep + isBaby + sep + hasChest + sep + llamaVariant + sep + hasSaddle + sep + color.name();
      }
    }
    return null;
  }

  public static String addNewPetData(EntityType entityType, String[] data, String newData, Option option) {
    final String sep = ",";
    switch (entityType) {
      case ALLAY, BAT, IRON_GOLEM -> {
        return data[0] + sep + newData;
      }
      case COW, BEE, CHICKEN, HOGLIN -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData;}
        }
      }
      case FOX -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2] + sep + data[3];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData + sep + data[3];}
          case VARIANT -> {return data[0] + sep + data[1] + sep + data[2] + sep + newData;}
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
      case CAMEL -> {
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
      case PARROT -> {
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
      case LLAMA -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2] + sep + data[3] + sep + data[4] + sep + data[5] + sep + data[6];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData + sep + data[3] + sep + data[4] + sep + data[5] + sep + data[6];}
          case CHEST -> {return data[0] + sep + data[1] + sep + data[2] + sep + newData + sep + data[4] + sep + data[5] + sep + data[6];}
          case VARIANT -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + newData + sep + data[5] + sep + data[6];}
          case SADDLE -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + data[4] + sep + newData + sep + data[6];}
          case COLOR -> {return data[0] + sep + data[1] + sep + data[2] + sep + data[3] + sep + data[4] + sep + data[5] + sep + newData;}
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
      case LLAMA -> {
        final Llama.Color llamaVariant = Llama.Color.valueOf(variant);
        final ArrayList<Llama.Color> variants = new ArrayList<>(List.of(Llama.Color.values()));
        final Llama.Color nextVariant = llamaVariant.ordinal() + 1 < variants.size() ? variants.get(llamaVariant.ordinal() + 1) : variants.get(0);
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
}
