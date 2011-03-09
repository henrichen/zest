/* ParserImpl.java

	Purpose:
		
	Description:
		
	History:
		Mon Mar  7 13:04:27 TST 2011, Created by tomyeh

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

*/
package org.zkoss.zest.sys.impl;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.net.URL;

import org.zkoss.lang.Classes;
import org.zkoss.util.resource.Locator;
import org.zkoss.util.logging.Log;
import org.zkoss.idom.Document;
import org.zkoss.idom.Element;
import org.zkoss.idom.input.SAXBuilder;
import org.zkoss.idom.util.IDOMs;

import org.zkoss.zest.sys.*;
import org.zkoss.zest.ZestException;

/**
 * The default implementation of the parser of the configuration file
 * (/WEB-INF/zest.xml).
 * @author tomyeh
 */
public class ParserImpl implements Parser {
	private static final Log log = Log.lookup(ParserImpl.class);

	public ParserImpl() {
	}

	@Override
	public Configuration parse(URL url, Locator loc) throws Exception {
		try {
			return parse(new SAXBuilder(true, false, true).build(url).getRootElement(), loc);
		} catch (Throwable ex) {
			log.realCauseBriefly("Failed to parse "+url, ex);
			if (ex instanceof Error) throw (Error)ex;
			throw (Exception)ex;
		}
	}
	/** Parses the specified root element.
	 */
	public Configuration parse(Element root, Locator loc) throws Exception {
		final List<ActionDefinition> defs = new LinkedList<ActionDefinition>();
		String[] exts = null;
		ErrorHandler errh = null;
		for (Iterator it = root.getElements().iterator(); it.hasNext();) {
			final Element el = (Element)it.next();
			final String elnm = el.getName();
			if ("action".equals(elnm)) {
				defs.add(parseAction(el));
			} else if ("error-handler-class".equals(elnm)) {
				errh = (ErrorHandler)Classes.newInstanceByThread(el.getText(true));
			} else if ("extensions".equals(elnm)) {
				exts = parseExtensions(el.getText(true));
			}
		}
		return new ConfigurationImpl(
			defs.toArray(new ActionDefinition[defs.size()]),
			exts != null ? exts: new String[] {""}, errh);
	}
	//parse action
	private static ActionDefinition parseAction(Element el)
	throws Exception {
		final Class<?> klass = Classes.forNameByThread(IDOMs.getRequiredAttributeValue(el, "class"));
		final Map<String, String> results = new HashMap<String, String>();
		for (Iterator it = el.getElements("result").iterator(); it.hasNext();)
			parseResult((Element)it.next(), results);
		return new ActionDefinitionImpl(IDOMs.getRequiredAttributeValue(el, "path"),
			klass, el.getAttributeValue("method"), results);
	}
	private static void parseResult(Element el, Map<String, String> results) {
		results.put(el.getAttributeValue("name"), el.getText(true));
	}
	//parse extensions
	private static String[] parseExtensions(String extensions) {
		if (extensions == null || extensions.length() == 0)
			return null;
		final String[] exts = extensions.split(",");
		for (int j = 0; j < exts.length; ++j) {
			exts[j] = exts[j].trim();
			if (exts[j].length() > 0 && exts[j].charAt(0) != '.')
				exts[j] = '.' + exts[j];
		}
		return exts;
	}
}
