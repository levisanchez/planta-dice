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
import edu.cnm.deepdive.plantadice.model.entity.PlantHistory;
import edu.cnm.deepdive.plantadice.service.PlantsDatabase;
import io.reactivex.Single;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PlantHistoryDao_Impl implements PlantHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PlantHistory> __insertionAdapterOfPlantHistory;

  private final EntityDeletionOrUpdateAdapter<PlantHistory> __deletionAdapterOfPlantHistory;

  private final EntityDeletionOrUpdateAdapter<PlantHistory> __updateAdapterOfPlantHistory;

  public PlantHistoryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlantHistory = new EntityInsertionAdapter<PlantHistory>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `PlantHistory` (`plant_history_id`,`plant_state`,`water_confirm`,`timestamp`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PlantHistory value) {
        stmt.bindLong(1, value.getId());
        if (value.getPlantState() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPlantState());
        }
        final int _tmp;
        _tmp = value.isWater_confirm() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final Long _tmp_1;
        _tmp_1 = PlantsDatabase.Converters.dateToLong(value.getTimestamp());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp_1);
        }
      }
    };
    this.__deletionAdapterOfPlantHistory = new EntityDeletionOrUpdateAdapter<PlantHistory>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `PlantHistory` WHERE `plant_history_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PlantHistory value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfPlantHistory = new EntityDeletionOrUpdateAdapter<PlantHistory>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `PlantHistory` SET `plant_history_id` = ?,`plant_state` = ?,`water_confirm` = ?,`timestamp` = ? WHERE `plant_history_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PlantHistory value) {
        stmt.bindLong(1, value.getId());
        if (value.getPlantState() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPlantState());
        }
        final int _tmp;
        _tmp = value.isWater_confirm() ? 1 : 0;
        stmt.bindLong(3, _tmp);
        final Long _tmp_1;
        _tmp_1 = PlantsDatabase.Converters.dateToLong(value.getTimestamp());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp_1);
        }
        stmt.bindLong(5, value.getId());
      }
    };
  }

  @Override
  public Single<Long> insert(final PlantHistory plantHistory) {
    return Single.fromCallable(new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          long _result = __insertionAdapterOfPlantHistory.insertAndReturnId(plantHistory);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<List<Long>> insert(final PlantHistory... plantHistories) {
    return Single.fromCallable(new Callable<List<Long>>() {
      @Override
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          List<Long> _result = __insertionAdapterOfPlantHistory.insertAndReturnIdsList(plantHistories);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> delete(final PlantHistory... plantHistory) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__deletionAdapterOfPlantHistory.handleMultiple(plantHistory);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public Single<Integer> update(final PlantHistory... plantHistory) {
    return Single.fromCallable(new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total +=__updateAdapterOfPlantHistory.handleMultiple(plantHistory);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    });
  }

  @Override
  public LiveData<List<PlantHistory>> selectAll() {
    final String _sql = "SELECT * FROM planthistory ORDER BY plant_history_id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"planthistory"}, false, new Callable<List<PlantHistory>>() {
      @Override
      public List<PlantHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "plant_history_id");
          final int _cursorIndexOfPlantState = CursorUtil.getColumnIndexOrThrow(_cursor, "plant_state");
          final int _cursorIndexOfWaterConfirm = CursorUtil.getColumnIndexOrThrow(_cursor, "water_confirm");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<PlantHistory> _result = new ArrayList<PlantHistory>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PlantHistory _item;
            _item = new PlantHistory();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpPlantState;
            _tmpPlantState = _cursor.getString(_cursorIndexOfPlantState);
            _item.setPlantState(_tmpPlantState);
            final boolean _tmpWater_confirm;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfWaterConfirm);
            _tmpWater_confirm = _tmp != 0;
            _item.setWater_confirm(_tmpWater_confirm);
            final Date _tmpTimestamp;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfTimestamp);
            }
            _tmpTimestamp = PlantsDatabase.Converters.longToDate(_tmp_1);
            _item.setTimestamp(_tmpTimestamp);
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
  public Single<List<PlantHistory>> selectByPlantHistoryId(final Long plantHistoryId) {
    final String _sql = "SELECT * FROM PlantHistory WHERE `plant_history_id` = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (plantHistoryId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, plantHistoryId);
    }
    return RxRoom.createSingle(new Callable<List<PlantHistory>>() {
      @Override
      public List<PlantHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "plant_history_id");
          final int _cursorIndexOfPlantState = CursorUtil.getColumnIndexOrThrow(_cursor, "plant_state");
          final int _cursorIndexOfWaterConfirm = CursorUtil.getColumnIndexOrThrow(_cursor, "water_confirm");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<PlantHistory> _result = new ArrayList<PlantHistory>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PlantHistory _item;
            _item = new PlantHistory();
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpPlantState;
            _tmpPlantState = _cursor.getString(_cursorIndexOfPlantState);
            _item.setPlantState(_tmpPlantState);
            final boolean _tmpWater_confirm;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfWaterConfirm);
            _tmpWater_confirm = _tmp != 0;
            _item.setWater_confirm(_tmpWater_confirm);
            final Date _tmpTimestamp;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfTimestamp)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfTimestamp);
            }
            _tmpTimestamp = PlantsDatabase.Converters.longToDate(_tmp_1);
            _item.setTimestamp(_tmpTimestamp);
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
  public Single<PlantHistory> selectById(final long plantHistoryId) {
    final String _sql = "SELECT * FROM PlantHistory WHERE `plant_history_id` = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, plantHistoryId);
    return RxRoom.createSingle(new Callable<PlantHistory>() {
      @Override
      public PlantHistory call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "plant_history_id");
            final int _cursorIndexOfPlantState = CursorUtil.getColumnIndexOrThrow(_cursor, "plant_state");
            final int _cursorIndexOfWaterConfirm = CursorUtil.getColumnIndexOrThrow(_cursor, "water_confirm");
            final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
            final PlantHistory _result;
            if(_cursor.moveToFirst()) {
              _result = new PlantHistory();
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              _result.setId(_tmpId);
              final String _tmpPlantState;
              _tmpPlantState = _cursor.getString(_cursorIndexOfPlantState);
              _result.setPlantState(_tmpPlantState);
              final boolean _tmpWater_confirm;
              final int _tmp;
              _tmp = _cursor.getInt(_cursorIndexOfWaterConfirm);
              _tmpWater_confirm = _tmp != 0;
              _result.setWater_confirm(_tmpWater_confirm);
              final Date _tmpTimestamp;
              final Long _tmp_1;
              if (_cursor.isNull(_cursorIndexOfTimestamp)) {
                _tmp_1 = null;
              } else {
                _tmp_1 = _cursor.getLong(_cursorIndexOfTimestamp);
              }
              _tmpTimestamp = PlantsDatabase.Converters.longToDate(_tmp_1);
              _result.setTimestamp(_tmpTimestamp);
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
