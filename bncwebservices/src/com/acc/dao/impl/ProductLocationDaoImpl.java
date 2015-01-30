/**
 * 
 */
package com.acc.dao.impl;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.acc.dao.ProductLocationDao;


/**
 * @author swapnil.a.pandey
 * 
 */
public class ProductLocationDaoImpl extends AbstractItemDao implements ProductLocationDao
{
	private static final Logger LOG = Logger.getLogger(ProductLocationDaoImpl.class);

	@Autowired
	private UserService userService;
	@Autowired
	private CatalogVersionService catalogVersionService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.dao.ProductLocationDao#getProductsForBeaconId(java.lang.String)
	 */
	@Override
	public List<ProductModel> getProductsForBeaconId(final String beaconId)
	{
		final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery("select {pk} from {Product} where {beaconID}='"
				+ beaconId + "'");

		flexibleQuery.setUser(userService.getAdminUser());
		catalogVersionService.setSessionCatalogVersion("electronicsProductCatalog", "Online");

		LOG.info("#########inside productLocationDaoImpl#########" + flexibleQuery);

		final SearchResult<ProductModel> result = getFlexibleSearchService().search(flexibleQuery);

		LOG.info("#########inside productLocationDaoImpl#########" + result.getResult().get(0).getLocation());

		return result.getResult();
	}

}
