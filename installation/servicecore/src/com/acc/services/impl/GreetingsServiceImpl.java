/**
 *
 */
package com.acc.services.impl;

import de.hybris.platform.servicelayer.dto.converter.Converter;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.acc.dao.GreetingsDao;
import com.acc.dao.impl.GreetingsDaoImpl;
import com.acc.data.GreetingsData;
import com.acc.model.GreetingModel;
import com.acc.services.GreetingsService;


/**
 * @author swarnima.gupta
 *
 */
public class GreetingsServiceImpl implements GreetingsService
{

	private static final Logger LOG = Logger.getLogger(GreetingsDaoImpl.class);

	private GreetingsDao greetingsDao;

	@Resource(name = "greetingsConverter")
	private Converter<GreetingModel, GreetingsData> greetingsConverter;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.acc.services.GreetingsService#getGreetingsForCondition(java.lang.String)
	 */
	@Override
	public GreetingsData getGreetingsForCondition(final String condition)
	{
		LOG.info("*******Inside GreetingsServiceImpl*********");
		final GreetingModel greeting = getGreetingsDao().getGreetingsForCondition(condition);
		return greetingsConverter.convert(greeting);
	}

	/**
	 * @return the greetingsDao
	 */
	public GreetingsDao getGreetingsDao()
	{
		return greetingsDao;
	}

	/**
	 * @param greetingsDao
	 *           the greetingsDao to set
	 */
	public void setGreetingsDao(final GreetingsDao greetingsDao)
	{
		this.greetingsDao = greetingsDao;
	}

}
