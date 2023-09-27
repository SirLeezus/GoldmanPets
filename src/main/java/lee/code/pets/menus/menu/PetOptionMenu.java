package lee.code.pets.menus.menu;

import lee.code.pets.Pets;
import lee.code.pets.database.cache.CachePets;
import lee.code.pets.lang.Lang;
import lee.code.pets.menus.menu.menudata.MenuItem;
import lee.code.pets.menus.menu.menudata.options.Option;
import lee.code.pets.menus.menu.menudata.options.OptionSelector;
import lee.code.pets.menus.system.MenuButton;
import lee.code.pets.menus.system.MenuPaginatedGUI;
import lee.code.pets.utils.CoreUtil;
import lee.code.pets.utils.PetDataUtil;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
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
    final String cappedData = option.equals(Option.NAME) ? targetData : CoreUtil.capitalize(targetData);
    final ItemStack optionItem = switch (option) {
      case COLOR -> option.createColorItem(targetData, cappedData);
      case BABY -> {
        final ItemStack item = option.createItem(cappedData);
        if (Boolean.parseBoolean(targetData)) item.setType(Material.NETHER_STAR);
        yield item;
      }
      default -> option.createItem(cappedData);
    };
    return new MenuButton()
      .creator(p -> optionItem)
      .consumer(e -> {
        pets.getPetManager().removeActivePet(player);
        switch (option) {
          case COLOR -> {
            final String color = PetDataUtil.getNextColor(DyeColor.valueOf(targetData));
            cachePets.updatePetData(petID, PetDataUtil.addNewPetData(entityType, petData, color, option));
          }
          case VARIANT -> {
            final String variant = PetDataUtil.getNextVariant(entityType, targetData);
            cachePets.updatePetData(petID, PetDataUtil.addNewPetData(entityType, petData, variant, option));
          }
          case MARKING -> {
            final String marking = PetDataUtil.getNextHorseMarking(targetData);
            cachePets.updatePetData(petID, PetDataUtil.addNewPetData(entityType, petData, marking, option));
          }
          case BABY, SADDLE, CHEST, HORNS, ANGRY, STUNG, NECTAR, PUMPKIN -> {
            final String petOption = String.valueOf(!Boolean.parseBoolean(targetData));
            cachePets.updatePetData(petID, PetDataUtil.addNewPetData(entityType, petData, petOption, option));
          }
          case MAIN_GENE, HIDDEN_GENE -> {
            final String gene = PetDataUtil.getNextPandaGene(targetData);
            cachePets.updatePetData(petID, PetDataUtil.addNewPetData(entityType, petData, gene, option));
          }
          case TYPE -> {
            final String type = PetDataUtil.getNextVillagerType(targetData);
            cachePets.updatePetData(petID, PetDataUtil.addNewPetData(entityType, petData, type, option));
          }
          case PROFESSION -> {
            final String profession = PetDataUtil.getNextVillagerProfession(targetData);
            cachePets.updatePetData(petID, PetDataUtil.addNewPetData(entityType, petData, profession, option));
          }
          case LEVEL -> {
            final String level = PetDataUtil.getNextVillagerLevel(targetData);
            cachePets.updatePetData(petID, PetDataUtil.addNewPetData(entityType, petData, level, option));
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
