/**
 * 
 */
package com.acc.dao;

import de.hybris.platform.promotions.model.AbstractPromotionModel;

import java.util.List;

import com.accenture.model.BeaconModel;


/**
 * @author swapnil.a.pandey
 * 
 */
public interface BeaconPromotionsDao
{
	/**
	 * @param beaconId
	 * @param majorId
	 * @param minorId
	 * @return BeaconModel
	 */
	public BeaconModel getBeaconById(final String beaconId, final String majorId, final String minorId);

	public List<AbstractPromotionModel> getCustomerHeathPromotionData(final List<String> pkList);

	public List<BeaconModel> getBeaconDetails();


}
