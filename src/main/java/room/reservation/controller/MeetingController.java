package room.reservation.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import room.reservation.repository.*;
import room.reservation.serviceInterface.*;
import room.reservation.entities.*;
@RestController
public class MeetingController {
	
	@Autowired
    MeetingInterface meetingService;
	

	@Autowired
    MeetingRepository meetingRepository;
	
	@Autowired
    EquipmentRepository equipmentRepository;
	
	
    @PostMapping("/addmeeting")
    public String addMeeting(@RequestBody Meeting meeting){
    	meetingService.addMeeting(meeting);
         return "Added successfully";
    }
    
    
    @PostMapping("/save-equipment-to-meeting")
    public String SaveEquipmentToMeeting(@RequestBody Map<String, Long> map){
    	
    	//Find the meeting already stored in database 
    	Optional < Meeting > meetinfToFind = meetingRepository.findById(map.get("meetingid"));
    	
    	//Find the equipment already stored in database 
    	Optional < Equipment > equipmentfToFind = equipmentRepository.findById(map.get("equipmentid"));
    	Equipment equipmentFound = new Equipment();
    	
    	// if meeting found
    	Meeting meetingFound = new Meeting();
    	
    	if (meetinfToFind.isPresent()) {
    		meetingFound= meetinfToFind.get();
    		// if equipment found :
    		
    		if(equipmentfToFind.isPresent()) {
    			equipmentFound= equipmentfToFind.get();
    			meetingFound.getEquipments().add(equipmentFound);
    			meetingRepository.save(meetingFound);
    		}
    		else {
    			 System.out.printf("No equipment found");
    		}
    		
    		
			
        } else {
            System.out.printf("No meeting found");
        }
		
        return "Added successfully";
    }
}
