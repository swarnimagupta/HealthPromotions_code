/**
 * 
 */
package com.acc.populator;

import de.hybris.platform.commercefacades.product.converters.populator.PromotionsPopulator;
import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.promotions.model.AbstractPromotionModel;


/**
 * @author swapnil.a.pandey
 * 
 */
public class RegionBasedPromotionPopulator extends PromotionsPopulator
{

	@Override
	public void populate(final AbstractPromotionModel source, final PromotionData target)

	{
		if (source != null)
		{
			target.setCloudCoverRange(source.getCloudCoverRange());
			target.setHumidityRange(source.getHumidityRange());
			target.setTemperatureRange(source.getTemperatureRange());
		}
	}
}
