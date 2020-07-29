package edu.cnm.deepdive.plantadice.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;
import javax.xml.transform.Source;

/**
 * The type Plant history.
 */
@Entity(
    foreignKeys = @ForeignKey(
        entity = Plant.class,
        parentColumns = "plant_id",
        childColumns = "plant_history_id",
        onDelete = ForeignKey.SET_NULL)
)

public class PlantHistory {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "plant_history_id")
  private long id;

  @ColumnInfo(name = "plant_state")
  private String plantState;

  @ColumnInfo(name = "water_confirm")
  private boolean water_confirm;

  @ColumnInfo(name = "timestamp")
  private Date timestamp;

  /**
   * Gets id of Plant History
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets id of Plant History.
   * @param id the id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets plant state.  This will be used in a future version
   * @return the plant state
   */
  public String getPlantState() {
    return plantState;
  }

  /**
   * Sets plant state. This will be used in a future version.
   * @param plantState the plant state
   */
  public void setPlantState(String plantState) {
    this.plantState = plantState;
  }

  /**
   * Is water confirm boolean. This determines if user watered plant. Will be used in future version
   * @return the boolean
   */
  public boolean isWater_confirm() {
    return water_confirm;
  }

  /**
   * Sets water confirm.
   * @param water_confirm the water confirm
   */
  public void setWater_confirm(boolean water_confirm) {
    this.water_confirm = water_confirm;
  }

  /**
   * Gets timestamp.
   * @return the timestamp
   */
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * Sets timestamp.
   * @param timestamp the timestamp
   */
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }
}