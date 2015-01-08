/**
 * 
 */
package com.acc.dao.impl;

import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.acc.dao.CustomerHealthDataDao;
import com.accenture.model.CustomerHealthDataModel;


/**
 * @author swarnima.gupta
 * 
 */
public class CustomerHealthDataDaoImpl extends AbstractItemDao implements CustomerHealthDataDao
{
	private static final Logger LOG = Logger.getLogger(CustomerHealthDataDaoImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accenture.dao.CustomerHealthDataDao#saveCustomerHealthData(java.lang.String, java.lang.Integer,
	 * java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void saveCustomerHealthData(final String uuid, final Integer heartBeatRate, final Integer bloodPressure,
			final Integer milesRun, final Integer caloriesBurned, final Integer timeTaken, final Integer age)
	{
		final CustomerHealthDataModel customerHealthDataModel = getCustomerHealthData(uuid);
		if (customerHealthDataModel != null)
		{
			customerHealthDataModel.setHeartBeatRate(heartBeatRate);
			customerHealthDataModel.setBloodPressure(bloodPressure);
			customerHealthDataModel.setMilesRun(milesRun);
			customerHealthDataModel.setCaloriesBurned(caloriesBurned);
			customerHealthDataModel.setTimeTaken(timeTaken);
			customerHealthDataModel.setAge(age);
			getModelService().save(customerHealthDataModel);
			LOG.info("::::::::::Health Data saved for the customer :::::::::");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accenture.dao.CustomerHealthDataDao#getCustomerHealthData(java.lang.String)
	 */
	@Override
	public CustomerHealthDataModel getCustomerHealthData(final String uuid)
	{
		final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery(
				"select {pk} from {CustomerHealthData} where {UUID} like '" + uuid + "%'");
		final SearchResult<CustomerHealthDataModel> result = getFlexibleSearchService().search(flexibleQuery);
		return result != null && CollectionUtils.isNotEmpty(result.getResult()) ? result.getResult().get(0) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accenture.dao.CustomerHealthDataDao#getAllCustomersHealthData()
	 */
	@Override
	public List<CustomerHealthDataModel> getAllCustomersHealthData()
	{
		// YTODO Auto-generated method stub
		return null;
	}

}
