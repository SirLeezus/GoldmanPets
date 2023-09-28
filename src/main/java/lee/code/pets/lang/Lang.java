package lee.code.pets.lang;

import lee.code.pets.utils.CoreUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.text.Component;

@AllArgsConstructor
public enum Lang {
  PREFIX("&d&lPets &6➔ "),
  MENU_PETS_TITLE("&5&lPets"),
  MENU_PET_OPTIONS_TITLE("&5&lPet Options"),
  MENU_PET_ITEM_LORE("&6» &dRight-Click &7- &eEdit pet!\n&6» &dLeft-Click &7- &eSpawn pet!"),
  CAPTURE_SUCCESSFUL("&aYou successfully captured a &3{0}&a!"),
  ERROR_NO_PERMISSION("&cYou do not have permission for this."),
  ERROR_ONE_COMMAND_AT_A_TIME("&cYou're currently processing another command, please wait for it to finish."),

  ;
  @Getter private final String string;

  public String getString(String[] variables) {
    String value = string;
    if (variables == null || variables.length == 0) return value;
    for (int i = 0; i < variables.length; i++) value = value.replace("{" + i + "}", variables[i]);
    return value;
  }

  public Component getComponent(String[] variables) {
    String value = string;
    if (variables == null || variables.length == 0) return CoreUtil.parseColorComponent(value);
    for (int i = 0; i < variables.length; i++) value = value.replace("{" + i + "}", variables[i]);
    return CoreUtil.parseColorComponent(value);
  }
}
