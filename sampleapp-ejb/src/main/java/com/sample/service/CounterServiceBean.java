package com.sample.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.Serializable;

/**
 * <!-- begin-user-doc -->
 * A generated session bean
 * <!-- end-user-doc -->
 * *
 * <!-- begin-xdoclet-definition -->
 * @ejb.bean name="CounterService"
 *           description="An EJB named CounterService"
 *           display-name="CounterService"
 *           jndi-name="ejb/CounterService"
 *           type="Stateful"
 *           transaction-type="Container"
 *           view-type="remote"
 *
 * @weblogic.pool initial-beans-in-free-pool = "1"
 * <!-- end-xdoclet-definition -->
 * @generated
 */
public abstract class CounterServiceBean implements javax.ejb.SessionBean {
	private final Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.create-method view-type="remote"
	 * <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void ejbCreate() {
	}
	
  private int count = 0;
    
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="remote"
	 * <!-- end-xdoclet-definition -->
	 * @generated
	 */    
  public int increment() {
  	log.debug("entering increment method");
    return ++count;
  }
    
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="remote"
	 * <!-- end-xdoclet-definition -->
	 * @generated
	 */    
  public int decrement() {
  	log.debug("entering decrement method");
    return --count;
  }
}
