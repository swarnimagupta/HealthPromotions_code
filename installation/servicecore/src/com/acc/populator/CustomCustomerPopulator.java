/**
 *
 */
package com.acc.populator;

import de.hybris.platform.commercefacades.user.converters.populator.CustomerPopulator;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.acc.data.TrackLatLongData;
import com.acc.model.TrackLatLongModel;


/**
 * @author swarnima.gupta
 *
 */
public class CustomCustomerPopulator extends CustomerPopulator
{

	@Resource(name = "trackLatLongConverter")
	private Converter<TrackLatLongModel, TrackLatLongData> trackLatLongConverter;

	@Override
	public void populate(final CustomerModel source, final CustomerData target)
	{
		super.populate(source, target);

		if (CollectionUtils.isNotEmpty(source.getTrackLatLongList()))
		{
			final List<TrackLatLongData> trackLatLongList = new ArrayList<TrackLatLongData>();
			for (final TrackLatLongModel model : source.getTrackLatLongList())
			{
				trackLatLongList.add(trackLatLongConverter.convert(model));
			}
			target.setTrackLatLongList(trackLatLongList);
		}
	}
}
