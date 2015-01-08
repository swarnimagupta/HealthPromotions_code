/**
 * 
 */
package com.acc.dao;

import de.hybris.platform.servicelayer.internal.dao.Dao;

import java.util.List;

import com.accenture.model.CustomerHealthDataModel;


/**
 * @author swarnima.gupta
 * 
 */
public interface CustomerHealthDataDao extends Dao
{
	public void saveCustomerHealthData(final String uuid, final Integer heartBeatRate, final Integer bloodPressure,
			final Integer milesRun, final Integer caloriesBurned, final Integer timeTaken, final Integer age);

	public CustomerHealthDataModel getCustomerHealthData(final String uuid);

	public List<CustomerHealthDataModel> getAllCustomersHealthData();

}
