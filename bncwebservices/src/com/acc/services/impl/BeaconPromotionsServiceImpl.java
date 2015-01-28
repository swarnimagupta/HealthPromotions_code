/**
 *
 */
package com.acc.services.impl;

import de.hybris.platform.commercefacades.product.data.PromotionData;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.promotions.model.AbstractPromotionModel;
import de.hybris.platform.promotions.model.PromotionUserRestrictionModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.acc.dao.BeaconPromotionsDao;
import com.acc.data.BeaconData;
import com.acc.product.data.PromotionDataList;
import com.acc.services.BeaconPromotionsService;
import com.accenture.model.BeaconModel;




/**
 * @author swapnil.a.pandey
 * 
 */
public class BeaconPromotionsServiceImpl implements BeaconPromotionsService
{

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


		if (beaconPromotionsDao.CheckEmailId(emailId) != null)
		{

			final UserModel user = userService.getUserForUID(emailId);

			System.out.println("###########user###########" + user);
			final Set<PrincipalGroupModel> sets = user.getAllGroups();
			System.out.println("allgroupssssssssssssssssssssss" + sets);
			final List<String> pkList = new ArrayList<String>();
			for (final PrincipalGroupModel principalGroup : sets)
			{
				pkList.add(principalGroup.getPk().toString());
			}
			final List<PromotionUserRestrictionModel> restrictionList = getBeaconPromotionsDao().getCustomerHeathPromotionData(
					pkList);

			for (final PromotionUserRestrictionModel promotion : restrictionList)
			{
				final AbstractPromotionModel promotionOnEmailID = promotion.getPromotion();
				promotionData = promotionsConverter.convert(promotionOnEmailID);
				promotionList.add(promotionData);

			}

			promotionDataList.setPromotions(promotionList);

		}
		return promotionDataList;
	}
}
