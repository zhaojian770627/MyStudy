
package ticket.business.boxoffice.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface BoxOfficeHome extends EJBLocalHome {
	
	BoxOfficeLocal create() throws CreateException;

}
