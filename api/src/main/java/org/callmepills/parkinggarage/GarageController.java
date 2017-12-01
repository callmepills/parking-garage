package org.callmepills.parkinggarage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/garages")
public class GarageController {
	
	@Autowired
	private GarageRepository garageRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createGarage(@RequestBody Garage garage) {
		garageRepository.save(garage);
	}

	@GetMapping("/{name}")
	public @ResponseBody Garage getGarage(@PathVariable("name") String name) {
		return garageRepository.findOne(name);
	}
	
	@DeleteMapping("/{name}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteGarage(@PathVariable("name") String name) {
		garageRepository.delete(name);
	}
}
