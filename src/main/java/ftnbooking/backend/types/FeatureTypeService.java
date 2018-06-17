package ftnbooking.backend.types;

import java.util.List;


public interface FeatureTypeService {
	FeatureType findOne(Long id);

	List<FeatureType> findAll();

	FeatureType add(FeatureType input);
}
