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
import java.util.Collection;
import java.util.List;

/**
 * This is the interface on actions for Plant objects
 */
@Dao
public interface PlantDao {

  /**
   * Insert single plant.
   *
   * @param plant the plant
   * @return the single
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(Plant plant);

  /**
   * Insert single.
   *
   * @param plants the plants
   * @return the single
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Plant... plants);

  /**
   * Insert single.
   *
   * @param plants the plants
   * @return the single
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Plant> plants);

  /**
   * Update single.
   *
   * @param plants the plants
   * @return the single
   */
  @Update
  Single<Integer> update(Plant...plants);

  /**
   * Delete single.
   *
   * @param plants the plants
   * @return the single
   */
  @Delete
  Single<Integer> delete(Plant...plants);

  /**
   * Select all live data.
   *
   * @return the live data
   */
  @Query("SELECT * FROM Plant ORDER BY name")
  LiveData<List<Plant>> selectAll();

  /**
   * Select by source id single.
   *
   * @param plantId the plant id
   * @return the single
   */
  @Query("SELECT * FROM Plant WHERE plant_id = :plantId")
  Single<List<Plant>> selectBySourceId(Long plantId);

  /**
   * Select by id single.
   *
   * @param plantId the plant id
   * @return the single
   */
  @Transaction
  @Query("SELECT * FROM Plant WHERE plant_id = :plantId")
  Single<Plant> selectById(long plantId);
}