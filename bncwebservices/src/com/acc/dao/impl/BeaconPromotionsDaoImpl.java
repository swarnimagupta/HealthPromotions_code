/**
 *
 */
package com.acc.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import org.apache.commons.collections.CollectionUtils;

import com.acc.dao.BeaconPromotionsDao;
import com.accenture.model.BeaconModel;


/**
 * @author swarnima.gupta
 *
 */
public class BeaconPromotionsDaoImpl extends AbstractItemDao implements BeaconPromotionsDao
{

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acc.dao.BeaconPromotionsDao#getAllPromotionsForBeacon(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public BeaconModel getBeaconById(final String beaconId, final String majorId, final String minorId)
	{
		final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery("select {pk} from {Beacon} where {identifier}='"
				+ beaconId + "' and {majorId}='" + majorId + "' and minorId='" + minorId + "'");
		final SearchResult<BeaconModel> result = getFlexibleSearchService().search(flexibleQuery);
		return result != null && CollectionUtils.isNotEmpty(result.getResult()) ? result.getResult().get(0) : new BeaconModel();
	}

}
