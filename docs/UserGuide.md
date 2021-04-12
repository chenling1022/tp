# User Guide

## Introduction

Location Buddy provides a navigation tool for fast-typing students to locate facilities on campus.

It currently supports three types of facilities, namely `Canteens`, `Lecture Theaters`, and
`Libraries`.

## Quick Start

1. Ensure that you have Java 11 or above installed in your computer.
2. Download the `JAR` file from the latest release of `Location Buddy` [here](https://github.com/AY2021S2-CS2113-T10-2/tp/tags).
3. On the command line, run `java -jar LocationBuddy.jar` in the current directory. 
   (In case JAR file name changes, change the command accordingly)
4. Here are some example commands you can try after starting up:\
   `listAllLocations<canteen>`\
   `search in SCSE`\
   `search library/1`

## Features 

### listAllLocations
List all locations for a specific facility.

Format: `listAllLocations<facility_type>`

* The `facility_type` can be either `canteen`, `library` or `lecture theater`.
* The `facility_type` are not case-sensitive, for example, canteen and Canteen mean the same thing. 

Example of usage: 

`listAllLocations<Canteen>`\
`listAllLocations<library>`\
`listAllLocations<LECTURE THEATER>`

### search
Search a facility's location by the facility type and facility id.

Format: `search facilityType/id`

* The `facilityType` is only either `canteen`, `library`, or `lectureTheater`
* The `facilityType` is not case-sensitive, for example, lectureTheater and LECTURETHEATER 
  mean the same thing.

Examples of usage:
`search lectureTheater/1`

Output:
`lectureTheater (1) is found at: NS3-05-43`

### search in
Search for all facilities found in a building. Currently, only building names 
`NorthSpine`, `SouthSpine`, and `SCSE` are supported.

Format: `search in building_name`

* The `building_name` supported is only one of the following: `NorthSpine`, `SouthSpine`, `SCSE`.
* The `building_name` is not case sensitive, for example, scse and SCSE mean the same thing.

Examples of usage:
`search in SCSE`

Output:
`Here are the facilities in "SCSE":`\
`1 (library1) is located at  (15.5, 16.6, N1-02-02)`

### findFacility
Find the top K nearest facilities of a certain type. Distance is determined with respect to a specific facility.
  
Format: `findFacility<facility><facility_type><top k>`

* The `facility` and `facility_type` are not case-sensitive. \
* `facility` can be found from the output of the `search in` command, or the
* `facility_type` is only either `canteen`, `library`, or `lectureTheater`
* `top k` must be an integer >= 1. It also cannot exceed the number of entries of that `facility_type`.

Examples of usage:
`findFacility<library1><Canteen><2>`

Output:\
`canteen1@N4-01-01`\
`canteen4@N5-04-02`

### Exit Application
Terminate the application gracefully. If you want to be a rebel, you can also either close the command prompt,
or press Ctrl+D (KeyboardInterrupt) or whatever the equivalent is on Mac.

Usage: `bye`
Output: `Bye. Hope you don't have to use me again! Otherwise you need to orientate about your own school better :)`

### Logging
> ℹ️ **Note:** A `Map-0.log` file is created after running the application to log all the user's inputs and error
> handling messages. 
