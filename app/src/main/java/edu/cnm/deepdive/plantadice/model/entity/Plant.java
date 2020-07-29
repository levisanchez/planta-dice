package edu.cnm.deepdive.plantadice.model.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * The Plant entity with elements of each plant
 */
@Entity(
    indices = @Index(value = "zip_code", unique = false)
)

public class Plant {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "plant_id")
  private long id;

  @ColumnInfo(name = "water_frequency_days")
  private int waterFrequencyDays;

  @ColumnInfo(name = "location_outdoor")
  private boolean locationOutdoor;

  @ColumnInfo(collate = ColumnInfo.NOCASE)
  private String name;

  @ColumnInfo(name = "zip_code")
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
   * Gets water frequency days.
   * @return the water frequency days
   */
  public int getWaterFrequencyDays() {
    return waterFrequencyDays;
  }

  /**
   * Sets water frequency days.
   * @param waterFrequencyDays the water frequency days
   */
  public void setWaterFrequencyDays(int waterFrequencyDays) {
    this.waterFrequencyDays = waterFrequencyDays;
  }

  /**
   * Is location outdoor boolean.
   * @return the boolean
   */
  public boolean isLocationOutdoor() {
    return locationOutdoor;
  }

  /**
   * Sets location outdoor.
   * @param locationOutdoor the location outdoor
   */
  public void setLocationOutdoor(boolean locationOutdoor) {
    this.locationOutdoor = locationOutdoor;
  }

  /**
   * Gets name of plant.
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name of the plant.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets zip code of the plant for weather preferences.
   *
   * @return the zip code
   */
  public int getZipCode() {
    return zipCode;
  }

  /**
   * Sets zip code.
   *
   * @param zipCode the zip code
   */
  public void setZipCode(int zipCode) {
    this.zipCode = zipCode;
  }
}
