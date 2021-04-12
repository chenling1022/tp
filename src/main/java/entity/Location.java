package entity;

//@@author zhangyongzhe20
public class Location {
    private static final double MILE_UNIT = 1.15077945;
    private double longitude;
    private double latitude;
    private String address;
    private String building;

    /**
     * Create and initialize a point with given, (latitude, longitude) specified in degrees
     * @param latitude
     * @param longitude
     * @param address
     * @param building
     */
    public Location(double latitude, double longitude, String address, String building) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.building = building;
    }
    /**
     * Return distance between this location and that location measured in statute miles
     * @param that
     * @return
     */
    public double distanceTo(Location that) {
        double lat1 = Math.toRadians(this.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lat2 = Math.toRadians(that.latitude);
        double lon2 = Math.toRadians(that.longitude);

        // great circle distance in radians, using law of cosines formula
        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        // each degree on a great circle of Earth is 60 nautical miles
        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = MILE_UNIT * nauticalMiles;
        return statuteMiles;
    }

    // return string representation of this point
    public String toString() {
        return " (" + latitude + ", " + longitude + ", " + address + ")";
    }

    public String getAddress() {
        return this.address; }

    public String getBuilding() {
        return this.building;
    }
}
