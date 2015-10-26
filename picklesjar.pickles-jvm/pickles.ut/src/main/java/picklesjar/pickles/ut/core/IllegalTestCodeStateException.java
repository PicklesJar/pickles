package picklesjar.pickles.ut.core;

public class IllegalTestCodeStateException
	extends IllegalTestStateException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2866517271131484034L;
	
	public IllegalTestCodeStateException() {
	
		super();
	}
	
	public IllegalTestCodeStateException( String msg ) {
	
		super( msg );
	}
	
	public IllegalTestCodeStateException( Throwable thrw ) {
	
		super( thrw );
	}
	
	public IllegalTestCodeStateException( String msg, Throwable thrw ) {
	
		super( msg, thrw );
	}
	
	public IllegalTestCodeStateException( int code ) {
	
		super( code );
	}
	
	public IllegalTestCodeStateException( int code, String msg ) {
	
		super( code, msg );
	}
	
	public IllegalTestCodeStateException( int code, Throwable thrw ) {
	
		super( code, thrw );
	}
	
	public IllegalTestCodeStateException( int code, String msg, Throwable thrw ) {
	
		super( code, msg, thrw );
	}
	
}
