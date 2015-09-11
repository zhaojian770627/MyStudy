/*
 * PrototypeStoreException.java
 *
 * Created on 16 April 2001, 09:53
 */

package ticket.framework.interface21.beans.factory;

import ticket.framework.interface21.beans.BeansException;

/**
 *
 * @author  johnsonr
 * @version 
 */
public class BeanDefinitionStoreException extends BeansException {


    /**
    * Constructs an <code>PrototypeStoreException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public BeanDefinitionStoreException(String msg,Throwable t) {
        super(msg, t);
    }
}


