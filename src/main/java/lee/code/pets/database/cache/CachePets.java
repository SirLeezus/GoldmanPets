package lee.code.pets.database.cache;

import lee.code.pets.database.DatabaseManager;
import lee.code.pets.database.cache.data.PlayerPetData;
import lee.code.pets.database.handlers.DatabaseHandler;
import lee.code.pets.database.tables.PetTable;
import lombok.Getter;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class CachePets extends DatabaseHandler {
  @Getter private final AtomicInteger nextID = new AtomicInteger(0);
  @Getter private final PlayerPetData playerPetData;
  private final HashMap<Integer, PetTable> petsCache = new HashMap<>();

  public CachePets(DatabaseManager databaseManager) {
    super(databaseManager);
    this.playerPetData = new PlayerPetData();
  }

  public PetTable getPetTable(int id) {
    return petsCache.get(id);
  }

  public void setPetTable(PetTable petTable) {
    petsCache.put(petTable.getId(), petTable);
    playerPetData.cachePlayerPetTable(petTable);
  }

  public void createNewPet(UUID uuid, String data) {
    nextID.addAndGet(1);
    final PetTable petTable = new PetTable(nextID.get(), uuid, data);
    setPetTable(petTable);
    createPetDatabase(petTable);
  }

  public void updatePetData(int id, String data) {
    final PetTable petTable = getPetTable(id);
    petTable.setData(data);
    updatePetDatabase(petTable);
  }

  public String[] getPetData(int id) {
    return getPetTable(id).getData().split(",");
  }

  public EntityType getPetEntityType(int id) {
    return EntityType.valueOf(getPetTable(id).getData().split(",")[0]);
  }

  public String getPetName(int id) {
    return getPetTable(id).getData().split(",")[1];
  }
}
