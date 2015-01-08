/**
 * 
 */
package com.acc.services;

import com.accenture.model.CustomerHealthDataModel;


/**
 * @author swarnima.gupta
 * 
 */
public interface CustomerHealthDataService
{
	public void saveCustomerHealthData(final String uuid, final Integer heartBeatRate, final Integer bloodPressure,
			final Integer milesRun, final Integer caloriesBurned, final Integer timeTaken, final Integer age);

	public CustomerHealthDataModel getCustomerHealthData(final String uuid);

}
