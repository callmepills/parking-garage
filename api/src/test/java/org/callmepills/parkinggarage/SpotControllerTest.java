package org.callmepills.parkinggarage;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(SpotController.class)
public class SpotControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private SpotRepository spotRepository;

	@Test
	public void findSpotsShouldReturnOk() throws Exception {
		String garage = "garage";
		List<Spot> spots = new ArrayList<>();
		spots.add(new Spot());
		spots.add(new Spot());
		spots.add(new Spot());
		spots.add(new Spot());
		spots.add(new Spot());
		when(spotRepository.findByGarage(garage)).thenReturn(spots);
		mvc.perform(get("/spots")
				.param("garage", "garage"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", is(5)));
	}

	@Test
	public void createSpotShouldReturnOk() throws Exception {
		Spot spot = newSpot(null, "text", 0);
		Spot savedSpot = newSpot(1L, "text", 0);
		when(spotRepository.save(spot)).thenReturn(savedSpot);
		mvc.perform(post("/spots")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\"text\": \"text\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.text", is("text")))
				.andExpect(jsonPath("$.likes", is(0)));
	}

	@Test
	public void updateSpotShouldReturnOk() throws Exception {
		long id = 1L;
		Spot spot = newSpot(1L, "text", 2);
		Spot updatedSpot = newSpot(1L, "updatedText", 3);
		when(spotRepository.findOne(id)).thenReturn(spot);
		when(spotRepository.save(updatedSpot)).thenReturn(updatedSpot);
		mvc.perform(post("/spots/{id}", id)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\"text\": \"updatedText\", \"likes\": 3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.text", is("updatedText")))
				.andExpect(jsonPath("$.likes", is(3)));
	}

	@Test
	public void deleteSpotShouldReturnNoContent() throws Exception {
		long id = 1L;
		mvc.perform(delete("/spots/{id}", id))
				.andExpect(status().isNoContent());
		verify(spotRepository).delete(id);
	}

	private Spot newSpot(Long id, String text, int likes) {
		Spot spot = new Spot();
		spot.setId(id);
		spot.setText(text);
		spot.setLikes(likes);
		return spot;
	}
}
