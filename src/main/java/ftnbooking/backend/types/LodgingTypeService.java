package ftnbooking.backend.types;

import java.util.List;

public interface LodgingTypeService {
	LodgingType findOne(Long id);

	List<LodgingType> findAll();

	LodgingType add(LodgingType input);
}
