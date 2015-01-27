/**
 *
 */
package com.acc.services;

import com.acc.data.BeaconData;


/**
 * @author swarnima.gupta
 *
 */
public interface BeaconPromotionsService
{
	public BeaconData getBeaconById(final String beaconId, final String majorId, final String minorId);
}
