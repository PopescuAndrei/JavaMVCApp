package ro.teamnet.zth.app.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.ParameterAnnotation;
import ro.teamnet.zth.app.domain.Location;
import ro.teamnet.zth.app.service.LocationServiceImpl;

import java.util.List;

/**
 * Created by Andrei on 5/6/2015.
 */
@MyController(urlPath = "/locations")
public class LocationsController {

    @MyRequestMethod(urlPath = "/all", methodType = "GET")
    public List<Location> getAllLocations(){
        LocationServiceImpl locationService = new LocationServiceImpl();
        return locationService.findAllLocations();
    }

    @MyRequestMethod(urlPath = "/one", methodType = "GET")
    public Location getOneLocation(@ParameterAnnotation(parameterName = "locationId")String locationId){
        LocationServiceImpl locationService = new LocationServiceImpl();
        return locationService.getLocation(Integer.parseInt(locationId));
    }
}
