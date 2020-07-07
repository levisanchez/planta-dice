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
  private int water_frequency_days;

  @ColumnInfo(name = "location_outdoor")
  private boolean location_outdoor;

  @ColumnInfo(name = "name")
  private String name;

  @ColumnInfo(name = "zip_code")
  private int zipCode;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getWater_frequency_days() {
    return water_frequency_days;
  }

  public void setWater_frequency_days(int water_frequency_days) {
    this.water_frequency_days = water_frequency_days;
  }

  public boolean isLocation_outdoor() {
    return location_outdoor;
  }

  public void setLocation_outdoor(boolean location_outdoor) {
    this.location_outdoor = location_outdoor;
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
