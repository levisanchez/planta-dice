package edu.cnm.deepdive.plantadice.model.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getWaterFrequencyDays() {
    return waterFrequencyDays;
  }

  public void setWaterFrequencyDays(int waterFrequencyDays) {
    this.waterFrequencyDays = waterFrequencyDays;
  }

  public boolean isLocationOutdoor() {
    return locationOutdoor;
  }

  public void setLocationOutdoor(boolean locationOutdoor) {
    this.locationOutdoor = locationOutdoor;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getZipCode() {
    return zipCode;
  }

  public void setZipCode(int zipCode) {
    this.zipCode = zipCode;
  }
}
