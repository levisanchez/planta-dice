package edu.cnm.deepdive.plantadice.model.service;



import android.annotation.SuppressLint;
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
import edu.cnm.deepdive.quotes.service.QuotesDatabase.Converters;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@Database(
    entities = {Plant.class, Weather.class, PlantHistory.class},
    version = 1,
    exportSchema = true
)

@TypeConverters({Converters.class})
public abstract class PlantDatabase extends RoomDatabase {

  private static final String DB_NAME = "plants_db";

  private static Application context;

  public static void setContext(Application context) {
    QuotesDatabase.context = context;
  }

  public abstract PlantDao getPlantDao();

  public abstract PlantHistoryDao getPlantHistoryDao

  public abstract WeatherDao getWeatherDao

  public static PlantDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {

    private static final PlantsDatabase INSTANCE =
        Room.databaseBuilder(context, PlantDatabase.class, DB_NAME)
            .addCallback(new QuotesCallback())
            .build();

  }

  private static class PlantCallback extends Callback {

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      try {
        Map<Source, List<Quote>> map = parseFile(R.raw.quotes);
        persist(map);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    private Map<Plant, List<PlantHistory>> parseFile(int resourceId) throws IOException {
      try (
          InputStream input = PlantDatabase.context.getResources().openRawResource(resourceId);
          Reader reader = new InputStreamReader(input);
          CSVParser parser = CSVParser.parse(
              reader, CSVFormat.EXCEL.withIgnoreSurroundingSpaces().withIgnoreEmptyLines());
      ) {
        Map<PlantHistory, List<PlantHistory>> map = new HashMap<>();
        for (CSVRecord record : parser) {
          Source source = null;
          String sourceName = record.get(0).trim();
          if (!sourceName.isEmpty()) {
            source = new Source();
            source.setName(sourceName);
          }
          List<Plant> plants = map.computeIfAbsent(PlantHistory, (s) -> new LinkedList<>());
          Plant plant = new Plant();
          plant.setText(record.get(1).trim());
          plant.add(quote);
        }
        return map;
      }
    }

    @SuppressLint("CheckResult")
    private void persist(Map<Plant List<PlantHistory>> map) {
      PlantDatabase database = PlantsDatabase.getInstance();
      PlantHistoryDao PlantHistoryDao = database.getPlantHistoryDao();
      PlantDao plantDao = database.getPlantDao();
      List<Plant> plants = new LinkedList<>(map.keySet());
      List<PlantHistory> unattributed = map.getOrDefault(null, Collections.emptyList());
      sources.remove(null);
      //noinspection ResultOfMethodCallIgnored
      plantDao.insert(plants)
          .subscribeOn(Schedulers.io())
          .flatMap((PlantIds) -> {
            List<Plant> plants = new LinkedList<>();
            Iterator<Long> idIterator = sourceIds.iterator();
            Iterator<Source> sourceIterator = sources.iterator();
            while (idIterator.hasNext()) {
              long sourceId = idIterator.next();
              for (Plant plant : map.getOrDefault(sourceIterator.next(), Collections.emptyList())) {
                plantHistory.setPlantId(plantId);
                plants.add(plant);
              }
            }
            plants.addAll(unattributed);
            return plantDao.insert(plants);
          })
          .subscribe(
              (plantIds) -> {},
              (throwable) -> {throw new RuntimeException(throwable);}
          );
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
