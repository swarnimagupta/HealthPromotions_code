/**
 *
 */
package com.acc.dao;


import com.accenture.model.BeaconModel;


/**
 * @author swarnima.gupta
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

}
