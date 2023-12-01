package lee.code.pets.menus.menu;

import lee.code.pets.Pets;
import lee.code.pets.database.cache.CachePets;
import lee.code.pets.lang.Lang;
import lee.code.pets.menus.menu.menudata.MenuItem;
import lee.code.pets.menus.menu.menudata.options.Option;
import lee.code.pets.menus.menu.menudata.options.OptionSelector;
import lee.code.pets.menus.system.MenuButton;
import lee.code.pets.menus.system.MenuGUI;
import lee.code.pets.utils.CoreUtil;
import lee.code.pets.utils.PetDataUtil;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class PetOptionMenu extends MenuGUI {
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
    return Bukkit.createInventory(null, 36, Lang.MENU_PET_OPTIONS_TITLE.getComponent(null));
  }

  @Override
  public void decorate(Player player) {
    addFillerGlass();
    int slot = 0;
    final List<Integer> slots = getOptionItemSlots();
    for (String option : OptionSelector.valueOf(entityType.name()).getOptions()) {
      addButton(slots.get(slot), createOptionButton(player, Option.valueOf(option)));
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
      case COLOR, BODY_COLOR, PATTERN_COLOR -> option.createColorItem(targetData, cappedData);
      case NAME -> option.createItem(cappedData, "&f");
      case BABY, SADDLE, CHEST, HORNS, ANGRY, STUNG, NECTAR, PUMPKIN, COLLAR, POWERED, DYE -> {
        final String loreColor = Boolean.parseBoolean(targetData) ? "&2" : "&c";
        yield option.createItem(cappedData, loreColor);
      }
      default -> option.createItem(cappedData, "&6");
    };
    return new MenuButton()
      .creator(p -> optionItem)
      .consumer(e -> {
        getMenuSoundManager().playClickSound(player);
        pets.getPetManager().removeActivePet(player);
        switch (option) {
          case NAME -> {
            pets.getRenameManager().addPlayerRenaming(player.getUniqueId(), petID);
            player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.MENU_RENAME_MESSAGE.getComponent(null)));
            getInventory().close();
            return;
          }
          case COLOR, BODY_COLOR, PATTERN_COLOR -> {
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
          case BABY, SADDLE, CHEST, HORNS, ANGRY, STUNG, NECTAR, PUMPKIN, COLLAR, POWERED, DYE, CHARGED -> {
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
          case SIZE -> {
            final String size = PetDataUtil.getNextSize(targetData);
            cachePets.updatePetData(petID, PetDataUtil.addNewPetData(entityType, petData, size, option));
          }
        }
        clearButtons();
        decorate(player);
      });
  }

  private void addInterfaceButtons(Player player) {
    addButton(29, new MenuButton()
      .creator(p -> MenuItem.PET_EFFECT.createPetEffectItem(entityType, pets.getCacheManager().getCachePets().getPetEffect(petID)))
      .consumer(e -> {
        final CachePets cachePets = pets.getCacheManager().getCachePets();
        getMenuSoundManager().playClickSound(player);
        cachePets.setPetEffect(petID, !cachePets.getPetEffect(petID));
        pets.getPetManager().removeActivePet(player);
        clearButtons();
        decorate(player);
      }));
    addButton(31, new MenuButton()
      .creator(p -> MenuItem.BACK_MENU.createItem())
      .consumer(e -> {
        getMenuSoundManager().playClickSound(player);
        pets.getMenuManager().openMenu(new PetMenu(pets), player);
      }));
    addButton(33, new MenuButton()
      .creator(p -> MenuItem.SPAWN_PET.createSpawnPetItem(entityType))
      .consumer(e -> {
        final CachePets cachePets = pets.getCacheManager().getCachePets();
        getMenuSoundManager().playClickSound(player);
        pets.getPetManager().removeActivePet(player);
        pets.getPetManager().spawn(player, player.getLocation(), petID, entityType, cachePets.getPetData(petID));
        player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.MENU_SPAWN_SUCCESSFUL.getComponent(new String[]{cachePets.getPetName(petID)})));
        getInventory().close();
      }));
  }

  private List<Integer> getOptionItemSlots() {
    final CachePets cachePets = pets.getCacheManager().getCachePets();
    final String[] petData = cachePets.getPetData(petID);
    return switch (petData.length - 1) {
      case 1 -> List.of(13);
      case 2 -> List.of(11, 15);
      case 3 -> List.of(11, 13, 15);
      case 4 -> List.of(10, 12, 14, 16);
      case 5 -> List.of(11, 12, 13, 14, 15);
      case 6 -> List.of(10, 11, 12, 14, 15, 16);
      case 7 -> List.of(10, 11, 12, 13, 14, 15, 16);
      default -> List.of(10, 11, 12, 13, 14, 15, 16, 17);
    };
  }
}
