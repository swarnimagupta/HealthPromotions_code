/**
 * 
 */
package com.acc.dao.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.promotions.model.PromotionUserRestrictionModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.acc.dao.BeaconPromotionsDao;
import com.accenture.model.BeaconModel;


/**
 * @author swapnil.a.pandey
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
	public List<PromotionUserRestrictionModel> getCustomerHeathPromotionData(final List<String> pkList)
	{
		final Map<String, Object> params = new HashMap<String, Object>();

		System.out.println("::::::::::customerHealthDataModel :::::::::");
		final StringBuffer str = new StringBuffer();
		str.append("SELECT {").append(PromotionUserRestrictionModel.PK).append("} ");
		str.append("FROM {").append(PromotionUserRestrictionModel._TYPECODE).append("} ");
		str.append("WHERE {").append(PromotionUserRestrictionModel.USERS).append("}");
		for (final String string : pkList)
		{

			str.append(" IN ( ?string ) ");
			params.put("string", string);

			//str.append(StringUtils.isNotEmpty(str.toString()) ? " or " : "{users} like '%" + string + "%'");
		}


		//final FlexibleSearchQuery query = new FlexibleSearchQuery("select {pk} from {promotionuserrestriction} where {users} "
		//+ str.toString());
		final SearchResult<PromotionUserRestrictionModel> result = getFlexibleSearchService().search(str.toString(), params);
		return result.getResult();



	}

	@Override
	public CustomerModel CheckEmailId(final String emailId)
	{
		final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery("select {pk} from {Customer} where {uid} like '"
				+ emailId + "%'");
		final SearchResult<CustomerModel> result = getFlexibleSearchService().search(flexibleQuery);
		return result != null && CollectionUtils.isNotEmpty(result.getResult()) ? result.getResult().get(0) : null;

	}

}
