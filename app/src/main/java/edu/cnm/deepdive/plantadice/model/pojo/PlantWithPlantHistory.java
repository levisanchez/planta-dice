package edu.cnm.deepdive.plantadice.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.plantadice.model.entity.PlantHistory;
import edu.cnm.deepdive.plantadice.model.entity.Plant;
import java.util.List;

public class PlantWithPlantHistory extends Plant {

  @Relation(entity = PlantHistory.class, entityColumn = "plant_id", parentColumn = "plant_id")
  private List<PlantHistory> quotes;

  public List<PlantHistory> getPlantHistories() {
    return quotes;
  }

  public void setPlantHistories(List<PlantHistory> quotes) {
    this.quotes = quotes;
  }
}
