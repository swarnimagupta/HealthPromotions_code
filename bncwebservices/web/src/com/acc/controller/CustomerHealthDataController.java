/**
 *
 */
package com.acc.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.acc.data.CustomerHealthData;
import com.acc.services.CustomerHealthDataService;


/**
 * @author swarnima.gupta
 *
 */
@Controller
@RequestMapping(value = "/v1/{baseSiteId}/CustomerHealthData")
public class CustomerHealthDataController extends BaseController
{
	private static final String HEALTH_DATA = "HealthData";
	private static final String TYPE = "type";
	private static final String VALUE = "Value";
	private static final String AGE = "age";
	private static final String TIMETAKEN = "timetaken";
	private static final String CALORIESBURNED = "caloriesburned";
	private static final String MILESRUN = "milesrun";
	private static final String BLOODPRESSURE = "bloodpressure";
	private static final String HEARTBEATRATE = "heartbeatrate";
	private static final String CUSTOMERID = "customerid";
	private static final Logger LOG = Logger.getLogger(CustomerHealthDataController.class);
	@Autowired
	private CustomerHealthDataService customerHealthDataService;

	@RequestMapping(value = "/saveCustomerHealthData", method = RequestMethod.POST)
	@ResponseBody
	public List<String> saveCustomerHeathData(final HttpServletRequest request) throws IOException, ParseException
	{
		LOG.info("::::::: in saveCustomerHeathData GET request method :::::::" + request.getParameter(HEALTH_DATA));
		final StringBuffer sbuf = getJsonBodyString(request);
		LOG.info("::::::: json object string is :::::::" + sbuf);
		final CustomerHealthData customerHealthData = new CustomerHealthData();
		getHealthDataFromJsonString(sbuf, customerHealthData);
		//returning JSON object
		final String responseString = customerHealthDataService.saveCustomerHealthData(customerHealthData.getCustomerId(),
				customerHealthData.getHeartBeatRate(), customerHealthData.getBloodPressure(), customerHealthData.getMilesRun(),
				customerHealthData.getCaloriesBurned(), customerHealthData.getTimeTaken(), customerHealthData.getAge());
		final List<String> list = new ArrayList<String>();
		list.add(responseString);
		return list;
	}

	/**
	 * @param sbuf
	 * @param customerHealthData
	 * @throws ParseException
	 */
	private void getHealthDataFromJsonString(final StringBuffer sbuf, final CustomerHealthData customerHealthData)
			throws ParseException
	{
		if (StringUtils.isNotEmpty(sbuf.toString()))
		{
			final JSONParser parser = new JSONParser();
			final JSONObject obj = (JSONObject) parser.parse(sbuf.toString());
			final List<JSONObject> JSONObjectList = (List<JSONObject>) obj.get(HEALTH_DATA);
			if (CollectionUtils.isNotEmpty(JSONObjectList))
			{
				populateCustomerHealthData(customerHealthData, JSONObjectList);
			}
		}
	}

	/**
	 * @param customerHealthData
	 * @param JSONObjectList
	 */
	private void populateCustomerHealthData(final CustomerHealthData customerHealthData, final List<JSONObject> JSONObjectList)
	{
		for (final JSONObject JSONObject : JSONObjectList)
		{
			switch (JSONObject.get(TYPE).toString().toLowerCase())
			{
				case CUSTOMERID:
					customerHealthData.setCustomerId(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case HEARTBEATRATE:
					customerHealthData.setHeartBeatRate(Integer.valueOf(JSONObject.get(VALUE).toString()));
					continue;
				case BLOODPRESSURE:
					customerHealthData.setBloodPressure(Integer.valueOf(JSONObject.get(VALUE).toString()));
					continue;
				case MILESRUN:
					customerHealthData.setMilesRun(Double.valueOf(JSONObject.get(VALUE).toString()));
					continue;
				case CALORIESBURNED:
					customerHealthData.setCaloriesBurned(Integer.valueOf(JSONObject.get(VALUE).toString()));
					continue;
				case TIMETAKEN:
					customerHealthData.setTimeTaken(Double.valueOf(JSONObject.get(VALUE).toString()));
					continue;
				case AGE:
					customerHealthData.setAge(Integer.valueOf(JSONObject.get(VALUE).toString()));
					continue;
			}
		}
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
