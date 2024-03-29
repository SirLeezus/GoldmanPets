package lee.code.pets.menus.menu.menudata;

import lee.code.pets.lang.Lang;
import lee.code.pets.utils.CoreUtil;
import lee.code.pets.utils.ItemUtil;
import lee.code.pets.enums.PetEffects;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

@AllArgsConstructor
public enum MenuItem {
  FILLER_GLASS(Material.BLACK_STAINED_GLASS_PANE, "", null, false, false, null),
  NEXT_PAGE(Material.PAPER, "&e&lNext Page ->", null, false, false, null),
  PREVIOUS_PAGE(Material.PAPER, "&e&l<- Prev Page", null, false, false, null),
  BACK_MENU(Material.BARRIER, "&c&l<-- Back", null, false, false, null),
  SPAWN_PET(Material.PLAYER_HEAD, "&a&lSpawn Pet", null, false, false, null),
  CONFIRM(Material.LIME_STAINED_GLASS_PANE, "&2&lConfirm Delete", null, false, false, null),
  CANCEL(Material.RED_STAINED_GLASS_PANE, "&c&lCancel Delete", null, false, false, null),
  DEACTIVATE_PET(Material.BARRIER, "&c&lDeactivate Pet", null, false, false, null),
  PET_EFFECT(Material.POTION, "&d&lPassive Effect", null, true, true, null),
  ;

  private final Material material;
  private final String name;
  private final String lore;
  private final boolean hideItemFlags;
  private final boolean enchantItem;
  private final String skin;

  public ItemStack createItem() {
    final ItemStack item = ItemUtil.createItem(material, name, lore, 0, skin);
    if (hideItemFlags) ItemUtil.hideItemFlags(item);
    if (enchantItem) ItemUtil.enchantItem(item, Enchantment.ARROW_INFINITE, 1);
    return item;
  }

  public ItemStack createSpawnPetItem(EntityType entityType) {
    final ItemStack head = PetItem.valueOf(entityType.name()).getHead();
    final ItemMeta itemMeta = head.getItemMeta();
    itemMeta.displayName(CoreUtil.parseColorComponent(name));
    head.setItemMeta(itemMeta);
    return head;
  }

  public ItemStack createPetEffectItem(EntityType entityType, boolean effect) {
    final ItemStack item = ItemUtil.createItem(material, name, createPetEffectLore(entityType, effect), 0, skin);
    final PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
    potionMeta.setBasePotionType(PetEffects.valueOf(entityType.name()).getPotionType());
    item.setItemMeta(potionMeta);
    if (hideItemFlags) ItemUtil.hideItemFlags(item);
    if (enchantItem) ItemUtil.enchantItem(item, Enchantment.ARROW_INFINITE, 1);
    return item;
  }

  private String createPetEffectLore(EntityType entityType, boolean effect) {
    final String effectColor = effect ? "&2" : "&c";
    final PotionEffect potionEffect = PetEffects.valueOf(entityType.name()).getPotionEffect();
    return Lang.MENU_PET_EFFECT_LORE.getString(new String[]{CoreUtil.capitalize(potionEffect.getType().getName()), String.valueOf(potionEffect.getAmplifier()), effectColor + CoreUtil.capitalize(String.valueOf(effect))});
  }
}
