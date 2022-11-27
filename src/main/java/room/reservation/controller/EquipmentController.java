package room.reservation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import room.reservation.repository.*;
import room.reservation.serviceInterface.*;
import room.reservation.entities.*;
@RestController
public class EquipmentController {
	@Autowired
	EquipmentInterface equipmentService;
	

    
    @PostMapping("/addequipment")
    public String addEquipment(@RequestBody Equipment equipment){
    	equipmentService.addEquipment(equipment);
         return "Added successfully";
    }

}
