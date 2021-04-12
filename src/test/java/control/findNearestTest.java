package control;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import entity.Canteen;
import entity.Location;
import exceptions.FacilityNotFoundException;
import seedu.duke.Map;
//@author geezzzyyy
class findNearestTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Test
    void findFacilityByName_validLibrary() throws FacilityNotFoundException {
        Map map = new Map();
        FindNearest findNearest = new FindNearest(map.getDataController());
        String outputFacility = findNearest.findFacilityByName("library1").getName();
        String expectedFacilityFound = "library1";
        assertEquals(expectedFacilityFound, outputFacility);

    }

    @Test
    void findFacilityByName_validCanteen() throws FacilityNotFoundException {
        Map map = new Map();
        FindNearest findNearest = new FindNearest(map.getDataController());
        String outputFacility = findNearest.findFacilityByName("canteen1").getName();
        String expectedFacilityFound = "canteen1";
        assertEquals(expectedFacilityFound, outputFacility);

    }

    @Test
    void findFacilityByName_validLT() throws FacilityNotFoundException {
        Map map = new Map();
        FindNearest findNearest = new FindNearest(map.getDataController());
        String outputFacility = findNearest.findFacilityByName("lectureTheater1").getName();
        String expectedFacilityFound = "lectureTheater1";
        assertEquals(expectedFacilityFound, outputFacility);

    }
    @Test
    void findFacilityByName_invalidLT() {
        Map map = new Map();
        FindNearest findNearest = new FindNearest(map.getDataController());
        assertThrows(FacilityNotFoundException.class, () -> {
            findNearest.findFacilityByName( "lectureTheater3");
        });

    }

    @Test
    void findTop2Shop_nearLibrary_InvalidFacilityType() {
        captureOut();
        Map map = new Map();
        Location testLibrary = getTestLibrary();
        String facilityType = "shop";
        assertThrows(FacilityNotFoundException.class, () -> {
            new FindNearest(map.getDataController()).findTopKFacility(testLibrary, facilityType, 2);
        });

    }

    @Test
    void findTop5Canteen_nearLibrary_NoOfCanteenLessThan5() {
        captureOut();
        Map map = new Map();
        Location testLibrary = getTestLibrary();
        String facilityType = "canteen";
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            new FindNearest(map.getDataController()).findTopKFacility(testLibrary, facilityType, 5);
        });

    }

    @Test
    void findTop2Canteen_nearLibrary() throws FacilityNotFoundException {
        captureOut();
        Map map = new Map();
        Location testLibrary = getTestLibrary();
        String facilityType = "canteen";
        new FindNearest(map.getDataController()).findTopKFacility(testLibrary, facilityType, 2);
        String theOutput = getOut();
        String expectedOutput = "canteen1@N4-01-01\n" +
                "canteen4@N5-04-02\n";
        assertEquals(expectedOutput, theOutput);
    }


    private Location getTestLibrary() {
        Canteen canteen = new Canteen();
        canteen.setFacilityID("2");
        canteen.setName("library1");
        canteen.strToFacilityType("LIBRARY");
        canteen.setLocation("15.5", "16.6", "N1-02-02", "lol");
        return canteen.getLocation();
    }

    /**
     * Turns on stdOut output capture
     */
    private void captureOut() {
        System.setOut( new PrintStream( outContent ) );
    }


    /**
     * Turns off stdOut capture and returns the contents
     * that have been captured
     *
     * @return
     */
    private String getOut() {
        System.setOut( new PrintStream( new FileOutputStream( FileDescriptor.out ) ) );
        return outContent.toString().replaceAll( "\r", "" );

    }


}