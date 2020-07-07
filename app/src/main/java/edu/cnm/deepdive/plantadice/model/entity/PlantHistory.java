package edu.cnm.deepdive.plantadice.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;
import javax.xml.transform.Source;

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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPlantState() {
    return plantState;
  }

  public void setPlantState(String plantState) {
    this.plantState = plantState;
  }

  public boolean isWater_confirm() {
    return water_confirm;
  }

  public void setWater_confirm(boolean water_confirm) {
    this.water_confirm = water_confirm;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }
}