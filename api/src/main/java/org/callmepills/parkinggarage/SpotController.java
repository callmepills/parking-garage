package org.callmepills.parkinggarage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/spots")
public class SpotController {

	@Autowired
	private SpotRepository spotRepository;

	@GetMapping
	public @ResponseBody List<Spot> findSpots(@RequestParam("garage") String garage) {
		return spotRepository.findByGarage(garage);
	}

	@PostMapping
	public @ResponseBody Spot createSpot(@RequestBody Spot spot) {
		return spotRepository.save(spot);
	}

	@PostMapping("/{id}")
	public @ResponseBody Spot updateSpot(
			@PathVariable("id") Long id,
			@RequestBody Spot updatedSpot) {
		Spot spot = spotRepository.findOne(id);
		spot.setText(updatedSpot.getText());
		spot.setLikes(updatedSpot.getLikes());
		return spotRepository.save(spot);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSpot(@PathVariable("id") long id) {
		spotRepository.delete(id);
	}
}
