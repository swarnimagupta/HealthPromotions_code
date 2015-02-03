/**
 *
 */
package com.acc.services.impl;

import com.acc.dao.CustomerHealthDataDao;
import com.acc.services.CustomerHealthDataService;
import com.accenture.model.CustomerHealthDataModel;


/**
 * @author swarnima.gupta
 *
 */
public class CustomerHealthDataServiceImpl implements CustomerHealthDataService
{
	private CustomerHealthDataDao customerHealthDataDao;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acc.services.CustomerHealthDataService#saveCustomerHealthData(java.lang.String, java.lang.Integer,
	 * java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String saveCustomerHealthData(final String customerId, final Integer heartBeatRate, final Integer bloodPressure,
			final Double milesRun, final Integer caloriesBurned, final Double timeTaken, final Integer age)
	{
		if (getCustomerHealthDataDao().isCustomerFound(customerId) == null)
		{
			return "error";
		}
		getCustomerHealthDataDao().saveCustomerHealthData(customerId, heartBeatRate, bloodPressure, milesRun, caloriesBurned,
				timeTaken, age);
		return "success";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acc.services.CustomerHealthDataService#getCustomerHealthData(java.lang.String)
	 */
	@Override
	public CustomerHealthDataModel getCustomerHealthData(final String customerId)
	{
		return getCustomerHealthDataDao().getCustomerHealthData(customerId);
	}

	/**
	 * @return the customerHealthDataDao
	 */
	public CustomerHealthDataDao getCustomerHealthDataDao()
	{
		return customerHealthDataDao;
	}

	/**
	 * @param customerHealthDataDao
	 *           the customerHealthDataDao to set
	 */
	public void setCustomerHealthDataDao(final CustomerHealthDataDao customerHealthDataDao)
	{
		this.customerHealthDataDao = customerHealthDataDao;
	}
}
