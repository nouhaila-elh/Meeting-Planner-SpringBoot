package room.reservation.serviceImpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import room.reservation.entities.*;
import room.reservation.repository.*;
import room.reservation.serviceInterface.*;

@Service
public class MeetingServiceImpl  implements MeetingInterface  {
	@Autowired
    MeetingRepository meetingRespository;
	
	

	@Override
	public void addMeeting(Meeting meeting) {
		Meeting newMeeting = new Meeting();
		newMeeting.setType(meeting.getType());
		meetingRespository.save(newMeeting);
		
	}



	@Override
	public Optional<Meeting> findById(Long id) {
		return meetingRespository.findById(id);
	}
}
