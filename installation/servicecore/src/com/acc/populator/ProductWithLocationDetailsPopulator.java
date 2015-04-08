/**
 * 
 */
package com.acc.populator;

import de.hybris.platform.commercefacades.product.converters.populator.ProductPopulator;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.apache.log4j.Logger;


/**
 * @author swapnil.a.pandey
 * 
 */
public class ProductWithLocationDetailsPopulator extends ProductPopulator
{
	private static final Logger LOG = Logger.getLogger(ProductWithLocationDetailsPopulator.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final ProductModel source, final ProductData target) throws ConversionException
	{
		LOG.info("###inside populator########");

		super.populate(source, target);
		// YTODO Auto-generated method stub
		if (source != null)
		{
			LOG.info("###inside populator######## source " + source.getLocation());

			target.setBeaconId(source.getBeaconId());
			target.setLocation(source.getLocation());
			LOG.info("###inside populator######## target " + target.getLocation());


		}
	}

}
