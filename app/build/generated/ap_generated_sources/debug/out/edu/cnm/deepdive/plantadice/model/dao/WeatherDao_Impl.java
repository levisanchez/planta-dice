package edu.cnm.deepdive.plantadice.model.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EmptyResultSetException;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import edu.cnm.deepdive.plantadice.model.entity.Weather;
import edu.cnm.deepdive.plantadice.service.PlantsDatabase;
import io.reactivex.Single;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class WeatherDao_Impl implements WeatherDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Weather> __insertionAdapterOfWeather;

  private final EntityDeletionOrUpdateAdapter<Weather> __deletionAdapterOfWeather;

  private final EntityDeletionOrUpdateAdapter<Weather> __updateAdapterOfWeather;

  public WeatherDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWeather = new EntityInsertionAdapter<Weather>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Weather` (`weather_id`,`humidity`,`rain`,`timestamp`,`zip_code`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Weather value) {
        stmt.bindLong(1, value.getId());
        stmt.bindDouble(2, value.getHumidity());
        final int _tmp;
        _tmp = value.isRain() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final Long _tmp_1;
        _tmp_1 = PlantsDatabase.Converters.dateToLong(value.getTimestamp());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp_1);
        }
        stmt.bindLong(5, value.getZipCode());
      }
    };
    this.__deletionAdapterOfWeather = new EntityDeletionOrUpdateAdapter<Weather>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Weather` WHERE `weather_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Weather value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfWeather = new EntityDeletionOrUpdateAdapter<Weather>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Weather` SET `weather_id` = ?,`humidity` = ?,`rain` = ?,`timestamp` = ?,`zip_code` = ? WHERE `weather_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Weather value) {
        stmt.bindLong(1, value.getId());
        stmt.bindDouble(2, value.getHumidity());
        final int _tmp;
        _tmp = value.isRain() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final Long _tmp_1;
        _tmp_1 = PlantsDatabase.Converters.dateToLong(value.getTimestamp());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp_1);
        }
        stmt.bindLong(5, value.getZipCode());
        stmt.bindLong(6, value.getId());
      }
    };
  }

  @Override
  public Single<Long> insert(final Weather weather) {
    return Single.fromCallable(new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfWeather.insertAndReturnId(weather);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<List<Long>> insert(final Weather... weathers) {
    return Single.fromCallable(new Callable<List<Long>>() {
      @Override
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          List<Long> _result = __insertionAdapterOfWeather.insertAndReturnIdsList(weathers);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<List<Long>> insert(final Collection<Weather> weathers) {
    return Single.fromCallable(new Callable<List<Long>>() {
      @Override
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          List<Long> _result = __insertionAdapterOfWeather.insertAndReturnIdsList(weathers);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> delete(final Weather... weather) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__deletionAdapterOfWeather.handleMultiple(weather);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final Weather... weather) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfWeather.handleMultiple(weather);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public LiveData<List<Weather>> selectAll() {
    final String _sql = "SELECT * FROM weather ORDER BY weather_id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"weather"}, false, new Callable<List<Weather>>() {
      @Override
      public List<Weather> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "weather_id");
          final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
          final int _cursorIndexOfRain = CursorUtil.getColumnIndexOrThrow(_cursor, "rain");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfZipCode = CursorUtil.getColumnIndexOrThrow(_cursor, "zip_code");
          final List<Weather> _result = new ArrayList<Weather>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Weather _item;
            _item = new Weather();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
            final float _tmpHumidity;
            _tmpHumidity = _cursor.getFloat(_cursorIndexOfHumidity);
            _item.setHumidity(_tmpHumidity);
            final boolean _tmpRain;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfRain);
            _tmpRain = _tmp != 0;
            _item.setRain(_tmpRain);
            final Date _tmpTimestamp;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfTimestamp);
            }
            _tmpTimestamp = PlantsDatabase.Converters.longToDate(_tmp_1);
            _item.setTimestamp(_tmpTimestamp);
            final int _tmpZipCode;
            _tmpZipCode = _cursor.getInt(_cursorIndexOfZipCode);
            _item.setZipCode(_tmpZipCode);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Single<List<Weather>> selectByWeatherId(final Long weatherId) {
    final String _sql = "SELECT * FROM Weather WHERE weather_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (weatherId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, weatherId);
    }
    return RxRoom.createSingle(new Callable<List<Weather>>() {
      @Override
      public List<Weather> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "weather_id");
          final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
          final int _cursorIndexOfRain = CursorUtil.getColumnIndexOrThrow(_cursor, "rain");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfZipCode = CursorUtil.getColumnIndexOrThrow(_cursor, "zip_code");
          final List<Weather> _result = new ArrayList<Weather>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Weather _item;
            _item = new Weather();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
            final float _tmpHumidity;
            _tmpHumidity = _cursor.getFloat(_cursorIndexOfHumidity);
            _item.setHumidity(_tmpHumidity);
            final boolean _tmpRain;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfRain);
            _tmpRain = _tmp != 0;
            _item.setRain(_tmpRain);
            final Date _tmpTimestamp;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfTimestamp);
            }
            _tmpTimestamp = PlantsDatabase.Converters.longToDate(_tmp_1);
            _item.setTimestamp(_tmpTimestamp);
            final int _tmpZipCode;
            _tmpZipCode = _cursor.getInt(_cursorIndexOfZipCode);
            _item.setZipCode(_tmpZipCode);
            _result.add(_item);
          }
          if(_result == null) {
            throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Single<Weather> selectById(final long weatherId) {
    final String _sql = "SELECT * FROM Weather WHERE weather_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, weatherId);
    return RxRoom.createSingle(new Callable<Weather>() {
      @Override
      public Weather call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "weather_id");
            final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
            final int _cursorIndexOfRain = CursorUtil.getColumnIndexOrThrow(_cursor, "rain");
            final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
            final int _cursorIndexOfZipCode = CursorUtil.getColumnIndexOrThrow(_cursor, "zip_code");
            final Weather _result;
            if(_cursor.moveToFirst()) {
              _result = new Weather();
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              _result.setId(_tmpId);
              final float _tmpHumidity;
              _tmpHumidity = _cursor.getFloat(_cursorIndexOfHumidity);
              _result.setHumidity(_tmpHumidity);
              final boolean _tmpRain;
              final int _tmp;
              _tmp = _cursor.getInt(_cursorIndexOfRain);
              _tmpRain = _tmp != 0;
              _result.setRain(_tmpRain);
              final Date _tmpTimestamp;
              final Long _tmp_1;
              if (_cursor.isNull(_cursorIndexOfTimestamp)) {
                _tmp_1 = null;
              } else {
                _tmp_1 = _cursor.getLong(_cursorIndexOfTimestamp);
              }
              _tmpTimestamp = PlantsDatabase.Converters.longToDate(_tmp_1);
              _result.setTimestamp(_tmpTimestamp);
              final int _tmpZipCode;
              _tmpZipCode = _cursor.getInt(_cursorIndexOfZipCode);
              _result.setZipCode(_tmpZipCode);
            } else {
              _result = null;
            }
            if(_result == null) {
              throw new EmptyResultSetException("Query returned empty result set: " + _statement.getSql());
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
