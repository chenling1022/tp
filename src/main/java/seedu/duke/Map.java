package seedu.duke;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import control.FileManager;
import control.FindInBuilding;
import control.FindNearest;
import control.SearchFacility;
import entity.Location;
import exceptions.BuildingNotFoundException;
import exceptions.FacilityNotFoundException;
import exceptions.InvalidCommandException;
import exceptions.InvalidSearchException;

//@@chenling
public class Map {
    private static FileManager dataController = new FileManager();
    private static Parser parser = new Parser();
    private static UI ui = new UI();
    private static SearchFacility searchFacility = new SearchFacility(dataController);
    private static FindInBuilding findInBuilding = new FindInBuilding(dataController);

    //logger
    private static final Logger LOGGER = Logger.getLogger(Map.class.getName());
    public static FileManager getDataController() {
        return dataController;
    }

    // Initialise logger configurations
    static {
        try {
            InputStream inputStream = Map.class.getClassLoader().getResourceAsStream("logger.properties");
            LogManager.getLogManager().readConfiguration(inputStream);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "An error occur when trying to read logger configuration file.", e);
        }
    }

    /**
     * Prints welcome message at initial run.
     */
    public static void show_welcome_msg() {
        System.out.println("Welcome to NTU Map \n");
        System.out.println("What would you like to do?");
    }

    /**
     * UI output for asking the user to input something again.
     */
    public static void prompt_input() {
        System.out.println("What else would you like to do?");
    }

    /**
     * Print out the exit message upon graceful exit.
     */
    public static void show_exit_msg() {
        System.out.println("Bye. Hope you don't have to use me again! "
                + "Otherwise you need to orientate about your own school better :)\n");
    }

    /**
     * Executes a given input string using the associated command.
     * @param input
     * @param c
     * @throws InvalidCommandException
     * @throws EmptyInputException
     * @throws FacilityNotFoundException
     * @throws InvalidSearchException
     * @throws BuildingNotFoundException
     */
    public static void executeCommand(String input, Command c)
            throws InvalidCommandException, EmptyInputException,
            FacilityNotFoundException, InvalidSearchException, BuildingNotFoundException {
        switch (c) {
        case LIST_ALL_LOCATIONS:
            String location = parser.getLocationsList(input);
            Feature.listAllLocations(location);
            break;
        case SEARCH:
            String facility = parser.getFacilitySearch(input);
            int id = parser.getIdSearch(input);
            searchFacility.query(facility, id);
            break;
        case SEARCH_IN:
            String buildingName = parser.getBuildingName(input);
            findInBuilding.findByBuildingName(buildingName);
            break;
        case FIND_FACILITY:
            String facilityLocation = parser.getFindFacilityLocation(input);
            if (Objects.nonNull(facilityLocation)) {
                FindNearest find = new FindNearest(dataController);
                Location currentLocation = find.findFacilityByName(facilityLocation).getLocation();
                String facilityType = parser.getFindFacilityType(input);
                if (Objects.nonNull(facilityType)) {
                    int topK = parser.getTopK(input);
                    if (topK != 0) {
                        find.findTopKFacility(currentLocation, facilityType, topK);
                    }
                }
            }
            break;
        case INVALID:
            throw new InvalidCommandException();
        default:
            break;
        }

    }

    /**
     * Main driver function
     * @param args
     */
    public static void main(String[] args) {
        show_welcome_msg();
        Command command;
        Scanner in = new Scanner(System.in);
        String userInput = ui.getString(in);

        while (!parser.isBye(userInput)) {
            if (parser.isList(userInput)) {
                command = Command.LIST_ALL_LOCATIONS;
            } else if (parser.isFind(userInput)) {
                command = Command.FIND_FACILITY;
            } else if (parser.isSearchIn(userInput)) {
                command = Command.SEARCH_IN;
            } else if (parser.isSearch(userInput)) {
                command = Command.SEARCH;
            } else {
                command = Command.INVALID;
            }

            try {
                executeCommand(userInput, command);
            } catch (InvalidCommandException e) {
                String errMsg = "OOPS!!! I'm sorry, but I don't know what that means :-(";
                System.out.println(errMsg);
                LOGGER.warning(errMsg);
            } catch (EmptyInputException e) {
                String errMsg = "OOPS!!! The command cannot be empty!";
                System.out.println(errMsg);
                LOGGER.warning(errMsg);
            } catch (FacilityNotFoundException e) {
                LOGGER.warning(e.getMessage());
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                LOGGER.warning(e.getMessage());
                System.out.println(e.getMessage());
            } catch (InvalidSearchException e) {
                LOGGER.warning(e.getMessage());
                System.out.println(e.getMessage());
            } catch (BuildingNotFoundException e) {
                LOGGER.warning(e.getMessage());
                System.out.println(e.getMessage());
            }
            //log user input
            LOGGER.log(Level.INFO, String.format("User input: %s", userInput));
            prompt_input();
            userInput = ui.getString(in);
        }
        show_exit_msg();
    }

}
