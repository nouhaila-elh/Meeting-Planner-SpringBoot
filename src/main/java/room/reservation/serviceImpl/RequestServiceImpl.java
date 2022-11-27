package room.reservation.serviceImpl;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import room.reservation.entities.*;
import room.reservation.repository.*;
import room.reservation.serviceInterface.*;
@Service
public class RequestServiceImpl implements RequestInterface {
	@Autowired
	RequestRepository requestRespository;
	


	@Override
	public Optional<Request> findById(Long id) {
		return requestRespository.findById(id);
	}



	@Override
	public void addRequest(Request request) {
		Request newRequest = new Request();
		newRequest.setDate(request.getDate());
		newRequest.setStartingHour(request.getStartingHour());
		newRequest.setEndingHour(request.getEndingHour());
		newRequest.setNbrPersons(request.getNbrPersons());
		requestRespository.save(newRequest);	
	}
}
