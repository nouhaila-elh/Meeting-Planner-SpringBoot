package room.reservation.entities;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data   // c'est équivalent à ajouter les annotations @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode à la classe
@Entity
@Table(name = "equipment")
public class Equipment {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;	// id of the equipment
	
	@Column(name = "name", unique = true)
	private String name;   // name of the equipment
	
	@ManyToMany(mappedBy = "ListofEquipments", fetch = FetchType.LAZY)
    private Set<Meeting> Listofmeeting = new HashSet<>();
	
	@ManyToMany(mappedBy = "ListofEquipments", fetch = FetchType.LAZY)
    private Set<Room> Listofroom = new HashSet<>();
}
