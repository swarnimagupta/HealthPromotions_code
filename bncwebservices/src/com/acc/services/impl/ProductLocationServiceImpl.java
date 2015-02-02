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
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.ModelNotFoundException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.acc.dao.ProductLocationDao;
import com.acc.services.ProductLocationService;


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

		try
		{


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
				throw new ModelNotFoundException("Invalid BeaconID!");

			}
		}
		catch (final Exception e)
		{
			LOG.error("Invalid beaconID, please try again", e);
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
