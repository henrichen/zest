/* ConfigurationImpl.java

	Purpose:
		
	Description:
		
	History:
		Mon Mar  7 19:07:58 TST 2011, Created by tomyeh

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

*/
package org.zkoss.zest.sys.impl;

import org.zkoss.zest.sys.*;

/**
 * The default implementation of {@link Configuration}.
 *
 * @author tomyeh
 */
public class ConfigurationImpl implements Configuration {
	private final ActionDefinition[] _defs;
	private final String[] _exts;
	private final ErrorHandler _errh;

	public ConfigurationImpl(ActionDefinition[] defs, String[] exts, ErrorHandler errh) {
		_defs = defs != null ? defs: new ActionDefinition[0];
		_exts = exts != null ? exts: new String[0];
		_errh = errh;
	}
	@Override
	public ActionDefinition[] getActionDefinitions() {
		return _defs;
	}
	@Override
	public String[] getExtensions() {
		return _exts;
	}
	@Override
	public ErrorHandler getErrorHandler() {
		return _errh;
	}
}
