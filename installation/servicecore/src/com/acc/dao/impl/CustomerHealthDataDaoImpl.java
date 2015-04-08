/**
 *
 */
package com.acc.dao.impl;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.acc.dao.CustomerHealthDataDao;
import com.acc.model.CustomerHealthDataModel;


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
	public void saveCustomerHealthData(final String customerId, final String HKCategoryTypeIdentifierSleepAnalysis,
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
		final CustomerHealthDataModel customerHealthDataModel = getCustomerHealthData(customerId) != null ? getCustomerHealthData(customerId)
				: (CustomerHealthDataModel) getModelService().create(CustomerHealthDataModel.class);
		LOG.info("::::::::::customerHealthDataModel :::::::::" + customerHealthDataModel);
		customerHealthDataModel.setCustomerId(customerId);
		customerHealthDataModel.setHkCategoryTypeIdentifierSleepAnalysis(HKCategoryTypeIdentifierSleepAnalysis);
		customerHealthDataModel.setHkQuantityTypeIdentifierActiveEnergyBurned(HKQuantityTypeIdentifierActiveEnergyBurned);
		customerHealthDataModel.setHkQuantityTypeIdentifierBasalEnergyBurned(HKQuantityTypeIdentifierBasalEnergyBurned);
		customerHealthDataModel.setHkQuantityTypeIdentifierBloodPressureDiastolic(HKQuantityTypeIdentifierBloodPressureDiastolic);
		customerHealthDataModel.setHkQuantityTypeIdentifierBloodPressureSystolic(HKQuantityTypeIdentifierBloodPressureSystolic);
		customerHealthDataModel.setHkQuantityTypeIdentifierBodyFatPercentage(HKQuantityTypeIdentifierBodyFatPercentage);
		customerHealthDataModel.setHkQuantityTypeIdentifierBodyMass(HKQuantityTypeIdentifierBodyMass);
		customerHealthDataModel.setHkQuantityTypeIdentifierBodyMassIndex(HKQuantityTypeIdentifierBodyMassIndex);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryBiotin(HKQuantityTypeIdentifierDietaryBiotin);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryCaffeine(HKQuantityTypeIdentifierDietaryCaffeine);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryCalcium(HKQuantityTypeIdentifierDietaryCalcium);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryCarbohydrates(HKQuantityTypeIdentifierDietaryCarbohydrates);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryChloride(HKQuantityTypeIdentifierDietaryChloride);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryChromium(HKQuantityTypeIdentifierDietaryChromium);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryCopper(HKQuantityTypeIdentifierDietaryCopper);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryEnergyConsumed(HKQuantityTypeIdentifierDietaryEnergyConsumed);
		customerHealthDataModel
				.setHkQuantityTypeIdentifierDietaryFatMonounsaturated(HKQuantityTypeIdentifierDietaryFatMonounsaturated);
		customerHealthDataModel
				.setHkQuantityTypeIdentifierDietaryFatPolyunsaturated(HKQuantityTypeIdentifierDietaryFatPolyunsaturated);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryFatSaturated(HKQuantityTypeIdentifierDietaryFatSaturated);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryFatTotal(HKQuantityTypeIdentifierDietaryFatTotal);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryFiber(HKQuantityTypeIdentifierDietaryFiber);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryFolate(HKQuantityTypeIdentifierDietaryFolate);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryIodine(HKQuantityTypeIdentifierDietaryIodine);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryIron(HKQuantityTypeIdentifierDietaryIron);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryMagnesium(HKQuantityTypeIdentifierDietaryMagnesium);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryManganese(HKQuantityTypeIdentifierDietaryManganese);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryMolybdenum(HKQuantityTypeIdentifierDietaryMolybdenum);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryNiacin(HKQuantityTypeIdentifierDietaryNiacin);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryPantothenicAcid(HKQuantityTypeIdentifierDietaryPantothenicAcid);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryPhosphorus(HKQuantityTypeIdentifierDietaryPhosphorus);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryPotassium(HKQuantityTypeIdentifierDietaryPotassium);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryProtein(HKQuantityTypeIdentifierDietaryProtein);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryRiboflavin(HKQuantityTypeIdentifierDietaryRiboflavin);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietarySelenium(HKQuantityTypeIdentifierDietarySelenium);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietarySodium(HKQuantityTypeIdentifierDietarySodium);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietarySugar(HKQuantityTypeIdentifierDietarySugar);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryThiamin(HKQuantityTypeIdentifierDietaryThiamin);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryVitaminA(HKQuantityTypeIdentifierDietaryVitaminA);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryVitaminB12(HKQuantityTypeIdentifierDietaryVitaminB12);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryVitaminB6(HKQuantityTypeIdentifierDietaryVitaminB6);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryVitaminC(HKQuantityTypeIdentifierDietaryVitaminC);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryVitaminD(HKQuantityTypeIdentifierDietaryVitaminD);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryVitaminE(HKQuantityTypeIdentifierDietaryVitaminE);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryVitaminK(HKQuantityTypeIdentifierDietaryVitaminK);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryZinc(HKQuantityTypeIdentifierDietaryZinc);
		customerHealthDataModel.setHkQuantityTypeIdentifierDistanceCycling(HKQuantityTypeIdentifierDistanceCycling);
		customerHealthDataModel.setHkQuantityTypeIdentifierDistanceWalkingRunning(HKQuantityTypeIdentifierDistanceWalkingRunning);
		customerHealthDataModel.setHkQuantityTypeIdentifierFlightsClimbed(HKQuantityTypeIdentifierFlightsClimbed);
		customerHealthDataModel.setHkQuantityTypeIdentifierHeartRate(HKQuantityTypeIdentifierHeartRate);
		customerHealthDataModel.setHkQuantityTypeIdentifierOxygenSaturation(HKQuantityTypeIdentifierOxygenSaturation);
		customerHealthDataModel.setHkQuantityTypeIdentifierRespiratoryRate(HKQuantityTypeIdentifierRespiratoryRate);
		customerHealthDataModel.setHkQuantityTypeIdentifierStepCount(HKQuantityTypeIdentifierStepCount);
		customerHealthDataModel.setHkQuantityTypeIdentifierDietaryCholesterol(HKQuantityTypeIdentifierDietaryCholesterol);
		getModelService().save(customerHealthDataModel);
		LOG.info("::::::::::Health Data saved for the customer :::::::::");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.accenture.dao.CustomerHealthDataDao#getCustomerHealthData(java.lang.String)
	 */
	@Override
	public CustomerHealthDataModel getCustomerHealthData(final String customerId)
	{
		final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery(
				"select {pk} from {CustomerHealthData} where {customerId} like '" + customerId + "%'");
		final SearchResult<CustomerHealthDataModel> result = getFlexibleSearchService().search(flexibleQuery);
		return result != null && CollectionUtils.isNotEmpty(result.getResult()) ? result.getResult().get(0) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.dao.CustomerHealthDataDao#isCustomerFound(java.lang.String)
	 */
	@Override
	public CustomerModel isCustomerFound(final String customerId)
	{
		final FlexibleSearchQuery flexibleQuery = new FlexibleSearchQuery("select {pk} from {customer} where {customerId} like '"
				+ customerId + "%'");
		final SearchResult<CustomerModel> result = getFlexibleSearchService().search(flexibleQuery);
		return result != null && CollectionUtils.isNotEmpty(result.getResult()) ? result.getResult().get(0) : null;
	}

}
