package ftnbooking.backend.lodgings;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@WebService(endpointInterface = "ftnbooking.backend.lodgings.LodgingServiceSoap",
			serviceName = "LodgingService",
			portName = "LodgingServicePort",
			targetNamespace = "http://backend.ftnbooking/lodgingServiceSoap")
public class LodgingServiceSoapImpl implements LodgingServiceSoap{

	@Autowired
	private LodgingRepository lodgingRepository;
	
	@Override
	public Long addLodging(Lodging lodging) {
		lodgingRepository.save(lodging);
		return (long) 5.0;
	}

}
