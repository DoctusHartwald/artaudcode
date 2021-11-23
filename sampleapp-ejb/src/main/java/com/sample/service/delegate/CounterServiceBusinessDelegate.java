/**
 * BusinessDelegate Pattern 
 */
package com.sample.service.delegate;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sample.service.CounterService;
import com.sample.service.CounterServiceHome;
import com.sample.service.exception.BusinessDelegateException;
import com.sample.service.exception.ServiceLocatorException;
import com.sample.service.locator.web.ServiceLocator;

/**
 * @author jrenno
 *
 */
public class CounterServiceBusinessDelegate {
	//~ Static fields/initializers =============================================
	// Logger for this class and subclasses
	protected final Log log = LogFactory.getLog(this.getClass());

	//~ Attributes =============================================================	
	private CounterService service = null;

	//~ Methods ================================================================
	public CounterServiceBusinessDelegate() {
		try {
			CounterServiceHome home = (CounterServiceHome) ServiceLocator.getInstance()
					.getRemoteHome(CounterServiceHome.JNDI_NAME, CounterServiceHome.class);
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
	 * @return
	 * @throws RemoteException
	 * @see com.sample.service.CounterService#increment()
	 */
	public int increment() throws RemoteException {
		return service.increment();
	}

	/**
	 * @return
	 * @throws RemoteException
	 * @see com.sample.service.CounterService#decrement()
	 */
	public int decrement() throws RemoteException {
		return service.decrement();
	}
}
