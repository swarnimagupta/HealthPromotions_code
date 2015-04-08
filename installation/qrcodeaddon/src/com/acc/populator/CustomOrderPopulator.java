/**
 *
 */
package com.acc.populator;

import de.hybris.platform.commercefacades.order.converters.populator.OrderPopulator;
import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.OrderModel;

import org.apache.log4j.Logger;


/**
 * @author swarnima.gupta
 *
 */
public class CustomOrderPopulator extends OrderPopulator
{
	private static final Logger LOG = Logger.getLogger(CustomOrderPopulator.class);

	@Override
	public void populate(final OrderModel source, final OrderData target)
	{
		super.populate(source, target);
		LOG.info(":::::::::::inside populate for Custom Order Populator::::::");
		if (null != source)
		{
			target.setUcoid(source.getUCOID());
		}
	}
}
