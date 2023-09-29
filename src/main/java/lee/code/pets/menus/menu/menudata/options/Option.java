package lee.code.pets.menus.menu.menudata.options;

import lee.code.pets.enums.DyeColorChatColor;
import lee.code.pets.utils.ItemUtil;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public enum Option {
  NAME(Material.NAME_TAG, "&e&lName"),
  BABY(Material.SUGAR, "&e&lBaby"),
  COLOR(Material.RED_DYE, "&e&lDye Color"),
  BODY_COLOR(Material.RED_DYE, "&e&lBody Color"),
  PATTERN_COLOR(Material.RED_DYE, "&e&lPattern Color"),
  VARIANT(Material.TURTLE_EGG, "&e&lVariant"),
  SADDLE(Material.SADDLE, "&e&lSaddle"),
  CHEST(Material.CHEST, "&e&lChest"),
  HORNS(Material.GOAT_HORN, "&e&lHorns"),
  MARKING(Material.HORSE_SPAWN_EGG, "&e&lMarking"),
  MAIN_GENE(Material.BAMBOO_BLOCK, "&e&lMain Gene"),
  HIDDEN_GENE(Material.STRIPPED_BAMBOO_BLOCK, "&e&lHidden Gene"),
  ANGRY(Material.REDSTONE, "&e&lAngry"),
  NECTAR(Material.BEEHIVE, "&e&lNectar"),
  STUNG(Material.HONEYCOMB, "&e&lStung"),
  PUMPKIN(Material.CARVED_PUMPKIN, "&e&lPumpkin"),
  TYPE(Material.BELL, "&e&lType"),
  PROFESSION(Material.ANVIL, "&e&lProfession"),
  LEVEL(Material.EXPERIENCE_BOTTLE, "&e&lLevel"),
  COLLAR(Material.LEAD, "&e&lCollar"),
  POWERED(Material.TNT, "&e&lPowered"),
  SIZE(Material.BONE_MEAL, "&e&lSize"),
  DYE(Material.FIREWORK_STAR, "&e&lDye")

  ;

  private final Material material;
  private final String name;

  public ItemStack createItem(String lore, String loreColor) {
    final ItemStack itemStack = ItemUtil.createItem(material, name, loreColor + lore, 0, null);
    ItemUtil.hideItemFlags(itemStack);
    return itemStack;
  }

  public ItemStack createColorItem(String color, String lore) {
    return ItemUtil.createItem(Material.valueOf(color + "_DYE"), name, DyeColorChatColor.valueOf(color).getColor() + lore, 0, null);
  }
}
