package picklesjar.pickles.ut.core;

public class IllegalTestStateException
	extends IllegalStateException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 501657369145421208L;
	
	private int code = 0;
	
	public IllegalTestStateException() {
	
		super();
	}
	
	public IllegalTestStateException( String msg ) {
	
		super( msg );
	}
	
	public IllegalTestStateException( Throwable thrw ) {
	
		super( thrw );
	}
	
	public IllegalTestStateException( String msg, Throwable thrw ) {
	
		super( msg, thrw );
	}
	
	public IllegalTestStateException( int code ) {
	
		super();
		this.code = code;
	}
	
	public IllegalTestStateException( int code, String msg ) {
	
		super( msg );
		this.code = code;
	}
	
	public IllegalTestStateException( int code, Throwable thrw ) {
	
		super( thrw );
		this.code = code;
	}
	
	public IllegalTestStateException( int code, String msg, Throwable thrw ) {
	
		super( msg, thrw );
		this.code = code;
	}
	
	public int getCode() {
	
		return code;
	}
	
}
