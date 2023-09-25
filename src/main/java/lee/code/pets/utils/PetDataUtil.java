package lee.code.pets.utils;

import lee.code.pets.menus.menu.menudata.options.Option;
import org.bukkit.entity.EntityType;

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
          case COLOR -> {return data[2];}
        }
      }
    }
    return null;
  }
}
