package ftnbooking.backend.types;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class FoodServiceTypeImpl implements FoodServiceTypeService{
	@Autowired
	private FoodServiceTypeRepository foodServiceTypeRepository;


	@Override
	public FoodServiceType findOne(Long id) {
		return foodServiceTypeRepository.findById(id).orElse(null);
	}

	@Override
	public List<FoodServiceType> findAll() {
		return foodServiceTypeRepository.findAll();
	}


	@Override
	@Transactional(readOnly = false)
	public FoodServiceType add(FoodServiceType input) {
		return foodServiceTypeRepository.save(input);
	}

}
