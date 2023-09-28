package lee.code.pets.managers;

import lee.code.pets.Pets;
import lee.code.pets.database.cache.CachePets;
import lee.code.pets.lang.Lang;
import lee.code.pets.menus.menu.menudata.options.Option;
import lee.code.pets.utils.PetDataUtil;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class RenameManager {
  private final Pets pets;
  private final ConcurrentHashMap<UUID, Integer> playersRenaming = new ConcurrentHashMap<>();

  public RenameManager(Pets pets) {
    this.pets = pets;
  }

  public void addPlayerRenaming(UUID uuid, int petID) {
    playersRenaming.put(uuid, petID);
  }

  public void removePlayerRenaming(UUID uuid) {
    playersRenaming.remove(uuid);
  }

  public boolean isPlayerRenaming(UUID uuid) {
    return playersRenaming.containsKey(uuid);
  }

  public int getSelectedPet(UUID uuid) {
    return playersRenaming.get(uuid);
  }

  public void updatePetName(Player player, String name) {
    final int petID = getSelectedPet(player.getUniqueId());
    final CachePets cachePets = pets.getCacheManager().getCachePets();
    final String[] data = cachePets.getPetData(petID);
    final EntityType entityType = EntityType.valueOf(data[0]);
    final String newData = PetDataUtil.addNewPetData(entityType, data, name, Option.NAME);
    cachePets.updatePetData(petID, newData);
    removePlayerRenaming(player.getUniqueId());
    player.sendMessage(Lang.PREFIX.getComponent(null).append(Lang.MENU_RENAME_SUCCESSFUL.getComponent(new String[]{name})));
  }
}
