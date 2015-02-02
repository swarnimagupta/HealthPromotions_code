/**
 *
 */
package com.accenture.job;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.acc.model.CustomerPromotionsProcessModel;
import com.acc.model.cronjob.EmailEligibleCustomersForPromotionsCronJobModel;
import com.acc.service.HPPromotionService;


/**
 * @author swarnima.gupta
 *
 */
public class EmailEligibleCustomersForPromotionsJob extends
		AbstractJobPerformable<EmailEligibleCustomersForPromotionsCronJobModel>
{
	private static final Logger LOG = Logger.getLogger(EmailEligibleCustomersForPromotionsJob.class);

	private UserService userService;

	private Converter<UserModel, CustomerData> customerConverter;

	private ModelService modelService;
	private BusinessProcessService businessProcessService;
	private BaseSiteService baseSiteService;
	private HPPromotionService hPPromotionService;

	/**
	 * @return the hPPromotionService
	 */
	public HPPromotionService gethPPromotionService()
	{
		return hPPromotionService;
	}

	/**
	 * @param hPPromotionService
	 *           the hPPromotionService to set
	 */
	public void sethPPromotionService(final HPPromotionService hPPromotionService)
	{
		this.hPPromotionService = hPPromotionService;
	}

	/**
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	/**
	 * @param baseSiteService
	 *           the baseSiteService to set
	 */
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Override
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * @return the businessProcessService
	 */
	public BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	/**
	 * @param businessProcessService
	 *           the businessProcessService to set
	 */
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel
	 * )
	 */
	@Override
	public PerformResult perform(final EmailEligibleCustomersForPromotionsCronJobModel cronJob)
	{
		final UserGroupModel userGroup = userService.getUserGroupForUID("customergroup");
		final Set<PrincipalModel> principalSet = userGroup.getMembers();
		PrincipalModel principalModel = null;
		for (final Iterator iterator = principalSet.iterator(); iterator.hasNext();)
		{
			principalModel = (PrincipalModel) iterator.next();
			try
			{
				final URL url = new URL("http://localhost:9001/bncwebservices/v1/electronics/CustomerHealthPromotions/"
						+ principalModel.getUid());
				final HttpURLConnection connection = getHttpConnection(url);
				if (null == connection)
				{
					return new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
				}
				final String jsonData = getJsonDataString(connection);
				final JSONParser parser = new JSONParser();
				final JSONObject obj = (JSONObject) parser.parse(jsonData);
				LOG.info("json object promotions " + " -> " + obj.get("promotions"));
				final List<JSONObject> JSONObjectList = (List<JSONObject>) obj.get("promotions");
				if (CollectionUtils.isNotEmpty(JSONObjectList))
				{
					sendEmailProcess(principalModel.getUid(), JSONObjectList);
				}
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}
			catch (final ParseException e)
			{
				e.printStackTrace();
			}
		}
		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	/**
	 * @param uid
	 * @param JSONObjectList
	 */
	private void sendEmailProcess(final String uid, final List<JSONObject> JSONObjectList)
	{
		final CustomerPromotionsProcessModel customerPromotionsProcessModel = (CustomerPromotionsProcessModel) getBusinessProcessService()
				.createProcess("customerPromotion-" + uid + "-" + System.currentTimeMillis(), "customerPromotionEmailProcess");
		final BaseSiteModel baseSite = baseSiteService.getBaseSiteForUID("electronics");
		customerPromotionsProcessModel.setSite(baseSite);
		customerPromotionsProcessModel.setPromotionsList(getPromotionsList(JSONObjectList));
		customerPromotionsProcessModel.setCustomer((CustomerModel) userService.getUserForUID(uid));
		customerPromotionsProcessModel.setCurrency(baseSite.getStores().get(0).getDefaultCurrency());
		customerPromotionsProcessModel.setStore(baseSite.getStores().get(0));
		customerPromotionsProcessModel.setLanguage(baseSite.getStores().get(0).getDefaultLanguage());
		getModelService().save(customerPromotionsProcessModel);
		getBusinessProcessService().startProcess(customerPromotionsProcessModel);
	}

	/**
	 * @param JSONObjectList
	 * @return List<AbstractPromotionModel>
	 */
	private List<AbstractPromotionModel> getPromotionsList(final List<JSONObject> JSONObjectList)
	{
		final List<AbstractPromotionModel> promotionsList = new ArrayList<AbstractPromotionModel>();
		for (final JSONObject JSONObject : JSONObjectList)
		{
			promotionsList.add(gethPPromotionService().getPromotionByCode(String.valueOf(JSONObject.get("code"))));
		}
		return promotionsList;
	}

	/**
	 * @param connection
	 * @return String
	 * @throws IOException
	 */
	private String getJsonDataString(final HttpURLConnection connection) throws IOException
	{
		final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String jsonData = StringUtils.EMPTY;
		String line = StringUtils.EMPTY;
		while ((line = in.readLine()) != null)
		{
			jsonData += line + "\n";
		}
		in.close();
		return jsonData;
	}

	/**
	 * @param url
	 * @return HttpURLConnection
	 * @throws IOException
	 */
	private HttpURLConnection getHttpConnection(final URL url) throws IOException
	{
		final URLConnection urlConnection = url.openConnection();
		HttpURLConnection connection = null;
		if (urlConnection instanceof HttpURLConnection)
		{
			connection = (HttpURLConnection) urlConnection;
		}
		else
		{
			LOG.warn("::::::::::::::::::::::::::::::URL not an http URL::::::::::::::::::::::::::::::::");
		}
		return connection;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * @param userService
	 *           the userService to set
	 */
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * @return the customerConverter
	 */
	public Converter<UserModel, CustomerData> getCustomerConverter()
	{
		return customerConverter;
	}

	/**
	 * @param customerConverter
	 *           the customerConverter to set
	 */
	public void setCustomerConverter(final Converter<UserModel, CustomerData> customerConverter)
	{
		this.customerConverter = customerConverter;
	}
}
