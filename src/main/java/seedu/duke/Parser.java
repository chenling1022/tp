package seedu.duke;

import exceptions.InvalidSearchException;

//@@chenling
public class Parser {

    public static String getFacilitySearch(String userInput) throws InvalidSearchException {
        if (userInput.length() < 7) {
            throw new InvalidSearchException("No parameters provided for search function :(");
        }
        int index = userInput.indexOf('/');
        if (index == -1) {
            throw new InvalidSearchException(
                    "Invalid syntax for search!!! It must be: \"search facilityType/facilityId\"");
        }
        String facility = userInput.substring(7, index);
        return facility;
    }

    public static int getIdSearch(String userInput) throws InvalidSearchException {
        int index = userInput.indexOf('/');
        if (index == -1) {
            throw new InvalidSearchException(
                    "Invalid syntax for search!!! It must be: \"search facilityType/facilityId\"");
        }
        String searchIdString = userInput.substring(index + 1);
        try {
            return Integer.parseInt(searchIdString);
        } catch (NumberFormatException exception) {
            throw new InvalidSearchException(String.format("facilityId provided to search must be an integer! "
                    + "\"%s\" is not a valid integer.", searchIdString));
        }

    }

    public static String getBuildingName(String userInput) {
        return userInput.substring(9).strip();
    }

    public static String getFindFacilityLocation(String userInput) {
        try {
            int index1 = userInput.indexOf('<');
            int index2 = userInput.indexOf('>');
            String location = userInput.substring(index1 + 1, index2);
            return location;
        } catch (NumberFormatException e) {
            String errMsg = "please try again! remember to input location name, "
                    + "the facility name that you want to look for and the number of "
                    + "nearest facilities you are searching for \nFor example:findFacility<library1><Canteen><2> ";
            System.out.println(errMsg);
        } catch (StringIndexOutOfBoundsException e) {
            String errMsg = "please try again! remember to input location name, "
                    + "the facility name that you want to look for and the "
                    + "number of nearest facilities you are searching for "
                    + "\nFor example:findFacility<library1><Canteen><2> ";
            System.out.println(errMsg);
        }
        return null;

    }

    public static String getFindFacilityType(String userInput) {
        try {
            int index1 = userInput.indexOf('>');
            String substring = userInput.substring(index1 + 1);
            int index2 = substring.indexOf('<');
            int index3 = substring.indexOf('>');
            String facilityType = substring.substring(index2 + 1, index3);
            return facilityType;
        } catch (NumberFormatException e) {
            String errMsg = "please try again! remember to input location name, the facility name "
                    + "that you want to look for and the number of nearest facilities you "
                    + "are searching for \nFor example:findFacility<library1><Canteen><2> ";
            System.out.println(errMsg);
        } catch (StringIndexOutOfBoundsException e) {
            String errMsg = "please try again! remember to input location name, the facility name "
                    + "that you want to look for and the number of nearest "
                    + "facilities you are searching for \nFor example:findFacility<library1><Canteen><2> ";
            System.out.println(errMsg);
        }
        return null;
    }

    public static int getTopK(String userInput) {
        try {
            int index1 = userInput.indexOf('>');
            String substring1 = userInput.substring(index1 + 1);
            int index2 = substring1.indexOf('>');
            String substring2 = substring1.substring(index2 + 1);
            int index3 = substring2.indexOf('<');
            int index4 = substring2.indexOf('>');
            String stringTopK = substring2.substring(index3 + 1, index4);
            int topK = Integer.parseInt(stringTopK);
            if (topK <= 0) {
                System.out.println("Please input a positive integer!");
            }
            return topK;
        } catch (NumberFormatException e) {
            String errMsg = "please try again! remember to input location name, the facility name "
                   + "that you want to look for and the number of nearest facilities you are searching"
                   + " for \nFor example:findFacility<library1><Canteen><2> ";
            System.out.println(errMsg);
        } catch (StringIndexOutOfBoundsException e) {
            String errMsg = "please try again! remember to input location name, the facility name "
                    + "that you want to look for and the number of nearest facilities "
                    + "you are searching for \nFor example:findFacility<library1><Canteen><2> ";
            System.out.println(errMsg);
        }
        return 0;
    }

    public static String getLocationsList(String userInput) {
        try {
            int index1 = userInput.indexOf('<');
            int index2 = userInput.indexOf('>');
            String location = userInput.substring(index1 + 1, index2);
            return location;
        } catch (StringIndexOutOfBoundsException e) {
            String errMsg = "please try again! remember to input the facility name in <>. "
                    + "\nFor example: listAllLocations<Canteen>";
            System.out.println(errMsg);
        }
        return null;
    }

    /**
     * Check if the command to execute is to listAllLocations
     * @param userInput
     * @return
     */
    public static boolean isList(String userInput) {
        if (userInput.length() >= 16) {
            return userInput.startsWith("listAllLocations");
        }
        return false;
    }

    /**
     * Check if the command to execute is to search for a specific facility
     * @param userInput
     * @return
     */
    public static boolean isSearch(String userInput) {
        if (userInput.length() >= 6) {
            return userInput.startsWith("search");
        }
        return false;
    }

    /**
     * Check if the command to execute is to search within a building
     * @param userInput
     * @return
     */
    public static boolean isSearchIn(String userInput) {
        if (userInput.length() >= 9) {
            return userInput.startsWith("search in");
        }
        return false;
    }

    /**
     * Check if the command to execute is to find the top K facilities
     * @param userInput
     * @return
     */
    public static boolean isFind(String userInput) {
        if (userInput.length() >= 12) {
            return userInput.startsWith("findFacility");
        }
        return false;
    }

    public static boolean isBye(String userInput) {
        return userInput.equalsIgnoreCase("bye");
    }


}
