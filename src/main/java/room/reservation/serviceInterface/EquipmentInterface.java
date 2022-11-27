package room.reservation.serviceInterface;
import java.util.List;
import java.util.Optional;

import room.reservation.entities.*;
public interface EquipmentInterface {
	void addEquipment(Equipment equipment);
	Optional<Equipment> findById(Long id);
	List<Equipment> findEquipmentsByMeetingsId(Long meetingId);
}
