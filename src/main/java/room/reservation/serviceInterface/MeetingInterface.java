package room.reservation.serviceInterface;

import java.util.List;
import java.util.Optional;

import room.reservation.entities.*;
public interface MeetingInterface {
	void addMeeting(Meeting meeting);
	Optional<Meeting> findById(Long id);
}
