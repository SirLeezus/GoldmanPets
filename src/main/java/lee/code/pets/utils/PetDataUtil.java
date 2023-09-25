package lee.code.pets.utils;

import lee.code.pets.menus.menu.menudata.options.Option;
import org.bukkit.DyeColor;
import org.bukkit.entity.*;

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
        final Parrot.Variant variant = entity instanceof Parrot parrot ? parrot.getVariant() : Parrot.Variant.RED;
        return startingData + sep + variant.name();
      }
    }
    return null;
  }
}
