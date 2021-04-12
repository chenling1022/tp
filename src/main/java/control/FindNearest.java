package control;

import java.util.ArrayList;
import java.util.List;

import entity.Facility;
import entity.Location;
import exceptions.FacilityNotFoundException;

//@author geezzzyyy
public class FindNearest {
    private static FileManager dataController;
    public FindNearest(FileManager dataController) {
        this.dataController = dataController;
    }

    /**
     * Find a facility by it's name.
     * @param facilityName name of facility.
     * @return a Facility object
     * @throws FacilityNotFoundException
     */
    public static Facility findFacilityByName(String facilityName) throws FacilityNotFoundException {
        for (Facility f: FileManager.getLibraries()) {
            if (f.getName().equals(facilityName)) {
                return f;
            }
        }
        for (Facility f: FileManager.getCanteens()) {
            if (f.getName().equals(facilityName)) {
                return f;
            }
        }
        for (Facility f: FileManager.getLectureTheaters()) {
            if (f.getName().equals(facilityName)) {
                return f;
            }
        }
        throw new FacilityNotFoundException("Your current location: " + facilityName + " does not exist");
    }

    /**
     * Find the top K facilities from a certain location, where K is a user-defined parameter
     * @param currentLocation
     * @param facilityType
     * @param topK
     * @throws FacilityNotFoundException
     */
    public static void findTopKFacility(Location currentLocation, String facilityType, int topK)
            throws FacilityNotFoundException {
        List<Facility> facilityList = new ArrayList<Facility>();
        switch (facilityType.toUpperCase()) {
        case "CANTEEN":
            facilityList = new ArrayList<Facility>(dataController.getCanteens());
            break;
        case "LIBRARY":
            facilityList = new ArrayList<Facility>(dataController.getLibraries());
            break;
        case "LECTURETHEATER":
            facilityList = new ArrayList<Facility>(dataController.getLectureTheaters());
            break;
        default:
            throw new FacilityNotFoundException(
                    "Invalid Facility type! please choose from CANTEEN, LIBRARY and LECTURETHEATER");
        }
        if (topK > facilityList.size()) {
            throw new ArrayIndexOutOfBoundsException("Unable to find top" + topK + "! "
                    + "There are only " + facilityList.size() + " " + facilityType + " available");
        } else {
            for (int i = 0; i < topK; i++) {
                int minIndex = 0;

                for (int j = 1; j < facilityList.size(); j++) {
                    double newDistance = facilityList.get(j).getLocation().distanceTo(currentLocation);
                    double shortestDistance = facilityList.get(minIndex).getLocation().distanceTo(currentLocation);
                    if (newDistance < shortestDistance) {
                        minIndex = j;
                    }
                }
                Facility facilityFound = facilityList.get(minIndex);
                System.out.println(facilityFound.getName() + "@" + facilityFound.getLocation().getAddress());
                facilityList.remove(minIndex);
            }
        }
    }
}
