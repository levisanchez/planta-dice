package edu.cnm.deepdive.plantadice.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Relation;
import edu.cnm.deepdive.plantadice.model.entity.Plant;
import edu.cnm.deepdive.plantadice.model.entity.PlantHistory;


/**
 * This Class pairs Plant history with Plant to create an abstract interface to be used by the database
 */
public class PlantHistoryWithPlant extends PlantHistory {

  @Relation(entity = Plant.class, entityColumn = "plant_id", parentColumn = "plant_id")
  private Plant plant;

  /**
   * Gets plant.
   * @return the plant
   */
  public Plant getPlant() {
    return plant;
  }

  /**
   * Sets plant.
   * @param plant the plant
   */
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
