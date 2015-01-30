/**
 * 
 */
package com.acc.services.impl;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
		LOG.info("######inside ProductLocationServiceImpl########## "
				+ getProductLocationDao().getProductsForBeaconId(beaconId).get(0).getCode());
		productList = getProductLocationDao().getProductsForBeaconId(beaconId);

		for (final ProductModel model : productList)
		{
			product = productConverter.convert(model);
			productData.add(product);
			LOG.info("######inside ProductLocationServiceImpl beacon Id ########## "
					+ getProductLocationDao().getProductsForBeaconId(beaconId).get(0).getCode());
			LOG.info("######inside ProductLocationServiceImpl location########## " + productData.get(0).getLocation());

		}

		LOG.info("######inside ProductLocationServiceImpl########## location after for loop" + productData.get(0).getLocation());


		return productData;


	}

}
