package springcloud.dispatchservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;

@Repository
@FeignClient(value = "location-service")
public interface LocationServiceFeignClient extends LocationService {}
