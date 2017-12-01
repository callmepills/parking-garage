package org.callmepills.parkinggarage;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(GarageController.class)
public class GarageControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private GarageRepository garageRepository;

	@Test
	public void postGaragesShouldReturnCreated() throws Exception {
		mvc.perform(post("/garages")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{\"name\": \"name\"}"))
				.andExpect(status().isCreated());
		verify(garageRepository).save(any(Garage.class));
	}

	@Test
	public void getGarageShouldReturnOk() throws Exception {
		String name = "name";
		Garage garage = new Garage();
		garage.setName(name);
		when(garageRepository.findOne(name)).thenReturn(garage);
		mvc.perform(get("/garages/{name}", name))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is(name)));
	}

	@Test
	public void deleteGarageShouldReturnNoContent() throws Exception {
		String name = "name";
		mvc.perform(delete("/garages/{name}", name))
				.andExpect(status().isNoContent());
		verify(garageRepository).delete(name);
	}
}
