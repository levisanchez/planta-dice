package edu.cnm.deepdive.plantadice.service;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * The Plants database records all elements of plant entity and makes available to other functions.
 */
@Database(
    entities = {Plant.class, PlantHistory.class, Weather.class},
    version = 1,
    exportSchema = true
)
@TypeConverters({Converters.class})
public abstract class PlantsDatabase extends RoomDatabase {

  private static final String DB_NAME = "plantHistories_db";

  private static Application context;

  /**
   * Sets context.
   *
   * @param context the context
   */
  public static void setContext(Application context) {
    PlantsDatabase.context = context;
  }

  /**
   * Gets plant dao.
   *
   * @return the plant dao
   */
  public abstract PlantDao getPlantDao();

  /**
   * Gets plant history dao.
   *
   * @return the plant history dao
   */
  public abstract PlantHistoryDao getPlantHistoryDao();

  /**
   * Gets weather dao.
   *
   * @return the weather dao
   */
  public abstract WeatherDao getWeatherDao();

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static PlantsDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {

    private static final PlantsDatabase INSTANCE =
        Room.databaseBuilder(context, PlantsDatabase.class, DB_NAME)
            .addCallback(new PlantHistoriesCallback(context))
            .build();

  }

  private static class PlantHistoriesCallback extends Callback {

    private final Context context;

    private PlantHistoriesCallback(Context context) {
      this.context = context;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      try (
          InputStream input = context.getResources().openRawResource(R.raw.plants);
          Reader reader = new InputStreamReader(input);
          CSVParser parser = new CSVParser(reader, CSVFormat.EXCEL
              .withIgnoreEmptyLines().withIgnoreSurroundingSpaces().withFirstRecordAsHeader());
      ) {
        List<Plant> plants = new LinkedList<>();
        for (CSVRecord record : parser){
          Plant plant = new Plant();
          plant.setName(record.get(0));
          plant.setWaterFrequencyDays(Integer.parseInt(record.get(1)));
          plant.setZipCode(Integer.parseInt(record.get(2)));
          plant.setLocationOutdoor(Boolean.parseBoolean(record.get(3)));
          plants.add(plant);
        }
        PlantsDatabase.getInstance().getPlantDao().insert(plants)
            .subscribeOn(Schedulers.io())
            .subscribe();
      } catch (IOException e){
        throw new RuntimeException(e);
      }
    }

  }

  /**
   * The type Converters.
   */
  public static class Converters {

    /**
     * Date to long long.
     *
     * @param value the value
     * @return the long
     */
    @TypeConverter //annotation for a method
    public static Long dateToLong(Date value) { //Date Java util not sql
      return (value != null) ? value.getTime() : null;
    }

    /**
     * Long to date date.
     *
     * @param value the value
     * @return the date
     */
    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }
  }

}
