/**
 * BusinessDelegate Pattern 
 */
package com.sample.service.delegate;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sample.service.EchoService;
import com.sample.service.EchoServiceHome;
import com.sample.service.exception.BusinessDelegateException;
import com.sample.service.exception.ServiceLocatorException;
import com.sample.service.locator.web.ServiceLocator;

/**
 * @author jrenno
 *
 */
public class EchoServiceBusinessDelegate {
	//~ Static fields/initializers =============================================
	// Logger for this class and subclasses
	protected final Log log = LogFactory.getLog(this.getClass());

	//~ Attributes =============================================================	
	private EchoService service = null;

	//~ Methods ================================================================
	public EchoServiceBusinessDelegate() {
		try {
			EchoServiceHome home = (EchoServiceHome) ServiceLocator.getInstance()
					.getRemoteHome(EchoServiceHome.JNDI_NAME, EchoServiceHome.class);
			service = home.create();
		} catch (ServiceLocatorException sle) {
			throw new BusinessDelegateException(sle.getMessage());
		} catch (CreateException ce) {
			throw new BusinessDelegateException(ce.getMessage());
		} catch (RemoteException re) {
			throw new BusinessDelegateException(re.getMessage());
		}
	}

	/**
	 * @param symbol
	 * @return
	 * @throws RemoteException
	 * @see com.sample.service.EchoService#echo(java.lang.String)
	 */
	public String echo(String message) throws RemoteException {
		return service.echo(message);
	}
}
