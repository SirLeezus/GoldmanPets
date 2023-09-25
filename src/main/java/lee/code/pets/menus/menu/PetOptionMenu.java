package lee.code.pets.menus.menu;

import lee.code.pets.Pets;
import lee.code.pets.lang.Lang;
import lee.code.pets.menus.menu.menudata.options.Option;
import lee.code.pets.menus.menu.menudata.options.OptionSelector;
import lee.code.pets.menus.system.MenuButton;
import lee.code.pets.menus.system.MenuPaginatedGUI;
import lee.code.pets.utils.PetDataUtil;
import org.bukkit.Bukkit;
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
    return Bukkit.createInventory(null, 54, Lang.MENU_PETS_TITLE.getComponent(null));
  }

  @Override
  public void decorate(Player player) {
    addBorderGlass();
    for (String option : OptionSelector.valueOf(entityType.name()).getOptions()) {
      addButton(1, createOptionButton(Option.valueOf(option)));
    }
    super.decorate(player);
  }

  private MenuButton createOptionButton(Option option) {
    final String[] petData = pets.getCacheManager().getCachePets().getPetData(petID);
    final ItemStack optionItem = option.createItem(PetDataUtil.getPetData(entityType, petData, option));
    return new MenuButton()
      .creator(p -> optionItem)
      .consumer(e -> {

      });
  }
}
