/**
 * 
 */
package com.acc.controller;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author swapnil.a.pandey
 * 
 */
@Controller
@RequestMapping(value = "/v1/{baseSiteId}/GeoLocation")
public class GeoLocationController
{
	private static final Logger LOG = Logger.getLogger(GeoLocationController.class);



	private static final String LONGITUDES = "longitudes";
	private static final String LATITUDES = "latitudes";
	private static final String CUSTOMERID = "customerId";

	@Autowired
	CustomerModel customerModel;
	@Autowired
	ModelService modelService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "getGeoLocation", method = RequestMethod.POST)
	public void getGeolocation(final HttpServletRequest request) throws UnsupportedEncodingException, IOException, ParseException
	{

		LOG.info("**************inside getgeoLocation**********");
		final StringBuffer sbuf = getJsonBodyString(request);
		if (StringUtils.isNotEmpty(sbuf.toString()))
		{
			final JSONParser parser = new JSONParser();
			final JSONObject obj = (JSONObject) parser.parse(sbuf.toString());
			final String longitude = String.valueOf(obj.get(LONGITUDES));
			final String latitude = String.valueOf(obj.get(LATITUDES));
			final String customerId = String.valueOf(obj.get(CUSTOMERID));

			final CustomerModel customerModel = (CustomerModel) userService.getUserForUID(customerId);
			final List<String> longitudeList = customerModel.getLongitudes();
			final List<String> latitudeList = customerModel.getLatitudes();

			final Date date = new Date();



			LOG.info("date+++++" + date);
			if (CollectionUtils.isNotEmpty(latitudeList) && CollectionUtils.isNotEmpty(longitudeList))
			{
				final List<String> newLongitudeList = new ArrayList<String>();
				newLongitudeList.addAll(longitudeList);
				final List<String> newLatitudeList = new ArrayList<String>();
				newLatitudeList.addAll(latitudeList);
				final List<Date> newDateList = new ArrayList<Date>();
				newDateList.addAll(Arrays.asList(date));



				final int index = newLongitudeList.size();
				final String longi = newLongitudeList.get(index - 1);
				final String lati = newLatitudeList.get(index - 1);
				if (!longi.equalsIgnoreCase(longitude) || !lati.equalsIgnoreCase(latitude))
				{
					newLongitudeList.add(longitude);
					newLatitudeList.add(latitude);
					customerModel.setLongitudes(newLongitudeList);
					customerModel.setLatitudes(newLatitudeList);
					customerModel.setDate(newDateList);
					LOG.info("Customer's Geo Date+++++++++++++++" + customerModel.getDate());
					LOG.info("Customer's Geo newDateList+++++++++++++++" + newDateList);

					modelService.save(customerModel);
					LOG.info("Customer's Geo Location Data saved successfully");
				}

			}
		}
	}



	private StringBuffer getJsonBodyString(final HttpServletRequest request) throws IOException, UnsupportedEncodingException
	{
		final ServletInputStream input = request.getInputStream();
		final byte[] buf = new byte[201];
		final StringBuffer sbuf = new StringBuffer();
		int result;
		do
		{
			result = input.readLine(buf, 0, buf.length);
			if (result != -1)
			{
				sbuf.append(new String(buf, 0, result, "UTF-8"));
			}
			else
			{
				break;
			}
		}
		while (result == buf.length);
		return sbuf;
	}
}
