package seedu.duke;

import java.util.ArrayList;
import java.util.List;

import control.FileManager;
import entity.Facility;

//@@cswbibibi
public class Feature {
    /**
     * List all locations by a certain facility type.
     * @param facilityType facility type {canteen, lecture theater, library}
     * @return list of locations
     * @throws NullPointerException
     */
    public static List<String> listAllLocations(String facilityType) throws NullPointerException {
        List<String> list = null;
        try {
            list = new ArrayList<>();
            if (facilityType.equalsIgnoreCase("canteen")) {
                FileManager fm = new FileManager();
                List<Facility> canteens = fm.getCanteens();
                for (int i = 0; i < canteens.size(); i++) {
                    System.out.println(canteens.get(i).getName() + "@" + canteens.get(i).getAddress());
                    list.add(canteens.get(i).getName() + "@" + canteens.get(i).getAddress());
                }
            } else if (facilityType.equalsIgnoreCase("lecture theater")) {
                FileManager fm = new FileManager();
                List<Facility> lectureTheaters = fm.getLectureTheaters();
                for (int i = 0; i < lectureTheaters.size(); i++) {
                    System.out.println(lectureTheaters.get(i).getName() + "@" + lectureTheaters.get(i).getAddress());
                    list.add(lectureTheaters.get(i).getName() + "@" + lectureTheaters.get(i).getAddress());
                }
            } else if (facilityType.equalsIgnoreCase("library")) {
                FileManager fm = new FileManager();
                List<Facility> libraries = fm.getLibraries();
                for (int i = 0; i < libraries.size(); i++) {
                    System.out.println(libraries.get(i).getName() + "@" + libraries.get(i).getAddress());
                    list.add(libraries.get(i).getName() + "@" + libraries.get(i).getAddress());
                }
            } else {
                System.out.println("Sorry, there's no such facility");
            }
        } catch (Exception e) {
            System.out.println(
                    "We couldn't find the facility you are searching, please input the correct facility name.");
        }
        return list;
    }
}


