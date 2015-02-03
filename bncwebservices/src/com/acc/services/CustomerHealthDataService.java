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
	/**
	 *
	 * @param customerId
	 * @param heartBeatRate
	 * @param bloodPressure
	 * @param milesRun
	 * @param caloriesBurned
	 * @param timeTaken
	 * @param age
	 * @return String
	 */
	public String saveCustomerHealthData(final String customerId, final Integer heartBeatRate, final Integer bloodPressure,
			final Double milesRun, final Integer caloriesBurned, final Double timeTaken, final Integer age);

	/**
	 *
	 * @param customerId
	 * @return CustomerHealthDataModel
	 */
	public CustomerHealthDataModel getCustomerHealthData(final String customerId);

}
