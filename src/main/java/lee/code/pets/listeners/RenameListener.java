package lee.code.pets.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import lee.code.pets.Pets;
import lee.code.pets.lang.Lang;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class RenameListener implements Listener {
  private final Pets pets;

  public RenameListener(Pets pets) {
    this.pets = pets;
  }

  @EventHandler (priority = EventPriority.LOWEST)
  public void onPetRename(AsyncChatEvent e) {
    if (!pets.getRenameManager().isPlayerRenaming(e.getPlayer().getUniqueId())) return;
    e.setCancelled(true);
    final String name = PlainTextComponentSerializer.plainText().serialize(e.message()).replaceAll(",", "");
    //TODO max length
    pets.getRenameManager().updatePetName(e.getPlayer(), name);
  }

  @EventHandler
  public void onPetRenameCommand(PlayerCommandPreprocessEvent e) {
    if (!pets.getRenameManager().isPlayerRenaming(e.getPlayer().getUniqueId())) return;
    e.setCancelled(true);
    e.getPlayer().sendMessage(Lang.PREFIX.getComponent(null).append(Lang.ERROR_RENAME_COMMAND.getComponent(null)));
  }
}
