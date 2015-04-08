/**
 *
 */
package com.acc.controller;

import java.util.Collections;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acc.data.BeaconData;
import com.acc.data.BeaconPromotionData;
import com.acc.services.BeaconPromotionsService;


/**
 * @author swarnima.gupta
 * 
 */
@Controller
@RequestMapping(value = "/v1/{baseSiteId}/BeaconPromotions")
public class BeaconPromotionsController
{
	private static final Logger LOG = Logger.getLogger(BeaconPromotionsController.class);

	@Autowired
	private BeaconPromotionsService beaconPromotionsService;

	@RequestMapping(value = "/{beaconId}/{majorId}/{minorId}", method = RequestMethod.GET)
	@ResponseBody
	public BeaconPromotionData getPromotionsForBeaconId(@PathVariable final String beaconId, @PathVariable final String majorId,
			@PathVariable final String minorId)
	{
		LOG.info("in getPromotionsForBeaconId GET request method :::::::beaconId is :::::" + beaconId + "::::::::majorId is :::::"
				+ majorId + "AND minorId::::" + minorId);
		final BeaconData beacon = beaconPromotionsService.getBeaconById(beaconId, majorId, minorId);
		final BeaconPromotionData beaconPromotionsData = new BeaconPromotionData();
		beaconPromotionsData.setWelcomeMessage(beacon.getWelcomeMessage());
		beaconPromotionsData.setPromotions(CollectionUtils.isNotEmpty(beacon.getPromotions()) ? beacon.getPromotions()
				: Collections.EMPTY_LIST);
		return beaconPromotionsData;
	}
}
