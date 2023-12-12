package lee.code.pets;

import com.mojang.brigadier.tree.LiteralCommandNode;
import lee.code.pets.commands.PetCMD;
import lee.code.pets.commands.TabCompletion;
import lee.code.pets.database.CacheManager;
import lee.code.pets.database.DatabaseManager;
import lee.code.pets.listeners.PetListener;
import lee.code.pets.listeners.QuitListener;
import lee.code.pets.listeners.RenameListener;
import lee.code.pets.managers.DelayManager;
import lee.code.pets.managers.RenameManager;
import lee.code.pets.menus.system.MenuListener;
import lee.code.pets.menus.system.MenuManager;
import lee.code.pets.pets.PetManager;
import lombok.Getter;
import me.lucko.commodore.CommodoreProvider;
import me.lucko.commodore.file.CommodoreFileReader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Pets extends JavaPlugin {
  @Getter private PetManager petManager;
  @Getter private CacheManager cacheManager;
  @Getter private MenuManager menuManager;
  @Getter private DelayManager delayManager;
  @Getter private RenameManager renameManager;
  private DatabaseManager databaseManager;

  @Override
  public void onEnable() {
    this.databaseManager = new DatabaseManager(this);
    this.cacheManager = new CacheManager(this, databaseManager);
    this.menuManager = new MenuManager();
    this.petManager = new PetManager(this);
    this.delayManager = new DelayManager(this);
    this.renameManager = new RenameManager(this);
    databaseManager.initialize(false);
    registerCommands();
    registerListeners();

  }

  @Override
  public void onDisable() {
    databaseManager.closeConnection();
  }

  private void registerListeners() {
    getServer().getPluginManager().registerEvents(new MenuListener(menuManager), this);
    getServer().getPluginManager().registerEvents(new PetListener(this), this);
    getServer().getPluginManager().registerEvents(new RenameListener(this), this);
    getServer().getPluginManager().registerEvents(new QuitListener(this), this);
  }

  private void registerCommands() {
    getCommand("pets").setExecutor(new PetCMD(this));
    getCommand("pets").setTabCompleter(new TabCompletion());
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
