package room.reservation.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import room.reservation.entities.Room;
import room.reservation.repository.RoomRespository;
import room.reservation.serviceInterface.RoomInterface;

@Service
public class RoomServiceImpl implements RoomInterface {
	
	@Autowired
    RoomRespository roomRespository;
	
	

	@Override
	public void addRoom(Room room) {
		Room newRoom = new Room();
		newRoom.setName(room.getName());
		newRoom.setNbrplaces(room.getNbrplaces());
		roomRespository.save(newRoom);
		
	}


}
