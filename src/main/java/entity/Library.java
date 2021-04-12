package entity;

//@@author zhangyongzhe20
public class Library extends Facility {
    public Library(int facilityID, String name, Location location, Location address) {
        super(facilityID, name, location, address, entity.FacilityType.LIBRARY);
    }

    public Library() {

    }

    public Library(int facilityID, String name, Location location) {
        super(facilityID, name, location, location, entity.FacilityType.LIBRARY);
    }
}
