package edu.cnm.deepdive.plantadice.model.service;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.plantadice.model.dao.PlantDao;
import edu.cnm.deepdive.plantadice.model.dao.PlantHistoryDao;
import edu.cnm.deepdive.plantadice.model.dao.WeatherDao;
import edu.cnm.deepdive.plantadice.model.entity.Plant;
import edu.cnm.deepdive.plantadice.model.entity.PlantHistory;
import edu.cnm.deepdive.plantadice.model.entity.Weather;
import edu.cnm.deepdive.plantadice.model.service.PlantsDatabase.Converters;
import java.util.Date;

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
    }


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
