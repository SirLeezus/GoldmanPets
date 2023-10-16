package lee.code.pets.lang;

import lee.code.pets.utils.CoreUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.text.Component;

@AllArgsConstructor
public enum Lang {
  PREFIX("&5&lPets &6➔ "),
  COMMAND_HELP_TITLE("&a------------------ &7[ &5&lPet Help &7] &a------------------"),
  COMMAND_HELP_LINE_1("&eYou can capture pets by purchasing a &5Pet Capture Lead"),
  COMMAND_HELP_LINE_2("&efrom &3/shop&e. By default you can capture three entities,"),
  COMMAND_HELP_LINE_3("&eif you want more you can subscribe to a premium rank."),
  COMMAND_HELP_LINE_4("&eAll entities captured can be found in your &3/pet &emenu."),
  COMMAND_HELP_LINE_5("&eAll entities can be captured besides the ender dragon."),
  COMMAND_HELP_FOOTER("&a-------------------------------------------------"),
  LEAD_HEALTH("&6&lHealth&7: {0}"),
  MENU_PETS_TITLE("&5&lPets"),
  MENU_PET_OPTIONS_TITLE("&5&lPet Options"),
  MENU_DELETE_PET_CONFIRM_SUCCESSFUL("&aYou successfully deleted pet &f{0}&a!"),
  MENU_DELETE_PET_CANCEL_SUCCESSFUL("&aYou successfully canceled deleting pet &f{0}&a!"),
  MENU_DEACTIVATE_SUCCESSFUL("&aYou successfully deactivated pet &f{0}&a!"),
  MENU_SPAWN_SUCCESSFUL("&aYou successfully spawned your pet &f{0}&a!"),
  MENU_RENAME_SUCCESSFUL("&aYou successfully renamed your pet to &f{0}&a!"),
  MENU_RENAME_MESSAGE("&aPlease type your new pet's name in chat&7:"),
  MENU_PET_ITEM_NAME("&e&lPet Name&7:"),
  MENU_PET_ITEM_LORE("&f{0}\n \n&e&lActions&7:\n&e» &7Right-Click &eEdit Pet\n&e» &7Left-Click &eSpawn Pet\n&e» &7Sneak-Click &eDelete Pet"),
  CAPTURE_SUCCESSFUL("&aYou successfully captured a &3{0}&a!"),
  ERROR_RENAME_COMMAND("&cYou can't use commands until you type in a new pet name."),
  ERROR_NO_PERMISSION("&cYou do not have permission for this."),
  ERROR_ONE_COMMAND_AT_A_TIME("&cYou're currently processing another command, please wait for it to finish."),
  ERROR_NO_ACTIVE_PET("&cYou currently don't have any pets active."),
  ERROR_MAX_PETS("&cYou can only capture &3{0} &cpets, if you want more consider subscribing to one of our premium ranks."),
  ERROR_PREVIOUS_PAGE("&7You are already on the first page."),
  ERROR_NEXT_PAGE("&7You are on the last page."),
  ERROR_NOT_CONSOLE_COMMAND("&cThis command does not work in console."),

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
