package com.sample.service;

import com.ibm.ejs.container.*;

/**
 * EJSRemoteStatelessEchoService_b44143a0
 */
public class EJSRemoteStatelessEchoService_b44143a0 extends EJSWrapper implements EchoService {
	/**
	 * EJSRemoteStatelessEchoService_b44143a0
	 */
	public EJSRemoteStatelessEchoService_b44143a0() throws java.rmi.RemoteException {
		super();	}
	/**
	 * echo
	 */
	public java.lang.String echo(java.lang.String arg0) throws java.rmi.RemoteException {
		EJSDeployedSupport _EJS_s = container.getEJSDeployedSupport(this);
		Object[] _jacc_parms = null;
		java.lang.String _EJS_result = null;
		try {
			if (container.doesJaccNeedsEJBArguments( this ))
			{
				_jacc_parms = new Object[1];
				_jacc_parms[0] = arg0;
			}
	com.sample.service.EchoServiceSession beanRef = (com.sample.service.EchoServiceSession)container.preInvoke(this, 0, _EJS_s, _jacc_parms);
			_EJS_result = beanRef.echo(arg0);
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
}
