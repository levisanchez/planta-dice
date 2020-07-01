package edu.cnm.deepdive.plantadice.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.sql.Date;
import javax.xml.transform.Source;

@Entity(
    foreignKeys = @ForeignKey(
        entity = Source.class,
        parentColumns = "plant_id",
        childColumns = "plant_id",
        onDelete = ForeignKey.SET_NULL)
)

public class PlantHistory {

        public void setPlantState(Enum plantState) {
                this.plantState = plantState;
        }

        public void setWater_confirm(boolean water_confirm) {
                this.water_confirm = water_confirm;
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

        public boolean isWater_confirm() {
                return water_confirm;
        }

        public Date getTimestamp() {
                return timestamp;
        }

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "plant-history_id")
        private long id;

        @ColumnInfo(name = "plant_state")
        private Enum plantState;

        @ColumnInfo(name = "water_confirm")
        private boolean water_confirm;

        @ColumnInfo(name = "timestamp")
        private Date timestamp;
        }