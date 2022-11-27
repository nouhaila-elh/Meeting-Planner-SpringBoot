package room.reservation.serviceInterface;

import java.util.List;
import java.util.Optional;

import room.reservation.entities.*;


public interface RoomInterface {
	
	void addRoom(Room room);
	List<Room> findRoomsByEquipmentsId(Long equipmentId);
	List<Room> getAllRooms() ;
	Optional<Room> findById(Long id);
}
