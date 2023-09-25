package lee.code.pets.database.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@DatabaseTable(tableName = "pets")
public class PetTable {
  @DatabaseField(id = true, canBeNull = false)
  private int id;

  @DatabaseField(columnName = "owner", canBeNull = false)
  private UUID owner;

  @DatabaseField(columnName = "data", canBeNull = false)
  private String data;

  public PetTable(int id, UUID owner, String data) {
    this.id = id;
    this.owner = owner;
    this.data = data;
  }
}
