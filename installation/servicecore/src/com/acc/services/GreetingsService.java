/**
 *
 */
package com.acc.services;

import com.acc.data.GreetingsData;


/**
 * @author swarnima.gupta
 *
 */
public interface GreetingsService
{
	public GreetingsData getGreetingsForCondition(String condition);
}
