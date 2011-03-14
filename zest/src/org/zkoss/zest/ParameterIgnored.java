/* ParameterIgnored.java

	Purpose:
		
	Description:
		
	History:
		Mon Mar 14 11:17:39 TST 2011, Created by tomyeh

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

*/
package org.zkoss.zest;

/**
 * A decorator interface that might be implemented by an action to indicate
 * the request's paramters shall not be converted to the action.
 *
 * <p>If this interface is not implemented by an action (default),
 * the setter method with the same name of a parameter will be called
 * to store the parameter's value.
 *
 * @author tomyeh
 */
public interface ParameterIgnored {
}
