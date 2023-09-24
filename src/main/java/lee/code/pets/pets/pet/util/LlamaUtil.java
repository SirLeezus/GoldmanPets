package lee.code.pets.pets.pet.util;

import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public enum LlamaUtil {
  WHITE_CARPET(Material.WHITE_CARPET),
  BLACK_CARPET(Material.BLACK_CARPET),
  CYAN_CARPET(Material.CYAN_CARPET),
  BLUE_CARPET(Material.BLUE_CARPET),
  GREEN_CARPET(Material.GREEN_CARPET),
  BROWN_CARPET(Material.BROWN_CARPET),
  MAGENTA_CARPET(Material.MAGENTA_CARPET),
  LIME_CARPET(Material.LIME_CARPET),
  RED_CARPET(Material.RED_CARPET),
  GRAY_CARPET(Material.GRAY_CARPET),
  YELLOW_CARPET(Material.YELLOW_CARPET),
  ORANGE_CARPET(Material.ORANGE_CARPET),
  PINK_CARPET(Material.PINK_CARPET),
  PURPLE_CARPET(Material.PURPLE_CARPET),
  LIGHT_BLUE_CARPET(Material.LIGHT_BLUE_CARPET),
  LIGHT_GRAY_CARPET(Material.LIGHT_GRAY_CARPET),

  ;
  private final Material carpet;

  public ItemStack getCarpet() {
    return new ItemStack(carpet);
  }
}
