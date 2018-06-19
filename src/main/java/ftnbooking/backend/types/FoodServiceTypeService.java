package ftnbooking.backend.types;

import java.util.List;

public interface FoodServiceTypeService {
	FoodServiceType findOne(Long id);

	List<FoodServiceType> findAll();

	FoodServiceType add(FoodServiceType input);
}
