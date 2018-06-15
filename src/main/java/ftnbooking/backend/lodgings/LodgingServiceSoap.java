package ftnbooking.backend.lodgings;

import javax.jws.WebService;

import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.annotations.SchemaValidation.SchemaValidationType;


@WebService(name = "LodgingServiceSoap", targetNamespace = "https://backend.ftnbooking/lodgingServiceSoap")
@SchemaValidation(type = SchemaValidationType.REQUEST)
public interface LodgingServiceSoap {
	Long addLodging(Lodging lodging);
}
