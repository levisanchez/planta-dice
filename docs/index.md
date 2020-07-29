# Planta Dice

## Introduction
This app proposal addresses challenges sometimes faced with the normal upkeep of plants. They require regular waterings to stay healthy and to thrive. In the high desert environment of New Mexico, outdoor plants can be particularly vulnerable to our exceptionally arid conditions. The Planta Dice (plant says) app helps to keep the user on schedule, and their plants happy, through smart phone notifications.

The Planta Dice app reminds users to water their plants on a regular basis. It takes into account whether the plants are inside or outside. If the plants are inside, the app will remind with a static interval frequency. If the plants are outside, Planta Dice app references the weather service to determine precipitation in the area. If precipitation requirements for the plant is met by weather conditions, the app would not generate a reminder message for the user.

## Intended users

* People who are busy with multiple activities outside of the house

>_As someone who goes to school, work, and has a growing family, I need an app to remind me that my plants need to be watered so that I don't have to invest more time and money in replacing my plants._  

* People who have color-deficient vision

>_As someone who has trouble distinguishing colors, particularly between green and brown, I need an app that alerts me when my plants need to be watered so that they survive and continue to adorn my household._

## Current State of App

The App is currently still in mid-development. The WeatherService class in the service directory does make successful calls to the API Weather service. The PlantsDatabase class also pulls successfully from the quotes.csv file in the raw directory under resources. The fragments are not complete and the App isn't functional. It will build successfully, but does not function.  The next steps are to complete the navigation and fragments to have a functioning user interface. The bottom navigation does change between fragments, but there is no functionality.

The order of importance for enhancements are:

* Functional buttons and navigation
* Recycler view for plants history fragment
* Ability to add plants to database from floating button on any screen

## Functionality

* App will allow user to input plant type and if it is located indoors or outdoors
* If outdoor is selected, app will require user's ZIP code
* App will determine watering interval based on plant's water needs with the interval counting down from the time of entering plant data
* App will query weather service for precipitation over the previous three days and adjust recommendations accordingly
* Send notifications to user when to water plants


## Persistent data

* Data log including plant's watering history and perceived condition by the user
    * Information log will be available to user by clicking on "Review Waterings" button on Home Screen

## Design documentation

* [Wireframe diagram](wireframe.md)

## Entity documentation

* [Entity-relationship diagram](erd.md)

## Entitities

* [Plant Entity](https://github.com/levisanchez/planta-dice/blob/master/app/src/main/java/edu/cnm/deepdive/plantadice/model/entity/Plant.java)
* [Plant History Entity](https://github.com/levisanchez/planta-dice/blob/master/app/src/main/java/edu/cnm/deepdive/plantadice/model/entity/PlantHistory.java)
* [Weather Entity](https://github.com/levisanchez/planta-dice/blob/master/app/src/main/java/edu/cnm/deepdive/plantadice/model/entity/Weather.java)

## Daos

* [PlantDao](https://github.com/levisanchez/planta-dice/blob/master/app/src/main/java/edu/cnm/deepdive/plantadice/model/dao/PlantDao.java)
* [PlantHistoryDao](https://github.com/levisanchez/planta-dice/blob/master/app/src/main/java/edu/cnm/deepdive/plantadice/model/dao/PlantHistoryDao.java)
* [WeatherDao](https://github.com/levisanchez/planta-dice/blob/master/app/src/main/java/edu/cnm/deepdive/plantadice/model/dao/WeatherDao.java)

## Repositories

* [Plant Repository](https://github.com/levisanchez/planta-dice/blob/master/app/src/main/java/edu/cnm/deepdive/plantadice/service/PlantRepository.java)
* [Plant History Repository](https://github.com/levisanchez/planta-dice/blob/master/app/src/main/java/edu/cnm/deepdive/plantadice/service/PlantHistoryRepository.java)
* [Weather Repository](https://github.com/levisanchez/planta-dice/blob/master/app/src/main/java/edu/cnm/deepdive/plantadice/service/WeatherRepository.java)

## Database

* [Plants Database](https://github.com/levisanchez/planta-dice/blob/master/app/src/main/java/edu/cnm/deepdive/plantadice/service/PlantsDatabase.java)

## DDL

* [Data Definition Language](ddl.md)

## Device/external services

* Android notification system
    * [Android Notification Manager documentation](https://developer.android.com/reference/android/app/NotificationManager?hl=en)
    * Will notify the user in the background through user-preference notifications (vibration, persistent blinking light...) that a plant needs to be watered 
* Android alarm manager
    * [Android Alarm Manager documentation](https://developer.android.com/reference/android/app/AlarmManager?hl=en)
    * Will run at a regular interval to determine app data state and notification necessity
    * Will function to retrieve any necessary weather data for API service results
* Weather tracking service
    * Realtime weather data by user's ZIP code
    * Data queries to made via API to [Weather Unlocked documentation](https://developer.weatherunlocked.com/documentation/localweather)
    * [API link](http://api.weatherunlocked.com/)
    * Location services of Weather Unlocked support the structure of postal codes by structuring the query with "country.postcode".ex: 'us.87109'.
    * If weather data becomes unavailable, app will continue standard interval notifications to user
* On the scheduled day of watering, query will be made for weather data for user's ZIP code
* Response will be reviewed for precipitation values over the prior 3 days
* If precipitation is greater than (>) 0.5 inches, then the app will _not_ notify the user to water the plant
* Data, log of precipitation value and scheduled water day skip will be written to Plant History database resource

## Stretch goals/possible enhancements

* Ability for users to upload pictures of plants being added
* User to request type of watering schedule
  * To group waterings for multiple plants to the fewest instances, or standard incremental notifications for each plant as they come up
* Rating system to determine if the plants need to dynamically modify water frequency based on plant condition poll
* Dew / humidity taken into account for outdoor plants
* Seasonal pattern recognition for varying watering requirements
* User able to log addition of fertilizer and noted on plant history

## Instructions to build

* Refer to Github repository for project at [https://github.com/levisanchez/planta-dice](https://github.com/levisanchez/planta-dice)
* Click on the green Code button
* Ensure that Clone with SSH is selected
    * If HTTPS is selected, click on SSH in the upper right-hand corner of dialog box.
* Click on the clipboard icon next to the SSH key value to copy the key
* Refer to your IDE and choose to create your project from version control
* Specify that the Version Control is Git
* Paste the key into URL field and click open
* Once fully loaded, click on Play
    * This will build the app and open it in your emulator or attached device
    
## Basic user instructions

* Once the app is launched, click you will land on the HomeScreen fragment. 
* To add a plant click on the floating plus button in the lower right-hand corner
    * This will display a list of plants to add including adding the ZIP code
* To review history of plants being watered, click on History from the bottom navigation. 
* When it is time to have a plant watered, a notification will show up in the notifications tab in the bottom navigation. 

## License information
 
 Sliding Tiles was written by Levi Sanchez.
 
 Copyright 2020 Deep Dive Coding/CNM Ingenuity, Inc.
 
 Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 
 http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 
 For copyright & license information on the libraries incorporated into Planta Dice, see Notice.