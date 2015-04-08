/**
 * 
 */
package com.acc.services;

import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import java.util.Collection;
import java.util.List;


/**
 * @author swapnil.a.pandey
 * 
 */
public interface ProductLocationService
{
	public List<ProductData> getProductsForBeaconId(String beaconId);

	ProductData getProductForCodeAndOptions(String code, Collection<ProductOption> options) throws UnknownIdentifierException,
			IllegalArgumentException;

}
