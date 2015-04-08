/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Mar 18, 2015 3:57:49 PM                     ---
 * ----------------------------------------------------------------
 */
package com.acc.jalo;

import com.acc.constants.ServicecoreConstants;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.promotions.jalo.AbstractPromotion;
import de.hybris.platform.util.Utilities;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type {@link de.hybris.platform.jalo.GenericItem Beacon}.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedBeacon extends GenericItem
{
	/** Qualifier of the <code>Beacon.promotions</code> attribute **/
	public static final String PROMOTIONS = "promotions";
	/** Relation ordering override parameter constants for Beacon2PromotionsRel from ((servicecore))*/
	protected static String BEACON2PROMOTIONSREL_SRC_ORDERED = "relation.Beacon2PromotionsRel.source.ordered";
	protected static String BEACON2PROMOTIONSREL_TGT_ORDERED = "relation.Beacon2PromotionsRel.target.ordered";
	/** Relation disable markmodifed parameter constants for Beacon2PromotionsRel from ((servicecore))*/
	protected static String BEACON2PROMOTIONSREL_MARKMODIFIED = "relation.Beacon2PromotionsRel.markmodified";
	/** Qualifier of the <code>Beacon.welcomeMessage</code> attribute **/
	public static final String WELCOMEMESSAGE = "welcomeMessage";
	/** Qualifier of the <code>Beacon.majorId</code> attribute **/
	public static final String MAJORID = "majorId";
	/** Qualifier of the <code>Beacon.identifier</code> attribute **/
	public static final String IDENTIFIER = "identifier";
	/** Qualifier of the <code>Beacon.minorId</code> attribute **/
	public static final String MINORID = "minorId";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(WELCOMEMESSAGE, AttributeMode.INITIAL);
		tmp.put(MAJORID, AttributeMode.INITIAL);
		tmp.put(IDENTIFIER, AttributeMode.INITIAL);
		tmp.put(MINORID, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Beacon.identifier</code> attribute.
	 * @return the identifier
	 */
	public String getIdentifier(final SessionContext ctx)
	{
		return (String)getProperty( ctx, IDENTIFIER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Beacon.identifier</code> attribute.
	 * @return the identifier
	 */
	public String getIdentifier()
	{
		return getIdentifier( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Beacon.identifier</code> attribute. 
	 * @param value the identifier
	 */
	public void setIdentifier(final SessionContext ctx, final String value)
	{
		setProperty(ctx, IDENTIFIER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Beacon.identifier</code> attribute. 
	 * @param value the identifier
	 */
	public void setIdentifier(final String value)
	{
		setIdentifier( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Beacon.majorId</code> attribute.
	 * @return the majorId
	 */
	public String getMajorId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MAJORID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Beacon.majorId</code> attribute.
	 * @return the majorId
	 */
	public String getMajorId()
	{
		return getMajorId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Beacon.majorId</code> attribute. 
	 * @param value the majorId
	 */
	public void setMajorId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MAJORID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Beacon.majorId</code> attribute. 
	 * @param value the majorId
	 */
	public void setMajorId(final String value)
	{
		setMajorId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Beacon.minorId</code> attribute.
	 * @return the minorId
	 */
	public String getMinorId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, MINORID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Beacon.minorId</code> attribute.
	 * @return the minorId
	 */
	public String getMinorId()
	{
		return getMinorId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Beacon.minorId</code> attribute. 
	 * @param value the minorId
	 */
	public void setMinorId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, MINORID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Beacon.minorId</code> attribute. 
	 * @param value the minorId
	 */
	public void setMinorId(final String value)
	{
		setMinorId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Beacon.promotions</code> attribute.
	 * @return the promotions
	 */
	public List<AbstractPromotion> getPromotions(final SessionContext ctx)
	{
		final List<AbstractPromotion> items = getLinkedItems( 
			ctx,
			true,
			ServicecoreConstants.Relations.BEACON2PROMOTIONSREL,
			null,
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_TGT_ORDERED, true)
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Beacon.promotions</code> attribute.
	 * @return the promotions
	 */
	public List<AbstractPromotion> getPromotions()
	{
		return getPromotions( getSession().getSessionContext() );
	}
	
	public long getPromotionsCount(final SessionContext ctx)
	{
		return getLinkedItemsCount(
			ctx,
			true,
			ServicecoreConstants.Relations.BEACON2PROMOTIONSREL,
			null
		);
	}
	
	public long getPromotionsCount()
	{
		return getPromotionsCount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Beacon.promotions</code> attribute. 
	 * @param value the promotions
	 */
	public void setPromotions(final SessionContext ctx, final List<AbstractPromotion> value)
	{
		setLinkedItems( 
			ctx,
			true,
			ServicecoreConstants.Relations.BEACON2PROMOTIONSREL,
			null,
			value,
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(BEACON2PROMOTIONSREL_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Beacon.promotions</code> attribute. 
	 * @param value the promotions
	 */
	public void setPromotions(final List<AbstractPromotion> value)
	{
		setPromotions( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to promotions. 
	 * @param value the item to add to promotions
	 */
	public void addToPromotions(final SessionContext ctx, final AbstractPromotion value)
	{
		addLinkedItems( 
			ctx,
			true,
			ServicecoreConstants.Relations.BEACON2PROMOTIONSREL,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(BEACON2PROMOTIONSREL_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to promotions. 
	 * @param value the item to add to promotions
	 */
	public void addToPromotions(final AbstractPromotion value)
	{
		addToPromotions( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from promotions. 
	 * @param value the item to remove from promotions
	 */
	public void removeFromPromotions(final SessionContext ctx, final AbstractPromotion value)
	{
		removeLinkedItems( 
			ctx,
			true,
			ServicecoreConstants.Relations.BEACON2PROMOTIONSREL,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(BEACON2PROMOTIONSREL_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from promotions. 
	 * @param value the item to remove from promotions
	 */
	public void removeFromPromotions(final AbstractPromotion value)
	{
		removeFromPromotions( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Beacon.welcomeMessage</code> attribute.
	 * @return the welcomeMessage
	 */
	public String getWelcomeMessage(final SessionContext ctx)
	{
		return (String)getProperty( ctx, WELCOMEMESSAGE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Beacon.welcomeMessage</code> attribute.
	 * @return the welcomeMessage
	 */
	public String getWelcomeMessage()
	{
		return getWelcomeMessage( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Beacon.welcomeMessage</code> attribute. 
	 * @param value the welcomeMessage
	 */
	public void setWelcomeMessage(final SessionContext ctx, final String value)
	{
		setProperty(ctx, WELCOMEMESSAGE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Beacon.welcomeMessage</code> attribute. 
	 * @param value the welcomeMessage
	 */
	public void setWelcomeMessage(final String value)
	{
		setWelcomeMessage( getSession().getSessionContext(), value );
	}
	
}
