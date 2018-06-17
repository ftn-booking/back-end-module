package ftnbooking.backend.types;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@Service
public class FeatureTypeServiceImpl implements FeatureTypeService{
	@Autowired
	private FeatureTypeRepository featureTypeRepository;


	@Override
	public FeatureType findOne(Long id) {
		return featureTypeRepository.findById(id).orElse(null);
	}

	@Override
	public List<FeatureType> findAll() {
		return featureTypeRepository.findAll();
	}


	@Override
	@Transactional(readOnly = false)
	public FeatureType add(FeatureType input) {
		return featureTypeRepository.save(input);
	}

}
