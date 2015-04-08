/**
 * 
 */
package com.acc.dao.impl;

import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.acc.dao.BeaconPromotionsDao;
import com.acc.model.BeaconModel;


/**
 * @author swapnil.a.pandey
 * 
 */
public class BeaconPromotionsDaoImpl extends AbstractItemDao implements BeaconPromotionsDao
{
	private static final Logger LOG = Logger.getLogger(BeaconPromotionsDaoImpl.class);


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
				+ beaconId + "' and {majorId}='" + majorId + "' and {minorId}='" + minorId + "'");
		final SearchResult<BeaconModel> result = getFlexibleSearchService().search(flexibleQuery);
		return result != null && CollectionUtils.isNotEmpty(result.getResult()) ? result.getResult().get(0) : new BeaconModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.dao.BeaconPromotionsDao#saveCustomerHeathPromotionData(java.lang.String)
	 */
	@Override
	public List<AbstractPromotionModel> getCustomerHeathPromotionData(final List<String> pkList)
	{

		LOG.info("::::::::::customerHealthDataModel :::::::::");
		final StringBuffer str = new StringBuffer();

		LOG.info("::::::::::pklist :::::::::" + pkList);

		for (final String string : pkList)
		{

			if (StringUtils.isNotEmpty(str.toString()))
			{
				str.append(" or ");
			}
			str.append("{users} like '%" + string + "%'");
		}
		final FlexibleSearchQuery query = new FlexibleSearchQuery("select {promotion} from {promotionuserrestriction} where " + str);
		final SearchResult<AbstractPromotionModel> result = getFlexibleSearchService().search(query);

		LOG.info("*****inside beaconpromotionsdaoimpl***************" + result);

		return result.getResult();


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.dao.BeaconPromotionsDao#getBeaconDetails()
	 */
	@Override
	public List<BeaconModel> getBeaconDetails()
	{
		LOG.info("*****inside beacondetailsdao***************");
		final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery("select {pk} from {Beacon}");
		final SearchResult<BeaconModel> result = getFlexibleSearchService().search(flexibleQuery);
		LOG.info("*****inside beacondetailsdao***************" + result);
		return result.getResult();
	}


	@Override
	public List<AbstractPromotionModel> getPromotionsForUsers(final String name)
	{
		final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery(
				"select {pk} from {AbstractPromotion} where {code} like '%" + name + "%'");


		final SearchResult<AbstractPromotionModel> result = getFlexibleSearchService().search(flexibleQuery);

		LOG.info("*****inside beaconpromotionsdaoimpl***promotionsForUsers***" + result);

		return result.getResult();
	}


}
