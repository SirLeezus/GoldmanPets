package lee.code.pets.menus.menu;

import lee.code.pets.Pets;
import lee.code.pets.database.cache.CachePets;
import lee.code.pets.lang.Lang;
import lee.code.pets.menus.menu.menudata.MenuItem;
import lee.code.pets.menus.menu.menudata.PetItem;
import lee.code.pets.menus.system.MenuButton;
import lee.code.pets.menus.system.MenuGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PetDeleteMenu extends MenuGUI {
  private final Pets pets;
  private final int petID;

  public PetDeleteMenu(Pets pets, int petID) {
    this.pets = pets;
    this.petID = petID;
    setInventory();
  }

  @Override
  protected Inventory createInventory() {
    return Bukkit.createInventory(null, 9, Lang.MENU_PETS_TITLE.getComponent(null));
  }

  @Override
  public void decorate(Player player) {
    addFillerGlass();
    addButtons(player);
    super.decorate(player);
  }

  private void addButtons(Player player) {
    final CachePets cachePets = pets.getCacheManager().getCachePets();
    addButton(2,
      new MenuButton()
        .creator(p -> MenuItem.CANCEL.createItem())
        .consumer(e -> {
          getMenuSoundManager().playClickSound(player);
          player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.MENU_DELETE_PET_CANCEL_SUCCESSFUL.getComponent(new String[]{pets.getCacheManager().getCachePets().getPetName(petID)})));
          pets.getMenuManager().openMenu(new PetMenu(pets), player);
        }));
    addButton(4,
      new MenuButton()
        .creator(p -> PetItem.valueOf(cachePets.getPetEntityType(petID).name()).getHeadWithName(cachePets.getPetName(petID)))
        .consumer(e -> {
          getMenuSoundManager().playClickSound(player);
          player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.MENU_DELETE_PET_CANCEL_SUCCESSFUL.getComponent(new String[]{pets.getCacheManager().getCachePets().getPetName(petID)})));
          pets.getMenuManager().openMenu(new PetMenu(pets), player);
        }));
    addButton(6,
      new MenuButton()
        .creator(p -> MenuItem.CONFIRM.createItem())
        .consumer(e -> {
          getMenuSoundManager().playClickSound(player);
          final String name = cachePets.getPetName(petID);
          cachePets.deletePet(petID);
          player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.MENU_DELETE_PET_SUCCESSFUL.getComponent(new String[]{name})));
          pets.getMenuManager().openMenu(new PetMenu(pets), player);
        }));
  }
}
