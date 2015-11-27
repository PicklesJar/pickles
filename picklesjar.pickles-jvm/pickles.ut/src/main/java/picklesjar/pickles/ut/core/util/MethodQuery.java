package picklesjar.pickles.ut.core.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import picklesjar.pickles.ut.core.IllegalMethodQueryException;
import picklesjar.pickles.ut.recipes.MethodQueryString;

/**
 * 
 * 
 * 
 *
 */
public final class MethodQuery {
	
	private static final String METHOD_PARAMETER_DELIMITER = "+";
	
	private static final String METHOD_VALIDATE_STRING = "(^\\s*[a-zA-Z]\\w*\\s*\\(\\s*(([a-zA-Z]\\w*\\.)*[a-zA-Z]\\w*(\\s*\\[\\s*\\])*(\\s*\\+\\s*([a-zA-Z]\\w*\\.)*[a-zA-Z]\\w*(\\s*\\[\\s*\\])*)*(\\s*\\.\\.\\.)?)?\\s*\\)\\s*$){1}";
	
	private String name = null;
	
	private String[] parameters = null;
	
	private static Pattern pattern = null;
	
	static {
		
		pattern = Pattern.compile( METHOD_VALIDATE_STRING );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param query
	 */
	private MethodQuery( @MethodQueryString String query ) {
	
		analyze( query );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param query
	 * @return
	 */
	public synchronized static MethodQuery newInstance( @MethodQueryString String query )
		throws IllegalMethodQueryException {
	
		if( !checkFormat( query ) ) {
			
			throw new IllegalMethodQueryException();
		}
		
		return new MethodQuery( query );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param query
	 * @return
	 */
	public static boolean checkFormat( @MethodQueryString String query ) {
	
		return pattern.matcher( query ).matches();
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param query
	 * @return
	 */
	public String getMethodName() {
	
		return name;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param query
	 * @return
	 */
	public String[] getMethodParameters() {
	
		return parameters;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param query
	 */
	private void analyze( @MethodQueryString String query ) {
	
		Matcher mather = pattern.matcher( query );
		mather.find();
		
		String parameterString = mather.group( 2 );
		if( parameterString != null ) {
			
			List< String > parameterList = new LinkedList<>();
			
			Arrays.stream( parameterString.split( Pattern.quote( METHOD_PARAMETER_DELIMITER ) ) ).forEach(
				param -> {
					parameterList.add( param.replaceAll( "\\s", "" ).replace( "...", "[]" ) );
				} );
			
			parameters = new String[ parameterList.size() ];
			parameterList.toArray( parameters );
		}
		
		name = query.substring( 0, query.indexOf( "(" ) ).trim();
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param x
	 * @return
	 */
	@Override
	public boolean equals( Object x ) {
	
		boolean result = false;
		
		if( x instanceof MethodQuery ) {
			result = equals( ( MethodQuery )x );
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param query
	 * @return
	 */
	public boolean equals( MethodQuery query ) {
	
		boolean result = false;
		
		if( query != null ) {
			
			if( parameters == null ) {
				result = name.equals( query.getMethodName() )
					&& query.getMethodParameters() == null;
				
			} else if( query.getMethodParameters() != null ) {
				
				String[] xparameters = query.getMethodParameters();
				if( parameters.length == xparameters.length ) {
					
					boolean paramCheck = true;
					final int length = parameters.length;
					for( int i = 0; i < length; ++i ) {
						
						if( !( parameters[ i ].equals( xparameters[ i ] ) ) ) {
							paramCheck = false;
							break;
						}
						
					}
					
					result = name.equals( query.getMethodName() )
						&& paramCheck;
				}
				
			}
			
		}
		
		return result;
	}
}
