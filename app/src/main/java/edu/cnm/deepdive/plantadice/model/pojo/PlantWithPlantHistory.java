package edu.cnm.deepdive.plantadice.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.plantadice.model.entity.PlantHistory;
import edu.cnm.deepdive.plantadice.model.entity.Plant;
import java.util.List;

/**
 *  This Class pairs Plant with Plant History to create an abstract interface to be used by the database
 */
public class PlantWithPlantHistory extends Plant {

  @Relation(entity = PlantHistory.class, entityColumn = "plant_id", parentColumn = "plant_id")
  private List<PlantHistory> quotes;

  /**
   * Gets plant histories.
   *
   * @return the plant histories
   */
  public List<PlantHistory> getPlantHistories() {
    return quotes;
  }

  /**
   * Sets plant histories.
   *
   * @param quotes the quotes
   */
  public void setPlantHistories(List<PlantHistory> quotes) {
    this.quotes = quotes;
  }
}
