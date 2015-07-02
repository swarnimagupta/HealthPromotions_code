/**
 *
 */
package com.acc.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.acc.data.TrackLatLongData;
import com.acc.model.TrackLatLongModel;


/**
 * @author swarnima.gupta
 *
 */
public class TrackLatLongPopulator implements Populator<TrackLatLongModel, TrackLatLongData>
{

	@Override
	public void populate(final TrackLatLongModel source, final TrackLatLongData target) throws ConversionException
	{
		if (source != null)
		{
			target.setDate(source.getCreationtime());
			target.setLatitude(source.getLatitude());
			target.setLongitude(source.getLongitude());
			target.setCode(source.getCode());
		}
	}
}
