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
  VARIANT(Material.COMPASS, "&e&lVariant")

  ;

  private final Material material;
  private final String name;

  public ItemStack createItem(String lore) {
    return ItemUtil.createItem(material, name, lore, 0, null);
  }
}
