package lee.code.pets.commands.cmds;

import lee.code.pets.Pets;
import lee.code.pets.commands.SubCommand;
import lee.code.pets.pets.SheepPet;
import net.minecraft.server.level.ServerLevel;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

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
    final ServerLevel world = ((CraftWorld) player.getWorld()).getHandle();
    final SheepPet cow = new SheepPet(player, player.getLocation(), "&e&lTesting");
    world.addFreshEntity(cow, CreatureSpawnEvent.SpawnReason.CUSTOM);
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
