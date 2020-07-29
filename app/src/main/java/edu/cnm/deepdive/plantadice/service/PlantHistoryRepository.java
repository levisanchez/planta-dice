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

/**
 * The type Plant history repository.
 */
public class PlantHistoryRepository {

  private final Context context;
  private final PlantsDatabase database;
  private final PlantDao plantDao;
  private final PlantHistoryDao plantHistoryDao;


  /**
   * Instantiates a new Plant history repository.
   *
   * @param context the context
   */
  public PlantHistoryRepository(Context context) {
    this.context = context;
    database = PlantsDatabase.getInstance();
    plantDao = database.getPlantDao();
    plantHistoryDao = database.getPlantHistoryDao();
  }

  /**
   * Gets all.
   *
   * @return the all
   */
  public LiveData<List<PlantHistory>> getAll() {
    return plantHistoryDao.selectAll();
  }

  /**
   * Get single.
   *
   * @param id the id
   * @return the single
   */
  public Single<PlantHistory> get(long id) {
    return plantHistoryDao.selectById(id)
        .subscribeOn(Schedulers.io());
  }

  /**
   * Save completable.
   *
   * @param plantHistory the plant history
   * @return the completable
   */
  public Completable save(PlantHistory plantHistory) {
    if (plantHistory.getId() == 0){
      return Completable.fromSingle(plantHistoryDao.insert(plantHistory))
          .subscribeOn(Schedulers.io());
    } else {
      return  Completable.fromSingle(plantHistoryDao.update(plantHistory))
          .subscribeOn(Schedulers.io());
    }

  }

  /**
   * Delete completable.
   *
   * @param plantHistory the plant history
   * @return the completable
   */
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


