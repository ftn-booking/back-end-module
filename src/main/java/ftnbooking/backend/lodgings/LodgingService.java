package ftnbooking.backend.lodgings;

import java.util.List;

public interface LodgingService {

	Lodging findOne(Long id);

	List<Lodging> findAll();

	boolean isAvailable(Lodging lodging, long fromDate, long toDate);

	List<Lodging> findAvailable(long fromDate, long toDate);

	Lodging add(Lodging input);

}
