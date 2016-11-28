package bst;

public class BinarySearchTreeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Every instance of this exception must have a message describing the exception.
	 * 
	 * @param message
	 *            the string describing the error causing the exception
	 */
	public BinarySearchTreeException(String message)
	{
		super(message);
	}

	/**
	 * An exception that was caused by some other exception.
	 * 
	 * @param message
	 *            the string describing the error causing the exception
	 * @param cause
	 *            the error that caused this exception
	 */
	public BinarySearchTreeException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
