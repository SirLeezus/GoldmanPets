package lee.code.pets.database.handlers;

import lee.code.pets.database.DatabaseManager;
import lee.code.pets.database.tables.PetTable;

public class DatabaseHandler {
  private final DatabaseManager databaseManager;

  public DatabaseHandler(DatabaseManager databaseManager) {
    this.databaseManager = databaseManager;
  }

  public void createPetDatabase(PetTable petTable) {
    databaseManager.createPetTable(petTable);
  }

  public void updatePetDatabase(PetTable petTable) {
    databaseManager.updatePetTable(petTable);
  }

  public void deletePetDatabase(PetTable petTable) {
    databaseManager.deletePetTable(petTable);
  }
}
