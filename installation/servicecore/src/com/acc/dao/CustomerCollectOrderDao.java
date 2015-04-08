/**
 *
 */
package com.acc.dao;

import de.hybris.platform.core.model.order.OrderModel;

import java.util.List;

import com.accenture.model.CollectOrderModel;



/**
 * @author swarnima.gupta
 *
 */
public interface CustomerCollectOrderDao
{
	/**
	 * @param ucoid
	 * @return CollectOrderModel
	 */
	public CollectOrderModel getCollectOrderByUCOID(String ucoid);

	/**
	 * @param orderCode
	 * @return CollectOrderModel
	 */
	public CollectOrderModel getCollectOrderByOrderCode(String orderCode);

	/**
	 *
	 * @param customerID
	 * @return
	 */
	public List<CollectOrderModel> getCustomerListOrders(String customerID);

	/**
	 * @param orderCode
	 * @return OrderModel
	 */
	public OrderModel getOrderDetailsForCode(String orderCode);
}
