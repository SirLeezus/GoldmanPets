package lee.code.pets.menus.menu.menudata.options;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OptionSelector {
  COW(new String[] {Option.NAME.name(), Option.BABY.name()}),
  SHEEP(new String[] {Option.NAME.name(), Option.BABY.name(), Option.COLOR.name()}),
  PARROT(new String[] {Option.NAME.name(), Option.VARIANT.name()}),
  ALLAY(new String[] {Option.NAME.name()}),
  BAT(new String[] {Option.NAME.name()}),
  BEE(new String[] {Option.NAME.name(), Option.BABY.name()}),
  CAMEL(new String[] {Option.NAME.name(), Option.BABY.name(), Option.SADDLE.name()}),
  CAT(new String[] {Option.NAME.name(), Option.BABY.name(), Option.VARIANT.name(), Option.COLOR.name()}),
  CHICKEN(new String[] {Option.NAME.name(), Option.BABY.name()}),
  DONKEY(new String[] {Option.NAME.name(), Option.BABY.name(), Option.CHEST.name(), Option.SADDLE.name()}),
  FOX(new String[] {Option.NAME.name(), Option.BABY.name(), Option.VARIANT.name()}),
  GOAT(new String[] {Option.NAME.name(), Option.BABY.name(), Option.HORNS.name()}),
  HOGLIN(new String[] {Option.NAME.name(), Option.BABY.name()}),
  HORSE(new String[] {Option.NAME.name(), Option.BABY.name(), Option.SADDLE.name(), Option.VARIANT.name(), Option.MARKING.name()}),
  IRON_GOLEM(new String[] {Option.NAME.name()}),
  LLAMA(new String[] {Option.NAME.name(), Option.BABY.name(), Option.CHEST.name(), Option.VARIANT.name(), Option.SADDLE.name(), Option.COLOR.name()}),
  MUSHROOM_COW(new String[] {Option.NAME.name(), Option.BABY.name(), Option.VARIANT.name()})
  ;

  @Getter private final String[] options;
}
