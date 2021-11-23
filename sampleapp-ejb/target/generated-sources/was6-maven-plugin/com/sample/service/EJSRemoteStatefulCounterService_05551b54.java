package com.sample.service;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatefulCounterService_05551b54
 */
public class EJSRemoteStatefulCounterService_05551b54 extends EJSWrapper implements CounterService {
	/**
	 * EJSRemoteStatefulCounterService_05551b54
	 */
	public EJSRemoteStatefulCounterService_05551b54() throws java.rmi.RemoteException {
		super();	}
	/**
	 * decrement
	 */
	public int decrement() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		int _EJS_result = 0;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	com.sample.service.CounterServiceSession beanRef = (com.sample.service.CounterServiceSession)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.decrement();
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 0, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
	/**
	 * increment
	 */
	public int increment() throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		int _EJS_result = 0;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[0];
			}
	com.sample.service.CounterServiceSession beanRef = (com.sample.service.CounterServiceSession)container.preInvoke(this, 1, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.increment();
		}
		catch (java.rmi.RemoteException ex) {
			_EJS_s.setUncheckedException(ex);
		}
		catch (Throwable ex) {
			_EJS_s.setUncheckedException(ex);
			throw new java.rmi.RemoteException("bean method raised unchecked exception", ex);
		}

		finally {
			try{
				container.postInvoke(this, 1, _EJS_s);
			} finally {
				container.putEJSDeployedSupport(_EJS_s);
			}
		}
		return _EJS_result;
	}
}
