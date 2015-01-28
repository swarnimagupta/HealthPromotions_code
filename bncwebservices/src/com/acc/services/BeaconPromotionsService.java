/**
 * 
 */
package com.acc.services;

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
}
