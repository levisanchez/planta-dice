package edu.cnm.deepdive.plantadice.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.plantadice.model.entity.PlantHistory;
import io.reactivex.Single;
import java.util.List;

@Dao
public interface PlantHistoryDao {


  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(PlantHistory plantHistory);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(PlantHistory... plantHistories);

  @Update
  Single<Integer> update(PlantHistory...plantHistory);

  @Delete
  Single<Integer> delete(PlantHistory...plantHistory);

  @Query("SELECT * FROM planthistory ORDER BY plant_history_id")
  LiveData<List<PlantHistory>> selectAll();

  @Query("SELECT * FROM PlantHistory WHERE `plant_history_id` = :plantHistoryId")
  Single<List<PlantHistory>> selectByPlantHistoryId(Long plantHistoryId);

  @Transaction
  @Query("SELECT * FROM PlantHistory WHERE `plant_history_id` = :plantHistoryId")
  Single<PlantHistory> selectById(long plantHistoryId);
}
