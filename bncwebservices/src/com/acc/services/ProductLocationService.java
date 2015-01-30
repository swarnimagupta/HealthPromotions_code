/**
 * 
 */
package com.acc.services;

import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.List;


/**
 * @author swapnil.a.pandey
 * 
 */
public interface ProductLocationService
{
	public List<ProductData> getProductsForBeaconId(String beaconId);
}
