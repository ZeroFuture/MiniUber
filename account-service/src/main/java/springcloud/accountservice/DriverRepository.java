package springcloud.accountservice;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Long> {
  List<Driver> findByFirstName(String firstName);
  List<Driver> findByLastName(String lastName);
  List<Driver> findByFirstNameAndLastName(String firstName, String lastName);
}
