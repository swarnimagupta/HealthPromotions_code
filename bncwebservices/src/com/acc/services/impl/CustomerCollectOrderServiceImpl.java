/**
 *
 */
package com.acc.services.impl;

import de.hybris.platform.commercefacades.order.data.OrderData;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.acc.dao.CustomerCollectOrderDao;
import com.acc.data.CollectOrderWsData;
import com.acc.services.CustomerCollectOrderService;
import com.accenture.model.CollectOrderModel;



/**
 * @author swarnima.gupta
 *
 */
public class CustomerCollectOrderServiceImpl implements CustomerCollectOrderService
{
	private static final Logger LOG = Logger.getLogger(CustomerCollectOrderServiceImpl.class);
	@Resource(name = "collectOrderWsConverter")
	private Converter<CollectOrderModel, CollectOrderWsData> collectOrderWsConverter;

	@Resource(name = "orderConverter")
	private Converter<OrderModel, OrderData> orderConverter;

	private CustomerCollectOrderDao collectOrderDao;



	/**
	 * @param collectOrderDao
	 *           the collectOrderDao to set
	 */
	public void setCollectOrderDao(final CustomerCollectOrderDao collectOrderDao)
	{
		this.collectOrderDao = collectOrderDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acc.core.services.collectorder.CustomerCollectOrderService#getCollectOrderByUCOID(java.lang.String)
	 */
	@Override
	public CollectOrderWsData getCollectOrderByUCOID(final String ucoid)
	{
		return collectOrderWsConverter.convert(collectOrderDao.getCollectOrderByUCOID(ucoid));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acc.core.services.collectorder.CustomerCollectOrderService#getCollectOrderByOrderCode(java.lang.String)
	 */
	@Override
	public CollectOrderWsData getCollectOrderByOrderCode(final String orderCode)
	{
		return collectOrderWsConverter.convert(collectOrderDao.getCollectOrderByOrderCode(orderCode));
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see com.acc.core.services.collectorder.CustomerCollectOrderService#getCustomerListOrders(java.lang.String)
	 */
	@Override
	public List<CollectOrderWsData> getCustomerListOrders(final String customerID)
	{
		final List<CollectOrderModel> customerOrdersList = collectOrderDao.getCustomerListOrders(customerID);
		return convert(customerOrdersList);
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see com.acc.core.services.collectorder.CustomerCollectOrderService#getOrderDetailsForCode(java.lang.String)
	 */
	@Override
	public OrderData getOrderDetailsForCode(final String orderCode)
	{
		return orderConverter.convert(collectOrderDao.getOrderDetailsForCode(orderCode));
	}

	/**
	 * @param collectOrderModelsList
	 * @return List<CollectOrderData>
	 */
	private List<CollectOrderWsData> convert(final List<CollectOrderModel> collectOrderModelsList)
	{
		final List<CollectOrderWsData> collectOrderDataList = new ArrayList<CollectOrderWsData>();
		if (CollectionUtils.isNotEmpty(collectOrderModelsList))
		{
			for (final CollectOrderModel collectOrderModel : collectOrderModelsList)
			{

				collectOrderDataList.add(collectOrderWsConverter.convert(collectOrderModel));
			}
		}
		return collectOrderDataList;
	}

}
