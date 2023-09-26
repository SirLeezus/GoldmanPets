package lee.code.pets.menus.menu.menudata.options;

import lee.code.pets.utils.ItemUtil;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public enum Option {
  NAME(Material.NAME_TAG, "&e&lName"),
  BABY(Material.BONE_MEAL, "&e&lBaby"),
  COLOR(Material.RED_DYE, "&e&lDye Color"),
  VARIANT(Material.COMPASS, "&e&lVariant"),
  SADDLE(Material.SADDLE, "&e&lSaddle"),
  CHEST(Material.CHEST, "&e&lChest"),
  HORNS(Material.GOAT_HORN, "&e&lHorns")

  ;

  private final Material material;
  private final String name;

  public ItemStack createItem(String lore) {
    final ItemStack itemStack =  ItemUtil.createItem(material, name, "&6" + lore, 0, null);
    ItemUtil.hideItemFlags(itemStack);
    return itemStack;
  }
}
