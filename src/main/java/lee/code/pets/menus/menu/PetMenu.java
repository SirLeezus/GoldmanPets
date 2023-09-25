package lee.code.pets.menus.menu;

import lee.code.pets.Pets;
import lee.code.pets.lang.Lang;
import lee.code.pets.menus.system.MenuButton;
import lee.code.pets.menus.system.MenuPaginatedGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PetMenu extends MenuPaginatedGUI {
  private final Pets pets;

  public PetMenu(Pets pets) {
    this.pets = pets;
    setInventory();
  }

  @Override
  protected Inventory createInventory() {
    return Bukkit.createInventory(null, 54, Lang.MENU_PETS_TITLE.getComponent(null));;
  }

  @Override
  public void decorate(Player player) {
    addBorderGlass();
  }

  private MenuButton createPetButton() {
    return new MenuButton();
  }
}
