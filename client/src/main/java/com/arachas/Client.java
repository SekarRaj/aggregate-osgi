package com.arachas;

import com.arachas.definition.Greeter;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceReference;

public class Client implements BundleActivator, ServiceListener{

	private ServiceReference reference;

	private BundleContext ctx;

	public void start(BundleContext ctx) throws Exception {
		this.ctx = ctx;
		try {
			ctx.addServiceListener(this, "(objectclass=" + Greeter.class.getName() + ")");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void stop(BundleContext ctx) throws Exception {
		if(reference != null) {
			ctx.ungetService(reference);
		}

		this.ctx = null;
	}

	public void serviceChanged(ServiceEvent event) {
		int type = event.getType();

		switch (type) {
        case (ServiceEvent.REGISTERED):
            System.out.println("Notification of service registered.");
            reference = event.getServiceReference();
            Greeter service = (Greeter) (ctx.getService(reference));
            System.out.println(service.sayHiTo("Sekar"));
            break;
        case (ServiceEvent.UNREGISTERING):
            System.out.println("Notification of service unregistered.");
            ctx.ungetService(event.getServiceReference());
            break;
        default:
            break;
        }
	}
}
