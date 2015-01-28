/**
 *
 */
package com.acc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acc.services.CustomerHealthDataService;


/**
 * @author swarnima.gupta
 *
 */
@Controller
@RequestMapping(value = "/v1/{baseSiteId}/CustomerHealthData")
public class CustomerHealthDataController extends BaseController
{
	private static final Logger LOG = Logger.getLogger(CustomerHealthDataController.class);
	@Autowired
	private CustomerHealthDataService customerHealthDataService;

	@RequestMapping(value = "/{customerId}/{heartBeatRate}/{bloodPressure}/{milesRun}/{caloriesBurned}/{timeTaken}/{age}", method = RequestMethod.GET)
	@ResponseBody
	public String saveCustomerHeathData(@PathVariable final String customerId, @PathVariable final String heartBeatRate,
			@PathVariable final String bloodPressure, @PathVariable final String milesRun,
			@PathVariable final String caloriesBurned, @PathVariable final String timeTaken, @PathVariable final String age)
	{
		LOG.info("in saveCustomerHeathData GET request method :::::::customerId is :::::" + customerId
				+ "::::::::heartBeatRate is :::::" + heartBeatRate + "AND age::::" + age);
		return customerHealthDataService.saveCustomerHealthData(customerId, Integer.valueOf(heartBeatRate),
				Integer.valueOf(bloodPressure), Integer.valueOf(milesRun), Integer.valueOf(caloriesBurned),
				Integer.valueOf(timeTaken), Integer.valueOf(age));
	}
}
