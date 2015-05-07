package ro.teamnet.zth.app.service;

import ro.teamnet.zth.app.dao.LocationDao;
import ro.teamnet.zth.app.domain.Location;

import java.util.List;

/**
 * Created by Andrei on 5/7/2015.
 */
public class LocationServiceImpl implements LocationService{
    @Override
    public List<Location> findAllLocations() {
        LocationDao dao = new LocationDao();
        List<Location> locations = dao.getAllLocations();
        return locations;
    }

    @Override
    public Location getLocation(int locationId) {
        return new LocationDao().getLocationById(locationId);
    }
}
