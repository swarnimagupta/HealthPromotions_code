/**
 *
 */
package com.acc.dao.impl;

import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.acc.dao.HPPromotionDao;


/**
 * @author swarnima.gupta
 *
 */
public class HPPromotionDaoImpl extends AbstractItemDao implements HPPromotionDao
{
	private static final Logger LOG = Logger.getLogger(HPPromotionDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.dao.HPPromotionDao#getPromotionByCode(java.lang.String)
	 */
	@Override
	public AbstractPromotionModel getPromotionByCode(final String code)
	{
		final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery("select {pk} from {abstractpromotion} where {code}='"
				+ code + "'");
		final SearchResult<AbstractPromotionModel> result = getFlexibleSearchService().search(flexibleQuery);
		return result != null && CollectionUtils.isNotEmpty(result.getResult()) ? result.getResult().get(0) : null;
	}

}
