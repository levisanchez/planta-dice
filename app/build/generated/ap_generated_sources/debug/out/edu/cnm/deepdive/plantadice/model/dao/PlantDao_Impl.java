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
import edu.cnm.deepdive.plantadice.model.entity.Plant;
import io.reactivex.Single;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PlantDao_Impl implements PlantDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Plant> __insertionAdapterOfPlant;

  private final EntityDeletionOrUpdateAdapter<Plant> __deletionAdapterOfPlant;

  private final EntityDeletionOrUpdateAdapter<Plant> __updateAdapterOfPlant;

  public PlantDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlant = new EntityInsertionAdapter<Plant>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Plant` (`plant_id`,`water_frequency_days`,`location_outdoor`,`name`,`zip_code`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Plant value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getWater_frequency_days());
        final int _tmp;
        _tmp = value.isLocation_outdoor() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        stmt.bindLong(5, value.getZipCode());
      }
    };
    this.__deletionAdapterOfPlant = new EntityDeletionOrUpdateAdapter<Plant>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Plant` WHERE `plant_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Plant value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfPlant = new EntityDeletionOrUpdateAdapter<Plant>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Plant` SET `plant_id` = ?,`water_frequency_days` = ?,`location_outdoor` = ?,`name` = ?,`zip_code` = ? WHERE `plant_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Plant value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getWater_frequency_days());
        final int _tmp;
        _tmp = value.isLocation_outdoor() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        if (value.getName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getName());
        }
        stmt.bindLong(5, value.getZipCode());
        stmt.bindLong(6, value.getId());
      }
    };
  }

  @Override
  public Single<Long> insert(final Plant plant) {
    return Single.fromCallable(new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfPlant.insertAndReturnId(plant);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<List<Long>> insert(final Plant... plants) {
    return Single.fromCallable(new Callable<List<Long>>() {
      @Override
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          List<Long> _result = __insertionAdapterOfPlant.insertAndReturnIdsList(plants);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> delete(final Plant... plants) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__deletionAdapterOfPlant.handleMultiple(plants);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final Plant... plants) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfPlant.handleMultiple(plants);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public LiveData<List<Plant>> selectAll() {
    final String _sql = "SELECT * FROM Plant ORDER BY name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Plant"}, false, new Callable<List<Plant>>() {
      @Override
      public List<Plant> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "plant_id");
          final int _cursorIndexOfWaterFrequencyDays = CursorUtil.getColumnIndexOrThrow(_cursor, "water_frequency_days");
          final int _cursorIndexOfLocationOutdoor = CursorUtil.getColumnIndexOrThrow(_cursor, "location_outdoor");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfZipCode = CursorUtil.getColumnIndexOrThrow(_cursor, "zip_code");
          final List<Plant> _result = new ArrayList<Plant>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Plant _item;
            _item = new Plant();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
            final int _tmpWater_frequency_days;
            _tmpWater_frequency_days = _cursor.getInt(_cursorIndexOfWaterFrequencyDays);
            _item.setWater_frequency_days(_tmpWater_frequency_days);
            final boolean _tmpLocation_outdoor;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfLocationOutdoor);
            _tmpLocation_outdoor = _tmp != 0;
            _item.setLocation_outdoor(_tmpLocation_outdoor);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
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
  public Single<List<Plant>> selectBySourceId(final Long plantId) {
    final String _sql = "SELECT * FROM Plant WHERE plant_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (plantId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, plantId);
    }
    return RxRoom.createSingle(new Callable<List<Plant>>() {
      @Override
      public List<Plant> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "plant_id");
          final int _cursorIndexOfWaterFrequencyDays = CursorUtil.getColumnIndexOrThrow(_cursor, "water_frequency_days");
          final int _cursorIndexOfLocationOutdoor = CursorUtil.getColumnIndexOrThrow(_cursor, "location_outdoor");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfZipCode = CursorUtil.getColumnIndexOrThrow(_cursor, "zip_code");
          final List<Plant> _result = new ArrayList<Plant>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Plant _item;
            _item = new Plant();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
            final int _tmpWater_frequency_days;
            _tmpWater_frequency_days = _cursor.getInt(_cursorIndexOfWaterFrequencyDays);
            _item.setWater_frequency_days(_tmpWater_frequency_days);
            final boolean _tmpLocation_outdoor;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfLocationOutdoor);
            _tmpLocation_outdoor = _tmp != 0;
            _item.setLocation_outdoor(_tmpLocation_outdoor);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item.setName(_tmpName);
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
  public Single<Plant> selectById(final long plantId) {
    final String _sql = "SELECT * FROM Plant WHERE plant_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, plantId);
    return RxRoom.createSingle(new Callable<Plant>() {
      @Override
      public Plant call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "plant_id");
            final int _cursorIndexOfWaterFrequencyDays = CursorUtil.getColumnIndexOrThrow(_cursor, "water_frequency_days");
            final int _cursorIndexOfLocationOutdoor = CursorUtil.getColumnIndexOrThrow(_cursor, "location_outdoor");
            final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
            final int _cursorIndexOfZipCode = CursorUtil.getColumnIndexOrThrow(_cursor, "zip_code");
            final Plant _result;
            if(_cursor.moveToFirst()) {
              _result = new Plant();
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              _result.setId(_tmpId);
              final int _tmpWater_frequency_days;
              _tmpWater_frequency_days = _cursor.getInt(_cursorIndexOfWaterFrequencyDays);
              _result.setWater_frequency_days(_tmpWater_frequency_days);
              final boolean _tmpLocation_outdoor;
              final int _tmp;
              _tmp = _cursor.getInt(_cursorIndexOfLocationOutdoor);
              _tmpLocation_outdoor = _tmp != 0;
              _result.setLocation_outdoor(_tmpLocation_outdoor);
              final String _tmpName;
              _tmpName = _cursor.getString(_cursorIndexOfName);
              _result.setName(_tmpName);
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
