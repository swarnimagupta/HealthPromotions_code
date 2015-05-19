/**
 * 
 */
package com.acc.controller;

import de.hybris.platform.acceleratorcms.data.RequestContextData;
import de.hybris.platform.acceleratorcms.utils.SpringHelper;
import de.hybris.platform.cms2lib.model.components.ProductLocationComponentModel;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.session.SessionService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acc.controller.constants.ControllerConstants;
import com.acc.services.ProductLocationService;


/**
 * @author swapnil.a.pandey
 * 
 */
@Controller("ProductLocationComponentController")
@Scope("tenant")
@RequestMapping(value = ControllerConstants.Actions.Cms.PRODUCTLOCATIONCOMPONENT)
public class ProductLocationComponentController
{
	private static final Logger LOG = Logger.getLogger(ProductLocationComponentController.class);

	@Autowired
	private ProductLocationService productLocationService;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private ProductData productData;
	@Autowired
	ProductService productService;





	protected void fillModel(final HttpServletRequest request, final Model model, final ProductLocationComponentModel component)
	{
		final ProductModel currentProduct = getRequestContextData(request).getProduct();


		LOG.info("***********inside ProductLocationComponentController*************** " + currentProduct);

		final ProductModel product = productService.getProductForCode(currentProduct.getCode());
		final String beaconID = product.getBeaconId();
		/*
		 * final String beaconId = sessionService.getAttribute("beaconId"); sessionService.removeAttribute(beaconId);
		 */
		LOG.info("inside ProductLocationComponentController " + beaconID);
		final List<ProductData> productData = productLocationService.getProductsForBeaconId(beaconID);
		if (!CollectionUtils.isEmpty(productData))
		{
			for (final ProductData data : productData)
			{
				if (StringUtils.isNotEmpty(data.getLocation()))
				{
					model.addAttribute("productLocationData", data.getLocation());
					LOG.info("inside ProductLocationComponentController " + data.getLocation());


				}
			}
		}
	}

	protected RequestContextData getRequestContextData(final HttpServletRequest request)
	{
		return getBean(request, "requestContextData", RequestContextData.class);
	}

	protected <T> T getBean(final HttpServletRequest request, final String beanName, final Class<T> beanType)
	{
		return SpringHelper.getSpringBean(request, beanName, beanType, true);
	}

}
