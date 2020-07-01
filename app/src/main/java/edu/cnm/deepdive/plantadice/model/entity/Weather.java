package edu.cnm.deepdive.plantadice.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.sql.Date;

@Entity(
    indices = @Index(value = "zip_code", unique = false)
)

public class Weather {

  public void setPlantState(Enum plantState) {
    this.plantState = plantState;
  }

  public void setRain(boolean rain) {
    this.rain = rain;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public long getId() {
    return id;
  }

  public Enum getPlantState() {
    return plantState;
  }

  public boolean isRain() {
    return rain;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "weather_id")
  private long id;

  @ColumnInfo(name = "plant_state")
  private Enum plantState;

  @ColumnInfo(name = "rain")
  private boolean rain;

  @ColumnInfo(name = "timestamp")
  private Date timestamp;
}