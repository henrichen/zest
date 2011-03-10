/* ActionDefinitionImpl.java

	Purpose:
		
	Description:
		
	History:
		Mon Mar  7 14:05:43 TST 2011, Created by tomyeh

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

*/
package org.zkoss.zest.sys.impl;

import java.lang.reflect.Method;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.zkoss.lang.Classes;

import org.zkoss.zest.ActionContext;
import org.zkoss.zest.sys.ActionDefinition;
import org.zkoss.zest.sys.ExValue;
import org.zkoss.zest.ZestException;

/**
 * The default implementation of {@link ActionDefinition}, used
 * by {@link ParserImpl}.
 *
 * @author tomyeh
 */
public class ActionDefinitionImpl implements ActionDefinition {
	private Pattern _path;
	private Class<?> _klass;
	private ExValue _method;
	private Map<String, ExValue> _results;
	/**
	 * @param klass the class of the action
	 * @param results a map of result to the view's URI
	 */
	public ActionDefinitionImpl(String path, Class<?> klass, String method,
	Map<String, String> results) {
		_path = Pattern.compile(path);
		_klass = klass;
		_method = new ExValue(method != null ? method: "execute", String.class);

		_results = new HashMap<String, ExValue>();
		for (Map.Entry<String, String> me: results.entrySet())
			_results.put(me.getKey(), new ExValue(me.getValue(), String.class));
	}
	public Object getAction(ActionContext ac) throws Exception {
		String path = ac.getRequestPath();
		final int len = path.length();
		if (len > 1 && path.charAt(len - 1) == '/' && !_path.pattern().endsWith("/"))
			path = path.substring(0, len - 1);
			//Thus, /hello/ matches actions with either /hello/ or /hello

		Matcher matcher = _path.matcher(path);
		if (!matcher.matches())
			return null;

		final List<String> matches = new LinkedList<String>();
		for (int j = matcher.groupCount() + 1; --j >= 0;)
			matches.add(0, matcher.group(j));
		ac.getServletRequest().setAttribute("matches",
			matches.toArray(new String[matches.size()]));

		return _klass.newInstance();
	}
	public String execute(ActionContext ac, Object action) throws Exception {
		final String mtdnm = (String)_method.getValue(ac);
		if (mtdnm == null)
			throw new ZestException("Method, "+_method+", required for "+action);

		Method m1 = null, m2 = null;
		try {
			m1 = Classes.getMethodInPublic(action.getClass(), mtdnm,
				new Class[] {ActionContext.class});
		} catch (NoSuchMethodException ex) {
			m2 = Classes.getMethodInPublic(action.getClass(), mtdnm, null);
		}
		return m1 != null ? (String)m1.invoke(action, ac): (String)m2.invoke(action);
	}
	public String getView(ActionContext ac, String result) throws Exception {
		ExValue uri = _results.get(result);
		if (uri == null && result != null)
			uri = _results.get(null); //default view
		return (String)uri.getValue(ac);
	}
}
