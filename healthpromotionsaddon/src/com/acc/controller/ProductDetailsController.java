/**
 * 
 */

package com.acc.controller;

import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acc.services.ProductLocationService;



/**
 * @author swapnil.a.pandey
 * 
 */
@Controller
@RequestMapping(value = "/v1/{baseSiteId}/productDetails")
public class ProductDetailsController
{


	private static final Logger LOG = Logger.getLogger(ProductDetailsController.class);

	private static final String BASIC_OPTION = "BASIC";

	@Autowired
	private ProductLocationService productLocationService;


	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	@ResponseBody
	public ProductData getProductByCode(@PathVariable final String code,
			@RequestParam(required = false, defaultValue = BASIC_OPTION) final String options)
	{

		LOG.info("inside ProductDetailsController**********");
		if (LOG.isDebugEnabled())
		{
			LOG.debug("getProductByCode: code=" + code + " | options=" + options);
		}

		final Set<ProductOption> opts = extractOptions(options);
		LOG.info("inside ProductDetailsController**********" + opts);

		return productLocationService.getProductForCodeAndOptions(code, opts);



	}

	protected Set<ProductOption> extractOptions(final String options)
	{
		final String optionsStrings[] = options.split(",");

		final Set<ProductOption> opts = new HashSet<ProductOption>();
		for (final String option : optionsStrings)
		{
			opts.add(ProductOption.valueOf(option));
		}
		return opts;
	}
}
