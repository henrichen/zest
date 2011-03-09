/* ActionSupport.java

	Purpose:
		
	Description:
		
	History:
		Mon Mar  7 14:25:33 TST 2011, Created by tomyeh

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

*/
package org.zkoss.zest;

/**
 * The skeletal implementation of an action.
 * Notice an action can be an instance of any class.
 * And, this class is used only to simplify the implementation. Whether to extend
 * from it is optional.
 *
 * @author tomyeh
 */
public class ActionSupport {
	/** Indicates "success". */
	public static final String SUCCESS = "success";

	/** The default execution of an action.
	 */
	public String execute() {
		return SUCCESS;
	}
}
