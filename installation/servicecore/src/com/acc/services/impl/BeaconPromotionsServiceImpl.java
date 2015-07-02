/**
 *
 */
package com.acc.services.impl;

import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.acc.dao.BeaconPromotionsDao;
import com.acc.data.BeaconData;
import com.acc.model.BeaconModel;
import com.acc.product.data.PromotionDataList;
import com.acc.services.BeaconPromotionsService;




/**
 * @author swapnil.a.pandey
 *
 */
public class BeaconPromotionsServiceImpl implements BeaconPromotionsService
{
	private static final Logger LOG = Logger.getLogger(BeaconPromotionsServiceImpl.class);


	private BeaconPromotionsDao beaconPromotionsDao;


	@Resource(name = "beaconConverter")
	private Converter<BeaconModel, BeaconData> beaconConverter;
	@Resource(name = "promotionsConverter")
	private Converter<AbstractPromotionModel, PromotionData> promotionsConverter;
	@Resource(name = "userService")
	UserService userService;

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
	 * @return the promotionsConverter
	 */
	public Converter<AbstractPromotionModel, PromotionData> getPromotionsConverter()
	{
		return promotionsConverter;
	}

	/**
	 * @param promotionsConverter
	 *           the promotionsConverter to set
	 */
	public void setPromotionsConverter(final Converter<AbstractPromotionModel, PromotionData> promotionsConverter)
	{
		this.promotionsConverter = promotionsConverter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.services.BeaconPromotionsService#getAllPromotionsForBeacon(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public BeaconData getBeaconById(final String beaconId, final String majorId, final String minorId)
	{
		return beaconConverter.convert(getBeaconPromotionsDao().getBeaconById(beaconId, majorId, minorId));
	}

	/**
	 * @return the beaconPromotionsDao
	 */
	public BeaconPromotionsDao getBeaconPromotionsDao()
	{
		return beaconPromotionsDao;
	}

	/**
	 * @param beaconPromotionsDao
	 *           the beaconPromotionsDao to set
	 */
	public void setBeaconPromotionsDao(final BeaconPromotionsDao beaconPromotionsDao)
	{
		this.beaconPromotionsDao = beaconPromotionsDao;
	}

	@Override
	public PromotionDataList getCustomerHeathPromotionData(final String emailId)
	{
		PromotionData promotionData = new PromotionData();
		final List<PromotionData> promotionList = new ArrayList<PromotionData>();

		final PromotionDataList promotionDataList = new PromotionDataList();



		final UserModel user = userService.getUserForUID(emailId);
		if (user != null)
		{

			LOG.info("###########user###########" + user);
			final Set<PrincipalGroupModel> sets = user.getAllGroups();
			LOG.info("allgroups******************" + sets);
			final List<String> pkList = new ArrayList<String>();
			for (final PrincipalGroupModel principalGroup : sets)
			{
				pkList.add(principalGroup.getPk().toString());
			}
			final List<AbstractPromotionModel> restrictionList = getBeaconPromotionsDao().getCustomerHeathPromotionData(pkList);

			for (final AbstractPromotionModel promotion : restrictionList)
			{
				promotionData = promotionsConverter.convert(promotion);
				promotionData
						.setProductUrl("/yacceleratorstorefront/electronics/en/Health-Products/Health-Equipment/Ge-Ohmeda-Tuffsat-Monitor-With-Sensor/p/prod_1010?site=electronics");
				promotionList.add(promotionData);

			}

			promotionDataList.setPromotions(promotionList);

		}
		else
		{
			LOG.error("Invalid EmailId, please try again");

		}


		return promotionDataList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.services.BeaconPromotionsService#getBeaconDetails()
	 */
	@Override
	public List<BeaconData> getBeaconDetails()
	{
		// YTODO Auto-generated method stub

		BeaconData beaconData = new BeaconData();
		final List<BeaconData> beaconList = new ArrayList<BeaconData>();
		List<BeaconModel> beaconModel = new ArrayList<BeaconModel>();
		LOG.info("*****inside beacondetailsservice***************");
		beaconModel = getBeaconPromotionsDao().getBeaconDetails();
		if (null != beaconModel)
		{
			for (final BeaconModel beaconDetails : beaconModel)
			{
				beaconData = beaconConverter.convert(beaconDetails);
				beaconData.setPromotions(null);
				beaconData.setWelcomeMessage(null);
				beaconList.add(beaconData);
				LOG.info("*****inside beacondetailsservice***************" + beaconList.get(0).getMajorId());
			}
		}
		return beaconList;
	}

	@Override
	public PromotionDataList getPromotionsForUsers(final String name)
	{
		final PromotionDataList promotionDataList = new PromotionDataList();

		if (null != name)
		{


			PromotionData promotionData = new PromotionData();
			final List<PromotionData> promotionList = new ArrayList<PromotionData>();

			final List<AbstractPromotionModel> promotionsForUsers = getBeaconPromotionsDao().getPromotionsForUsers(name);
			for (final AbstractPromotionModel promotion : promotionsForUsers)
			{
				promotionData = promotionsConverter.convert(promotion);
				promotionList.add(promotionData);
			}
			promotionDataList.setPromotions(promotionList);
		}

		return promotionDataList;



	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acc.services.BeaconPromotionsService#getPromotionsBasedOnClimate(java.lang.String)
	 */
	@Override
	public List<PromotionData> getPromotionsBasedOnClimate(final String condition)
	{
		final List<AbstractPromotionModel> promotions = getBeaconPromotionsDao().getPromotionsBasedOnClimate(condition);
		final List<PromotionData> promotionList = new ArrayList<PromotionData>();
		PromotionData promotionData = new PromotionData();
		for (final AbstractPromotionModel promotion : promotions)
		{
			promotionData = promotionsConverter.convert(promotion);
			promotionList.add(promotionData);
		}
		return promotionList;
	}
}
