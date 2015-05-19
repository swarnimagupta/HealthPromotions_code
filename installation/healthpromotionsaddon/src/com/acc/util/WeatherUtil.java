/**
 *
 */
package com.acc.util;

import de.hybris.platform.util.Config;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


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
	private static final String TEMP_C = "temp_C";
	private static final String PRECIP_MM = "precipMM";
	private static final String CLOUDCOVER = "cloudcover";
	private static final String CURRENT_CONDITION = "current_condition";
	private static final String DATA = "data";


	/**
	 * @param parser
	 * @param latitude
	 * @param longitude
	 * @return String
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static String executeClimateWebservice(final JSONParser parser, final String latitude, final String longitude)
			throws MalformedURLException, IOException, ParseException
	{
		final URL url = new URL(
				"http://api.worldweatheronline.com/free/v2/weather.ashx?q="
						+ latitude
						+ "%2C"
						+ longitude
						+ "&format=json&num_of_days=1&date=today&fx=no&mca=no&fx24=no&includelocation=yes&show_comments=yes&showlocaltime=yes&key=61c3cc6652f35c4e318b62ff3b196");
		final WebservicesUtil webservicesUtil = new WebservicesUtil();
		final HttpURLConnection connection = webservicesUtil.getHttpConnection(url);

		final String jsonData = webservicesUtil.getJsonDataString(connection);
		final JSONObject object = (JSONObject) parser.parse(jsonData);
		final JSONObject dataObject = (JSONObject) object.get(DATA);
		final JSONArray ccObject = (JSONArray) dataObject.get(CURRENT_CONDITION);
		final Iterator<JSONObject> driveIterator = ccObject.iterator();
		String climate = StringUtils.EMPTY;
		while (driveIterator.hasNext())
		{
			final JSONObject driveJSON = driveIterator.next();
			final float cloudCover = Float.valueOf(String.valueOf(driveJSON.get(CLOUDCOVER))).floatValue();
			final float precipitation = Float.valueOf(String.valueOf(driveJSON.get(PRECIP_MM))).floatValue();
			final float temperature = Float.valueOf(String.valueOf(driveJSON.get(TEMP_C))).floatValue();
			climate = getClimate(cloudCover, precipitation, temperature);
		}
		return climate;
	}

	/**
	 * @param cloudCover
	 * @param precipitation
	 * @param temperature
	 */
	public static String getClimate(final float cloudCover, final float precipitation, final float temperature)
	{
		String snowOrRain = StringUtils.EMPTY;
		if (Float.compare(temperature, 0) <= 0)
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
	private static String runConditionsForClimate(final float cloudCover, final float precipitation, final String snowOrRain)
	{
		return Float.compare(precipitation, 0) > 0 ? getClimateForRainOrSnow(precipitation, snowOrRain)
				: getCloudyOrSunny(cloudCover);
	}

	/**
	 * @param precipitation
	 * @param snowOrRain
	 * @return String
	 */
	private static String getClimateForRainOrSnow(final float precipitation, final String snowOrRain)
	{
		if (Float.compare(precipitation, Float
				.valueOf(Config.getString(MIN_PRECIPITATION_LIGHT, NumberUtils.FLOAT_ZERO.toString())).floatValue()) >= 0
				&& Float.compare(precipitation,
						Float.valueOf(Config.getString(MAX_PRECIPITATION_LIGHT, NumberUtils.FLOAT_ZERO.toString())).floatValue()) <= 0)
		{
			return "light " + snowOrRain;
		}
		else if (Float.compare(precipitation,
				Float.valueOf(Config.getString(MIN_PRECIPITATION_MODERATE, NumberUtils.FLOAT_ZERO.toString())).floatValue()) >= 0
				&& Float.compare(precipitation,
						Float.valueOf(Config.getString(MAX_PRECIPITATION_MODERATE, NumberUtils.FLOAT_ZERO.toString())).floatValue()) <= 0)
		{
			return "moderate " + snowOrRain;
		}
		else
		{
			return "heavy " + snowOrRain;
		}
	}

	/**
	 * @param cloudCover
	 */
	private static String getCloudyOrSunny(final float cloudCover)
	{
		return Float.compare(cloudCover,
				Float.valueOf(Config.getString(MIN_CLOUD_COVER_PARTLY_CLOUDY, NumberUtils.FLOAT_ZERO.toString())).floatValue()) < 0 ? SUNNY
				: CLOUDY;
	}
}
