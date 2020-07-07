package edu.cnm.deepdive.plantadice.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Relation;
import edu.cnm.deepdive.plantadice.model.entity.Plant;
import edu.cnm.deepdive.plantadice.model.entity.PlantHistory;


public class PlantHistoryWithPlant extends PlantHistory {

  @Relation(entity = Plant.class, entityColumn = "plant_id", parentColumn = "plant_id")
  private Plant plant;

  public Plant getPlant() {
    return plant;
  }

  public void setPlant(Plant plant) {
    this.plant = plant;
  }

  @NonNull
  @Override
  public String toString() {
    String plantName = (plant != null) ? plant.getName() : "(unknown)";
    return String.format("%s%n\u2014%s", getPlant(), plantName);
  }
}
