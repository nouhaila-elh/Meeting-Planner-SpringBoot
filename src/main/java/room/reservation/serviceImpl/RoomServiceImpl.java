package room.reservation.serviceImpl;

import java.util.List;
import java.util.Optional;

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
		
		roomRespository.save(room);
		
	}



	@Override
	public List<Room> findRoomsByEquipmentsId(Long equipmentId) {
		return roomRespository.findRoomsByEquipmentsId(equipmentId);
	}



	@Override
	public List<Room> getAllRooms() {
		return roomRespository.findAll();
	}



	@Override
	public Optional<Room> findById(Long id) {
		return roomRespository.findById(id);
	}


}
