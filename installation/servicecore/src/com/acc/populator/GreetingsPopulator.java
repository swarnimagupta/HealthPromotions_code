/**
 *
 */
package com.acc.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import org.springframework.util.Assert;

import com.acc.data.GreetingsData;
import com.acc.model.GreetingModel;


/**
 * @author swarnima.gupta
 *
 */
public class GreetingsPopulator implements Populator<GreetingModel, GreetingsData>
{

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final GreetingModel source, final GreetingsData target) throws ConversionException
	{
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(target, "Parameter target cannot be null.");
		target.setMessage(source.getMessage());
		target.setCondition(source.getCondition());
		target.setCode(source.getCode());
	}

}
