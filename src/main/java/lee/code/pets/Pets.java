package lee.code.pets;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.mojang.brigadier.tree.LiteralCommandNode;
import lee.code.pets.commands.CommandManager;
import lee.code.pets.commands.TabCompletion;
import lee.code.pets.database.CacheManager;
import lee.code.pets.database.DatabaseManager;
import lee.code.pets.listeners.PetListener;
import lee.code.pets.managers.DelayManager;
import lee.code.pets.menus.system.MenuListener;
import lee.code.pets.menus.system.MenuManager;
import lee.code.pets.pets.PetManager;
import lombok.Getter;
import me.lucko.commodore.CommodoreProvider;
import me.lucko.commodore.file.CommodoreFileReader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Pets extends JavaPlugin {
  @Getter private CommandManager commandManager;
  @Getter private PetManager petManager;
  @Getter private ProtocolManager protocolManager;
  @Getter private CacheManager cacheManager;
  @Getter private MenuManager menuManager;
  @Getter private DelayManager delayManager;
  private DatabaseManager databaseManager;

  @Override
  public void onEnable() {
    this.databaseManager = new DatabaseManager(this);
    this.cacheManager = new CacheManager(this, databaseManager);
    this.commandManager = new CommandManager(this);
    this.menuManager = new MenuManager();
    this.protocolManager = ProtocolLibrary.getProtocolManager();
    this.petManager = new PetManager(this);
    this.delayManager = new DelayManager(this);
    databaseManager.initialize(false);
    registerCommands();
    registerPacketListeners();
    registerListeners();

  }

  @Override
  public void onDisable() {
    databaseManager.closeConnection();
  }

  private void registerPacketListeners() {
    //protocolManager.addPacketListener(new KeyboardPacketListener(this));
  }

  private void registerListeners() {
    getServer().getPluginManager().registerEvents(new MenuListener(menuManager), this);
    getServer().getPluginManager().registerEvents(new PetListener(this), this);
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
