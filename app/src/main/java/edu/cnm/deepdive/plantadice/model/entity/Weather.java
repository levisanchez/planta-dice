package edu.cnm.deepdive.plantadice.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.nio.file.WatchEvent;
import java.util.Date;
import java.util.List;


/**
 * The type Weather.
 */
@Entity
public class Weather {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "weather_id")
  private long id;

  @SerializedName("humid_pct")
  @Expose
  private float humidity;

  @ColumnInfo(name = "rain")
  private boolean rain;

  @ColumnInfo(name = "timestamp")
  private Date timestamp;

  @ColumnInfo(name = "zip_code", index = true)
  private int zipCode;

  /**
   * Gets id.
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets id.
   * @param id the id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets humidity. This will be used to get humidity in the air to determine if watering is needed
   * @return the humidity
   */
  public float getHumidity() {
    return humidity;
  }

  /**
   * Sets humidity.
   * @param humidity the humidity
   */
  public void setHumidity(float humidity) {
    this.humidity = humidity;
  }

  /**
   * Is rain boolean. This will be used in a future version as an additional param for determining watering requirements.
   * @return the boolean
   */
  public boolean isRain() {
    return rain;
  }

  /**
   * Sets rain.
   * @param rain the rain
   */
  public void setRain(boolean rain) {
    this.rain = rain;
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

  /**
   * Gets zip code from user input
   * @return the zip code
   */
  public int getZipCode() {
    return zipCode;
  }

  /**
   * Sets zip code as per user input
   * @param zipCode the zip code
   */
  public void setZipCode(int zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * The type Search result.
   */
  public static class SearchResult {

    @Expose
    private List<Weather> data;

    /**
     * Gets data.
     * @return the data
     */
    public List<Weather> getData() {
      return data;
    }

    /**
     * Sets data.
     * @param data the data
     */
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