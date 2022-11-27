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
public class ReservationController {
	
	@Autowired
    MeetingInterface meetingService;
	
	@Autowired
    RequestInterface requestInterface;
	
	@Autowired
    EquipmentRepository equipmentRepository;
	
	@Autowired
    RoomRespository roomRepository;
	
	@Autowired
    RequestRepository requestRepository;

	
	@GetMapping("/FindRoom")
    public String FindRoomForRequest(@RequestBody Request request){

		// well contain the meeting type
		Meeting meetingType = new Meeting();
		
		// well contain the request already saved in the DB
		Request OfficialRequest = new Request();
		
		// Find the request already saved in the DB
		Optional < Request > lastRequest = requestInterface.findById(request.getId());
		
		// if the request found
		if (lastRequest.isPresent()) {
			OfficialRequest = lastRequest.get();
			
			//find the type of meeting associated to this request 
			meetingType = lastRequest.get().getMeeting();
			
        } else {
            System.out.printf("No request found ");
        }
		
		// Find the list of equipments needed for this type of meeting
		List<Equipment> equipmentsNeeded = equipmentRepository.findEquipmentsByMeetingsId(meetingType.getId());
		
		// this list will have all rooms with those equipments
		List<Room> RoomsWithEquipments = new ArrayList<>();
		
		// for every equipment in the needed equipments list, we will find rooms with this specific equipment
		
		
		if (!equipmentsNeeded.isEmpty()) {
			
			for(Equipment equipment : equipmentsNeeded) {
				
				// search for the rooms by equipment id 
				List<Room> roomsNeeded = roomRepository.findRoomsByEquipmentsId(equipment.getId());
				
				for(Room r :roomsNeeded) {
					// only keep rooms with ==> capacity*70 /100 >= number of persons
					if((r.getNbrplaces()*70/100)>= OfficialRequest.getNbrPersons()){
						RoomsWithEquipments.add(r);
					}
				}
				
				// add rooms found to the list
				
				
			}
		}
		else {
			// if there is no needed equipment, we will store all rooms that have capacity > 3
			List<Room> roomsNeeded = roomRepository.findAll();
			for(Room r :roomsNeeded) {
				// only keep rooms with ==> capacity > 3
				if(r.getNbrplaces() >= 3){
					RoomsWithEquipments.add(r);
				}
			}
			
		}
		
		
		
		

		// we will calculate the score for each room 
		// if for a meeting we need both Ecran and Tableau
		// A room that contain (Ecran + Tableau ) will have score = 2
		// And A room that contain (Ecran) will have score = 1
		// and we will sort the list to be like [1=2, 2=1] ==> room with id 1 have score 2
		Map<Long, Long> idFrequency = RoomsWithEquipments.stream().collect(Collectors.groupingBy(Room::getId,Collectors.counting()));
		Set<Entry<Long, Long>> set = idFrequency.entrySet();
		List<Entry<Long, Long>> list = new ArrayList<Entry<Long, Long>>(set);
		Collections.sort( list, new Comparator<Map.Entry<Long, Long>>()
		{
	        public int compare( Map.Entry<Long, Long> o1, Map.Entry<Long, Long> o2 )
	        {
	            return (o2.getValue()).compareTo( o1.getValue() );//Descending order
	        }
	    } );
		
		System.out.println(list);
		int count =0;
		// check availability for each room
		for (Entry<Long, Long> map : list) {
			
			// get the room id
		    Long id = map.getKey();
		    
		    // find the room already stored in the DB
		    Room ChosenRoom = new Room();
			Optional < Room > TheChosenRoom = roomRepository.findById(id);
			
			if (TheChosenRoom.isPresent()) {
				ChosenRoom = TheChosenRoom.get();
				
	        } else {
	            System.out.printf("No room found with id %d%n", id);
	        }
			
			// check if there is reservations in this room at the same time wanted in the request
		    List<Request> requestWithSameRoom = requestRepository.findRequestsByRoomsId(id);
		    System.out.println(requestWithSameRoom);
		   
		    // if there is reservations :
		    if(!requestWithSameRoom.isEmpty()) {
		    	// for each reservation :
		    	 for (Request Requestwithsameroom : requestWithSameRoom)
		       {
		    		// if the room isn't available in this time, we will increment count  
					if((Requestwithsameroom.getDate().equals(OfficialRequest.getDate()) && 
							Requestwithsameroom.getStartingHour()<OfficialRequest.getStartingHour() && 
							OfficialRequest.getStartingHour()<Requestwithsameroom.getEndingHour() )
							|| (Requestwithsameroom.getDate().equals(OfficialRequest.getDate()) && 
									Requestwithsameroom.getEndingHour()>OfficialRequest.getEndingHour() && 
									OfficialRequest.getEndingHour()>Requestwithsameroom.getStartingHour() )
							||(Requestwithsameroom.getDate().equals(OfficialRequest.getDate()) && 
									Requestwithsameroom.getEndingHour()==OfficialRequest.getEndingHour() && 
									Requestwithsameroom.getStartingHour()==OfficialRequest.getStartingHour()  )
							 ) {
						count++;
						
					}
			
				
		       }

			   if(count!=0) {
				    // if the count isn't equal to 0 , that means this room isn't available in this time so we will go to check the other room
			    	System.out.println("impossible de réserver cette salle à cet heure "+id);
			    	continue;
			   }
			   else {
				   // if this room is available so we will use it for the reservation
				   System.out.println("haaha possible de réserver cette salle à cet heure "+id);
				   ChosenRoom.getRequests().add(OfficialRequest);
				   roomRepository.save(ChosenRoom);
				   break;
			   }
		    	
		    }
		   
	  
		    else {
		    	// if this room is available so we will use it for the reservation
		    	System.out.println("LALA posible de réserver cette salle à cet heure "+id);
		    	
		    	
		    	//save the chosen room with the request
		    	ChosenRoom.getRequests().add(OfficialRequest);
				roomRepository.save(ChosenRoom);
		    	
		    	break;
		    }
		    
		   
		   
		}
		
         return "";
    }
}



