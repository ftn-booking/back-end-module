package ftnbooking.backend.rating.contract;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import ftnbooking.backend.rating.RatingData;

//Service Endpoint Interface
@WebService
//@SOAPBinding(style = Style.RPC)
//@SOAPBinding(style = Style.DOCUMENT)
public interface IRatingService {

	
	@WebMethod double CalculateRating(RatingData ratingData);
}
