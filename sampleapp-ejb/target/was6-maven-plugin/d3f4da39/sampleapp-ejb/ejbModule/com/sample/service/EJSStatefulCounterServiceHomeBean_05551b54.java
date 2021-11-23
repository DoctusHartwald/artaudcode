package com.sample.service;

import com.ibm.ejs.container.*;

/**
 * EJSStatefulCounterServiceHomeBean_05551b54
 */
public class EJSStatefulCounterServiceHomeBean_05551b54 extends EJSHome {
	static final long serialVersionUID = 61;
	/**
	 * EJSStatefulCounterServiceHomeBean_05551b54
	 */
	public EJSStatefulCounterServiceHomeBean_05551b54() throws java.rmi.RemoteException {
		super();	}
	/**
	 * create
	 */
	public com.sample.service.CounterService create() throws javax.ejb.CreateException, java.rmi.RemoteException {
BeanO beanO = null;
com.sample.service.CounterService result = null;
boolean createFailed = false;
boolean preCreateFlag = false;
try {
	beanO = super.createBeanO();
	com.sample.service.CounterServiceSession bean = (com.sample.service.CounterServiceSession) beanO.getEnterpriseBean();
preCreateFlag = super.preEjbCreate(beanO);
	bean.ejbCreate();
	result = (com.sample.service.CounterService) super.postCreate(beanO);
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
	if(preCreateFlag && !createFailed)
		super.afterPostCreateCompletion(beanO);
	if (createFailed) {
		super.createFailure(beanO);
	}
}
return result;	}
}
