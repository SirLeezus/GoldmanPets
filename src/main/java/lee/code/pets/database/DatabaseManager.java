package lee.code.pets.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.db.DatabaseTypeUtils;
import com.j256.ormlite.logger.LogBackendType;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import lee.code.pets.Pets;
import lee.code.pets.database.tables.PetTable;
import org.bukkit.Bukkit;

import java.io.File;
import java.sql.SQLException;

public class DatabaseManager {
  private final Pets pets;
  private final Object synchronizedThreadLock = new Object();
  private Dao<PetTable, Integer> petsDao;
  private ConnectionSource connectionSource;

  public DatabaseManager(Pets pets) {
        this.pets = pets;
  }

  private String getDatabaseURL() {
    if (!pets.getDataFolder().exists()) pets.getDataFolder().mkdir();
    return "jdbc:sqlite:" + new File(pets.getDataFolder(), "database.db");
  }

  public void initialize(boolean debug) {
    if (!debug) LoggerFactory.setLogBackendFactory(LogBackendType.NULL);
    try {
      final String databaseURL = getDatabaseURL();
      connectionSource = new JdbcConnectionSource(
        databaseURL,
        "test",
        "test",
        DatabaseTypeUtils.createDatabaseType(databaseURL));
      createOrCacheTables();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void closeConnection() {
    try {
      connectionSource.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void updateDatabase() {
    try {
      final QueryBuilder<PetTable, Integer> queryBuilder = petsDao.queryBuilder();
      queryBuilder.where().like("data", "%WITHER,%");

      for (PetTable petTable : queryBuilder.query()) {
        petTable.setData(petTable.getData() + ",false");
        petsDao.update(petTable);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private void createOrCacheTables() throws SQLException {
    final CacheManager cacheManager = pets.getCacheManager();

    //Pet data
    TableUtils.createTableIfNotExists(connectionSource, PetTable.class);
    petsDao = DaoManager.createDao(connectionSource, PetTable.class);

    int highestID = 0;
    for (PetTable petTable : petsDao.queryForAll()) {
      cacheManager.getCachePets().setPetTable(petTable);
      if (petTable.getId() > highestID) highestID = petTable.getId();
    }
    cacheManager.getCachePets().getNextID().set(highestID);
  }

  public void createPetTable(PetTable petTable) {
    synchronized (synchronizedThreadLock) {
      Bukkit.getAsyncScheduler().runNow(pets, scheduledTask -> {
        try {
          petsDao.createIfNotExists(petTable);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      });
    }
  }

  public void updatePetTable(PetTable petTable) {
    synchronized (synchronizedThreadLock) {
      Bukkit.getAsyncScheduler().runNow(pets, scheduledTask -> {
        try {
          petsDao.update(petTable);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      });
    }
  }

  public void deletePetTable(PetTable petTable) {
    synchronized (synchronizedThreadLock) {
      Bukkit.getAsyncScheduler().runNow(pets, scheduledTask -> {
        try {
          petsDao.delete(petTable);
        } catch (SQLException e) {
          e.printStackTrace();
        }
      });
    }
  }
}
