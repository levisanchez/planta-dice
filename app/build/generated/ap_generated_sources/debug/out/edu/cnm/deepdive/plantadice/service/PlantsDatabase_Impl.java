package edu.cnm.deepdive.plantadice.service;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import edu.cnm.deepdive.plantadice.model.dao.PlantDao;
import edu.cnm.deepdive.plantadice.model.dao.PlantDao_Impl;
import edu.cnm.deepdive.plantadice.model.dao.PlantHistoryDao;
import edu.cnm.deepdive.plantadice.model.dao.PlantHistoryDao_Impl;
import edu.cnm.deepdive.plantadice.model.dao.WeatherDao;
import edu.cnm.deepdive.plantadice.model.dao.WeatherDao_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PlantsDatabase_Impl extends PlantsDatabase {
  private volatile PlantDao _plantDao;

  private volatile PlantHistoryDao _plantHistoryDao;

  private volatile WeatherDao _weatherDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Plant` (`plant_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `water_frequency_days` INTEGER NOT NULL, `location_outdoor` INTEGER NOT NULL, `name` TEXT, `zip_code` INTEGER NOT NULL)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_Plant_zip_code` ON `Plant` (`zip_code`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `PlantHistory` (`plant_history_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `plant_state` TEXT, `water_confirm` INTEGER NOT NULL, `timestamp` INTEGER, FOREIGN KEY(`plant_history_id`) REFERENCES `Plant`(`plant_id`) ON UPDATE NO ACTION ON DELETE SET NULL )");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Weather` (`weather_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `plant_state` TEXT, `rain` INTEGER NOT NULL, `timestamp` INTEGER, `zip_code` INTEGER NOT NULL)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_Weather_zip_code` ON `Weather` (`zip_code`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'bdf33d8805673622a1a01f3a0198ea00')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Plant`");
        _db.execSQL("DROP TABLE IF EXISTS `PlantHistory`");
        _db.execSQL("DROP TABLE IF EXISTS `Weather`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsPlant = new HashMap<String, TableInfo.Column>(5);
        _columnsPlant.put("plant_id", new TableInfo.Column("plant_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlant.put("water_frequency_days", new TableInfo.Column("water_frequency_days", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlant.put("location_outdoor", new TableInfo.Column("location_outdoor", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlant.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlant.put("zip_code", new TableInfo.Column("zip_code", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlant = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlant = new HashSet<TableInfo.Index>(1);
        _indicesPlant.add(new TableInfo.Index("index_Plant_zip_code", false, Arrays.asList("zip_code")));
        final TableInfo _infoPlant = new TableInfo("Plant", _columnsPlant, _foreignKeysPlant, _indicesPlant);
        final TableInfo _existingPlant = TableInfo.read(_db, "Plant");
        if (! _infoPlant.equals(_existingPlant)) {
          return new RoomOpenHelper.ValidationResult(false, "Plant(edu.cnm.deepdive.plantadice.model.entity.Plant).\n"
                  + " Expected:\n" + _infoPlant + "\n"
                  + " Found:\n" + _existingPlant);
        }
        final HashMap<String, TableInfo.Column> _columnsPlantHistory = new HashMap<String, TableInfo.Column>(4);
        _columnsPlantHistory.put("plant_history_id", new TableInfo.Column("plant_history_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlantHistory.put("plant_state", new TableInfo.Column("plant_state", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlantHistory.put("water_confirm", new TableInfo.Column("water_confirm", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlantHistory.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlantHistory = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysPlantHistory.add(new TableInfo.ForeignKey("Plant", "SET NULL", "NO ACTION",Arrays.asList("plant_history_id"), Arrays.asList("plant_id")));
        final HashSet<TableInfo.Index> _indicesPlantHistory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlantHistory = new TableInfo("PlantHistory", _columnsPlantHistory, _foreignKeysPlantHistory, _indicesPlantHistory);
        final TableInfo _existingPlantHistory = TableInfo.read(_db, "PlantHistory");
        if (! _infoPlantHistory.equals(_existingPlantHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "PlantHistory(edu.cnm.deepdive.plantadice.model.entity.PlantHistory).\n"
                  + " Expected:\n" + _infoPlantHistory + "\n"
                  + " Found:\n" + _existingPlantHistory);
        }
        final HashMap<String, TableInfo.Column> _columnsWeather = new HashMap<String, TableInfo.Column>(5);
        _columnsWeather.put("weather_id", new TableInfo.Column("weather_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeather.put("plant_state", new TableInfo.Column("plant_state", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeather.put("rain", new TableInfo.Column("rain", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeather.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWeather.put("zip_code", new TableInfo.Column("zip_code", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWeather = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWeather = new HashSet<TableInfo.Index>(1);
        _indicesWeather.add(new TableInfo.Index("index_Weather_zip_code", false, Arrays.asList("zip_code")));
        final TableInfo _infoWeather = new TableInfo("Weather", _columnsWeather, _foreignKeysWeather, _indicesWeather);
        final TableInfo _existingWeather = TableInfo.read(_db, "Weather");
        if (! _infoWeather.equals(_existingWeather)) {
          return new RoomOpenHelper.ValidationResult(false, "Weather(edu.cnm.deepdive.plantadice.model.entity.Weather).\n"
                  + " Expected:\n" + _infoWeather + "\n"
                  + " Found:\n" + _existingWeather);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "bdf33d8805673622a1a01f3a0198ea00", "3925b96b69f51b67bb65eb572b5e045f");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Plant","PlantHistory","Weather");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `Plant`");
      _db.execSQL("DELETE FROM `PlantHistory`");
      _db.execSQL("DELETE FROM `Weather`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public PlantDao getPlantDao() {
    if (_plantDao != null) {
      return _plantDao;
    } else {
      synchronized(this) {
        if(_plantDao == null) {
          _plantDao = new PlantDao_Impl(this);
        }
        return _plantDao;
      }
    }
  }

  @Override
  public PlantHistoryDao getPlantHistoryDao() {
    if (_plantHistoryDao != null) {
      return _plantHistoryDao;
    } else {
      synchronized(this) {
        if(_plantHistoryDao == null) {
          _plantHistoryDao = new PlantHistoryDao_Impl(this);
        }
        return _plantHistoryDao;
      }
    }
  }

  @Override
  public WeatherDao getWeatherDao() {
    if (_weatherDao != null) {
      return _weatherDao;
    } else {
      synchronized(this) {
        if(_weatherDao == null) {
          _weatherDao = new WeatherDao_Impl(this);
        }
        return _weatherDao;
      }
    }
  }
}
