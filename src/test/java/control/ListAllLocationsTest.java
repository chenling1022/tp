package control;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.duke.Feature;
import seedu.duke.Parser;

//@@cswbibibi
public class ListAllLocationsTest {

    @Test
    void listAllCanteenLocations() {
        Parser parser = new Parser();
        String userInput1 = "listAllLocations<canteen>";
        String userInput2 = "listAllLocations<CANTEEN>";
        String userInput3 = "listAllLocations<Canteen>";
        ArrayList expectedOutput = new ArrayList<>(Arrays.asList(
                "canteen1@N4-01-01", "canteen2@N3-02-02", "canteen3@N5-04-01", "canteen4@N5-04-02"));
        String location1 = parser.getLocationsList(userInput1);
        String location2 = parser.getLocationsList(userInput2);
        String location3 = parser.getLocationsList(userInput3);
        assertEquals(expectedOutput, Feature.listAllLocations(location1));
        assertEquals(expectedOutput, Feature.listAllLocations(location2));
        assertEquals(expectedOutput, Feature.listAllLocations(location3));
    }

    @Test
    void listAllLibraryLocations() {
        Parser parser = new Parser();
        String userInput1 = "listAllLocations<library>";
        String userInput2 = "listAllLocations<LIBRARY>";
        String userInput3 = "listAllLocations<Library>";
        String userInput4 = "listAllLocations<LIBraRY>";
        String userInput5 = "listAllLocations<liBRAry>";
        ArrayList expectedOutput = new ArrayList<>(Arrays.asList("library1@N1-02-02"));
        String location1 = parser.getLocationsList(userInput1);
        String location2 = parser.getLocationsList(userInput2);
        String location3 = parser.getLocationsList(userInput3);
        String location4 = parser.getLocationsList(userInput4);
        String location5 = parser.getLocationsList(userInput5);
        assertEquals(expectedOutput, Feature.listAllLocations(location1));
        assertEquals(expectedOutput, Feature.listAllLocations(location2));
        assertEquals(expectedOutput, Feature.listAllLocations(location3));
        assertEquals(expectedOutput, Feature.listAllLocations(location4));
        assertEquals(expectedOutput, Feature.listAllLocations(location5));
    }

    @Test
    void listAllLectureTheaterLocations() {
        Parser parser = new Parser();
        Feature feature = new Feature();
        String userInput1 = "listAllLocations<Lecture Theater>";
        String userInput2 = "listAllLocations<lecture theater>";
        String userInput3 = "listAllLocations<Lecture theater>";
        String userInput4 = "listAllLocations<LECTURE THEATER>";
        String userInput5 = "listAllLocations<LeCTure THeater>";
        String userInput6 = "listAllLocations<LecTURE thEATER>";
        ArrayList expectedOutput = new ArrayList<>(Arrays.asList("lectureTheater1@NS3-05-43"));
        String location1 = parser.getLocationsList(userInput1);
        String location2 = parser.getLocationsList(userInput2);
        String location3 = parser.getLocationsList(userInput3);
        String location4 = parser.getLocationsList(userInput4);
        String location5 = parser.getLocationsList(userInput5);
        String location6 = parser.getLocationsList(userInput6);
        assertEquals(expectedOutput, feature.listAllLocations(location1));
        assertEquals(expectedOutput, feature.listAllLocations(location2));
        assertEquals(expectedOutput, feature.listAllLocations(location3));
        assertEquals(expectedOutput, feature.listAllLocations(location4));
        assertEquals(expectedOutput, feature.listAllLocations(location5));
        assertEquals(expectedOutput, feature.listAllLocations(location6));
    }
}

