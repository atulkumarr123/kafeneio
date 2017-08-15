package com.kafeneio.configuration;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.kafeneio.configuration.AppConfiguration;
import com.kafeneio.configuration.CROSFilter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	 @Override
	    protected Class<?>[] getRootConfigClasses() {
	        return new Class[] { AppConfiguration.class };
	    }
	  
	    @Override
	    protected Class<?>[] getServletConfigClasses() {
	        return null;
	    }
	  
	    @Override
	    protected String[] getServletMappings() {
	        return new String[] { "/" };
	    }
	    
	    @Override
	    protected Filter[] getServletFilters() {
	    	Filter [] singleton = { new CROSFilter() };
	    	return singleton;
		}
}
