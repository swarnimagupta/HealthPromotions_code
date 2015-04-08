/**
 * 
 */
package com.acc.controller;

import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acc.services.ProductLocationService;


/**
 * @author swapnil.a.pandey
 * 
 */
@Controller
@RequestMapping(value = "/v1/{baseSiteId}/ProductLocationDetails")
public class ProductLocationDetailsController
{
	private static final Logger LOG = Logger.getLogger(ProductLocationDetailsController.class);
	@Autowired
	private ProductLocationService productLocationService;

	@RequestMapping(value = "/{beaconId:.+}", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductData> getProductsForBeaconId(@PathVariable final String beaconId)

	{
		LOG.info("in  ProductLocationDetailsController GET request method :::::::beaconId is :::::" + beaconId);
		final List<ProductData> productData = productLocationService.getProductsForBeaconId(beaconId);
		LOG.info("in  ProductLocationDetailsController GET request method :::::::product location is :::::"
				+ productData.get(0).getLocation());


		return productData;
	}
}
