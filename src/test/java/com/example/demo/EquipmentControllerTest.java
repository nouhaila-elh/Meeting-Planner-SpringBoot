package com.example.demo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import room.reservation.controller.EquipmentController;
import room.reservation.controller.MeetingController;
import room.reservation.entities.Equipment;
import room.reservation.entities.Meeting;

@WebMvcTest(controllers = EquipmentController.class) 
public class EquipmentControllerTest {
	@Autowired
	private MockMvc mockMvc;   // this instance is to simulate HTTP requests.
	
	@Autowired
	private ObjectMapper objectMapper;   // to map to and from JSON
	
	@MockBean
	private EquipmentController equipmentController;
	
	


	
	@Test
	void AddEquipment() throws Exception {
		Equipment equipment = new Equipment();

		
		equipment.setName("Ecran");
		
		mockMvc.perform(post("/addequipment")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(equipment))).andDo(print())
		        .andExpect(status().isOk());
	}
}
