package lee.code.pets;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.mojang.brigadier.tree.LiteralCommandNode;
import lee.code.pets.commands.CommandManager;
import lee.code.pets.commands.TabCompletion;
import lee.code.pets.listeners.KeyboardPacketListener;
import lee.code.pets.listeners.MobInteractListener;
import lombok.Getter;
import me.lucko.commodore.CommodoreProvider;
import me.lucko.commodore.file.CommodoreFileReader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Pets extends JavaPlugin {
  @Getter private CommandManager commandManager;
  @Getter private ProtocolManager protocolManager;

  @Override
  public void onEnable() {
    this.commandManager = new CommandManager(this);
    this.protocolManager = ProtocolLibrary.getProtocolManager();
    registerCommands();
    registerPacketListeners();
    registerListeners();
  }

  @Override
  public void onDisable() {

  }

  private void registerPacketListeners() {
    //protocolManager.addPacketListener(new KeyboardPacketListener(this));
  }

  private void registerListeners() {
    getServer().getPluginManager().registerEvents(new MobInteractListener(), this);
  }

  private void registerCommands() {
    getCommand("pets").setExecutor(commandManager);
    getCommand("pets").setTabCompleter(new TabCompletion(commandManager));
    loadCommodoreData();
  }

  private void loadCommodoreData() {
    try {
      final LiteralCommandNode<?> towns = CommodoreFileReader.INSTANCE.parse(getResource("pets.commodore"));
      CommodoreProvider.getCommodore(this).register(getCommand("pets"), towns);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
