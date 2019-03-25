package springcloud.accountservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {
  @Autowired
  private DriverRepository driverRepository;
  @RequestMapping(value = "/drivers", method = RequestMethod.POST)
  public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {

  }
}
