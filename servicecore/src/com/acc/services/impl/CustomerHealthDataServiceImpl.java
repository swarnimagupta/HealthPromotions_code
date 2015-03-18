/**
 *
 */
package com.acc.services.impl;

import org.apache.log4j.Logger;

import com.acc.dao.CustomerHealthDataDao;
import com.acc.model.CustomerHealthDataModel;
import com.acc.services.CustomerHealthDataService;


/**
 * @author swarnima.gupta
 * 
 */
public class CustomerHealthDataServiceImpl implements CustomerHealthDataService
{

	private static final Logger LOG = Logger.getLogger(CustomerHealthDataServiceImpl.class);
	private CustomerHealthDataDao customerHealthDataDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.services.CustomerHealthDataService#saveCustomerHealthData(java.lang.String, java.lang.Integer,
	 * java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String saveCustomerHealthData(final String customerId, final String HKCategoryTypeIdentifierSleepAnalysis,
			final String HKQuantityTypeIdentifierActiveEnergyBurned, final String HKQuantityTypeIdentifierBasalEnergyBurned,
			final String HKQuantityTypeIdentifierBloodPressureDiastolic, final String HKQuantityTypeIdentifierBloodPressureSystolic,
			final String HKQuantityTypeIdentifierBodyFatPercentage, final String HKQuantityTypeIdentifierBodyMass,
			final String HKQuantityTypeIdentifierBodyMassIndex, final String HKQuantityTypeIdentifierDietaryBiotin,
			final String HKQuantityTypeIdentifierDietaryCaffeine, final String HKQuantityTypeIdentifierDietaryCalcium,
			final String HKQuantityTypeIdentifierDietaryCarbohydrates, final String HKQuantityTypeIdentifierDietaryChloride,
			final String HKQuantityTypeIdentifierDietaryChromium, final String HKQuantityTypeIdentifierDietaryCopper,
			final String HKQuantityTypeIdentifierDietaryEnergyConsumed,
			final String HKQuantityTypeIdentifierDietaryFatMonounsaturated,
			final String HKQuantityTypeIdentifierDietaryFatPolyunsaturated,
			final String HKQuantityTypeIdentifierDietaryFatSaturated, final String HKQuantityTypeIdentifierDietaryFatTotal,
			final String HKQuantityTypeIdentifierDietaryFiber, final String HKQuantityTypeIdentifierDietaryFolate,
			final String HKQuantityTypeIdentifierDietaryIodine, final String HKQuantityTypeIdentifierDietaryIron,
			final String HKQuantityTypeIdentifierDietaryMagnesium, final String HKQuantityTypeIdentifierDietaryManganese,
			final String HKQuantityTypeIdentifierDietaryMolybdenum, final String HKQuantityTypeIdentifierDietaryNiacin,
			final String HKQuantityTypeIdentifierDietaryPantothenicAcid, final String HKQuantityTypeIdentifierDietaryPhosphorus,
			final String HKQuantityTypeIdentifierDietaryPotassium, final String HKQuantityTypeIdentifierDietaryProtein,
			final String HKQuantityTypeIdentifierDietaryRiboflavin, final String HKQuantityTypeIdentifierDietarySelenium,
			final String HKQuantityTypeIdentifierDietarySodium, final String HKQuantityTypeIdentifierDietarySugar,
			final String HKQuantityTypeIdentifierDietaryThiamin, final String HKQuantityTypeIdentifierDietaryVitaminA,
			final String HKQuantityTypeIdentifierDietaryVitaminB12, final String HKQuantityTypeIdentifierDietaryVitaminB6,
			final String HKQuantityTypeIdentifierDietaryVitaminC, final String HKQuantityTypeIdentifierDietaryVitaminD,
			final String HKQuantityTypeIdentifierDietaryVitaminE, final String HKQuantityTypeIdentifierDietaryVitaminK,
			final String HKQuantityTypeIdentifierDietaryZinc, final String HKQuantityTypeIdentifierDistanceCycling,
			final String HKQuantityTypeIdentifierDistanceWalkingRunning, final String HKQuantityTypeIdentifierFlightsClimbed,
			final String HKQuantityTypeIdentifierHeartRate, final String HKQuantityTypeIdentifierOxygenSaturation,
			final String HKQuantityTypeIdentifierRespiratoryRate, final String HKQuantityTypeIdentifierStepCount,
			final String HKQuantityTypeIdentifierDietaryCholesterol)
	{

		LOG.info("inside CustomerHealthDataServiceImpl ********************");
		if (getCustomerHealthDataDao().isCustomerFound(customerId) == null)
		{

			LOG.info("inside CustomerHealthDataServiceImpl *******error*************");

			return "error";
		}

		LOG.info("inside CustomerHealthDataServiceImpl *******success*************");

		getCustomerHealthDataDao().saveCustomerHealthData(customerId, HKCategoryTypeIdentifierSleepAnalysis,
				HKQuantityTypeIdentifierActiveEnergyBurned, HKQuantityTypeIdentifierBasalEnergyBurned,
				HKQuantityTypeIdentifierBloodPressureDiastolic, HKQuantityTypeIdentifierBloodPressureSystolic,
				HKQuantityTypeIdentifierBodyFatPercentage, HKQuantityTypeIdentifierBodyMass, HKQuantityTypeIdentifierBodyMassIndex,
				HKQuantityTypeIdentifierDietaryBiotin, HKQuantityTypeIdentifierDietaryCaffeine,
				HKQuantityTypeIdentifierDietaryCalcium, HKQuantityTypeIdentifierDietaryCarbohydrates,
				HKQuantityTypeIdentifierDietaryChloride, HKQuantityTypeIdentifierDietaryChromium,
				HKQuantityTypeIdentifierDietaryCopper, HKQuantityTypeIdentifierDietaryEnergyConsumed,
				HKQuantityTypeIdentifierDietaryFatMonounsaturated, HKQuantityTypeIdentifierDietaryFatPolyunsaturated,
				HKQuantityTypeIdentifierDietaryFatSaturated, HKQuantityTypeIdentifierDietaryFatTotal,
				HKQuantityTypeIdentifierDietaryFiber, HKQuantityTypeIdentifierDietaryFolate, HKQuantityTypeIdentifierDietaryIodine,
				HKQuantityTypeIdentifierDietaryIron, HKQuantityTypeIdentifierDietaryMagnesium,
				HKQuantityTypeIdentifierDietaryManganese, HKQuantityTypeIdentifierDietaryMolybdenum,
				HKQuantityTypeIdentifierDietaryNiacin, HKQuantityTypeIdentifierDietaryPantothenicAcid,
				HKQuantityTypeIdentifierDietaryPhosphorus, HKQuantityTypeIdentifierDietaryPotassium,
				HKQuantityTypeIdentifierDietaryProtein, HKQuantityTypeIdentifierDietaryRiboflavin,
				HKQuantityTypeIdentifierDietarySelenium, HKQuantityTypeIdentifierDietarySodium, HKQuantityTypeIdentifierDietarySugar,
				HKQuantityTypeIdentifierDietaryThiamin, HKQuantityTypeIdentifierDietaryVitaminA,
				HKQuantityTypeIdentifierDietaryVitaminB12, HKQuantityTypeIdentifierDietaryVitaminB6,
				HKQuantityTypeIdentifierDietaryVitaminC, HKQuantityTypeIdentifierDietaryVitaminD,
				HKQuantityTypeIdentifierDietaryVitaminE, HKQuantityTypeIdentifierDietaryVitaminK,
				HKQuantityTypeIdentifierDietaryZinc, HKQuantityTypeIdentifierDistanceCycling,
				HKQuantityTypeIdentifierDistanceWalkingRunning, HKQuantityTypeIdentifierFlightsClimbed,
				HKQuantityTypeIdentifierHeartRate, HKQuantityTypeIdentifierOxygenSaturation, HKQuantityTypeIdentifierRespiratoryRate,
				HKQuantityTypeIdentifierStepCount, HKQuantityTypeIdentifierDietaryCholesterol);
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
