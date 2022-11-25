package room.reservation.entities;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data   // c'est équivalent à ajouter les annotations @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode à la classe
@Entity
@Table(name = "meeting")
public class Meeting {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;	// id of the meeting
	
	@Column(name = "type", unique = true)
	private String type;   // the type of the meeting
	
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "meeting_equipment",
            joinColumns = {
                    @JoinColumn(name = "meeting_id", referencedColumnName = "id",
                            nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "equipment_id", referencedColumnName = "id",
                            nullable = false)})
    private Set<Equipment> ListofEquipments = new HashSet<>();
	
}
