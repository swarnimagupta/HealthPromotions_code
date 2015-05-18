/**
 *
 */
package com.acc.controller;


import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
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
	 * @param webservicesUtil
	 * @param beaconPromotionsData
	 * @param parser
	 * @param url
	 * @throws IOException
	 * @throws ParseException
	 */
	private void retrieveClimatedBasedPromotionsNGreetings(final WebservicesUtil webservicesUtil,
			final BeaconPromotionData beaconPromotionsData, final JSONParser parser, final URL url) throws IOException,
			ParseException
	{
		final HttpURLConnection connection = webservicesUtil.getHttpConnection(url);

		final String jsonData = webservicesUtil.getJsonDataString(connection);
		final JSONObject object = (JSONObject) parser.parse(jsonData);
		System.out.println("Object is->" + object);
		final JSONObject dataObject = (JSONObject) object.get("data");
		System.out.println("Data Object is->" + dataObject);
		final JSONArray ccObject = (JSONArray) dataObject.get("current_condition");
		System.out.println("ccObject Object is->" + ccObject);
		final Iterator<JSONObject> driveIterator = ccObject.iterator();
		while (driveIterator.hasNext())
		{
			final JSONObject driveJSON = driveIterator.next();
			System.out.println("drive JSON ###########->" + driveJSON);
			final float cloudCover = Float.valueOf(String.valueOf(driveJSON.get("cloudcover"))).floatValue();
			final float precipitation = Float.valueOf(String.valueOf(driveJSON.get("precipMM"))).floatValue();
			final float temperature = Float.valueOf(String.valueOf(driveJSON.get("temp_C"))).floatValue();
			final String climate = WeatherUtil.getClimate(cloudCover, precipitation, temperature);
			final GreetingsData greetingData = greetingsService.getGreetingsForCondition(climate);
			beaconPromotionsData.setWelcomeMessage(greetingData.getMessage());
			beaconPromotionsData.setPromotions(beaconPromotionsService.getPromotionsBasedOnClimate(climate));
		}
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
		final List<String> longitudeList = customerModel.getLongitudes();
		final List<String> latitudeList = customerModel.getLatitudes();
		final List<Date> dateList = customerModel.getDate();
		if (null != latitudeList && null != longitudeList && null != dateList)
		{
			final List<String> newLongitudeList = new ArrayList<String>();
			final List<String> newLatitudeList = new ArrayList<String>();
			final List<Date> newDateList = new ArrayList<Date>();
			newLatitudeList.addAll(latitudeList);
			newLongitudeList.addAll(longitudeList);
			newDateList.addAll(dateList);

			if (CollectionUtils.isNotEmpty(newLongitudeList))
			{
				final int index = newLongitudeList.size();
				final String longi = newLongitudeList.get(index - 1);
				final String lati = newLatitudeList.get(index - 1);
				if (!longi.equalsIgnoreCase(longitude) || !lati.equalsIgnoreCase(latitude))
				{
					saveAndCallWebservice(latitude, longitude, beaconPromotionsData, parser, customerModel, newLongitudeList,
							newLatitudeList, newDateList);
				}
			}
			else
			{
				saveAndCallWebservice(latitude, longitude, beaconPromotionsData, parser, customerModel, newLongitudeList,
						newLatitudeList, newDateList);
			}


		}
	}

	/**
	 * @param latitude
	 * @param longitude
	 * @param beaconPromotionsData
	 * @param parser
	 * @param customerModel
	 * @param newLongitudeList
	 * @param newLatitudeList
	 * @param newDateList
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ParseException
	 */
	private void saveAndCallWebservice(final String latitude, final String longitude,
			final BeaconPromotionData beaconPromotionsData, final JSONParser parser, final CustomerModel customerModel,
			final List<String> newLongitudeList, final List<String> newLatitudeList, final List<Date> newDateList)
			throws MalformedURLException, IOException, ParseException
	{
		newLongitudeList.add(longitude);
		newLatitudeList.add(latitude);
		newDateList.add(new Date());
		customerModel.setLongitudes(newLongitudeList);
		customerModel.setLatitudes(newLatitudeList);
		customerModel.setDate(newDateList);
		modelService.save(customerModel);
		LOG.info("Customer's Geo Location Data saved successfully");

		//calling the climate check service
		final URL url = new URL(
				"http://api.worldweatheronline.com/free/v2/weather.ashx?q="
						+ latitude
						+ "%2C"
						+ longitude
						+ "&format=json&num_of_days=1&date=today&fx=no&mca=no&fx24=no&includelocation=yes&show_comments=yes&showlocaltime=yes&key=61c3cc6652f35c4e318b62ff3b196");
		retrieveClimatedBasedPromotionsNGreetings(new WebservicesUtil(), beaconPromotionsData, parser, url);
	}
}
