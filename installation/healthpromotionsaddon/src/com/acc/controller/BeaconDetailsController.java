/**
 * 
 */
package com.acc.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acc.data.BeaconData;
import com.acc.services.BeaconPromotionsService;


/**
 * @author swapnil.a.pandey
 * 
 */
@Controller
@RequestMapping(value = "/v1/{baseSiteId}/BeaconDetails")
public class BeaconDetailsController
{
	private static final Logger LOG = Logger.getLogger(BeaconDetailsController.class);
	@Autowired
	private BeaconPromotionsService beaconPromotionsService;

	@RequestMapping(value = "beaconlist", method = RequestMethod.GET)
	@ResponseBody
	public List<BeaconData> getBeaconDetails()

	{
		LOG.info("******inside BeaconDetailsController******");
		final List<BeaconData> beaconList = beaconPromotionsService.getBeaconDetails();


		LOG.info("******inside BeaconDetailsController******" + beaconList);
		return beaconList;
	}

}
