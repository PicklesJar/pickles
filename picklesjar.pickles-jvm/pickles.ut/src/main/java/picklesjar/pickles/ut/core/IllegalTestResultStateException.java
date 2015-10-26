package picklesjar.pickles.ut.core;

public class IllegalTestResultStateException
	extends IllegalTestStateException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5946677363364161587L;
	
	public IllegalTestResultStateException() {
	
		super();
	}
	
	public IllegalTestResultStateException( String msg ) {
	
		super( msg );
	}
	
	public IllegalTestResultStateException( Throwable thrw ) {
	
		super( thrw );
	}
	
	public IllegalTestResultStateException( String msg, Throwable thrw ) {
	
		super( msg, thrw );
	}
	
	public IllegalTestResultStateException( int code ) {
	
		super( code );
	}
	
	public IllegalTestResultStateException( int code, String msg ) {
	
		super( code, msg );
	}
	
	public IllegalTestResultStateException( int code, Throwable thrw ) {
	
		super( code, thrw );
	}
	
	public IllegalTestResultStateException( int code, String msg, Throwable thrw ) {
	
		super( code, msg, thrw );
	}
	
}
