/**
 *
 */
package com.acc.services;

import de.hybris.platform.commercefacades.order.data.OrderData;

import java.util.List;

import com.acc.data.CollectOrderWsData;


/**
 * @author swarnima.gupta
 *
 */
public interface CustomerCollectOrderService
{
	/**
	 * @param ucoid
	 * @return CollectOrderWsData
	 */
	public CollectOrderWsData getCollectOrderByUCOID(String ucoid);

	/**
	 * @param orderCode
	 * @return CollectOrderData
	 */
	public CollectOrderWsData getCollectOrderByOrderCode(String orderCode);


	/**
	 *
	 * @param customerID
	 * @return List<CollectOrderData>
	 */
	public List<CollectOrderWsData> getCustomerListOrders(final String customerID);

	/**
	 * @param orderCode
	 * @return OrderModel
	 */
	public OrderData getOrderDetailsForCode(String orderCode);

}
