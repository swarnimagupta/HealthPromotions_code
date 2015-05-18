/**
 * 
 */
package com.acc.util;

import de.hybris.platform.util.Config;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * @author swarnima.gupta
 *
 */
public class WeatherUtil
{

	private static final String RAIN = "rain";
	private static final String SNOW = "snow";
	private static final String CLOUDY = "cloudy";
	private static final String SUNNY = "sunny";
	private static final String MAX_PRECIPITATION_MODERATE = "max.precipitation.moderate";
	private static final String MAX_PRECIPITATION_LIGHT = "max.precipitation.light";
	private static final String MIN_CLOUD_COVER_PARTLY_CLOUDY = "min.cloud.cover.partly.cloudy";
	private static final String MIN_PRECIPITATION_MODERATE = "min.precipitation.moderate";
	private static final String MIN_PRECIPITATION_LIGHT = "min.precipitation.light";

	/**
	 * @param cloudCover
	 * @param precipitation
	 * @param temperature
	 */
	public static String getClimate(final float cloudCover, final float precipitation, final float temperature)
	{
		String snowOrRain = StringUtils.EMPTY;
		if(Float.compare(temperature, 0)<=0)
		{
			snowOrRain = SNOW;
			return runConditionsForClimate(cloudCover, precipitation, snowOrRain);
		}
		snowOrRain = RAIN;
		return runConditionsForClimate(cloudCover, precipitation, snowOrRain);
	}

	/**
	 * @param cloudCover
	 * @param precipitation
	 * @param snowOrRain
	 * @return String
	 */
	private static String runConditionsForClimate(final float cloudCover, final float precipitation, String snowOrRain)
	{
		return Float.compare(precipitation, 0)>0?getClimateForRainOrSnow(precipitation, snowOrRain):getCloudyOrSunny(cloudCover);
	}

	/**
	 * @param precipitation
	 * @param snowOrRain
	 * @return String
	 */
	private static String getClimateForRainOrSnow(final float precipitation, String snowOrRain)
	{
		if(Float.compare(precipitation, Float.valueOf(Config.getString(MIN_PRECIPITATION_LIGHT, NumberUtils.FLOAT_ZERO.toString())).floatValue())>=0 
				&& Float.compare(precipitation, Float.valueOf(Config.getString(MAX_PRECIPITATION_LIGHT, NumberUtils.FLOAT_ZERO.toString())).floatValue())<=0)
		{
			return "light "+snowOrRain;
		}
		else if(Float.compare(precipitation, Float.valueOf(Config.getString(MIN_PRECIPITATION_MODERATE, NumberUtils.FLOAT_ZERO.toString())).floatValue())>=0 
				&& Float.compare(precipitation, Float.valueOf(Config.getString(MAX_PRECIPITATION_MODERATE, NumberUtils.FLOAT_ZERO.toString())).floatValue())<=0)
		{
			return "moderate "+snowOrRain;
		}
		else
		{
			return "heavy "+snowOrRain;
		}
	}

	/**
	 * @param cloudCover
	 */
	private static String getCloudyOrSunny(final float cloudCover)
	{
		return Float.compare(cloudCover, Float.valueOf(Config.getString(MIN_CLOUD_COVER_PARTLY_CLOUDY, NumberUtils.FLOAT_ZERO.toString())).floatValue())<0?SUNNY:CLOUDY;
	}
}
