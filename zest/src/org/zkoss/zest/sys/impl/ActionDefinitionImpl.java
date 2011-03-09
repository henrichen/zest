/* ActionDefinitionImpl.java

	Purpose:
		
	Description:
		
	History:
		Mon Mar  7 14:05:43 TST 2011, Created by tomyeh

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

*/
package org.zkoss.zest.sys.impl;

import java.lang.reflect.Method;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;

import org.zkoss.lang.Classes;

import org.zkoss.zest.ActionContext;
import org.zkoss.zest.sys.ActionDefinition;

/**
 * The default implementation of {@link ActionDefinition}, used
 * by {@link ParserImpl}.
 *
 * @author tomyeh
 */
public class ActionDefinitionImpl implements ActionDefinition {
	private Pattern _path;
	private Class<?> _klass;
	private String _method;
	private Map<String, String> _results;
	/**
	 * @param klass the class of the action
	 * @param results a map of result to the view's URI
	 */
	public ActionDefinitionImpl(String path, Class<?> klass, String method,
	Map<String, String> results) {
		_path = Pattern.compile(path);
		_klass = klass;
		_method = method != null ? method: "execute";
		_results = results;
	}
	public Object getAction(ActionContext ctx) throws Exception {
		String path = ctx.getRequestPath();
		final int len = path.length();
		if (len > 1 && path.charAt(len - 1) == '/' && !_path.pattern().endsWith("/"))
			path = path.substring(0, len - 1);
			//Thus, /hello/ matches actions with either /hello/ or /hello

		Matcher matcher = _path.matcher(path);
		if (!matcher.matches())
			return null;

		Object action = _klass.newInstance();
		return action;
	}
	public String execute(ActionContext ctx, Object action) throws Exception {
		return (String)Classes.getMethodInPublic(action.getClass(), _method, null)
			.invoke(action);
	}
	public String getView(ActionContext ctx, String result) throws Exception {
		String uri = _results.get(result);
		if (uri == null && result != null)
			uri = _results.get(null); //default view
		return uri;
	}
}
