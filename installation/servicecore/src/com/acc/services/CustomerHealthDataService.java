/**
 *
 */
package com.acc.services;

import com.acc.model.CustomerHealthDataModel;



/**
 * @author swarnima.gupta
 * 
 */
/**
 * @author swapnil.a.pandey
 * 
 */
public interface CustomerHealthDataService
{


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
			final String HKQuantityTypeIdentifierDietaryCholesterol);

	/**
	 * 
	 * @param customerId
	 * @return CustomerHealthDataModel
	 */
	public CustomerHealthDataModel getCustomerHealthData(final String customerId);

}
