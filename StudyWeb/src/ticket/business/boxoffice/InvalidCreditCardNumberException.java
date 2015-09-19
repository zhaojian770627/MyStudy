
package ticket.business.boxoffice;

public class InvalidCreditCardNumberException extends CreditCardAuthorizationException {

	/**
	 * Constructor for InvalidCreditCardNumberException.
	 * @param s
	 */
	public InvalidCreditCardNumberException(String s) {
		super(s);
	}

}
