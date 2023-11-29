package lee.code.pets.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import lee.code.pets.Pets;
import lee.code.pets.lang.Lang;
import lee.code.pets.utils.CoreUtil;
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
    final String name = CoreUtil.trimToMaxCharacters(CoreUtil.removeSpecialCharactersButColorCodes(PlainTextComponentSerializer.plainText().serialize(e.message())), 20);
    if (name.isBlank()) {
      e.getPlayer().sendMessage(Lang.PREFIX.getComponent(null).append(Lang.ERROR_RENAME_BLANK.getComponent(null)));
      return;
    }
    pets.getRenameManager().updatePetName(e.getPlayer(), name);
  }

  @EventHandler
  public void onPetRenameCommand(PlayerCommandPreprocessEvent e) {
    if (!pets.getRenameManager().isPlayerRenaming(e.getPlayer().getUniqueId())) return;
    e.setCancelled(true);
    e.getPlayer().sendMessage(Lang.PREFIX.getComponent(null).append(Lang.ERROR_RENAME_COMMAND.getComponent(null)));
  }
}
