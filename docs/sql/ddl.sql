CREATE TABLE IF NOT EXISTS `Plant`
(
    `plant_id`             INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `water_frequency_days` INTEGER                           NOT NULL,
    `location_outdoor`     INTEGER                           NOT NULL,
    `name`                 TEXT,
    `zip_code`             INTEGER                           NOT NULL
);

CREATE INDEX IF NOT EXISTS `index_Plant_zip_code` ON `Plant` (`zip_code`);

CREATE TABLE IF NOT EXISTS `PlantHistory`
(
    `plant_history_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `plant_state`      TEXT,
    `water_confirm`    INTEGER                           NOT NULL,
    `timestamp`        INTEGER,
    FOREIGN KEY (`plant_history_id`)
        REFERENCES `Plant` (`plant_id`) ON UPDATE NO ACTION ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS `Weather`
(
    `weather_id`  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `plant_state` TEXT,
    `rain`        INTEGER                           NOT NULL,
    `timestamp`   INTEGER,
    `zip_code`    INTEGER                           NOT NULL
);

CREATE INDEX IF NOT EXISTS `index_Weather_zip_code` ON `Weather` (`zip_code`
    );