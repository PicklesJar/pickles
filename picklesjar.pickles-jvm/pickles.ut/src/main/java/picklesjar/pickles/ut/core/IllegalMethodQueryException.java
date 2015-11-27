package picklesjar.pickles.ut.core;

public class IllegalMethodQueryException
	extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 907451462174462561L;
	
	public IllegalMethodQueryException() {
	
		super();
	}
	
	public IllegalMethodQueryException( String msg ) {
	
		super( msg );
	}
	
	public IllegalMethodQueryException( Throwable thrw ) {
	
		super( thrw );
	}
	
	public IllegalMethodQueryException( String msg, Throwable thrw ) {
	
		super( msg, thrw );
	}
	
}
