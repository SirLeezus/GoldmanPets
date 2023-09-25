package lee.code.pets.listeners;

import lee.code.pets.Pets;
import lee.code.pets.pets.PetManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

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
    e.getRightClicked().addPassenger(e.getPlayer());
  }

  @EventHandler
  public void onPetDamage(EntityDamageEvent e) {
    if (!pets.getPetManager().isPet(e.getEntity())) return;
    e.setCancelled(true);
  }

  @EventHandler
  public void onTeleportWithPetActive(PlayerTeleportEvent e) {
    final PetManager petManager = pets.getPetManager();
    final UUID uuid = e.getPlayer().getUniqueId();
    if (!petManager.hasActivePet(uuid)) return;
    petManager.removePet(e.getPlayer(), petManager.getActivePetID(uuid));
    System.out.println("Removed mob!");
  }
}
