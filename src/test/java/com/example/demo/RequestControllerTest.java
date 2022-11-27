package com.example.demo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import room.reservation.controller.RequestController;
import room.reservation.entities.Meeting;
import room.reservation.entities.Request;
import room.reservation.entities.RequestToSend;
import room.reservation.repository.MeetingRepository;



@WebMvcTest(controllers = RequestController.class)
public class RequestControllerTest {

	@Autowired
	private MockMvc mockMvc;   // this instance is to simulate HTTP requests.
	
	@Autowired
	private ObjectMapper objectMapper;   // to map to and from JSON
	
	@MockBean
	private RequestController requestController;
	
	@Test
	void AddMeetingTest() throws Exception {
		RequestToSend requestToSend = new RequestToSend();

		java.sql.Date date = new java.sql.Date(2022-11-27);
		requestToSend.setDate(date);
		requestToSend.setStartingHour(9);
		requestToSend.setEndingHour(10);
		requestToSend.setNbrPersons(12);
		requestToSend.setMeetingid((long)1);
		
		when(requestController.SaveRequest(requestToSend)).thenReturn("Added successfully");
		mockMvc.perform(post("/save-request")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(requestToSend))).andDo(print())
		        .andExpect(status().isOk());

	}
	
	@Test
	void FindRoomForRequest() throws Exception{
		Request request = new Request();
		
		request.setId((long) 1);
		java.sql.Date date = new java.sql.Date(2022-11-27);
		request.setDate(date);
		request.setStartingHour(9);
		request.setEndingHour(10);
		request.setNbrPersons(12);
		
		Meeting meeting = new Meeting();
		meeting.setId((long)3);
		meeting.setType("RC");
		request.setMeeting(meeting);
		
		when(requestController.FindRoomForRequest(request)).thenReturn("the best room has been found");
		mockMvc.perform(get("/FindRoom")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(request))).andDo(print())
		        .andExpect(status().isOk());
		
		
	}
}
