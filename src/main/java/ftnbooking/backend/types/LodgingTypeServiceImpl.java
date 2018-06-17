package ftnbooking.backend.types;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class LodgingTypeServiceImpl implements LodgingTypeService{
	@Autowired
	private LodgingTypeRepository lodgingTypeRepository;


	@Override
	public LodgingType findOne(Long id) {
		return lodgingTypeRepository.findById(id).orElse(null);
	}

	@Override
	public List<LodgingType> findAll() {
		return lodgingTypeRepository.findAll();
	}


	@Override
	@Transactional(readOnly = false)
	public LodgingType add(LodgingType input) {
		return lodgingTypeRepository.save(input);
	}
}
