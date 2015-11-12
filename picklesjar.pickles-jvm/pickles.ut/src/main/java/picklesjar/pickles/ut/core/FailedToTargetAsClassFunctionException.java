package picklesjar.pickles.ut.core;

public class FailedToTargetAsClassFunctionException
	extends IllegalTestCodeStateException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3327988396560733419L;
	
	public FailedToTargetAsClassFunctionException() {
	
		super();
	}
	
	public FailedToTargetAsClassFunctionException( String msg ) {
	
		super( msg );
	}
	
	public FailedToTargetAsClassFunctionException( Throwable thrw ) {
	
		super( thrw );
	}
	
	public FailedToTargetAsClassFunctionException( String msg, Throwable thrw ) {
	
		super( msg, thrw );
	}
	
	public FailedToTargetAsClassFunctionException( int code ) {
	
		super( code );
	}
	
	public FailedToTargetAsClassFunctionException( int code, String msg ) {
	
		super( code, msg );
	}
	
	public FailedToTargetAsClassFunctionException( int code, Throwable thrw ) {
	
		super( code, thrw );
	}
	
	public FailedToTargetAsClassFunctionException( int code, String msg, Throwable thrw ) {
	
		super( code, msg, thrw );
	}
	
}
