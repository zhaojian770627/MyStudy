package ticket.interface21.ejb.access;

import javax.ejb.EJBLocalHome;

import ticket.interface21.beans.FatalBeanException;
import ticket.interface21.jndi.AbstractJndiLocator;

 
/** 
* Superclass for business delegate objects 
* or service locators that contact a single
* local SLSB.
* Exposes JNDI name property and loads it when initialized
* by a BeanFactory.
* It's safe to cache a local home.
* Subclasses must implement all methods themselves and call
* a session bean instance as necessary;
* this is not a proxy.
* @author Rod Johnson
*/
public abstract class AbstractLocalStatelessSessionServiceLocator 
	extends AbstractJndiLocator {
	
	
	public AbstractLocalStatelessSessionServiceLocator() {
	}
	
	public AbstractLocalStatelessSessionServiceLocator(String jndiName) {
		super(jndiName);
	}

	
	/**
	 * Subclasses should implement this to do whatever they need to to
	 * after an EJB home is available. They must themselves
	 * create session bean instances as necessary.
	 */
	protected abstract void setEjbHome(EJBLocalHome home);

	
	/**
	 * @see AbstractJndiLocator#located(Object)
	 */
	protected final void located(Object o) {
		if (!(o instanceof EJBLocalHome))
			throw new FatalBeanException("Located object with JNDI name '" + getJndiName() + "' must be an EJBLocalHome object", null);
		setEjbHome((EJBLocalHome) o);
	}

} 	// class LocalStatelessSessionServiceLocator