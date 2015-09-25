package uncc.sambit.exception;

public class AppException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 662772110262716853L;
	public AppException(String message){
		super(message);
	}
	public AppException(String message, Throwable cause){
		super(message,cause);
	}
}
