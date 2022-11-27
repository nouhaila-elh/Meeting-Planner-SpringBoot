package room.reservation.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import room.reservation.repository.*;
import room.reservation.serviceInterface.*;
import room.reservation.entities.*;

@RestController
public class RequestController {
	@Autowired
    RequestInterface requestInterface;
	
	@Autowired
    RequestRepository requestRepository;
	
	@Autowired
    MeetingRepository meetingRepository;
	
	@PostMapping("/save-request")
    public String addMeeting(@RequestBody RequestToSend requestToSend){
		// get the meeting id from the request
		Long meetingid = requestToSend.getMeetingid();
		
		// find the meeting object by id
		Optional < Meeting > meetinfToFind = meetingRepository.findById(meetingid);
		
		// if the meeting found 
		Meeting meetingFound = new Meeting();
		if (meetinfToFind.isPresent()) {
			meetingFound= meetinfToFind.get();
			
			// prepare the request to save 
			Request requestToSave = new Request();
			requestToSave.setDate(requestToSend.getDate());
			requestToSave.setMeeting(meetingFound);
			requestToSave.setStartingHour(requestToSend.getStartingHour());
			requestToSave.setEndingHour(requestToSend.getStartingHour()+2);
			requestToSave.setNbrPersons(requestToSend.getNbrPersons());
			
			// save the request 
			
			requestRepository.save(requestToSave);

			
        } else {
            System.out.printf("No meeting found");
        }
		
		return "Saved successufully";
    }
}
