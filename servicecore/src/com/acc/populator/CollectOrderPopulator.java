/**
 *
 */
package com.acc.populator;


import de.hybris.platform.converters.Populator;

import org.apache.log4j.Logger;

import com.acc.data.CollectOrderWsData;
import com.acc.data.CollectOrderWsStatus;
import com.accenture.model.CollectOrderModel;


/**
 * @author swarnima.gupta
 *
 */
public class CollectOrderPopulator implements Populator<CollectOrderModel, CollectOrderWsData>
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(CollectOrderPopulator.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hybris.commons.conversion.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final CollectOrderModel source, final CollectOrderWsData target)
	{
		if (source != null)
		{
			target.setPk(String.valueOf(source.getPk().getLongValue()));
			target.setUcoid(source.getUCOID());
			target.setOrderId(source.getOID());
			target.setCustomerId(source.getCID());
			target.setPId(source.getPID());
			target.setStatus(CollectOrderWsStatus.valueOf(source.getStatus()));
			target.setCreatedTS(source.getCreationtime());
		}
	}

}
