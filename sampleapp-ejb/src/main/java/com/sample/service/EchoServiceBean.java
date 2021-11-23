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
 * @ejb.bean name="EchoService"
 *           description="An EJB named EchoService"
 *           display-name="EchoService"
 *           jndi-name="ejb/EchoService"
 *           local-jndi-name="ejb/EchoServiceLocal" 
 *           type="Stateless"
 *           transaction-type="Container"
 *           view-type="both"
 *
 * @weblogic.pool initial-beans-in-free-pool = "1"
 * <!-- end-xdoclet-definition -->
 * @generated
 */
public abstract class EchoServiceBean implements javax.ejb.SessionBean {
	private final Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.create-method view-type="remote"
	 * <!-- end-xdoclet-definition -->
	 * @generated
	 */
	public void ejbCreate() {
	}

	/**
	 * <!-- begin-xdoclet-definition -->
	 * @ejb.interface-method view-type="remote"
	 * <!-- end-xdoclet-definition -->
	 * @generated
	 */    
  public String echo(String message) {
    return message;
  }
}
