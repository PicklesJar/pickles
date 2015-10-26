package picklesjar.pickles.ut.core;

public class IllegalTestLockingStateException
	extends IllegalTestStateException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4394878153116915518L;
	
	public IllegalTestLockingStateException() {
	
		super();
	}
	
	public IllegalTestLockingStateException( String msg ) {
	
		super( msg );
	}
	
	public IllegalTestLockingStateException( Throwable thrw ) {
	
		super( thrw );
	}
	
	public IllegalTestLockingStateException( String msg, Throwable thrw ) {
	
		super( msg, thrw );
	}
	
	public IllegalTestLockingStateException( int code ) {
	
		super( code );
	}
	
	public IllegalTestLockingStateException( int code, String msg ) {
	
		super( code, msg );
	}
	
	public IllegalTestLockingStateException( int code, Throwable thrw ) {
	
		super( code, thrw );
	}
	
	public IllegalTestLockingStateException( int code, String msg, Throwable thrw ) {
	
		super( code, msg, thrw );
	}
	
}
