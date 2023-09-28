package lee.code.pets.listeners;

import lee.code.pets.Pets;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {
  private final Pets pets;

  public QuitListener(Pets pets) {
    this.pets = pets;
  }

  @EventHandler
  public void onQuitRenaming(PlayerQuitEvent e) {
    if (!pets.getRenameManager().isPlayerRenaming(e.getPlayer().getUniqueId())) return;
    pets.getRenameManager().removePlayerRenaming(e.getPlayer().getUniqueId());
  }
}
