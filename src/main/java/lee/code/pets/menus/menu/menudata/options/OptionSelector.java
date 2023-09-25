package lee.code.pets.menus.menu.menudata.options;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OptionSelector {
  COW(new String[] {Option.NAME.name(), Option.BABY.name()}),
  SHEEP(new String[] {Option.NAME.name(), Option.BABY.name(), Option.COLOR.name()}),
  PARROT(new String[] {Option.NAME.name(), Option.VARIANT.name()}),

  ;

  @Getter private final String[] options;
}
