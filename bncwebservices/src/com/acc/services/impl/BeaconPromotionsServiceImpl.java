/**
 *
 */
package com.acc.services.impl;

import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;

import com.acc.dao.BeaconPromotionsDao;
import com.acc.data.BeaconData;
import com.acc.services.BeaconPromotionsService;
import com.accenture.model.BeaconModel;


/**
 * @author swarnima.gupta
 *
 */
public class BeaconPromotionsServiceImpl implements BeaconPromotionsService
{

	private BeaconPromotionsDao beaconPromotionsDao;
	@Resource(name = "beaconConverter")
	private Converter<BeaconModel, BeaconData> beaconConverter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.services.BeaconPromotionsService#getAllPromotionsForBeacon(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public BeaconData getBeaconById(final String beaconId, final String majorId, final String minorId)
	{
		return beaconConverter.convert(getBeaconPromotionsDao().getBeaconById(beaconId, majorId, minorId));
	}

	/**
	 * @return the beaconPromotionsDao
	 */
	public BeaconPromotionsDao getBeaconPromotionsDao()
	{
		return beaconPromotionsDao;
	}

	/**
	 * @param beaconPromotionsDao
	 *           the beaconPromotionsDao to set
	 */
	public void setBeaconPromotionsDao(final BeaconPromotionsDao beaconPromotionsDao)
	{
		this.beaconPromotionsDao = beaconPromotionsDao;
	}

}
