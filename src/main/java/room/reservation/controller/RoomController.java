package room.reservation.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import room.reservation.repository.*;
import room.reservation.serviceInterface.RoomInterface;
import room.reservation.entities.*;
@RestController
public class RoomController {

	
    @Autowired
    RoomInterface userService;
	

    
    @PostMapping("/addroom")
    public String addRoom(@RequestBody Room room){
         userService.addRoom(room);
         return "Added successfully";
    }
    
    

}
