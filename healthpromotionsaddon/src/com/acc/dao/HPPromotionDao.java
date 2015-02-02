/**
 *
 */
package com.acc.dao;

import de.hybris.platform.promotions.model.AbstractPromotionModel;

/**
 * @author swarnima.gupta
 *
 */
public interface HPPromotionDao
{

	public AbstractPromotionModel getPromotionByCode(String code);
}
