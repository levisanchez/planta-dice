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

/**
 * The interface Plant history dao.
 */
@Dao
public interface PlantHistoryDao {


  /**
   * Insert single plant history.
   *
   * @param plantHistory the plant history
   * @return the single
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(PlantHistory plantHistory);

  /**
   * Insert single plant history.
   *
   * @param plantHistories the plant histories
   * @return the single
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(PlantHistory... plantHistories);

  /**
   * Update single plant history.
   *
   * @param plantHistory the plant history
   * @return the single
   */
  @Update
  Single<Integer> update(PlantHistory...plantHistory);

  /**
   * Delete single plant history.
   *
   * @param plantHistory the plant history
   * @return the single
   */
  @Delete
  Single<Integer> delete(PlantHistory...plantHistory);

  /**
   * Selects all live data available and orders by plant history id
   *
   * @return the live data found
   */
  @Query("SELECT * FROM planthistory ORDER BY plant_history_id")
  LiveData<List<PlantHistory>> selectAll();

  /**
   * Select a single plant by plant history id.
   *
   * @param plantHistoryId the plant history id
   * @return the single
   */
  @Query("SELECT * FROM PlantHistory WHERE `plant_history_id` = :plantHistoryId")
  Single<List<PlantHistory>> selectByPlantHistoryId(Long plantHistoryId);

  /**
   * Select plant history by id single.
   *
   * @param plantHistoryId the plant history id
   * @return the single
   */
  @Transaction
  @Query("SELECT * FROM PlantHistory WHERE `plant_history_id` = :plantHistoryId")
  Single<PlantHistory> selectById(long plantHistoryId);
}
