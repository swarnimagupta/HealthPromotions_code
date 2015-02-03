/**
 *
 */
package com.acc.dao;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;

import com.accenture.model.CustomerHealthDataModel;


/**
 * @author swarnima.gupta
 *
 */
public interface CustomerHealthDataDao extends Dao
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
	 */
	public void saveCustomerHealthData(final String customerId, final Integer heartBeatRate, final Integer bloodPressure,
			final Double milesRun, final Integer caloriesBurned, final Double timeTaken, final Integer age);

	/**
	 *
	 * @param customerId
	 * @return CustomerHealthDataModel
	 */
	public CustomerHealthDataModel getCustomerHealthData(final String customerId);

	/**
	 * @param customerId
	 * @return CustomerModel
	 */
	public CustomerModel isCustomerFound(String customerId);


}
