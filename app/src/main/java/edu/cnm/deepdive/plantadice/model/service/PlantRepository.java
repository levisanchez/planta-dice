package edu.cnm.deepdive.plantadice.model.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.plantadice.model.dao.PlantDao;
import edu.cnm.deepdive.plantadice.model.entity.Plant;
import edu.cnm.deepdive.plantadice.model.service.PlantsDatabase;
import java.util.List;

public class PlantRepository {

  private final Context context;
  private final PlantsDatabase database;
  private final PlantDao plantDao;


  public PlantRepository(Context context) {
    this.context = context;
    database = PlantsDatabase.getInstance();
    plantDao = database.getPlantDao();

  }

  public LiveData<List<Plant>> getAll() {
    return plantDao.selectAll();

  }

  //TODO Add methods for save, delete, get.

}
