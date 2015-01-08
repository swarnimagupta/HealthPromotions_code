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
	public void saveCustomerHealthData(final String uuid, final Integer heartBeatRate, final Integer bloodPressure,
			final Integer milesRun, final Integer caloriesBurned, final Integer timeTaken, final Integer age)
	{
		getCustomerHealthDataDao().saveCustomerHealthData(uuid, heartBeatRate, bloodPressure, milesRun, caloriesBurned, timeTaken,
				age);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.services.CustomerHealthDataService#getCustomerHealthData(java.lang.String)
	 */
	@Override
	public CustomerHealthDataModel getCustomerHealthData(final String uuid)
	{
		return getCustomerHealthDataDao().getCustomerHealthData(uuid);
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
