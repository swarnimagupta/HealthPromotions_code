/**
 *
 */
package com.acc.service.impl;

import de.hybris.platform.promotions.model.AbstractPromotionModel;

import com.acc.dao.HPPromotionDao;
import com.acc.service.HPPromotionService;


/**
 * @author swarnima.gupta
 *
 */
public class HPPromotionServiceImpl implements HPPromotionService
{
	private HPPromotionDao hPPromotionDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acc.service.HPPromotionService#getPromotionByCode(java.lang.String)
	 */
	@Override
	public AbstractPromotionModel getPromotionByCode(final String code)
	{
		return gethPPromotionDao().getPromotionByCode(code);
	}

	/**
	 * @return the hPPromotionDao
	 */
	public HPPromotionDao gethPPromotionDao()
	{
		return hPPromotionDao;
	}

	/**
	 * @param hPPromotionDao
	 *           the hPPromotionDao to set
	 */
	public void sethPPromotionDao(final HPPromotionDao hPPromotionDao)
	{
		this.hPPromotionDao = hPPromotionDao;
	}

}
