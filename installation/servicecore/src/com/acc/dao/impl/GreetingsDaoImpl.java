/**
 *
 */
package com.acc.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.acc.dao.GreetingsDao;
import com.acc.model.GreetingModel;


/**
 * @author swarnima.gupta
 *
 */
public class GreetingsDaoImpl extends AbstractItemDao implements GreetingsDao
{

	private static final Logger LOG = Logger.getLogger(GreetingsDaoImpl.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acc.dao.GreetingsDao#getGreetingsForCondition(java.lang.String)
	 */
	@Override
	public GreetingModel getGreetingsForCondition(final String condition)
	{
		LOG.info("*****inside GreetingsDaoImpl***************");
		final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery("select {pk} from {Greeting} where {condition}='"
				+ condition + "'");
		final SearchResult<GreetingModel> result = getFlexibleSearchService().search(flexibleQuery);
		return CollectionUtils.isNotEmpty(result.getResult()) ? result.getResult().get(0) : new GreetingModel();
	}

}
