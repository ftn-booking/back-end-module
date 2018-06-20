package ftnbooking.backend;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftnbooking.backend.comments.Approval;
import ftnbooking.backend.comments.Comment;
import ftnbooking.backend.comments.CommentService;
import ftnbooking.backend.lodgings.Lodging;
import ftnbooking.backend.lodgings.LodgingService;
import ftnbooking.backend.prices.Price;
import ftnbooking.backend.prices.PriceService;
import ftnbooking.backend.reservations.Reservation;
import ftnbooking.backend.reservations.ReservationService;
import ftnbooking.backend.types.FeatureType;
import ftnbooking.backend.types.FeatureTypeService;
import ftnbooking.backend.types.FoodServiceType;
import ftnbooking.backend.types.FoodServiceTypeService;
import ftnbooking.backend.types.LodgingType;
import ftnbooking.backend.types.LodgingTypeService;
import ftnbooking.backend.users.ApplicationUser;
import ftnbooking.backend.users.ApplicationUserService;
import ftnbooking.backend.users.ApplicationUserType;

@Component
public class TestData {

	@Autowired
	private ApplicationUserService userService;
	@Autowired
	private LodgingService lodgingService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private LodgingTypeService lodgingTypeService;
	@Autowired
	private FeatureTypeService featureTypeService;
	@Autowired
	private FoodServiceTypeService foodServiceTypeService;
	@Autowired
	private PriceService priceService;
	@Autowired
	private CommentService commentService;

	@PostConstruct
	private void init() {
		ApplicationUser user1 = new ApplicationUser("ch@me", "qweqwe", "Chewbacca", "Chewbacca", "Kashyyyk", null);
		user1.setUserType(ApplicationUserType.VISITOR);
		userService.add(user1);

		ApplicationUser user2 = new ApplicationUser("rms@me", "qweqwe", "Richard", "Stallman", "New York", null);
		user2.setUserType(ApplicationUserType.VISITOR);
		userService.add(user2);

		ApplicationUser user3 = new ApplicationUser("han@me", "qweqwe", "Han", "Solo", "Corellia", null);
		user3.setUserType(ApplicationUserType.AGENT);
		userService.add(user3);

		ApplicationUser user4 = new ApplicationUser("pot@me", "qweqwe", "Lennart", "Poettering", "Guatemala City", null);
		user4.setUserType(ApplicationUserType.AGENT);
		userService.add(user4);

		ApplicationUser user5 = new ApplicationUser("ben@me", "qweqwe", "Ben", "Solo", "Chandrila", null);
		user5.setUserType(ApplicationUserType.ADMIN);
		userService.add(user5);

		ApplicationUser user6 = new ApplicationUser("an@me", "qweqwe", "Anakin", "Skywalker", "Tatooine", null);
		user6.setUserType(ApplicationUserType.ADMIN);
		userService.add(user6);

		ApplicationUser user7 = new ApplicationUser("sys@me", "qweqwe", "Sheev", "Palpatine", "Naboo", null);
		user7.setUserType(ApplicationUserType.ADMIN);
		userService.add(user7);

		LodgingType lodgingType1 = new LodgingType("HOTEL");
		LodgingType lodgingType2 = new LodgingType("B & B");
		LodgingType lodgingType3 = new LodgingType("APARTMENT");
		lodgingTypeService.add(lodgingType1);
		lodgingTypeService.add(lodgingType2);
		lodgingTypeService.add(lodgingType3);

		FeatureType featureType1 = new FeatureType("TV");
		FeatureType featureType2 = new FeatureType("WiFi");
		FeatureType featureType3 = new FeatureType("Parking");
		featureTypeService.add(featureType1);
		featureTypeService.add(featureType2);
		featureTypeService.add(featureType3);
		Set<FeatureType> features1 = new HashSet<>(featureTypeService.findAll());
		FeatureType featureType4 = new FeatureType("Kitchen");
		FeatureType featureType5 = new FeatureType("Bathroom");
		featureTypeService.add(featureType4);
		featureTypeService.add(featureType5);
		Set<FeatureType> features2 = new HashSet<>(featureTypeService.findAll());


		FoodServiceType foodServiceType1 = new FoodServiceType("Breakfast");
		FoodServiceType foodServiceType2 = new FoodServiceType("Full board");
		FoodServiceType foodServiceType3 = new FoodServiceType("Half board");
		foodServiceTypeService.add(foodServiceType1);
		foodServiceTypeService.add(foodServiceType2);
		foodServiceTypeService.add(foodServiceType3);

		Lodging lodging1 = new Lodging("Lodging",
				"Hawaii",
				"Test lodging 1",
				4,
				lodgingType1,
				foodServiceType1,
				2,
				features1,
				user3);
		lodging1.addImagePath("img/placeholder1.png");
		lodging1.addImagePath("img/placeholder2.png");
		lodging1.addImagePath("img/placeholder3.png");
		lodging1.setRating(4.6);
		lodging1.setNumberOfRatings(5);
		lodgingService.add(lodging1);

		Lodging lodging2 = new Lodging("Tourist Inn",
				"Sri Lanka",
				"Calm place for family",
				3,
				lodgingType2,
				foodServiceType2,
				3,
				features2,
				user4);
		lodging2.addImagePath("img/placeholder1.png");
		lodging2.addImagePath("img/placeholder2.png");
		lodgingService.add(lodging2);

		// from 2018-01-01 - 2019-01-01
		Price price1 = new Price(lodging1, 1514764800000l, 1546300800000l, 20);
		// from 2019-01-01 - 2020-01-01
		Price price2 = new Price(lodging1, 1546300800001l, 1577836800000l, 30);
		// from 2020-01-01 - 2021-01-01
		Price price3 = new Price(lodging1, 1577836800001l, 1609459200000l, 40);
		// from 2018-01-01 - 2019-01-01
		Price price4 = new Price(lodging2, 1514764800000l, 1546300800000l, 50);
		// from 2019-01-01 - 2020-01-01
		Price price5 = new Price(lodging2, 1546300800001l, 1577836800000l, 60);
		// from 2020-01-01 - 2021-01-01
		Price price6 = new Price(lodging2, 1577836800001l, 1609459200000l, 70);
		priceService.add(price1);
		priceService.add(price2);
		priceService.add(price3);
		priceService.add(price4);
		priceService.add(price5);
		priceService.add(price6);

		Reservation reservation1 = new Reservation(user1, lodging2, 1534291200000l, 1535155200000l);
		reservation1.setApproved(true);
		reservationService.add(reservation1);

		Reservation reservation2 = new Reservation(user1, lodging1, 1539907200000l, 1540252800000l);
		reservationService.add(reservation2);

		Comment comment1 = new Comment(user1, reservation1, "Nice place");
		comment1.setApproved(Approval.APPROVED);
		commentService.add(comment1);
		Comment comment2 = new Comment(user1, reservation1, "Good staff");
		comment2.setApproved(Approval.APPROVED);
		commentService.add(comment2);
		Comment comment3 = new Comment(user1, reservation1, "Nice view!");
		comment3.setApproved(Approval.APPROVED);
		commentService.add(comment3);
		Comment comment4 = new Comment(user1, reservation1, "Nice view!");
		comment4.setApproved(Approval.PENDING);
		commentService.add(comment4);

	}
}
