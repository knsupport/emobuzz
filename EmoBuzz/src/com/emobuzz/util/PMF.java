package com.emobuzz.util;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class PMF {

	 private static PersistenceManagerFactory pmfInstance = null;

	    private PMF() {}

	    public synchronized static PersistenceManagerFactory get() {
	    	
	    	if(pmfInstance==null)
	    	{
	    		pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	    	}
	        return pmfInstance;
	    }
}
