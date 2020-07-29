package edu.cnm.deepdive.plantadice.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.plantadice.model.entity.Weather;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * The Weather Dao interface which interacts with the weather api.
 */
@Dao
public interface WeatherDao {

  /**
   * Insert single weather element
   *
   * @param weather the weather
   * @return single weather
   */
  @Insert
  Single<Long> insert(Weather weather);

  /**
   * Insert single piece of weather data
   *
   * @param weathers the weathers
   * @return the single
   */
  @Insert
  Single<List<Long>> insert(Weather... weathers);

  /**
   * Insert single.
   *
   * @param weathers the weathers
   * @return the single
   */
  @Insert
  Single<List<Long>> insert(Collection<Weather> weathers);

  /**
   * Update single.
   *
   * @param weather the weather
   * @return the single
   */
  @Update
  Single<Integer> update(Weather...weather);

  /**
   * Delete single.
   *
   * @param weather the weather
   * @return the single
   */
  @Delete
  Single<Integer> delete(Weather...weather);

  /**
   * Select all live weater data and orders by weather id.
   *
   * @return the live data
   */
  @Query("SELECT * FROM weather ORDER BY weather_id")
  LiveData<List<Weather>> selectAll();

  /**
   * Select a single weather id element by weatherId
   *
   * @param weatherId the weather id
   * @return the single
   */
  @Query("SELECT * FROM Weather WHERE weather_id = :weatherId")
  Single<List<Weather>> selectByWeatherId(Long weatherId);

  /**
   * Select by id single.
   *
   * @param weatherId the weather id
   * @return the single
   */
  @Transaction
  @Query("SELECT * FROM Weather WHERE weather_id = :weatherId")
  Single<Weather> selectById(long weatherId);
}