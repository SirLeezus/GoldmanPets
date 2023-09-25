package lee.code.pets.commands.cmds;

import lee.code.pets.Pets;
import lee.code.pets.commands.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TestCMD extends SubCommand {
  private final Pets pets;

  public TestCMD(Pets pets) {
    this.pets = pets;
  }

  @Override
  public String getName() {
    return "test";
  }

  @Override
  public String getDescription() {
    return "Test command.";
  }

  @Override
  public String getSyntax() {
    return "/pets test";
  }

  @Override
  public String getPermission() {
    return "pets.command.test";
  }

  @Override
  public boolean performAsync() {
    return false;
  }

  @Override
  public boolean performAsyncSynchronized() {
    return false;
  }

  @Override
  public void perform(Player player, String[] args) {
    pets.getPetManager().spawn(player, EntityType.WANDERING_TRADER, new String[]{"&d&lGIGA CHAD"});
  }

  @Override
  public void performConsole(CommandSender console, String[] args) {

  }

  @Override
  public void performSender(CommandSender sender, String[] args) {

  }

  @Override
  public List<String> onTabComplete(CommandSender sender, String[] args) {
    return new ArrayList<>();
  }
}
