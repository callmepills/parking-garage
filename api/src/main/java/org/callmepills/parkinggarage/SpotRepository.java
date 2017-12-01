package org.callmepills.parkinggarage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SpotRepository extends CrudRepository<Spot, Long> {

	List<Spot> findByGarage(String garage);
}
