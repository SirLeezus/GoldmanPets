package lee.code.pets.menus.menu.menudata.options;

public enum OptionSelector {
  COW,
  SHEEP,
  PARROT,

  ;

  public String[] getOptions() {
    switch (this) {
      case COW -> {
        return new String[] {Option.NAME.name(), Option.BABY.name()};
      }
      case SHEEP -> {
        return new String[] {Option.NAME.name(), Option.BABY.name(), Option.COLOR.name()};
      }
      case PARROT -> {
        return new String[] {Option.NAME.name(), Option.COLOR.name(), Option.VARIANT.name()};
      }
      default -> {
        return new String[]{};
      }
    }
  }
}
