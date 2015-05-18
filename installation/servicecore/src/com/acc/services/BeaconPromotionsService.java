/**
 *
 */
package com.acc.services;


import de.hybris.platform.commercefacades.product.data.PromotionData;

import java.util.List;

import com.acc.data.BeaconData;
import com.acc.product.data.PromotionDataList;


/**
 * @author swapnil.a.pandey
 *
 */
public interface BeaconPromotionsService
{
	public BeaconData getBeaconById(final String beaconId, final String majorId, final String minorId);

	public PromotionDataList getCustomerHeathPromotionData(String emailId);

	public List<BeaconData> getBeaconDetails();

	public PromotionDataList getPromotionsForUsers(final String name);

	public List<PromotionData> getPromotionsBasedOnClimate(final String condition);

}
