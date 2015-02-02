/**
 * 
 */
package com.acc.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogModel;
import de.hybris.platform.catalog.model.CatalogVersionModel;
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

	@Autowired(required = true)
	private CatalogService catalogService;


	/**
	 * @return the catalogService
	 */
	public CatalogService getCatalogService()
	{
		return catalogService;
	}

	/**
	 * @param catalogService
	 *           the catalogService to set
	 */
	public void setCatalogService(final CatalogService catalogService)
	{
		this.catalogService = catalogService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.dao.ProductLocationDao#getProductsForBeaconId(java.lang.String)
	 */
	public CatalogVersionModel getPresentCatalogVersion()
	{
		final CatalogModel catalog = catalogService.getCatalogForId("electronicsProductCatalog");

		final CatalogVersionModel catalogVersion = catalogService.getCatalogVersion(catalog.getId(), catalog.getVersion());
		return catalogVersion;
	}

	@Override
	public List<ProductModel> getProductsForBeaconId(final String beaconId)
	{

		final CatalogVersionModel catalogVersion = getPresentCatalogVersion();
		final String Catalog = catalogVersion.getPk().toString();
		final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery("select {pk} from {Product} where {beaconID}='"
				+ beaconId + "' and {catalogversion}='" + Catalog + "'");

		flexibleQuery.setUser(userService.getAdminUser());
		catalogVersionService.setSessionCatalogVersion("electronicsProductCatalog", "Online");

		LOG.info("#########inside productLocationDaoImpl#########" + flexibleQuery);
		
		final SearchResult<ProductModel> result = getFlexibleSearchService().search(flexibleQuery);

		LOG.info("#########inside productLocationDaoImpl#########" + result.getResult().get(0).getLocation());

		return result.getResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.dao.ProductLocationDao#findProductsByCode(java.lang.String)
	 */
	@Override
	public List<ProductModel> findProductsByCode(final String code)
	{

		final CatalogVersionModel catalogVersion = getPresentCatalogVersion();
		final String Catalog = catalogVersion.getPk().toString();

		validateParameterNotNull(code, "Product code must not be null!");
		final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery("select {pk} from {Product} where {code}='" + code
				+ "' and {catalogversion}='" + Catalog + "'");

		flexibleQuery.setUser(userService.getAdminUser());
		catalogVersionService.setSessionCatalogVersion("electronicsProductCatalog", "Online");

		LOG.info("##insidefindProductsByCode##findProductsByCode###" + flexibleQuery);
	
		final SearchResult<ProductModel> result = getFlexibleSearchService().search(flexibleQuery);

		LOG.info("####inside findProductsByCode#####findProductsByCode###" + result.getResult().get(0).getLocation());

		return result.getResult();
	}

}
