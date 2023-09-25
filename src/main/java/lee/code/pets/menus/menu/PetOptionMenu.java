package lee.code.pets.menus.menu;

import lee.code.pets.Pets;
import lee.code.pets.database.cache.CachePets;
import lee.code.pets.lang.Lang;
import lee.code.pets.menus.menu.menudata.MenuItem;
import lee.code.pets.menus.menu.menudata.options.Option;
import lee.code.pets.menus.menu.menudata.options.OptionSelector;
import lee.code.pets.menus.system.MenuButton;
import lee.code.pets.menus.system.MenuPaginatedGUI;
import lee.code.pets.utils.PetDataUtil;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PetOptionMenu extends MenuPaginatedGUI {
  private final Pets pets;
  private final EntityType entityType;
  private final int petID;

  public PetOptionMenu(Pets pets, EntityType entityType, int petID) {
    this.pets = pets;
    this.entityType = entityType;
    this.petID =  petID;
    setInventory();
  }

  @Override
  protected Inventory createInventory() {
    return Bukkit.createInventory(null, 36, Lang.MENU_PETS_TITLE.getComponent(null));
  }

  @Override
  public void decorate(Player player) {
    addFillerGlass();
    int slot = 10;
    for (String option : OptionSelector.valueOf(entityType.name()).getOptions()) {
      addButton(slot, createOptionButton(player, Option.valueOf(option)));
      slot++;
    }
    addInterfaceButtons(player);
    super.decorate(player);
  }

  private MenuButton createOptionButton(Player player, Option option) {
    final CachePets cachePets = pets.getCacheManager().getCachePets();
    final String[] petData = cachePets.getPetData(petID);
    final String targetData = PetDataUtil.getPetData(entityType, petData, option);
    final ItemStack optionItem = option.createItem(targetData);
    return new MenuButton()
      .creator(p -> optionItem)
      .consumer(e -> {
        pets.getPetManager().removeActivePet(player);
        switch (option) {
          case COLOR -> {
            final String color = PetDataUtil.getNextColor(DyeColor.valueOf(targetData));
            cachePets.updatePetData(petID, PetDataUtil.addNewPetData(entityType, petData, color, option));
          }
          case BABY -> {
            final String isBaby = String.valueOf(!Boolean.parseBoolean(targetData));
            cachePets.updatePetData(petID, PetDataUtil.addNewPetData(entityType, petData, isBaby, option));
          }
          case VARIANT -> {
            final String variant = PetDataUtil.getNextVariant(entityType, targetData);
            cachePets.updatePetData(petID, PetDataUtil.addNewPetData(entityType, petData, variant, option));
          }
        }
        clearButtons();
        decorate(player);
      });
  }

  private void addInterfaceButtons(Player player) {
    addButton(30, new MenuButton()
      .creator(p -> MenuItem.BACK_MENU.createItem())
      .consumer(e -> {
        getMenuSoundManager().playClickSound(player);
        pets.getMenuManager().openMenu(new PetMenu(pets), player);
      }));
    addButton(32, new MenuButton()
      .creator(p -> MenuItem.SPAWN_PET.createSpawnPetItem(entityType)).consumer(e -> {
        getMenuSoundManager().playClickSound(player);
        pets.getPetManager().removeActivePet(player);
        pets.getPetManager().spawn(player, petID, entityType, pets.getCacheManager().getCachePets().getPetData(petID));
        getInventory().close();
      }));
  }
}
