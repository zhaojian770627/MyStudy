
package ticket.business.boxoffice.support;

import ticket.business.boxoffice.CreditCardAuthorizationException;
import ticket.business.boxoffice.InvalidCreditCardNumberException;

public interface CreditCardProcessor  {
	
	
	/** Return a confirmation code
	 */
	String process(double amount, String creditCardNumber, String expiry) throws CreditCardAuthorizationException;
	 
	
	/**
	 * Returns if valid
	 * @throw if invalid
	 */
	void validate(String creditCardNumber, String expiry) throws InvalidCreditCardNumberException;
	
}
