package com.sample.service;

import com.ibm.ejs.container.*;

/**
 * EJSStatelessEchoServiceHomeBean_b44143a0
 */
public class EJSStatelessEchoServiceHomeBean_b44143a0 extends EJSHome {
	static final long serialVersionUID = 61;
	/**
	 * EJSStatelessEchoServiceHomeBean_b44143a0
	 */
	public EJSStatelessEchoServiceHomeBean_b44143a0() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public com.sample.service.EchoService create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
com.sample.service.EchoService result = null;
boolean createFailed = false;
try {
	result = (com.sample.service.EchoService) super.createWrapper(null);
}
catch (javax.ejb.CreateException ex) {
	createFailed = true;
	throw ex;
} catch (java.rmi.RemoteException ex) {
	createFailed = true;
	throw ex;
} catch (Throwable ex) {
	createFailed = true;
	throw new CreateFailureException(ex);
} finally {
	if (createFailed) {
		super.createFailure(beanO);
	}
}
return result;	}
	/**
	 * create_Local
	 */
	public com.sample.service.EchoServiceLocal create_Local() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
com.sample.service.EchoServiceLocal result = null;
boolean createFailed = false;
boolean preCreateFlag = false;
try {
	result = (com.sample.service.EchoServiceLocal) super.createWrapper_Local(null);
}
catch (javax.ejb.CreateException ex) {
	createFailed = true;
	throw ex;
} catch (java.rmi.RemoteException ex) {
	createFailed = true;
	throw ex;
} catch (Throwable ex) {
	createFailed = true;
	throw new CreateFailureException(ex);
} finally {
	if (createFailed) {
		super.createFailure(beanO);
	}
}
return result;	}
}
