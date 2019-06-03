package com.danielturato.homeautomationapi;

import com.danielturato.homeautomationapi.device.Device;
import com.danielturato.homeautomationapi.room.Room;
import com.danielturato.homeautomationapi.user.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeAutomationApiApplicationTests extends BaseTest {

	@Test
	@WithMockUser
	public void adminsCanOnlyCreateRooms() throws Exception {
		Room room = new Room("test", 100);
		mockMvc.perform(post("/api/v1/rooms")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.content(mapToJson(room)))
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(authorities = {"ROLE_ADMIN"})
	public void adminsCanCreateRooms() throws Exception {
		Room room = new Room("ZZZZ", 500);
		mockMvc.perform(post("/api/v1/rooms")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.content(mapToJson(room)))
				.andExpect(status().isCreated());
	}

	@Test
	@WithMockUser
	public void queryRoomByArea() throws Exception {

		mockMvc.perform(get("/api/v1/rooms/search/areaIsBelow?area=200")
						.accept(MediaTypes.HAL_JSON))
				.andExpect(content().json("{'page': {'totalElements': 1}}"));


	}

	@Test
	@WithMockUser
	public void queryRoomByName() throws Exception {

		mockMvc.perform(get("/api/v1/rooms/search/containsName?name=kit")
					.accept(MediaTypes.HAL_JSON))
				.andExpect(content().json("{'page': {'totalElements': 1}}"));
	}

	@Test
	@WithMockUser
	public void onlyRoomAdminCanEditDevice() throws Exception {

		// Change name of device & try update it from a non ROOM-ADMIN
		mockMvc.perform(put("/api/v1/devices/4")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaTypes.HAL_JSON)
					.content(mapToJson(new Name("name")))).andExpect(status().isForbidden());

	}

	@Test
	@WithMockUser
	public void onlyRoomAdminCanEditControl() throws Exception {

		// Change name of control & try update it from a non ROOM-ADMIN
		mockMvc.perform(put("/api/v1/controls/5")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.content(mapToJson(new Name("name")))).andExpect(status().isForbidden());

	}

	@Test
	@WithMockUser(username = "admin", password = "password", authorities = {"ROLE_ADMIN", "ROLE_USER"})
	public void roomAdminCanEditDevice() throws Exception {

		// Change name of device & try update it from a ROOM-ADMIN
		mockMvc.perform(put("/api/v1/devices/4")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.content(mapToJson(new Name("name")))).andExpect(status().isOk());

	}

	@Test
	@WithMockUser(username = "admin", password = "password", authorities = {"ROLE_ADMIN", "ROLE_USER"})
	public void roomAdminCanEditControl() throws Exception {

		// Change name of control & try update it from a ROOM-ADMIN
		mockMvc.perform(put("/api/v1/controls/5")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaTypes.HAL_JSON)
				.content(mapToJson(new Name("name")))).andExpect(status().isOk());

	}

	private class Name {
		String name;

		public Name(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}

