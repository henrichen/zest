**Why ZEST?**

ZEST is aimed to provide a lightweight and high-performance Web framework for Java that enables MVC pattern and RESTful services.
ZEST is also designed to work seamlessly with [ZK](http://www.zkoss.org).

**Why not [Struts](http://struts.apache.org/)?**

The MVC approach of ZEST is inspired from Struts 2/WebWork. However, Struts is too complex. Many features are useless when working with a modern framework, such as [ZK](http://www.zkoss.org).

**Is ZEST suggested for ZK applications?**

With ZEST, you could provide an additional REST or MVC layer on top of ZK, such that URL will be logic names (e.g., `http://foo.com/accounts/list`) rather than ZUML files (e.g., `http://foo.com/accounts/list.zul`).

On the hand, you don't need ZEST if you prefer to work on ZUML files directly.

**Does ZEST require ZK?**

No, it works with any Web framework. However, it requires two jar files from ZK, zcommon.jar and zweb.jar.

**Why is the difference between ZEST's MVC and ZK's MVC?**

ZEST MVC is per-HTTP request. ZEST is implemented as a filter and then intercept and process each HTTP request. With ZK technology, each HTTP request is actually served by a ZUML page or a richlet.

On the hand, [ZK MVC](http://books.zkoss.org/wiki/ZK_Developer%27s_Reference/MVC) is the mechanism to handle the Ajax requests causing by user's access of a single ZUML page or a richlet It is _component-based_. ZK MVC could be applied to any part of the component tree of a given ZUML page.

Simply put, you could consider ZEST as a mechanism to beutify URL of a ZUML page, and to pre-process the request before reaching a ZUML page. Then, ZK MVC takes place after the ZUML page has been served and rendered.