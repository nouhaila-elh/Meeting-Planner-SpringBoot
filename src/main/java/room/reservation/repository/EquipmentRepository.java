package room.reservation.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import room.reservation.entities.*;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>{

	
	List<Equipment> findEquipmentsByMeetingsId(Long meetingId);
}