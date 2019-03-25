package springcloud.locationservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class LocationServiceController {
  private final AtomicLong counter = new AtomicLong();
  private Random rand = new Random();
  private HashMap<Long, DriverLocation> locations = new HashMap<>();

  @RequestMapping(value = "/drivers/{id}/locations", method = RequestMethod.POST)
  public ResponseEntity<Location> createLocation(@PathVariable("id") long id,
                                         @RequestBody(required = false) Location newLocation) {
    Location location;
    if (newLocation == null) {
      int randLatitude = rand.nextInt(90);
      int randLongitude = rand.nextInt(180);
      location = new Location((double) randLatitude, (double) randLongitude);
    }
    else {
      location = new Location(newLocation.getLatitude(), newLocation.getLongitude());
    }
    locations.putIfAbsent(id, new DriverLocation());
    locations.get(id).addLocation(location);
    return new ResponseEntity<>(location, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/drivers/{id}/locations", method = RequestMethod.GET)
  public ResponseEntity<List<Location>> getAllLocations(@PathVariable("id") long id) {
    if (!DriverController.isDriverValid(id)) {
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    if (!locations.containsKey(id)) {
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
    return new ResponseEntity<>(locations.get(id).getAllLocations(), HttpStatus.OK);
  }

  @RequestMapping(value = {"/drivers/{id}/locations/{locationId}"}, method = RequestMethod.GET)
  public ResponseEntity<Location> getLocation(@PathVariable("id") long id,
                                              @PathVariable("locationId") long locationId) {
    if (!locations.containsKey(id)) {
      return new ResponseEntity<>((Location) null, HttpStatus.BAD_REQUEST);
    }
    DriverLocation driverLocation = locations.get(id);
    Location location = driverLocation.getLocation(locationId);
    if (location == null) {
      return new ResponseEntity<>((Location) null, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(location, HttpStatus.OK);
  }

  @RequestMapping(value = {"/drivers/{id}/locations/current"}, method = RequestMethod.GET)
  public ResponseEntity<Location> getCurrentLocation(@PathVariable("id") long id) {
    if (!locations.containsKey(id)) {
      return new ResponseEntity<>((Location) null, HttpStatus.BAD_REQUEST);
    }
    DriverLocation driverLocation = locations.get(id);
    return new ResponseEntity<>(driverLocation.getLastLocation(), HttpStatus.OK);
  }

  @RequestMapping(value = {"/drivers/{id}/locations/{locationId}"}, method = RequestMethod.PUT)
  public ResponseEntity<Location> updateLocation(@RequestBody Location location,
                                                 @PathVariable("id") long id,
                                                 @PathVariable("locationId") long locationId) {
    if (!locations.containsKey(id)) {
      return new ResponseEntity<>((Location) null, HttpStatus.BAD_REQUEST);
    }
    DriverLocation driverLocation = locations.get(id);
    if (driverLocation.updateLocation(locationId, location)) {
      return new ResponseEntity<>(driverLocation.getLocation(locationId), HttpStatus.OK);
    }
    return new ResponseEntity<>((Location) null, HttpStatus.BAD_REQUEST);
  }

  @RequestMapping(value = "/drivers/{id}/locations/{locationId}", method = RequestMethod.DELETE)
  public ResponseEntity<Location> deleteLocation(@PathVariable("id") long id,
                                                 @PathVariable("locationId") long locationId) {
    return deleteHelper(id, locationId);
  }

  @RequestMapping(value = "/drivers/{id}/locations/{locationId}", method = RequestMethod.POST)
  public ResponseEntity<Location> deleteLocationByPOST(@PathVariable("id") long id,
                                                 @PathVariable("locationId") long locationId) {
    return deleteHelper(id, locationId);
  }

  private ResponseEntity<Location> deleteHelper(long id, long locationId) {
    if (!locations.containsKey(id)) {
      return new ResponseEntity<>((Location) null, HttpStatus.BAD_REQUEST);
    }
    DriverLocation driverLocation = locations.get(id);
    if (driverLocation.deleteLocation(locationId)) {
      return new ResponseEntity<>((Location) null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>((Location) null, HttpStatus.BAD_REQUEST);
  }
}
