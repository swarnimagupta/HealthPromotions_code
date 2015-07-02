/**
 *
 */
package com.acc.controller;


import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acc.data.BeaconPromotionData;
import com.acc.data.GreetingsData;
import com.acc.model.TrackLatLongModel;
import com.acc.services.BeaconPromotionsService;
import com.acc.services.GreetingsService;
import com.acc.util.WeatherUtil;
import com.acc.util.WebservicesUtil;


/**
 * @author swarnima.gupta
 *
 */
@Controller
@RequestMapping(value = "/v1/{baseSiteId}/greetCustomer")
public class CustomerGreetingsController
{

	private static final String LATITUDE = "latitude";
	private static final String LONGITUDE = "longitude";
	private static final String CUSTOMERID = "customerId";

	private static final Logger LOG = Logger.getLogger(CustomerGreetingsController.class);

	@Autowired
	private GreetingsService greetingsService;

	@Autowired
	private ModelService modelService;

	@Autowired
	private UserService userService;

	@Autowired
	private BeaconPromotionsService beaconPromotionsService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public BeaconPromotionData sendCustomerGreetings(final HttpServletRequest request) throws IOException, ParseException
	{
		LOG.info("::::::: in sendCustomerGreetings POST request method :::::::");
		final WebservicesUtil webservicesUtil = new WebservicesUtil();
		final StringBuffer sbuf = webservicesUtil.getJsonBodyString(request);
		LOG.info("::::::: json object string is :::::::" + sbuf);

		final BeaconPromotionData beaconPromotionsData = new BeaconPromotionData();
		if (StringUtils.isNotEmpty(sbuf.toString()))
		{
			final JSONParser parser = new JSONParser();
			final JSONObject obj = (JSONObject) parser.parse(sbuf.toString());
			final String latitude = String.valueOf(obj.get(LATITUDE));
			final String longitude = String.valueOf(obj.get(LONGITUDE));
			final String customerId = String.valueOf(obj.get(CUSTOMERID));

			//saving the details in customer table
			//calling the climate check service inside this method
			addValuesInCustomerModel(latitude, longitude, customerId, beaconPromotionsData, parser);
		}
		return beaconPromotionsData;
	}

	/**
	 *
	 * @param beaconPromotionsData
	 * @param parser
	 * @param latitude
	 * @param longitude
	 * @throws IOException
	 * @throws ParseException
	 */
	private void retrieveClimatedBasedPromotionsNGreetings(final BeaconPromotionData beaconPromotionsData,
			final JSONParser parser, final String latitude, final String longitude) throws IOException, ParseException
	{
		final String climate = WeatherUtil.executeClimateWebservice(parser, latitude, longitude);
		final GreetingsData greetingData = greetingsService.getGreetingsForCondition(climate);
		beaconPromotionsData.setWelcomeMessage(greetingData.getMessage());
		beaconPromotionsData.setPromotions(beaconPromotionsService.getPromotionsBasedOnClimate(climate));
	}



	/**
	 * @param latitude
	 * @param longitude
	 * @param customerId
	 */
	private void addValuesInCustomerModel(final String latitude, final String longitude, final String customerId,
			final BeaconPromotionData beaconPromotionsData, final JSONParser parser) throws IOException, ParseException
	{
		final CustomerModel customerModel = (CustomerModel) userService.getUserForUID(customerId);
		final List<TrackLatLongModel> trackLatLongModelList = customerModel.getTrackLatLongList();
		if (null != trackLatLongModelList)
		{
			final List<TrackLatLongModel> trackLatLongModelNewList = new ArrayList<TrackLatLongModel>();
			trackLatLongModelNewList.addAll(trackLatLongModelList);
			final int index = trackLatLongModelNewList.size();
			final String longi = index == 0 ? StringUtils.EMPTY : trackLatLongModelNewList.get(index - 1).getLongitude();
			final String lati = index == 0 ? StringUtils.EMPTY : trackLatLongModelNewList.get(index - 1).getLatitude();
			if (!longi.equalsIgnoreCase(longitude) || !lati.equalsIgnoreCase(latitude))
			{
				final TrackLatLongModel trackLatLongModel = modelService.create(TrackLatLongModel.class);
				trackLatLongModel.setLatitude(latitude);
				trackLatLongModel.setLongitude(longitude);
				trackLatLongModelNewList.add(trackLatLongModel);
				customerModel.setTrackLatLongList(trackLatLongModelNewList);
				modelService.save(customerModel);
				LOG.info("Customer's Geo Location Data saved successfully");
			}
			//calling the climate check service
			retrieveClimatedBasedPromotionsNGreetings(beaconPromotionsData, parser, latitude, longitude);
		}
	}
}
