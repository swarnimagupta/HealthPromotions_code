/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Mar 18, 2015 3:57:49 PM                     ---
 * ----------------------------------------------------------------
 */
package com.acc.jalo;

import com.acc.constants.ServicecoreConstants;
import de.hybris.platform.commerceservices.jalo.process.StoreFrontCustomerProcess;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.promotions.jalo.AbstractPromotion;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.acc.jalo.CustomerPromotionsProcess CustomerPromotionsProcess}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedCustomerPromotionsProcess extends StoreFrontCustomerProcess
{
	/** Qualifier of the <code>CustomerPromotionsProcess.promotionsList</code> attribute **/
	public static final String PROMOTIONSLIST = "promotionsList";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(StoreFrontCustomerProcess.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(PROMOTIONSLIST, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerPromotionsProcess.promotionsList</code> attribute.
	 * @return the promotionsList - Object storing promotions details.
	 */
	public Collection<AbstractPromotion> getPromotionsList(final SessionContext ctx)
	{
		Collection<AbstractPromotion> coll = (Collection<AbstractPromotion>)getProperty( ctx, PROMOTIONSLIST);
		return coll != null ? coll : Collections.EMPTY_LIST;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CustomerPromotionsProcess.promotionsList</code> attribute.
	 * @return the promotionsList - Object storing promotions details.
	 */
	public Collection<AbstractPromotion> getPromotionsList()
	{
		return getPromotionsList( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerPromotionsProcess.promotionsList</code> attribute. 
	 * @param value the promotionsList - Object storing promotions details.
	 */
	public void setPromotionsList(final SessionContext ctx, final Collection<AbstractPromotion> value)
	{
		setProperty(ctx, PROMOTIONSLIST,value == null || !value.isEmpty() ? value : null );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CustomerPromotionsProcess.promotionsList</code> attribute. 
	 * @param value the promotionsList - Object storing promotions details.
	 */
	public void setPromotionsList(final Collection<AbstractPromotion> value)
	{
		setPromotionsList( getSession().getSessionContext(), value );
	}
	
}
