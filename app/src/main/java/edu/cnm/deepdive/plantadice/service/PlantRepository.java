package edu.cnm.deepdive.plantadice.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.plantadice.model.dao.PlantDao;
import edu.cnm.deepdive.plantadice.model.entity.Plant;
import java.util.List;

/**
 * The Plant repository gathers all requested data, organizes and displays it per the instructions given.
 */
public class PlantRepository {

  private final Context context;
  private final PlantsDatabase database;
  private final PlantDao plantDao;


  /**
   * Instantiates a new Plant repository.
   *
   * @param context the context
   */
  public PlantRepository(Context context) {
    this.context = context;
    database = PlantsDatabase.getInstance();
    plantDao = database.getPlantDao();

  }

  /**
   * Gets all.
   *
   * @return the all
   */
  public LiveData<List<Plant>> getAll() {
    return plantDao.selectAll();

  }

  //TODO Add methods for save, delete, get.

}
