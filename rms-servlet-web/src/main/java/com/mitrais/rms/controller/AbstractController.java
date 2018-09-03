package com.mitrais.rms.controller;

import javax.servlet.http.HttpServlet;

public abstract class AbstractController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	public static final String VIEW_PREFIX = "/WEB-INF/jsp";
    public static final String VIEW_SUFFIX = ".jsp";
    public static final String VIEW_ROOT_PREFIX = "/rms-servlet-web";

    protected String getTemplatePath(String path)
    {
        if (path.equalsIgnoreCase("/"))
        {
            return VIEW_ROOT_PREFIX + path + "index" + VIEW_SUFFIX;
        }
        else
        {
            return VIEW_PREFIX + path + VIEW_SUFFIX;
        }
    }
}
