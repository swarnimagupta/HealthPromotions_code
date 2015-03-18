/**
 *
 */
package com.acc.populator;

import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.acc.data.BeaconData;
import com.acc.model.BeaconModel;


/**
 * @author swarnima.gupta
 * 
 */
public class BeaconPopulator implements Populator<BeaconModel, BeaconData>
{
	@Resource(name = "promotionsConverter")
	private Converter<AbstractPromotionModel, PromotionData> promotionsConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final BeaconModel source, final BeaconData target) throws ConversionException
	{
		if (source != null)
		{
			target.setIdentifier(source.getIdentifier());
			target.setWelcomeMessage(source.getWelcomeMessage());
			target.setMajorId(source.getMajorId());
			target.setMinorId(source.getMinorId());
			final List<PromotionData> promotionsDatas = new ArrayList<PromotionData>();
			if (CollectionUtils.isNotEmpty(source.getPromotions()))
			{
				for (final AbstractPromotionModel model : source.getPromotions())
				{
					promotionsDatas.add(promotionsConverter.convert(model));
				}
				target.setPromotions(promotionsDatas);
			}
		}
	}

}
