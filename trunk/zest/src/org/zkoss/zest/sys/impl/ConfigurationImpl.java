/* ConfigurationImpl.java

	Purpose:
		
	Description:
		
	History:
		Mon Mar  7 19:07:58 TST 2011, Created by tomyeh

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

*/
package org.zkoss.zest.sys.impl;

import org.zkoss.xel.FunctionMapper;
import org.zkoss.xel.taglib.Taglib;

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
	private final  FunctionMapper _mapper;

	public ConfigurationImpl(ActionDefinition[] defs, String[] exts, ErrorHandler errh,
	FunctionMapper mapper) {
		_defs = defs != null ? defs: new ActionDefinition[0];
		_exts = exts != null ? exts: new String[0];
		_errh = errh;
		_mapper = mapper;
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
	@Override
	public FunctionMapper getFunctionMapper() {
		return _mapper;
	}
}
