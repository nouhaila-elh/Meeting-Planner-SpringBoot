package com.example.demo;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import room.reservation.entities.Equipment;
import room.reservation.entities.Meeting;
import room.reservation.entities.Request;
import room.reservation.entities.RequestToSend;
import room.reservation.entities.Room;
import room.reservation.repository.EquipmentRepository;
import room.reservation.repository.MeetingRepository;
import room.reservation.repository.RequestRepository;
import room.reservation.repository.RoomRespository;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
public class JPAUnitTest {
	//@Autowired
	//private TestEntityManager entityManager;
	
	@Autowired
	MeetingRepository meetingRepository;
	
	@Autowired
	EquipmentRepository equipmentRepository;
	
	@Autowired
	RoomRespository roomRepository;
	
	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	// -------------- Testing save meeting --------------
	@Test
	public void save_a_meeting() {
		
		Meeting newMeeting = new Meeting();
		newMeeting.setId((long) 5);
		newMeeting.setType("SPEC");
		
		Meeting meeting = meetingRepository.save(newMeeting);

		assertThat(meeting).hasFieldOrPropertyWithValue("type", "SPEC");
	}
	
	
	// -------------- Testing save equipment --------------
	@Test
	public void save_equipment() {
		
		Equipment newEquipment = new Equipment();
		newEquipment.setId((long) 5);
		newEquipment.setName("Ecran");
		
		Equipment equipment = equipmentRepository.save(newEquipment);

		assertThat(equipment).hasFieldOrPropertyWithValue("name", "Ecran");
	}
	
	// -------------- Testing save room --------------
	@Test
	public void save_room() {
		
		Room newRoom = new Room();
		newRoom.setId((long) 5);
		newRoom.setName("E002");
		newRoom.setNbrplaces(4);
		
		
		Room room = roomRepository.save(newRoom);

		assertThat(room).hasFieldOrPropertyWithValue("name", "E002");
	}
	
	
	// -------------- Testing Find the list of rooms by equipment needed --------------
	@Test
	public void find_rooms_by_equipments_id() {
		
		// we persist an equipment
		Equipment newEquipment = new Equipment();
		newEquipment.setName("Ecran");	
		entityManager.persist(newEquipment);
		
		// we persist a room
		Room newRoom = new Room();
		newRoom.setName("E002");
		newRoom.setNbrplaces(20);
		
		
		// we link the room with the equipment 
		Set<Equipment> equipments =  new HashSet<>();
		equipments.add(newEquipment);
		newRoom.setEquipments(equipments);
		
		entityManager.persist(newRoom);
		
		// we find the room by the equipment it 
		List<Room> roomsFound = roomRepository.findRoomsByEquipmentsId(newEquipment.getId());
		
		// we verify
		assertThat(roomsFound.get(0)).isEqualTo(newRoom);
	}
	
	
	// -------------- Testing Get all rooms --------------
		@Test
		public void find_rooms_by_capacity() {
			
			
			// we persist a room
			Room newRoom1 = new Room();
			newRoom1.setName("E001");
			newRoom1.setNbrplaces(20);
			entityManager.persist(newRoom1);
			
			Room newRoom2 = new Room();
			newRoom2.setName("E002");
			newRoom2.setNbrplaces(3);
			entityManager.persist(newRoom2);
			
			// add newRoom1 and newRoom2 to a list
			List<Room> rooms = new ArrayList<Room>();
			rooms.add(newRoom1);
			rooms.add(newRoom2);
			
			// getting all rooms
			List<Room> roomsFound = roomRepository.findAll();
			
			
			// we verify
			assertThat(roomsFound).isEqualTo(rooms);
		}
	
	
		// -------------- Testing get requests with the same room  --------------
		
		@Test
		public void find_reservation_with_same_room() {
			
			
			// we persist a room
			Room newRoom1 = new Room();
			newRoom1.setName("E001");
			newRoom1.setNbrplaces(20);
			entityManager.persist(newRoom1);
			
			// we persist a request 
			Request request = new Request();

			java.sql.Date date = new java.sql.Date(2022-11-27);
			request.setDate(date);
			request.setStartingHour(9);
			request.setEndingHour(10);
			request.setNbrPersons(12);
			
			Set<Request> requests =  new HashSet<>();
			requests.add(request);
			newRoom1.setRequests(requests);
			entityManager.persist(newRoom1);
			
			// put the request inside a list
			List<Request> ListOfrequests = new ArrayList<Request>();
			ListOfrequests.add(request);
			
			
			List<Request> requestWithSameRoom = requestRepository.findRequestsByRoomsId(newRoom1.getId());
			
			assertThat(requestWithSameRoom).isEqualTo(ListOfrequests);
		}
	
}
