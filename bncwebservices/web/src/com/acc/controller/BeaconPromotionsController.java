/**
 *
 */
package com.acc.controller;

import de.hybris.platform.commercefacades.product.data.PromotionData;

import java.util.List;

import javax.jws.WebParam;
import javax.xml.ws.Holder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acc.data.BeaconData;
import com.acc.services.BeaconPromotionsService;


/**
 * @author swarnima.gupta
 *
 */
@Controller
@RequestMapping(value = "/v1/{baseSiteId}/BeaconPromotions")
public class BeaconPromotionsController extends BaseController
{
	private static final Logger LOG = Logger.getLogger(BeaconPromotionsController.class);

	@Autowired
	private BeaconPromotionsService beaconPromotionsService;

	@RequestMapping(value = "/{beaconId}/{majorId}/{minorId}", method = RequestMethod.GET)
	@ResponseBody
	public void getPromotionsForBeaconId(@PathVariable final String beaconId, @PathVariable final String majorId,
			@PathVariable final String minorId,
			@WebParam(name = "welcomeMessage", mode = WebParam.Mode.OUT) final Holder<String> welcomeMessage,
			@WebParam(name = "promotions", mode = WebParam.Mode.OUT) final Holder<List<PromotionData>> promotions)
	{
		LOG.info("in getPromotionsForBeaconId GET request method :::::::beaconId is :::::" + beaconId + "::::::::majorId is :::::"
				+ majorId + "AND minorId::::" + minorId);
		final BeaconData beacon = beaconPromotionsService.getBeaconById(beaconId, majorId, minorId);
		welcomeMessage.value = beacon.getWelcomeMessage();
		promotions.value = beacon.getPromotions();
	}
}
