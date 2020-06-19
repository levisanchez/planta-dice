# Planta Dice

## Introduction
This app proposal addresses challenges sometimes faced with the normal upkeep of plants. They require regular waterings to stay healthy and to thrive. In the high desert environment of New Mexico, outdoor plants can be particularly vulnerable to our exceptionally arid conditions. The Planta Dice (plant says) app helps to keep the user on schedule, and their plants happy, through smart phone notifications.

The Planta Dice app reminds users to water their plants on a regular basis. It takes into account whether the plants are inside or outside. If the plants are inside, the app will remind with a static interval frequency. If the plants are outside, Planta Dice app references the weather service to determine precipitation in the area. If precipitation requirements for the plant is met by weather conditions, the app would not generate a reminder message for the user.

## Design documentation

* [Wireframe diagram](wireframe.md)

## Entity documentation

* [Entity-relationship diagram](erd.md)

## Intended users

* People who are busy with multiple activities outside of the house

>_As someone who goes to school, work, and has a growing family, I need an app to remind me that my plants need to be watered so that I don't have to invest more time and money in replacing my plants._  

* People who have color-deficient vision

>_As someone who has trouble distinguishing colors, particularly between green and brown, I need an app that alerts me when my plants need to be watered so that they survive and continue to adorn my household._

## Functionality

* App will allow user to input plant type and if it is located indoors or outdoors
* If outdoor is selected, app will require user's ZIP code
* App will determine watering interval based on plant's water needs with the interval counting down from the time of entering plant data
* App will query weather service for precipitation over the previous three days and adjust recommendations accordingly
* Send notifications to user when to water plants


## Persistent data

* Data log including plant's watering history and perceived condition by the user
    * Information log will be available to user by clicking on "Review Waterings" button on Home Screen


## Device/external services

* Android notification system
* Android alarm manager
* Realtime weather data by user's ZIP code
* Data queries to made via API to [Weather Unlocked](https://developer.weatherunlocked.com/documentation/localweather)
* [API link](http://api.weatherunlocked.com/)
* Location services of Weather Unlocked support the structure of postal codes by structuring the query with "country.postcode".ex: 'us.87109'.
* On the scheduled day of watering, query will be made for weather data for user's ZIP code
* Response will be reviewed for precipitation values over the prior 3 days
* If precipitation is greater than (>) 0.5 inches, then the app will _not_ notify the user to water the plant
* Data, log of precipitation value and scheduled water day skip will be written to Plant History database resource
* If weather data becomes unavailable, app will continue standard interval notifications to user

## Stretch goals/possible enhancements

* Ability for users to upload pictures of plants being added
* User to request type of watering schedule
  * To group waterings for multiple plants to the fewest instances, or standard incremental notifications for each plant as they come up
* Rating system to determine if the plants need to dynamically modify water frequency based on plant condition poll
* Dew / humidity taken into account for outdoor plants
* Seasonal pattern recognition for varying watering requirements
* User able to log addition of fertilizer and noted on plant history
