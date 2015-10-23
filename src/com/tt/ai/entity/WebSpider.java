package com.tt.ai.entity;

import java.io.IOException;

/**
 * Web Spider interface 
 * @author Tian 
 */
public interface WebSpider {
	/**
	 * Get the target web page. 
	 * If can not found the appropriate one, the default timeout is 30 seconds then show the best one
	 * @author Tian
	 */
	public void getTarget();
	
	/**
	 * Get the target web page.
	 * If can not found the appropriate one, if time exceed the timeout then show the best one
	 * @param The time out value (seconds)
	 * @author Tian 
	 */
	public void getTarget(long timeout);

}
