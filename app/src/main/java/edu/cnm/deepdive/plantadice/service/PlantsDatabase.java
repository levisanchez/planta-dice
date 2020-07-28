package edu.cnm.deepdive.plantadice.service;

import android.annotation.SuppressLint;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.plantadice.R;
import edu.cnm.deepdive.plantadice.model.dao.PlantDao;
import edu.cnm.deepdive.plantadice.model.dao.PlantHistoryDao;
import edu.cnm.deepdive.plantadice.model.dao.WeatherDao;
import edu.cnm.deepdive.plantadice.model.entity.Plant;
import edu.cnm.deepdive.plantadice.model.entity.PlantHistory;
import edu.cnm.deepdive.plantadice.model.entity.Weather;
import edu.cnm.deepdive.plantadice.service.PlantsDatabase.Converters;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Database(
    entities = {Plant.class, PlantHistory.class, Weather.class},
    version = 1,
    exportSchema = true
)
@TypeConverters({Converters.class})
public abstract class PlantsDatabase extends RoomDatabase {

  private static final String DB_NAME = "plantHistories_db";

  private static Application context;

  public static void setContext(Application context) {
    PlantsDatabase.context = context;
  }

  public abstract PlantDao getPlantDao();

  public abstract PlantHistoryDao getPlantHistoryDao();

  public abstract WeatherDao getWeatherDao();

  public static PlantsDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {

    private static final PlantsDatabase INSTANCE =
        Room.databaseBuilder(context, PlantsDatabase.class, DB_NAME)
            .addCallback(new PlantHistoriesCallback())
            .build();

  }

  private static class PlantHistoriesCallback extends Callback {

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      try {
        Map<Plant, List<Weather>> parseFile(R.raw.plants);
//        persist(map);
      } catch (IOException e){
        throw new RuntimeException(e);
      }
    }
//    @SuppressLint("CheckResult")
//    private void persist(Map<Plant, List<Weather>> map) {
//      PlantsDatabase database = PlantsDatabase.getInstance();
//      PlantDao plantDao = database.getPlantDao();
//      WeatherDao weatherDao = database.getWeatherDao();
//      List<Plant> plants = new LinkedList<>(map.keySet());
//      List<Weather> unattributed = map.getOrDefault(null, Collections.emptyList());
//      plants.remove(null);
//      //noinspection ResultOfMethodCallIgnored
//      plantDao.insert(plants)
//          .subscribeOn(Schedulers.io())
//          .flatMap((plantIds) -> {
//            List<Weather> weathers = new LinkedList<>();
//            Iterator<Long> idIterator = plantIds.iterator();
//            Iterator<Plant> plantIterator = plants.iterator();
//            while (idIterator.hasNext()) {
//              long plantId = idIterator.next();
//              for (Weather weather : map.getOrDefault(plantIterator.next(), Collections.emptyList())) {
//                plantId.getPlantId(plantId);
//                weathers.add(weather);
//              }
//            }
//            weathers.addAll(unattributed);
//            return weatherDao.insert(weathers);
//          })
//          .subscribe(
//              (weatherIds) -> {},
//              (throwable) -> {throw new RuntimeException(throwable);}
//          );
//    }

  }

  public static class Converters {
    @TypeConverter //annotation for a method
    public static Long dateToLong(Date value) { //Date Java util not sql
      return (value != null) ? value.getTime() : null;
    }
    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }
  }

}
