/**
 *
 */
package com.acc.controller;

import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.util.Config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acc.controller.constants.ControllerConstants;
import com.acc.data.BeaconPromotionData;
import com.acc.data.CustomerHealthData;
import com.acc.product.data.PromotionDataList;
import com.acc.services.BeaconPromotionsService;
import com.acc.services.CustomerHealthDataService;


/**
 * @author swarnima.gupta
 * 
 */
@Controller
@RequestMapping(value = "/v1/{baseSiteId}/CustomerHealthData")
public class CustomerHealthDataController
{
	private static final String HEALTH_DATA = "HealthData";
	private static final String TYPE = "type";
	private static final String VALUE = "Value";
	private static final String HEALTHDATA_HEARTRATE_THRESHOLDVALUE = "healthdata.heartrate.thresholdvalue";
	private static final String HEALTHDATA_STEPCOUNT_THRESHOLDVALUE = "healthdata.stepcount.thresholdvalue";
	private static final String HEALTHDATA_BODYMASS_THRESHOLDVALUE = "healthdata.bodymass.thresholdvalue";
	private static final String HEALTHDATA_CHOLESTEROL_THRESHOLDVALUE = "healthdata.cholesterol.thresholdvalue";
	private static final String HEALTHDATA_FATTOTAL_THRESHOLDVALUE = "healthdata.fattotal.thresholdvalue";

	@Autowired
	private BeaconPromotionsService beaconPromotionsService;


	private static final Logger LOG = Logger.getLogger(CustomerHealthDataController.class);
	@Autowired
	private CustomerHealthDataService customerHealthDataService;

	@RequestMapping(value = "/saveCustomerHealthData", method = RequestMethod.POST)
	@ResponseBody
	public BeaconPromotionData saveCustomerHeathData(final HttpServletRequest request) throws IOException, ParseException
	{
		LOG.info("::::::: in saveCustomerHeathData GET request method :::::::" + request.getParameter(HEALTH_DATA));
		final StringBuffer sbuf = getJsonBodyString(request);
		LOG.info("::::::: json object string is :::::::" + sbuf);
		final CustomerHealthData customerHealthData = new CustomerHealthData();
		BeaconPromotionData beaconPromotionsData = new BeaconPromotionData();

		beaconPromotionsData = getHealthDataFromJsonString(sbuf, customerHealthData);
		//returning JSON object
		customerHealthDataService.saveCustomerHealthData(customerHealthData.getCustomerId(),
				customerHealthData.getHKCategoryTypeIdentifierSleepAnalysis(),
				customerHealthData.getHKQuantityTypeIdentifierActiveEnergyBurned(),
				customerHealthData.getHKQuantityTypeIdentifierBasalEnergyBurned(),
				customerHealthData.getHKQuantityTypeIdentifierBloodPressureDiastolic(),
				customerHealthData.getHKQuantityTypeIdentifierBloodPressureSystolic(),
				customerHealthData.getHKQuantityTypeIdentifierBodyFatPercentage(),
				customerHealthData.getHKQuantityTypeIdentifierBodyMass(),
				customerHealthData.getHKQuantityTypeIdentifierBodyMassIndex(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryBiotin(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryCaffeine(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryCalcium(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryCarbohydrates(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryChloride(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryChromium(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryCopper(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryEnergyConsumed(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryFatMonounsaturated(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryFatPolyunsaturated(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryFatSaturated(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryFatTotal(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryFiber(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryFolate(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryIodine(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryIron(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryMagnesium(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryManganese(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryMolybdenum(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryNiacin(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryPantothenicAcid(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryPhosphorus(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryPotassium(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryProtein(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryRiboflavin(),
				customerHealthData.getHKQuantityTypeIdentifierDietarySelenium(),
				customerHealthData.getHKQuantityTypeIdentifierDietarySodium(),
				customerHealthData.getHKQuantityTypeIdentifierDietarySugar(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryThiamin(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryVitaminA(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryVitaminB12(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryVitaminB6(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryVitaminC(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryVitaminD(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryVitaminE(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryVitaminK(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryZinc(),
				customerHealthData.getHKQuantityTypeIdentifierDistanceCycling(),
				customerHealthData.getHKQuantityTypeIdentifierDistanceWalkingRunning(),
				customerHealthData.getHKQuantityTypeIdentifierFlightsClimbed(),
				customerHealthData.getHKQuantityTypeIdentifierHeartRate(),
				customerHealthData.getHKQuantityTypeIdentifierOxygenSaturation(),
				customerHealthData.getHKQuantityTypeIdentifierRespiratoryRate(),
				customerHealthData.getHKQuantityTypeIdentifierStepCount(),
				customerHealthData.getHKQuantityTypeIdentifierDietaryCholesterol());

		LOG.info("data customer healthdata controller promotions " + beaconPromotionsData.getPromotions());

		LOG.info("data customer healthdata controller " + beaconPromotionsData.getPromotions().get(0).getCode());
		return beaconPromotionsData;
	}

	/**
	 * @param sbuf
	 * @param customerHealthData
	 * @throws ParseException
	 */
	private BeaconPromotionData getHealthDataFromJsonString(final StringBuffer sbuf, final CustomerHealthData customerHealthData)
			throws ParseException
	{
		BeaconPromotionData beaconPromotionsData = new BeaconPromotionData();

		if (StringUtils.isNotEmpty(sbuf.toString()))
		{
			final JSONParser parser = new JSONParser();
			final JSONObject obj = (JSONObject) parser.parse(sbuf.toString());
			final List<JSONObject> JSONObjectList = (List<JSONObject>) obj.get(HEALTH_DATA);
			if (CollectionUtils.isNotEmpty(JSONObjectList))
			{
				beaconPromotionsData = populateCustomerHealthData(customerHealthData, JSONObjectList);
			}
		}
		return beaconPromotionsData;
	}

	/**
	 * @param customerHealthData
	 * @param JSONObjectList
	 */
	private BeaconPromotionData populateCustomerHealthData(final CustomerHealthData customerHealthData,
			final List<JSONObject> JSONObjectList)
	{
		final List<PromotionData> promotionsData = new ArrayList<PromotionData>();
		final BeaconPromotionData beaconPromotionsData = new BeaconPromotionData();

		for (final JSONObject JSONObject : JSONObjectList)
		{
			switch (JSONObject.get(TYPE).toString())
			{
				case ControllerConstants.CUSTOMERID:
					customerHealthData.setCustomerId(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKCATEGORYTYPEIDENTIFIERSLEEPANALYSIS:
					customerHealthData.setHKCategoryTypeIdentifierSleepAnalysis(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERACTIVEENERGYBURNED:
					customerHealthData.setHKQuantityTypeIdentifierActiveEnergyBurned(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERBASALENERGYBURNED:
					customerHealthData.setHKQuantityTypeIdentifierBasalEnergyBurned(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERBLOODPRESSUREDIASTOLIC:
					customerHealthData.setHKQuantityTypeIdentifierBloodPressureDiastolic(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERBLOODPRESSURESYSTOLIC:
					customerHealthData.setHKQuantityTypeIdentifierBloodPressureSystolic(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERBODYFATPERCENTAGE:
					customerHealthData.setHKQuantityTypeIdentifierBodyFatPercentage(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERBODYMASS:
					customerHealthData.setHKQuantityTypeIdentifierBodyMass(String.valueOf(JSONObject.get(VALUE)));
					final double bodyMass = Double.parseDouble(String.valueOf(JSONObject.get(VALUE)));
					final double bodyMassThreshold = Double.parseDouble(Config.getParameter(HEALTHDATA_BODYMASS_THRESHOLDVALUE));
					if ((bodyMass >= bodyMassThreshold))
					{
						final PromotionDataList promotionDataList = beaconPromotionsService.getPromotionsForUsers("body");
						promotionsData.addAll(CollectionUtils.isNotEmpty(promotionDataList.getPromotions()) ? promotionDataList
								.getPromotions() : Collections.EMPTY_LIST);
					}
					beaconPromotionsData.setPromotions(CollectionUtils.isNotEmpty(promotionsData) ? promotionsData
							: Collections.EMPTY_LIST);

					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERBODYMASSINDEX:
					customerHealthData.setHKQuantityTypeIdentifierBodyMassIndex(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYBIOTIN:
					customerHealthData.setHKQuantityTypeIdentifierDietaryBiotin(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYCAFFEINE:
					customerHealthData.setHKQuantityTypeIdentifierDietaryCaffeine(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYCALCIUM:
					customerHealthData.setHKQuantityTypeIdentifierDietaryCalcium(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYCARBOHYDRATES:
					customerHealthData.setHKQuantityTypeIdentifierDietaryCarbohydrates(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYCHLORIDE:
					customerHealthData.setHKQuantityTypeIdentifierDietaryChloride(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYCHROMIUM:
					customerHealthData.setHKQuantityTypeIdentifierDietaryChromium(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYCOPPER:
					customerHealthData.setHKQuantityTypeIdentifierDietaryCopper(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYENERGYCONSUMED:
					customerHealthData.setHKQuantityTypeIdentifierDietaryEnergyConsumed(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYFATMONOUNSATURATED:
					customerHealthData.setHKQuantityTypeIdentifierDietaryFatMonounsaturated(String.valueOf(JSONObject.get(VALUE)));

					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYFATPOLYUNSATURATED:
					customerHealthData.setHKQuantityTypeIdentifierDietaryFatPolyunsaturated(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYFATSATURATED:
					customerHealthData.setHKQuantityTypeIdentifierDietaryFatSaturated(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYFATTOTAL:
					customerHealthData.setHKQuantityTypeIdentifierDietaryFatTotal(String.valueOf(JSONObject.get(VALUE)));
					final double fatTotal = Double.parseDouble(String.valueOf(JSONObject.get(VALUE)));
					final double fatTotalThreshold = Double.parseDouble(Config.getParameter(HEALTHDATA_FATTOTAL_THRESHOLDVALUE));
					if (fatTotal >= fatTotalThreshold)
					{
						final PromotionDataList promotionDataList = beaconPromotionsService.getPromotionsForUsers("fat");
						promotionsData.addAll(CollectionUtils.isNotEmpty(promotionDataList.getPromotions()) ? promotionDataList
								.getPromotions() : Collections.EMPTY_LIST);
					}
					beaconPromotionsData.setPromotions(CollectionUtils.isNotEmpty(promotionsData) ? promotionsData
							: Collections.EMPTY_LIST);
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYFIBER:
					customerHealthData.setHKQuantityTypeIdentifierDietaryFiber(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYFOLATE:
					customerHealthData.setHKQuantityTypeIdentifierDietaryFolate(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYIODINE:
					customerHealthData.setHKQuantityTypeIdentifierDietaryIron(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYIRON:
					customerHealthData.setHKQuantityTypeIdentifierDietaryIron(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYMAGNESIUM:
					customerHealthData.setHKQuantityTypeIdentifierDietaryMagnesium(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYMANGANESE:
					customerHealthData.setHKQuantityTypeIdentifierDietaryManganese(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYMOLYBDENUM:
					customerHealthData.setHKQuantityTypeIdentifierDietaryMolybdenum(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYNIACIN:
					customerHealthData.setHKQuantityTypeIdentifierDietaryNiacin(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYPANTOTHENICACID:
					customerHealthData.setHKQuantityTypeIdentifierDietaryPantothenicAcid(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYPHOSPHORUS:
					customerHealthData.setHKQuantityTypeIdentifierDietaryPhosphorus(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYPOTASSIUM:
					customerHealthData.setHKQuantityTypeIdentifierDietaryPotassium(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYPROTEIN:
					customerHealthData.setHKQuantityTypeIdentifierDietaryProtein(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYRIBOFLAVIN:
					customerHealthData.setHKQuantityTypeIdentifierDietaryRiboflavin(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYSELENIUM:
					customerHealthData.setHKQuantityTypeIdentifierDietarySelenium(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYSODIUM:
					customerHealthData.setHKQuantityTypeIdentifierDietarySodium(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYSUGAR:
					customerHealthData.setHKQuantityTypeIdentifierDietarySugar(String.valueOf(JSONObject.get(VALUE)));
					continue;

				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYTHIAMIN:
					customerHealthData.setHKQuantityTypeIdentifierDietaryThiamin(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYVITAMINA:
					customerHealthData.setHKQuantityTypeIdentifierDietaryVitaminA(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYVITAMINB12:
					customerHealthData.setHKQuantityTypeIdentifierDietaryVitaminB12(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYVITAMINB6:
					customerHealthData.setHKQuantityTypeIdentifierDietaryVitaminB6(String.valueOf(JSONObject.get(VALUE)));
					continue;

				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYVITAMINC:
					customerHealthData.setHKQuantityTypeIdentifierDietaryVitaminC(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYVITAMIND:
					customerHealthData.setHKQuantityTypeIdentifierDietaryVitaminD(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYVITAMINE:
					customerHealthData.setHKQuantityTypeIdentifierDietaryVitaminE(String.valueOf(JSONObject.get(VALUE)));
					continue;

				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYVITAMINK:
					customerHealthData.setHKQuantityTypeIdentifierDietaryVitaminK(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYZINC:
					customerHealthData.setHKQuantityTypeIdentifierDietaryZinc(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDISTANCECYCLING:
					customerHealthData.setHKQuantityTypeIdentifierDistanceCycling(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDISTANCEWALKINGRUNNING:
					customerHealthData.setHKQuantityTypeIdentifierDistanceWalkingRunning(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERFLIGHTSCLIMBED:
					customerHealthData.setHKQuantityTypeIdentifierFlightsClimbed(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERHEARTRATE:
					customerHealthData.setHKQuantityTypeIdentifierHeartRate(String.valueOf(JSONObject.get(VALUE)));
					final double heartRateThreshold = Double.parseDouble(Config.getParameter(HEALTHDATA_HEARTRATE_THRESHOLDVALUE));
					final double heartRate = Double.parseDouble(String.valueOf(JSONObject.get(VALUE)));

					if (heartRate >= heartRateThreshold)
					{
						final PromotionDataList promotionDataList = beaconPromotionsService.getPromotionsForUsers("heart");
						promotionsData.addAll(CollectionUtils.isNotEmpty(promotionDataList.getPromotions()) ? promotionDataList
								.getPromotions() : Collections.EMPTY_LIST);
					}
					beaconPromotionsData.setPromotions(CollectionUtils.isNotEmpty(promotionsData) ? promotionsData
							: Collections.EMPTY_LIST);

					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIEROXYGENSATURATION:
					customerHealthData.setHKQuantityTypeIdentifierOxygenSaturation(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERRESPIRATORYRATE:
					customerHealthData.setHKQuantityTypeIdentifierRespiratoryRate(String.valueOf(JSONObject.get(VALUE)));
					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERSTEPCOUNT:
					customerHealthData.setHKQuantityTypeIdentifierStepCount(String.valueOf(JSONObject.get(VALUE)));
					final double stepCount = Double.parseDouble(String.valueOf(JSONObject.get(VALUE)));
					final double stepCountThreshold = Double.parseDouble(Config.getParameter(HEALTHDATA_STEPCOUNT_THRESHOLDVALUE));
					if (stepCount >= stepCountThreshold)
					{
						final PromotionDataList promotionDataList = beaconPromotionsService.getPromotionsForUsers("step");
						promotionsData.addAll(CollectionUtils.isNotEmpty(promotionDataList.getPromotions()) ? promotionDataList
								.getPromotions() : Collections.EMPTY_LIST);
					}
					beaconPromotionsData.setPromotions(CollectionUtils.isNotEmpty(promotionsData) ? promotionsData
							: Collections.EMPTY_LIST);

					continue;
				case ControllerConstants.HKQUANTITYTYPEIDENTIFIERDIETARYCHOLESTEROL:
					customerHealthData.setHKQuantityTypeIdentifierDietaryCholesterol(String.valueOf(JSONObject.get(VALUE)));
					final double cholesterol = Double.parseDouble(String.valueOf(JSONObject.get(VALUE)));
					final double cholesterolThreshold = Double.parseDouble(Config.getParameter(HEALTHDATA_CHOLESTEROL_THRESHOLDVALUE));
					if (cholesterol >= cholesterolThreshold)
					{
						final PromotionDataList promotionDataList = beaconPromotionsService.getPromotionsForUsers("cholesterol");
						promotionsData.addAll(CollectionUtils.isNotEmpty(promotionDataList.getPromotions()) ? promotionDataList
								.getPromotions() : Collections.EMPTY_LIST);
					}
					beaconPromotionsData.setPromotions(CollectionUtils.isNotEmpty(promotionsData) ? promotionsData
							: Collections.EMPTY_LIST);

					continue;

			}

		}
		return beaconPromotionsData;
	}

	/**
	 * @param request
	 * @return StringBuffer
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private StringBuffer getJsonBodyString(final HttpServletRequest request) throws IOException, UnsupportedEncodingException
	{
		final ServletInputStream input = request.getInputStream();
		final byte[] buf = new byte[201];
		final StringBuffer sbuf = new StringBuffer();
		int result;
		do
		{
			result = input.readLine(buf, 0, buf.length);
			if (result != -1)
			{
				sbuf.append(new String(buf, 0, result, "UTF-8"));
			}
			else
			{
				break;
			}
		}
		while (result == buf.length);
		return sbuf;
	}
}
