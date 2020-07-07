package edu.cnm.deepdive.plantadice.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.plantadice.model.entity.Plant;
import edu.cnm.deepdive.plantadice.model.entity.PlantHistory;
import io.reactivex.Single;
import java.util.List;

@Dao
public interface PlantDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(Plant plant);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Plant... plants);

  @Update
  Single<Integer> update(Plant...plants);

  @Delete
  Single<Integer> delete(Plant...plants);

  @Query("SELECT * FROM Plant ORDER BY name")
  LiveData<List<Plant>> selectAll();

  @Query("SELECT * FROM Plant WHERE plant_id = :plantId")
  Single<List<Plant>> selectBySourceId(Long plantId);

  @Transaction
  @Query("SELECT * FROM Plant WHERE plant_id = :plantId")
  Single<Plant> selectById(long plantId);
}