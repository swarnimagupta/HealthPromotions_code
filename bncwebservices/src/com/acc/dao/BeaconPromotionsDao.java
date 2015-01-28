/**
 * 
 */
package com.acc.dao;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.promotions.model.PromotionUserRestrictionModel;

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

	public List<PromotionUserRestrictionModel> getCustomerHeathPromotionData(final List<String> pkList);

	public CustomerModel CheckEmailId(String emailId);
}
