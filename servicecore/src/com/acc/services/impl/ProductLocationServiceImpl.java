/**
 * 
 */
package com.acc.services.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateIfSingleResult;
import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;
import static java.lang.String.format;

import de.hybris.platform.commercefacades.converter.ConfigurablePopulator;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.acc.dao.ProductLocationDao;
import com.acc.services.CustomerHealthDataService;
import com.acc.services.ProductLocationService;
import com.accenture.model.ConfigModel;


/**
 * @author swapnil.a.pandey
 * 
 */
public class ProductLocationServiceImpl implements ProductLocationService
{
	private static final Logger LOG = Logger.getLogger(ProductLocationServiceImpl.class);
	private ProductLocationDao productLocationDao;
	@Resource(name = "productConverter")
	private Converter<ProductModel, ProductData> productConverter;
	@Autowired
	private CustomerHealthDataService customerHealthDataService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private FlexibleSearchService flexibleSearchService;
	@Autowired
	private ModelService modelService;

	private ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator;

	/**
	 * @return the productConfiguredPopulator
	 */
	public ConfigurablePopulator<ProductModel, ProductData, ProductOption> getProductConfiguredPopulator()
	{
		return productConfiguredPopulator;
	}

	/**
	 * @param productConfiguredPopulator
	 *           the productConfiguredPopulator to set
	 */
	public void setProductConfiguredPopulator(
			final ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator)
	{
		this.productConfiguredPopulator = productConfiguredPopulator;
	}

	/**
	 * @return the productConverter
	 */
	public Converter<ProductModel, ProductData> getProductConverter()
	{
		return productConverter;
	}

	/**
	 * @param productConverter
	 *           the productConverter to set
	 */
	public void setProductConverter(final Converter<ProductModel, ProductData> productConverter)
	{
		this.productConverter = productConverter;
	}

	/**
	 * @return the productLocationDao
	 */
	public ProductLocationDao getProductLocationDao()
	{
		return productLocationDao;
	}

	/**
	 * @param productLocationDao
	 *           the productLocationDao to set
	 */
	public void setProductLocationDao(final ProductLocationDao productLocationDao)
	{
		this.productLocationDao = productLocationDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.services.ProductLocationService#getProductsForBeaconId(java.lang.String)
	 */
	@Override
	public List<ProductData> getProductsForBeaconId(final String beaconId)
	{
		List<ProductModel> productList = new ArrayList<ProductModel>();
		ProductData product = new ProductData();
		final List<ProductData> productData = new ArrayList<ProductData>();


		LOG.info("######inside ProductLocationServiceImpl########## " + getProductLocationDao().getProductsForBeaconId(beaconId));


		productList = getProductLocationDao().getProductsForBeaconId(beaconId);

		if (CollectionUtils.isNotEmpty(productList))
		{
			for (final ProductModel model : productList)
			{
				product = productConverter.convert(model);
				productData.add(product);

			}
		}
		else
		{
			LOG.error("Invalid beaconID, please try again");

		}


		return productData;


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.services.ProductLocationService#getProductForCodeAndOptions(java.lang.String, java.util.Collection)
	 */
	@Override
	public ProductData getProductForCodeAndOptions(final String code, final Collection<ProductOption> options)
			throws UnknownIdentifierException, IllegalArgumentException
	{
		validateParameterNotNull(code, "Parameter code must not be null");
		final List<ProductModel> products = getProductLocationDao().findProductsByCode(code);
		LOG.info("#inside productlocationserviceImpl###getProductForCodeAndOptions#####" + products.get(0));

		validateIfSingleResult(products, format("Product with code '%s' not found!", code),
				format("Product code '%s' is not unique, %d products found!", code, Integer.valueOf(products.size())));

		final ProductModel productModel = products.get(0);
		if (!userService.isAnonymousUser(userService.getCurrentUser()))
		{
			final UserModel currentUser = userService.getCurrentUser();
			List<ProductModel> list = new ArrayList<ProductModel>();
			final List<ProductModel> listlatest = new ArrayList<ProductModel>();
			list = (List<ProductModel>) currentUser.getRecentlyviewedproducts();
			if (!list.contains(productModel))
			{
				for (final ProductModel productModel2 : list)
				{
					listlatest.add(productModel2);
				}
				final SearchResult<ConfigModel> searchResult = flexibleSearchService.search("select {pk} from {Config}");
				if (list.size() < searchResult.getResult().get(0).getValueHolder().intValue())
				{
					listlatest.add(productModel);
					currentUser.setRecentlyviewedproducts(listlatest);
					modelService.save(currentUser);
					LOG.info("******** Product saved to recent list in if condition ************");
				}
				else
				{
					listlatest.remove(0);
					listlatest.add(productModel);
					currentUser.setRecentlyviewedproducts(listlatest);
					modelService.save(currentUser);
					LOG.info("******** Product saved to recent list in else condition ************");
				}
			}

		}
		return getProductForOptions(productModel, options);

	}


	public ProductData getProductForOptions(final ProductModel productModel, final Collection<ProductOption> options)
	{
		final ProductData productData = getProductConverter().convert(productModel);
		LOG.info("#inside productlocationserviceImpl###getProductForOptions##" + productData.getCode());
		LOG.info("#inside productlocationserviceImpl###getProductForOptions##" + productData.getLocation());

		if (options != null)
		{
			getProductConfiguredPopulator().populate(productModel, productData, options);
		}

		return productData;
	}
}
