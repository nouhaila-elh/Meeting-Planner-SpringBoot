package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import room.reservation.controller.MeetingController;
import room.reservation.controller.RoomController;
import room.reservation.entities.Meeting;
import room.reservation.entities.Room;



@WebMvcTest(controllers = RoomController.class) 
public class RoomControllerTest {
	@Autowired
	private MockMvc mockMvc;   // this instance is to simulate HTTP requests.
	
	@Autowired
	private ObjectMapper objectMapper;   // to map to and from JSON
	
	@MockBean
	private RoomController roomController;
	
	@Test
	void AddRoomTest() throws Exception {
		Room room = new Room();

		room.setId((long) 5);
		room.setName("E0002");
		room.setNbrplaces(23);
		
		when(roomController.addRoom(room)).thenReturn("Added successfully");
		mockMvc.perform(post("/addroom")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(room))).andDo(print())
		        .andExpect(status().isOk());

	}
	
	@Test
	void SaveEquipmentToRoomTest() throws Exception {
		
		Map<String, Long> map = new HashMap<>();
		 map.put("roomid", (long) 3);
		 map.put("equipmentid", (long) 2);
		
		when(roomController.SaveEquipmentToRoom(map)).thenReturn("Added successfully");
		mockMvc.perform(post("/save-equipment-to-room")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(map))).andDo(print())
		        .andExpect(status().isOk());
	}
}
