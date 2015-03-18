/**
 * 
 */
package com.acc.dao;

import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;


/**
 * @author swapnil.a.pandey
 * 
 */
public interface ProductLocationDao
{
	public List<ProductModel> getProductsForBeaconId(String beaconId);

	List<ProductModel> findProductsByCode(final String code);
}
