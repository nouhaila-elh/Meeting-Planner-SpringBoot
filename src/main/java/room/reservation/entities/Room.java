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
@Table(name = "room")
public class Room {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;	// id of the room
	
	@Column(name = "name", unique = true)
	private String name;   // name of the room
	
	@Column(name = "number_places")
	private int nbrplaces;   // number of available places 
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "room_equipment",
            joinColumns = {
                    @JoinColumn(name = "room_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "equipment_id", referencedColumnName = "id")})
    private Set<Equipment> ListofEquipments = new HashSet<>();

}
