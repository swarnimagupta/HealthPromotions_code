/**
 *
 */
package com.acc.controller;

import de.hybris.platform.util.Config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.acc.data.BeaconData;
import com.acc.data.BeaconPromotionData;
import com.acc.services.BeaconPromotionsService;
import com.acc.services.StoreLoginService;


/**
 * @author swarnima.gupta
 *
 */
@Controller
@RequestMapping(value = "/v1/{baseSiteId}")
public class WebserviceFactoryController
{
	private static final String DEFAULT_WELCOME_BEACON = "12168afc-c27e-4845-ba90-e7b5484bdbba";
	private static final String WELCOME_BEACON_ID = "welcome.beacon.id";
	private static final String HASH_STRING = "#";
	private static final String STORE_ID = "storeId";
	private static final String DEVICE_ID = "deviceId";
	private static final String MINOR_ID = "minorId";
	private static final String MAJOR_ID = "majorId";
	private static final String BEACON_ID = "beaconId";

	private static final Logger LOG = Logger.getLogger(WebserviceFactoryController.class);

	@Autowired
	private StoreLoginService storeLoginService;
	@Autowired
	private BeaconPromotionsService beaconPromotionsService;

	@RequestMapping(value = "/performBeaconFunction", method = RequestMethod.POST)
	@ResponseBody
	public BeaconPromotionData performBeaconFuntion(final HttpServletRequest request) throws IOException, ParseException
	{

		LOG.info("::::::: in performBeaconFuntion POST request method :::::::");
		final StringBuffer sbuf = getJsonBodyString(request);
		LOG.info("::::::: json object string is :::::::" + sbuf);
		final BeaconPromotionData beaconPromotionsData = new BeaconPromotionData();
		if (StringUtils.isNotEmpty(sbuf.toString()))
		{
			final JSONParser parser = new JSONParser();
			final JSONObject obj = (JSONObject) parser.parse(sbuf.toString());
			final String beaconId = String.valueOf(obj.get(BEACON_ID));
			final String majorId = String.valueOf(obj.get(MAJOR_ID));
			final String minorId = String.valueOf(obj.get(MINOR_ID));
			if (Config.getString(WELCOME_BEACON_ID, DEFAULT_WELCOME_BEACON).equals(beaconId))
			{
				final String deviceId = String.valueOf(obj.get(DEVICE_ID));
				final String storeId = String.valueOf(obj.get(STORE_ID));
				LOG.info(":::::::::::uuid is " + HASH_STRING + deviceId + HASH_STRING + "::::::::::storeid is::::::::: " + storeId
						+ "AND Customer ID :::::::" + deviceId);
				beaconPromotionsData.setResponse(storeLoginService.saveCustomerLoginDetails(HASH_STRING + deviceId + HASH_STRING,
						storeId, deviceId));
			}
			final BeaconData beacon = beaconPromotionsService.getBeaconById(beaconId, majorId, minorId);
			beaconPromotionsData.setWelcomeMessage(beacon.getWelcomeMessage());
			beaconPromotionsData.setPromotions(CollectionUtils.isNotEmpty(beacon.getPromotions()) ? beacon.getPromotions()
					: Collections.EMPTY_LIST);
		}
		return beaconPromotionsData;
	}

	/**
	 * @param request
	 * @return StringBuffer
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
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
