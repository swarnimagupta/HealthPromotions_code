/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at Mar 18, 2015 3:57:49 PM                     ---
 * ----------------------------------------------------------------
 */
package com.acc.jalo;

import com.acc.constants.ServicecoreConstants;
import com.acc.jalo.Beacon;
import com.acc.jalo.CustomerHealthData;
import com.acc.jalo.CustomerPromotionsProcess;
import com.acc.jalo.cronjob.EmailEligibleCustomersForPromotionsCronJob;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.link.Link;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.promotions.jalo.AbstractPromotion;
import de.hybris.platform.util.Utilities;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generated class for type <code>ServicecoreManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast","PMD"})
public abstract class GeneratedServicecoreManager extends Extension
{
	/** Relation ordering override parameter constants for Beacon2PromotionsRel from ((servicecore))*/
	protected static String BEACON2PROMOTIONSREL_SRC_ORDERED = "relation.Beacon2PromotionsRel.source.ordered";
	protected static String BEACON2PROMOTIONSREL_TGT_ORDERED = "relation.Beacon2PromotionsRel.target.ordered";
	/** Relation disable markmodifed parameter constants for Beacon2PromotionsRel from ((servicecore))*/
	protected static String BEACON2PROMOTIONSREL_MARKMODIFIED = "relation.Beacon2PromotionsRel.markmodified";
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("beaconId", AttributeMode.INITIAL);
		tmp.put("location", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.product.Product", Collections.unmodifiableMap(tmp));
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.beaconId</code> attribute.
	 * @return the beaconId
	 */
	public String getBeaconId(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, ServicecoreConstants.Attributes.Product.BEACONID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.beaconId</code> attribute.
	 * @return the beaconId
	 */
	public String getBeaconId(final Product item)
	{
		return getBeaconId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.beaconId</code> attribute. 
	 * @param value the beaconId
	 */
	public void setBeaconId(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, ServicecoreConstants.Attributes.Product.BEACONID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.beaconId</code> attribute. 
	 * @param value the beaconId
	 */
	public void setBeaconId(final Product item, final String value)
	{
		setBeaconId( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractPromotion.beacons</code> attribute.
	 * @return the beacons
	 */
	public List<Beacon> getBeacons(final SessionContext ctx, final AbstractPromotion item)
	{
		final List<Beacon> items = item.getLinkedItems( 
			ctx,
			false,
			ServicecoreConstants.Relations.BEACON2PROMOTIONSREL,
			null,
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_TGT_ORDERED, true)
		);
		return items;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractPromotion.beacons</code> attribute.
	 * @return the beacons
	 */
	public List<Beacon> getBeacons(final AbstractPromotion item)
	{
		return getBeacons( getSession().getSessionContext(), item );
	}
	
	public long getBeaconsCount(final SessionContext ctx, final AbstractPromotion item)
	{
		return item.getLinkedItemsCount(
			ctx,
			false,
			ServicecoreConstants.Relations.BEACON2PROMOTIONSREL,
			null
		);
	}
	
	public long getBeaconsCount(final AbstractPromotion item)
	{
		return getBeaconsCount( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractPromotion.beacons</code> attribute. 
	 * @param value the beacons
	 */
	public void setBeacons(final SessionContext ctx, final AbstractPromotion item, final List<Beacon> value)
	{
		item.setLinkedItems( 
			ctx,
			false,
			ServicecoreConstants.Relations.BEACON2PROMOTIONSREL,
			null,
			value,
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(BEACON2PROMOTIONSREL_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractPromotion.beacons</code> attribute. 
	 * @param value the beacons
	 */
	public void setBeacons(final AbstractPromotion item, final List<Beacon> value)
	{
		setBeacons( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to beacons. 
	 * @param value the item to add to beacons
	 */
	public void addToBeacons(final SessionContext ctx, final AbstractPromotion item, final Beacon value)
	{
		item.addLinkedItems( 
			ctx,
			false,
			ServicecoreConstants.Relations.BEACON2PROMOTIONSREL,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(BEACON2PROMOTIONSREL_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Adds <code>value</code> to beacons. 
	 * @param value the item to add to beacons
	 */
	public void addToBeacons(final AbstractPromotion item, final Beacon value)
	{
		addToBeacons( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from beacons. 
	 * @param value the item to remove from beacons
	 */
	public void removeFromBeacons(final SessionContext ctx, final AbstractPromotion item, final Beacon value)
	{
		item.removeLinkedItems( 
			ctx,
			false,
			ServicecoreConstants.Relations.BEACON2PROMOTIONSREL,
			null,
			Collections.singletonList(value),
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_SRC_ORDERED, true),
			Utilities.getRelationOrderingOverride(BEACON2PROMOTIONSREL_TGT_ORDERED, true),
			Utilities.getMarkModifiedOverride(BEACON2PROMOTIONSREL_MARKMODIFIED)
		);
	}
	
	/**
	 * <i>Generated method</i> - Removes <code>value</code> from beacons. 
	 * @param value the item to remove from beacons
	 */
	public void removeFromBeacons(final AbstractPromotion item, final Beacon value)
	{
		removeFromBeacons( getSession().getSessionContext(), item, value );
	}
	
	public Beacon createBeacon(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ServicecoreConstants.TC.BEACON );
			return (Beacon)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating Beacon : "+e.getMessage(), 0 );
		}
	}
	
	public Beacon createBeacon(final Map attributeValues)
	{
		return createBeacon( getSession().getSessionContext(), attributeValues );
	}
	
	public CustomerHealthData createCustomerHealthData(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ServicecoreConstants.TC.CUSTOMERHEALTHDATA );
			return (CustomerHealthData)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating CustomerHealthData : "+e.getMessage(), 0 );
		}
	}
	
	public CustomerHealthData createCustomerHealthData(final Map attributeValues)
	{
		return createCustomerHealthData( getSession().getSessionContext(), attributeValues );
	}
	
	public CustomerPromotionsProcess createCustomerPromotionsProcess(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ServicecoreConstants.TC.CUSTOMERPROMOTIONSPROCESS );
			return (CustomerPromotionsProcess)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating CustomerPromotionsProcess : "+e.getMessage(), 0 );
		}
	}
	
	public CustomerPromotionsProcess createCustomerPromotionsProcess(final Map attributeValues)
	{
		return createCustomerPromotionsProcess( getSession().getSessionContext(), attributeValues );
	}
	
	public EmailEligibleCustomersForPromotionsCronJob createEmailEligibleCustomersForPromotionsCronJob(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( ServicecoreConstants.TC.EMAILELIGIBLECUSTOMERSFORPROMOTIONSCRONJOB );
			return (EmailEligibleCustomersForPromotionsCronJob)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating EmailEligibleCustomersForPromotionsCronJob : "+e.getMessage(), 0 );
		}
	}
	
	public EmailEligibleCustomersForPromotionsCronJob createEmailEligibleCustomersForPromotionsCronJob(final Map attributeValues)
	{
		return createEmailEligibleCustomersForPromotionsCronJob( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return ServicecoreConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.location</code> attribute.
	 * @return the location
	 */
	public String getLocation(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, ServicecoreConstants.Attributes.Product.LOCATION);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.location</code> attribute.
	 * @return the location
	 */
	public String getLocation(final Product item)
	{
		return getLocation( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.location</code> attribute. 
	 * @param value the location
	 */
	public void setLocation(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, ServicecoreConstants.Attributes.Product.LOCATION,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.location</code> attribute. 
	 * @param value the location
	 */
	public void setLocation(final Product item, final String value)
	{
		setLocation( getSession().getSessionContext(), item, value );
	}
	
}
