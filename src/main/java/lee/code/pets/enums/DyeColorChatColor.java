package lee.code.pets.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum DyeColorChatColor {
  RED("&c"),
  LIGHT_GRAY("&7"),
  GRAY("&8"),
  BLACK("&#4A4A4A"),
  BROWN("&#964B00"),
  CYAN("&3"),
  WHITE("&f"),
  LIME("&a"),
  GREEN("&2"),
  PINK("&#FFC0CB"),
  PURPLE("&5"),
  BLUE("&9"),
  ORANGE("&#FFA500"),
  MAGENTA("&#FF00FF"),
  LIGHT_BLUE("&#ADD8E6"),
  YELLOW("&e")
  ;
  @Getter private String color;
}
