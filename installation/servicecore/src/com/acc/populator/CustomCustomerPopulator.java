/**
 *
 */
package com.acc.populator;

import de.hybris.platform.commercefacades.user.converters.populator.CustomerPopulator;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;


/**
 * @author swarnima.gupta
 * 
 */
public class CustomCustomerPopulator extends CustomerPopulator
{

	@Override
	public void populate(final CustomerModel source, final CustomerData target)
	{
		super.populate(source, target);
		target.setLatitudes(source.getLatitudes());
		target.setLongitudes(source.getLongitudes());
		target.setDate(source.getDate());
	}
}
