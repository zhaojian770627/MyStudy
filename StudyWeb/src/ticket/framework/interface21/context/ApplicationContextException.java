

package ticket.framework.interface21.context;

import ticket.framework.interface21.core.NestedRuntimeException;

/**
 *
 * @author  rod
 * @version 
 */
public class ApplicationContextException extends NestedRuntimeException {

    /**
	 * Constructs an <code>ApplicationContextException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ApplicationContextException(String msg) {
        super(msg);
    }
	
	public ApplicationContextException(String msg, Throwable t) {
        super(msg, t);
    }
}


