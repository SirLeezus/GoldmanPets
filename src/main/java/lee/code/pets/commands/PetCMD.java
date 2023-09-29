package lee.code.pets.commands;

import lee.code.pets.Pets;
import lee.code.pets.lang.Lang;
import lee.code.pets.menus.menu.PetMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PetCMD implements CommandExecutor {
  private final Pets pets;

  public PetCMD(Pets pets) {
    this.pets = pets;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (!(sender instanceof Player player)) {
      sender.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.ERROR_NOT_CONSOLE_COMMAND.getComponent(null)));
      return true;
    }
    pets.getMenuManager().openMenu(new PetMenu(pets), player);
    return true;
  }
}
