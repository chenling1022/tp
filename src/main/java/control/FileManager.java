package control;

import static java.lang.System.err;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entity.Canteen;
import entity.Facility;
import entity.LectureTheater;
import entity.Library;
import exceptions.FileIsEmptyException;

//@@author zhangyongzhe20
public class FileManager {
    protected static String[] files = {"canteens.txt", "libraries.txt", "lectureTheaters.txt"};
    protected static List<Facility> canteens;
    protected static List<Facility> libraries;
    protected static List<Facility> lectureTheaters;
    //logger
    private static final Logger LOGGER = Logger.getLogger(FileManager.class.getName());

    /**
     * File Manager
     */
    public FileManager() {
        this.canteens = new ArrayList<>();
        this.libraries = new ArrayList<>();
        this.lectureTheaters = new ArrayList<>();
        try {
            this.load();
        } catch (FileNotFoundException e) {
            LOGGER.warning(e.getMessage());
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(3);
        } catch (FileIsEmptyException e) {
            LOGGER.warning(e.getMessage());
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(3);
        }
    }

    public static List<Facility> getFacilities() {
        List<Facility> mergedFacilities = new ArrayList<>();
        mergedFacilities.addAll(canteens);
        mergedFacilities.addAll(libraries);
        mergedFacilities.addAll(lectureTheaters);

        return mergedFacilities;
    }



    public static List<Facility> getCanteens() {
        return canteens;
    }

    public static List<Facility> getLibraries() {
        return libraries; }

    public static List<Facility> getLectureTheaters() {
        return lectureTheaters;
    }

    /**
     * Load data
     * @throws FileNotFoundException
     * @throws FileIsEmptyException
     */
    @SuppressWarnings("checkstyle:Indentation")
    public void load() throws FileNotFoundException, FileIsEmptyException {
        int numOfFiles = files.length;
        Facility facility = new Canteen();

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = null;
        try {
            for (int i = 0; i < numOfFiles; i++) {

                inputStream = classLoader.getResourceAsStream(files[i]);
                if (inputStream == null) {
                    throw new FileNotFoundException("No data file is found!");
                }
                if (inputStream.read() == -1) {
                    throw new FileIsEmptyException("This file is empty, please add some data");
                }
                try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                    String data;
                    while ((data = br.readLine()) != null) {
                        if (!data.isEmpty()) {
                            String[] temp = data.split(",");
                            switch (i) {
                            case 0:
                                facility = new Canteen();
                                break;
                            case 1:
                                facility = new Library();
                                break;
                            case 2:
                                facility = new LectureTheater();
                                break;
                            default:
                                err.println("File can not be loaded");
                                System.exit(0);
                            }
                            facility.setFacilityID(temp[0]);
                            facility.setName(temp[1]);
                            facility.strToFacilityType(temp[2]);
                            facility.setLocation(temp[3], temp[4], temp[5], temp[6]);
                            switch (i) {
                            case 0:
                                canteens.add(facility);
                                break;
                            case 1:
                                libraries.add(facility);
                                break;
                            case 2:
                                lectureTheaters.add(facility);
                                break;
                            default:
                                err.println("Data can not be collected");
                                System.exit(0);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
