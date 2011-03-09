/* ErrorHandler.java

	Purpose:
		
	Description:
		
	History:
		Mon Mar  7 17:54:14 TST 2011, Created by tomyeh

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

*/
package org.zkoss.zest.sys;

import org.zkoss.zest.ActionContext;

/**
 * The error handler for handling exceptions thrown
 * when processing a request.
 * @author tomyeh
 */
public interface ErrorHandler {
	public Throwable onError(ActionContext reqctx, Object action, Throwable ex);
}
