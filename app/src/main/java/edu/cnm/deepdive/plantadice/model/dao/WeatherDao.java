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

@Dao
public interface WeatherDao {

  @Insert
  Single<Long> insert(Weather weather);

  @Insert
  Single<List<Long>> insert(Weather... weathers);

  @Insert
  Single<List<Long>> insert(Collection<Weather> weathers);

  @Update
  Single<Integer> update(Weather...weather);

  @Delete
  Single<Integer> delete(Weather...weather);

  @Query("SELECT * FROM weather ORDER BY weather_id")
  LiveData<List<Weather>> selectAll();

  @Query("SELECT * FROM Weather WHERE weather_id = :weatherId")
  Single<List<Weather>> selectByWeatherId(Long weatherId);

  @Transaction
  @Query("SELECT * FROM Weather WHERE weather_id = :weatherId")
  Single<Weather> selectById(long weatherId);
}