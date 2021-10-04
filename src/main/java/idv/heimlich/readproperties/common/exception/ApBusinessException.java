package idv.heimlich.readproperties.common.exception;

public class ApBusinessException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ApBusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}
	
	private ApBusinessException(final String message) {
		super(message);
	}

}
