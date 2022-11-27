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

import room.reservation.entities.Meeting;



@WebMvcTest(controllers = MeetingController.class) // to start an application context that contains only the beans needed for testing a web controller
public class MeetingControllerTest {				// All other beans we might need have to be included separately or mocked away with @MockBean.
	
	@Autowired
	private MockMvc mockMvc;   // this instance is to simulate HTTP requests.
	
	@Autowired
	private ObjectMapper objectMapper;   // to map to and from JSON
	
	@MockBean
	private MeetingController meetingController;
	
	


	
	@Test
	void AddMeetingTest() throws Exception {
		Meeting meeting = new Meeting();

		meeting.setId((long) 5);
		meeting.setType("SPEC");
		
		when(meetingController.addMeeting(meeting)).thenReturn("Added successfully");
		mockMvc.perform(post("/addmeeting")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(meeting))).andDo(print())
		        .andExpect(status().isOk());

	}
	
	@Test
	void SaveEquipmentToMeetingTest() throws Exception {
		
		Map<String, Long> map = new HashMap<>();
		 map.put("meetingid", (long) 3);
		 map.put("equipmentid", (long) 2);
		
		when(meetingController.SaveEquipmentToMeeting(map)).thenReturn("Added successfully");
		mockMvc.perform(post("/save-equipment-to-meeting")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(map))).andDo(print())
		        .andExpect(status().isOk());
	}
}
