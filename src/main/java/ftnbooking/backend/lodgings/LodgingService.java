package ftnbooking.backend.lodgings;

import java.util.List;

public interface LodgingService {

	Lodging findOne(Long id);

	List<Lodging> findAll();

	Lodging add(Lodging input);

}
