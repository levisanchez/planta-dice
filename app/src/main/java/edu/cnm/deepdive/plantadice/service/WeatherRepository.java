package edu.cnm.deepdive.plantadice.service;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.plantadice.BuildConfig;
import edu.cnm.deepdive.plantadice.model.dao.WeatherDao;
import edu.cnm.deepdive.plantadice.model.entity.Weather;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import java.util.List;

/**
 * The Weather repository gathers all requested data, organizes and displays it per the instructions given.
 */
public class WeatherRepository {

  private final Context context;
  private final WeatherService weatherService;
  private final PlantsDatabase database;
  private final WeatherDao weatherDao;

  /**
   * Instantiates a new Weather repository.
   *
   * @param context the context
   */
  public WeatherRepository(Context context) {
    this.context = context;
    weatherService = WeatherService.getInstance();

    database = PlantsDatabase.getInstance();
    weatherDao = database.getWeatherDao();
  }


  /**
   * Gets current.
   *
   * @param zipCode the zip code
   * @return the current
   */
  public Single<Weather> getCurrent(int zipCode) {
    @SuppressLint("DefaultLocale")
    String zipCodeString = String.format("%05d", zipCode);
    return weatherService.getWeather(zipCodeString, BuildConfig.APP_ID, BuildConfig.APP_KEY)
        .flatMap((weather)-> {
          weather.setZipCode(zipCode);
          weather.setTimestamp(new Date());
          return weatherDao.insert(weather)
              .map((id)-> {
                weather.setId(id);
                return weather;
              });
        })
        .subscribeOn(Schedulers.io());
  }

  /**
   * Get all live data.
   *
   * @return the live data
   */
  public LiveData<List<Weather>> getAll(){
    return weatherDao.selectAll();
  }
}
