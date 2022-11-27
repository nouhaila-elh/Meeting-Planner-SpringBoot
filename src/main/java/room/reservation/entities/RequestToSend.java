package room.reservation.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class RequestToSend {
 
	private java.sql.Date date;
	private int startingHour ;
	private int endingHour;
	private int nbrPersons;
	private Long meetingid;
}
