package ro.teamnet.zth.app.service;

import ro.teamnet.zth.app.domain.Employee;
import ro.teamnet.zth.app.domain.Location;

import java.util.List;

/**
 * Created by Andrei on 5/7/2015.
 */
public interface LocationService {
    public List<Location> findAllLocations();
    public Location getLocation(int locationId);
}
