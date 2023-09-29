package lee.code.pets.menus.menu;

import lee.code.pets.Pets;
import lee.code.pets.database.cache.CachePets;
import lee.code.pets.lang.Lang;
import lee.code.pets.menus.menu.menudata.MenuItem;
import lee.code.pets.menus.menu.menudata.PetItem;
import lee.code.pets.menus.system.MenuButton;
import lee.code.pets.menus.system.MenuPaginatedGUI;
import lee.code.pets.pets.PetManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PetMenu extends MenuPaginatedGUI {
  private final Pets pets;

  public PetMenu(Pets pets) {
    this.pets = pets;
    setInventory();
  }

  @Override
  protected Inventory createInventory() {
    return Bukkit.createInventory(null, 54, Lang.MENU_PETS_TITLE.getComponent(null));
  }

  @Override
  public void decorate(Player player) {
    addBorderGlass();
    final List<Integer> allPets = new ArrayList<>(pets.getCacheManager().getCachePets().getPlayerPetData().getAllPets(player.getUniqueId()));
    int slot = 0;
    for (int i = 0; i < maxItemsPerPage; i++) {
      index = maxItemsPerPage * page + i;
      if (index >= allPets.size()) break;
      final int targetPet = allPets.get(index);
      addButton(paginatedSlots.get(slot), createPetButton(player, targetPet));
      slot++;
    }
    addDeactivateButton(player);
    super.decorate(player);
  }

  private MenuButton createPetButton(Player player, int petID) {
    final CachePets cachePets = pets.getCacheManager().getCachePets();
    final EntityType entityType = cachePets.getPetEntityType(petID);
    final ItemStack item = PetItem.valueOf(entityType.name()).getHomePageHead(cachePets.getPetName(petID));
    return new MenuButton().creator(p -> item)
      .consumer(e -> {
        if (e.isShiftClick()) {
          getMenuSoundManager().playClickSound(player);
          pets.getMenuManager().openMenu(new PetDeleteMenu(pets, petID), player);
        } else if (e.isLeftClick()) {
          getMenuSoundManager().playClickSound(player);
          final PetManager petManager = pets.getPetManager();
          petManager.removeActivePet(player);
          petManager.spawn(player, petID, entityType, cachePets.getPetData(petID));
          player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.MENU_SPAWN_SUCCESSFUL.getComponent(new String[]{cachePets.getPetName(petID)})));
          getInventory().close();
        } else if (e.isRightClick()) {
          getMenuSoundManager().playClickSound(player);
          pets.getMenuManager().openMenu(new PetOptionMenu(pets, entityType, petID), player);
        }
      });
  }

  private void addDeactivateButton(Player player) {
    addButton(49, new MenuButton()
      .creator(p -> MenuItem.DEACTIVATE_PET.createItem())
      .consumer(e -> {
        getMenuSoundManager().playClickSound(player);
        final PetManager petManager = pets.getPetManager();
        if (petManager.hasActivePet(player.getUniqueId())) {
          final String petName = pets.getCacheManager().getCachePets().getPetName(petManager.getActivePetID(player.getUniqueId()));
          petManager.removePet(player);
          player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.MENU_DEACTIVATE_SUCCESSFUL.getComponent(new String[]{petName})));
        } else {
          player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.ERROR_NO_ACTIVE_PET.getComponent(null)));
        }
        getInventory().close();
      }));
  }
}
