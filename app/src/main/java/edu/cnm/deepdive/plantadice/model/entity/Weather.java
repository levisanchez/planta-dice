package edu.cnm.deepdive.plantadice.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import java.nio.file.WatchEvent;
import java.util.Date;
import java.util.List;


@Entity(
    indices = @Index(value = "zip_code", unique = false)
)

public class Weather {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "weather_id")
  private long id;

  @ColumnInfo(name = "plant_state")
  private String plantState;

  @ColumnInfo(name = "rain")
  private boolean rain;

  @ColumnInfo(name = "timestamp")
  private Date timestamp;

  @ColumnInfo(name = "zip_code")
  private int zipCode;

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

  public boolean isRain() {
    return rain;
  }

  public void setRain(boolean rain) {
    this.rain = rain;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public int getZipCode() {
    return zipCode;
  }

  public void setZipCode(int zipCode) {
    this.zipCode = zipCode;
  }

  public static class SearchResult {

    @Expose
    private List<Weather> data;

    public List<Weather> getData() {
      return data;
    }

    public void setData(List<Weather> data) {
      this.data = data;
    }

    @Override
    public String toString() {
      return "SearchResult{" +
          "data=" + data.toString();
    }
  }
}