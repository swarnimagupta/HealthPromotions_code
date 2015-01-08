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
public class HealthPromotionsDataController extends BaseController
{
	private static final Logger LOG = Logger.getLogger(CSRController.class);
	@Autowired
	private CustomerHealthDataService customerHealthDataService;

	@RequestMapping(value = "/{uuid}/{heartBeatRate}/{bloodPressure}/{milesRun}/{caloriesBurned}/{timeTaken}/{age}", method = RequestMethod.GET)
	@ResponseBody
	public void saveCustomerHeathData(@PathVariable final String uuid, @PathVariable final Integer heartBeatRate,
			@PathVariable final Integer bloodPressure, @PathVariable final Integer milesRun,
			@PathVariable final Integer caloriesBurned, @PathVariable final Integer timeTaken, @PathVariable final Integer age)
	{
		LOG.info("in saveCustomerHeathData GET request method :::::::uuid is :::::" + uuid + "::::::::heartBeatRate is :::::"
				+ heartBeatRate + "AND age::::" + age);
		customerHealthDataService.saveCustomerHealthData(uuid, heartBeatRate, bloodPressure, milesRun, caloriesBurned, timeTaken,
				age);
	}
}
