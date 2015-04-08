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
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		final Map<String, Object> params = new HashMap<String, Object>();
		final CatalogVersionModel catalogVersion = getPresentCatalogVersion();
		final String catalog = catalogVersion.getPk().toString();
		final StringBuilder fQuery = new StringBuilder();
		fQuery.append("SELECT {").append(ProductModel.PK).append("} ");
		fQuery.append("FROM {").append(ProductModel._TYPECODE).append("} ");
		fQuery.append("WHERE {").append(ProductModel.BEACONID).append("}");
		fQuery.append(" = ?beaconId ");
		fQuery.append("and");
		fQuery.append("{").append(ProductModel.CATALOGVERSION).append("}");
		fQuery.append(" = ?catalog ");
		params.put("beaconId", beaconId);
		params.put("catalog", catalog);

		catalogVersionService.setSessionCatalogVersion("electronicsProductCatalog", "Online");
		LOG.info("catalog version" + catalogVersionService.getSessionCatalogVersionForCatalog("electronicsProductCatalog"));
		LOG.info("#########inside productLocationDaoImpl#########" + fQuery);

		final SearchResult<ProductModel> result = search(fQuery.toString(), params);


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
		final Map<String, Object> params = new HashMap<String, Object>();
		final CatalogVersionModel catalogVersion = getPresentCatalogVersion();
		final String catalog = catalogVersion.getPk().toString();
		final StringBuilder fQuery = new StringBuilder();
		validateParameterNotNull(code, "Product code must not be null!");
		fQuery.append("SELECT {").append(ProductModel.PK).append("} ");
		fQuery.append("FROM {").append(ProductModel._TYPECODE).append("} ");
		fQuery.append("WHERE {").append(ProductModel.CODE).append("}");
		fQuery.append(" = ?code ");
		fQuery.append("and");
		fQuery.append("{").append(ProductModel.CATALOGVERSION).append("}");
		fQuery.append(" = ?catalog ");
		params.put("code", code);
		params.put("catalog", catalog);

		catalogVersionService.setSessionCatalogVersion("electronicsProductCatalog", "Online");

		LOG.info("##insidefindProductsByCode##findProductsByCode###" + fQuery);

		final SearchResult<ProductModel> result = search(fQuery.toString(), params);

		LOG.info("####inside findProductsByCode#####findProductsByCode###" + result.getResult().get(0).getLocation());

		return result.getResult();
	}

}
