package ftnbooking.backend.lodgings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class LodgingServiceImpl implements LodgingService {

	@Autowired
	private LodgingRepository lodgingRepository;

	@Override
	public Lodging findOne(Long id) {
		return lodgingRepository.findById(id).orElse(null);
	}

	@Override
	public List<Lodging> findAll() {
		return lodgingRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Lodging add(Lodging input) {
		return lodgingRepository.save(input);
	}

}
