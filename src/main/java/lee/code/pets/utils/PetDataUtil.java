package lee.code.pets.utils;

import lee.code.pets.menus.menu.menudata.options.Option;
import lee.code.pets.pets.pet.util.ParrotUtil;
import org.bukkit.DyeColor;
import org.bukkit.entity.*;

import java.util.ArrayList;
import java.util.List;

public class PetDataUtil {

  public static String getPetData(EntityType entityType, String[] data, Option option) {
    switch (entityType) {
      case COW -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
        }
      }
      case SHEEP -> {
        switch (option) {
          case NAME -> {return data[1];}
          case BABY -> {return data[2];}
          case COLOR -> {return data[3];}
        }
      }
      case PARROT -> {
        switch (option) {
          case NAME -> {return data[1];}
          case VARIANT -> {return data[2];}
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
      case COW -> {
        return startingData + sep + isBaby;
      }
      case SHEEP -> {
        DyeColor color = entity instanceof Sheep sheep ? sheep.getColor() : DyeColor.WHITE;
        if (color == null) color = DyeColor.WHITE;
        return startingData + sep + isBaby + sep + color.name();
      }
      case PARROT -> {
        final ParrotUtil variant = ParrotUtil.getVariant(entity);
        return startingData + sep + variant.name();
      }
    }
    return null;
  }

  public static String addNewPetData(EntityType entityType, String[] data, String newData, Option option) {
    final String sep = ",";
    switch (entityType) {
      case COW -> {
        switch (option) {
          case NAME -> {return data[0] + sep + newData + sep + data[2];}
          case BABY -> {return data[0] + sep + data[1] + sep + newData;}
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
        final ParrotUtil parrotVariant = ParrotUtil.valueOf(variant);
        final ArrayList<ParrotUtil> variants = new ArrayList<>(List.of(ParrotUtil.values()));
        final ParrotUtil nextVariant = parrotVariant.ordinal() + 1 < variants.size() ? variants.get(parrotVariant.ordinal() + 1) : variants.get(0);
        return nextVariant.name();
      }
    }
    return null;
  }
}
