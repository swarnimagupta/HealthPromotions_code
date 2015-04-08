/**
 *
 */
package com.acc.service;

import de.hybris.platform.promotions.model.AbstractPromotionModel;


/**
 * @author swarnima.gupta
 *
 */
public interface HPPromotionService
{
	public AbstractPromotionModel getPromotionByCode(final String code);
}
