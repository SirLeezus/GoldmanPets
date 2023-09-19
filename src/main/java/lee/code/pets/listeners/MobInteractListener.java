package lee.code.pets.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class MobInteractListener implements Listener {

  @EventHandler
  public void onPlayerInteract(PlayerInteractEntityEvent e) {
    e.getRightClicked().addPassenger(e.getPlayer());
  }
}
