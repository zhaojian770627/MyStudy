
package ticket.business.referencedata.support;

import java.util.List;

import ticket.business.referencedata.Calendar;
import ticket.business.referencedata.Performance;
import ticket.business.referencedata.Show;
import ticket.framework.interface21.context.ApplicationContext;
import ticket.framework.interface21.context.ApplicationContextAware;
import ticket.framework.interface21.context.ApplicationContextException;
import ticket.java14.java.util.logging.Logger;


/**
 * Uses copy-on-write
 * Calendar that can cache data from any other calendar.
 * Thus there is no need to implement caching with real,
 * underlying, calendars.
 * @author Rod Johnson
 */
public class CachingCalendar implements Calendar, ApplicationContextAware {

	public static String REAL_CALENDAR_BEAN_NAME = "realCalendar";
	
	//---------------------------------------------------------------------
	// Instance data
	//---------------------------------------------------------------------
	/**
	* Create a logging category that is available
	* to subclasses. 
	*/
	protected final Logger logger = Logger.getLogger(getClass().getName());
	
	private ApplicationContext applicationContext;
	
	private Calendar realCalendar;
 
	/**
	 * Constructor for CachingCalendar.
	 */
	public CachingCalendar() {
		super();
	}
	
	
	/**
	 * @see ApplicationContextAware#setApplicationContext(ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext ctx) throws ApplicationContextException {
		logger.info("Saved ApplicationContext");
		this.applicationContext = ctx;
		refresh(); 
	}
	
	
	/** 
	 * Switching the reference with clients shouldn't matter
	 */
	public void refresh() throws ApplicationContextException {
		
		if (this.applicationContext == null)
			throw new ApplicationContextException("################### application context is null: can't refresh calendar");
		
		logger.info("Refreshing cached calendar");  
		Calendar newCalendar = (Calendar) this.applicationContext.getBean(REAL_CALENDAR_BEAN_NAME);
		System.out.println("NEW CALENDAR IS " + newCalendar);
		this.realCalendar = newCalendar;
	}
	
	public long getLastModified() {
		return realCalendar.getLastModified();
	}


	/**
	 * @see Calendar#getGenres()
	 */
	public List getCurrentGenres() {
		return realCalendar.getCurrentGenres();
	}

	/**
	 * @see Calendar#getPerformance(int)
	 */
	public Performance getPerformance(int id) {
		return realCalendar.getPerformance(id);
	}

	/**
	 * @see Calendar#getShow(int)
	 */
	public Show getShow(int id) {
		return realCalendar.getShow(id);
	}
	


	/**
	 * @see Calendar#getAllGenres()
	 */
	public List getAllGenres() {
		return realCalendar.getAllGenres();
	}

	/**
	 * @see ApplicationContextAware#getApplicationContext()
	 */
	public ApplicationContext getApplicationContext() {
		return this.applicationContext;
	}

	public String toString() {
		return "CachingCalendar: real calendar is [" + this.realCalendar + "]";
	}
	
}
