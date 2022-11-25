package room.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import room.reservation.entities.*;

@Repository
public interface RoomRespository extends JpaRepository<Room, Long> {

}
