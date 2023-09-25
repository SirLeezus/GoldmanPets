package lee.code.pets.listeners;

import lee.code.pets.Pets;
import lee.code.pets.pets.PetManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PetInteractListener implements Listener {
  private final Pets pets;

  public PetInteractListener(Pets pets) {
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
}
