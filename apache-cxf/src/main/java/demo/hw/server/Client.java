package demo.hw.server;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class Client {

	public static void main(String[] args) {
		HelloWorldImpl implementor = new HelloWorldImpl();
		//JaxWsServerFactoryBean = Server endpoints for JAX-WS
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(HelloWorld.class);
		svrFactory.setAddress("http://localhost:9000/helloWorld");
		svrFactory.setServiceBean(implementor);
		//svrFactory.getInInterceptors().add(new LoggingInInterceptor());
		//svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		svrFactory.create();
		
		/**
		 * 1/creation d'un JaxWsServerFactoryBean
		 * 2/setService()
		 * 3/setAddress()
		 * create()
		 */
	}
}
