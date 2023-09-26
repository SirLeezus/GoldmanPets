package lee.code.pets.database.cache.data;

import lee.code.pets.database.tables.PetTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerPetData {
  private final HashMap<UUID, Set<Integer>> playerPetCache = new HashMap<>();

  public void cachePlayerPetTable(PetTable petTable) {
    if (playerPetCache.containsKey(petTable.getOwner())) {
      playerPetCache.get(petTable.getOwner()).add(petTable.getId());
    } else {
      final Set<Integer> pets = new HashSet<>();
      pets.add(petTable.getId());
      playerPetCache.put(petTable.getOwner(), pets);
    }
  }

  public Set<Integer> getAllPets(UUID uuid) {
    if (!playerPetCache.containsKey(uuid)) return new HashSet<>();
    else return playerPetCache.get(uuid);
  }

  public void removePlayerPet(UUID uuid, int id) {
    playerPetCache.get(uuid).remove(id);
    if (playerPetCache.get(uuid).isEmpty()) playerPetCache.remove(uuid);
  }
}
