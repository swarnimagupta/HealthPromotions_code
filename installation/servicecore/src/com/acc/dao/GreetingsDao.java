/**
 *
 */
package com.acc.dao;

import com.acc.model.GreetingModel;


/**
 * @author swarnima.gupta
 *
 */
public interface GreetingsDao
{

	/**
	 * @param condition
	 * @return GreetingModel
	 */
	public GreetingModel getGreetingsForCondition(String condition);

}
