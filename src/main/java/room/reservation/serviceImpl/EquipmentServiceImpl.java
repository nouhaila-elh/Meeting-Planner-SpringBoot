package room.reservation.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import room.reservation.entities.*;
import room.reservation.repository.*;
import room.reservation.serviceInterface.*;

@Service
public class EquipmentServiceImpl implements EquipmentInterface  {
	@Autowired
    EquipmentRepository equipmentRespository;
	
	

	@Override
	public void addEquipment(Equipment equipment) {
		Equipment newEquipment = new Equipment();
		newEquipment.setName(equipment.getName());
		equipmentRespository.save(newEquipment);
		
	}
}
