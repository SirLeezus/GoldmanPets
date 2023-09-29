package lee.code.pets.listeners;

import lee.code.pets.Pets;
import lee.code.pets.lang.Lang;
import lee.code.pets.pets.PetManager;
import lee.code.pets.utils.CoreUtil;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PetListener implements Listener {
  private final Pets pets;

  public PetListener(Pets pets) {
    this.pets = pets;
  }

  @EventHandler
  public void onPlayerPetInteractAtEntity(PlayerInteractAtEntityEvent e) {
    if (!pets.getPetManager().isPet(e.getRightClicked())) return;
    e.setCancelled(true);
  }

  @EventHandler
  public void onPlayerPetRide(PlayerInteractEntityEvent e) {
    final PetManager petManager = pets.getPetManager();
    if (!petManager.isPet(e.getRightClicked())) return;
    e.setCancelled(true);
    //TODO CHECK IF OWNER
    final ItemStack handItem = e.getPlayer().getInventory().getItemInMainHand();
    if (handItem.getType().equals(Material.LEAD)) return;
    if (pets.getDelayManager().isOnDelayOrSchedule(e.getPlayer().getUniqueId(), 500)) return;
    e.getRightClicked().addPassenger(e.getPlayer());
  }

  @EventHandler (priority = EventPriority.MONITOR)
  public void onDamageWithLead(EntityDamageByEntityEvent e) {
    if (e.isCancelled()) return;
    final PetManager petManager = pets.getPetManager();
    if (!(e.getDamager() instanceof Player player)) return;
    if (e.getEntity() instanceof Player) return;
    if (petManager.isPet(e.getEntity())) return;
    final ItemStack handItem = player.getInventory().getItemInMainHand();
    final ItemMeta itemMeta = handItem.getItemMeta();
    if (itemMeta == null) return;
    if (!itemMeta.hasCustomModelData() || itemMeta.getCustomModelData() != 1) return;
    final int[] health = CoreUtil.getEntityHealth(e.getEntity());
    if (health == null) return;
    CoreUtil.sendHealthProgress(player, health[0], health[1]);
  }

  @EventHandler (priority = EventPriority.MONITOR)
  public void onCapturePet(PlayerInteractEntityEvent e) {
    if (e.isCancelled()) return;
    final PetManager petManager = pets.getPetManager();
    if (petManager.isPet(e.getRightClicked())) return;
    final ItemStack handItem = e.getPlayer().getInventory().getItemInMainHand();
    final ItemMeta itemMeta = handItem.getItemMeta();
    if (itemMeta == null) return;
    if (!itemMeta.hasCustomModelData() || itemMeta.getCustomModelData() != 1) return;
    e.setCancelled(true);
    final Entity entity = e.getRightClicked();
    if (pets.getDelayManager().isOnDelayOrSchedule(e.getPlayer().getUniqueId(), 500)) return;
    final int[] health = CoreUtil.getEntityHealth(entity);
    if (health == null) return;
    final double threshold = health[1] * 0.3;
    CoreUtil.sendHealthProgress(e.getPlayer(), health[0], health[1]);
    if (health[0] <= threshold) {
      entity.getWorld().playEffect(entity.getLocation(), Effect.ENDER_SIGNAL, 1);
      entity.getWorld().playSound(entity.getBoundingBox().getCenter().toLocation(entity.getWorld()), Sound.ENTITY_ENDERMAN_TELEPORT, (float) 1, (float) 1);
      petManager.capturePet(e.getPlayer(), entity);
      e.getPlayer().sendMessage(Lang.PREFIX.getComponent(null).append(Lang.CAPTURE_SUCCESSFUL.getComponent(new String[]{CoreUtil.capitalize(entity.getType().name())})));
    }
  }

  @EventHandler
  public void onPetDamage(EntityDamageEvent e) {
    if (!pets.getPetManager().isPet(e.getEntity())) return;
    e.setCancelled(true);
  }

  @EventHandler
  public void onTeleportWithPetActive(PlayerTeleportEvent e) {
    pets.getPetManager().removeActivePet(e.getPlayer());
  }

  @EventHandler
  public void onQuitPetActive(PlayerQuitEvent e) {
    pets.getPetManager().removeActivePet(e.getPlayer());
  }
}
