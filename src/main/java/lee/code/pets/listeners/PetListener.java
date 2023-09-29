package lee.code.pets.listeners;

import lee.code.pets.Pets;
import lee.code.pets.lang.Lang;
import lee.code.pets.pets.PetManager;
import lee.code.pets.utils.CoreUtil;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

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

  @EventHandler
  public void onCaptureEntity(PlayerInteractEntityEvent e) {
    final PetManager petManager = pets.getPetManager();
    if (petManager.isPet(e.getRightClicked())) return;
    final ItemStack handItem = e.getPlayer().getInventory().getItemInMainHand();
    if (!handItem.getType().equals(Material.LEAD)) return;
    e.setCancelled(true);
    final Entity entity = e.getRightClicked();
    if (pets.getDelayManager().isOnDelayOrSchedule(e.getPlayer().getUniqueId(), 500)) return;
    entity.getWorld().playEffect(entity.getLocation(), Effect.ENDER_SIGNAL, 1);
    entity.getWorld().playSound(entity.getBoundingBox().getCenter().toLocation(entity.getWorld()), Sound.ENTITY_ENDERMAN_TELEPORT, (float) 1, (float) 1);
    petManager.capturePet(e.getPlayer(), entity);
    e.getPlayer().sendMessage(Lang.PREFIX.getComponent(null).append(Lang.CAPTURE_SUCCESSFUL.getComponent(new String[]{CoreUtil.capitalize(entity.getType().name())})));
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
