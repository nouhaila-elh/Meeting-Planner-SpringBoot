package room.reservation.serviceInterface;
import java.util.Optional;

import room.reservation.entities.*;
public interface RequestInterface {
	void addRequest(Request request);
	Optional<Request> findById(Long id);
}
