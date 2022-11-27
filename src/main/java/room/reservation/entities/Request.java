package room.reservation.entities;



import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data   // c'est équivalent à ajouter les annotations @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode à la classe
@Entity
@Table(name = "request")
public class Request {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	// id of the room
	
	@Column(name = "date")
	private java.sql.Date date;
	
	@Column(name = "startingHour")
	private int startingHour ;
	
	@Column(name = "endingHour")
	private int endingHour;
	
	@Column(name = "nbrPersons")
	private int nbrPersons;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;
	
	@ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      },
		      mappedBy = "requests")
	@JsonIgnore
	private Set<Room> rooms = new HashSet<>();
	
	
	
}
