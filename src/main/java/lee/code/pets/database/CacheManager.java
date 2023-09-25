package lee.code.pets.database;

import lee.code.pets.Pets;
import lee.code.pets.database.cache.CachePets;
import lombok.Getter;

public class CacheManager {
  private final Pets pets;
  @Getter private final CachePets cachePets;

  public CacheManager(Pets pets, DatabaseManager databaseManager) {
    this.pets = pets;
    this.cachePets = new CachePets(databaseManager);
  }
}
