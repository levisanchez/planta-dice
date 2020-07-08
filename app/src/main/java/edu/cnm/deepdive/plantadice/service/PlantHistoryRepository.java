package edu.cnm.deepdive.plantadice.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.plantadice.model.dao.PlantDao;
import edu.cnm.deepdive.plantadice.model.dao.PlantHistoryDao;
import edu.cnm.deepdive.plantadice.model.entity.PlantHistory;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class PlantHistoryRepository {

  private final Context context;
  private final PlantsDatabase database;
  private final PlantDao plantDao;
  private final PlantHistoryDao plantHistoryDao;


  public PlantHistoryRepository(Context context) {
    this.context = context;
    database = PlantsDatabase.getInstance();
    plantDao = database.getPlantDao();
    plantHistoryDao = database.getPlantHistoryDao();
  }

  public LiveData<List<PlantHistory>> getAll() {
    return plantHistoryDao.selectAll();
  }

  public Single<PlantHistory> get(long id) {
    return plantHistoryDao.selectById(id)
        .subscribeOn(Schedulers.io());
  }

  public Completable save(PlantHistory plantHistory) {
    if (plantHistory.getId() == 0){
      return Completable.fromSingle(plantHistoryDao.insert(plantHistory))
          .subscribeOn(Schedulers.io());
    } else {
      return  Completable.fromSingle(plantHistoryDao.update(plantHistory))
          .subscribeOn(Schedulers.io());
    }

  }
  public Completable delete(PlantHistory plantHistory) {
    if (plantHistory.getId() == 0) {
      return Completable.fromAction(() -> {})
          .subscribeOn(Schedulers.io());
    } else {
      return Completable.fromSingle(plantHistoryDao.delete(plantHistory))
          .subscribeOn(Schedulers.io());

    }
  }

}


