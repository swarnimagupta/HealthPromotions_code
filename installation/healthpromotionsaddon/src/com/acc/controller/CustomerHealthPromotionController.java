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

import com.acc.product.data.PromotionDataList;
import com.acc.services.BeaconPromotionsService;


/**
 * @author swapnil.a.pandey
 * 
 */
@Controller
@RequestMapping(value = "/v1/{baseSiteId}/CustomerHealthPromotions")
public class CustomerHealthPromotionController
{
	private static final Logger LOG = Logger.getLogger(CustomerHealthPromotionController.class);
	@Autowired
	private BeaconPromotionsService beaconPromotionsService;

	@RequestMapping(value = "/{emailId:.+}", method = RequestMethod.GET)
	@ResponseBody
	public PromotionDataList getCustomerHeathPromotionData(@PathVariable final String emailId)

	{
		LOG.info("in saveCustomerHeathPromotionData GET request method :::::::EmailId is :::::" + emailId);
		final PromotionDataList promotionDataList = beaconPromotionsService.getCustomerHeathPromotionData(emailId);


		return promotionDataList;
	}
}
