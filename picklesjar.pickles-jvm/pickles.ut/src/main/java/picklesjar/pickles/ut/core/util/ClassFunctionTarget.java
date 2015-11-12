package picklesjar.pickles.ut.core.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nonnull;

import picklesjar.pickles.ut.core.FailedToTargetAsClassFunctionException;
import picklesjar.pickles.ut.core.IllegalTestCodeStateException;

public class ClassFunctionTarget {
	
	private Class< ? > targetClass = null;
	
	private MethodQuery targetMethodQuery = null;
	
	private Class< ? >[] targetMethodParameters = null;
	
	private boolean constructorFlag = false;
	
	/**
	 * 
	 * 
	 * 
	 * @param clazz
	 * @param methodQuery
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	public ClassFunctionTarget( Class< ? > clazz, MethodQuery methodQuery )
		throws IllegalTestCodeStateException {
	
		if( ( clazz == null ) || ( methodQuery == null ) ) {
			throw new IllegalTestCodeStateException( new IllegalArgumentException() );
		}
		
		if( !analyze( clazz, methodQuery ) ) {
			throw new FailedToTargetAsClassFunctionException( new IllegalStateException() );
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param clazz
	 * @param methodQuery
	 * @return
	 */
	protected boolean analyze( @Nonnull Class< ? > clazz, @Nonnull MethodQuery methodQuery ) {
	
		boolean result = false;
		
		List< Class< ? >[] > parametersCondidate = new LinkedList<>();
		
		final String targetMethodName = methodQuery.getMethodName();
		constructorFlag = clazz.getSimpleName().equals( targetMethodName );
		if( constructorFlag ) {
			
			Constructor< ? >[] constructors = clazz.getDeclaredConstructors();
			if( constructors != null ) {
				
				Arrays.stream( constructors ).filter(
					( constructor ) -> {
						return constructor.getName().equals( targetMethodName );
						
					} ).forEach(
					( constructor ) -> {
						parametersCondidate.add( constructor.getParameterTypes() );
						
					} );
				
			}
			
		} else {
			
			Method[] methods = clazz.getDeclaredMethods();
			if( methods != null ) {
				
				Arrays.stream( methods ).filter(
					( method ) -> {
						return method.getName().equals( targetMethodName );
						
					} ).forEach(
					( method ) -> {
						parametersCondidate.add( method.getParameterTypes() );
						
					} );
				
			}
			
		}
		
		targetMethodParameters = null;
		if( parametersCondidate.size() > 0 ) {
			
			final String[] methodParameters = methodQuery.getMethodParameters();
			parametersCondidate.stream().forEach(
				( parameters ) -> {
					if( targetMethodParameters == null ) {
						
						final int length = parameters.length;
						if( ( length == 0 ) && ( methodParameters == null ) ) {
							
							targetMethodParameters = parameters;
							
						} else if( ( methodParameters != null ) && ( length == methodParameters.length ) ) {
							
							String name = null;
							for( int i = 0; i < length; ++i ) {
								
								if( ( parameters[ i ].isArray() )
									^ ( methodParameters[ i ].indexOf( "[" ) > 0 ) ) {
									continue;
								}
								
								name = parameters[ i ].getCanonicalName();
								if( name == null ) {
									name = parameters[ i ].getTypeName();
								}
								
								if( ( name.equals( methodParameters[ i ] ) )
									|| ( name.substring( name.lastIndexOf( "." ) + 1 )
										.equals( methodParameters[ i ] ) ) ) {
									
									targetMethodParameters = parameters;
								}
							}
							
						}
						
					}
				} );
			
			result = targetMethodParameters != null;
			if( ( result ) && ( targetMethodParameters.length == 0 ) ) {
				
				targetMethodParameters = null;
			}
		}
		
		targetClass = clazz;
		targetMethodQuery = methodQuery;
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public final Class< ? > getTargetClass() {
	
		return targetClass;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public final MethodQuery getTargetMethodQuery() {
	
		return targetMethodQuery;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public final Class< ? >[] getTargetMethodParameters() {
	
		return targetMethodParameters;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public final boolean isConstructor() {
	
		return constructorFlag;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public Constructor< ? > getConstructor()
		throws NoSuchMethodException,
			SecurityException {
	
		Constructor< ? > result = null;
		
		if( ( targetClass != null ) && ( targetMethodQuery != null ) && ( constructorFlag ) ) {
			
			result = targetClass.getDeclaredConstructor( targetMethodParameters );
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public Method getMethod()
		throws NoSuchMethodException,
			SecurityException {
	
		Method result = null;
		
		if( ( targetClass != null ) && ( targetMethodQuery != null ) && !( constructorFlag ) ) {
			
			result = targetClass.getDeclaredMethod(
				targetMethodQuery.getMethodName(), targetMethodParameters );
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param instance
	 * @param args
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public Object execute( Object instance, Object[] args )
		throws NoSuchMethodException,
			SecurityException,
			IllegalStateException,
			IllegalAccessException,
			IllegalArgumentException,
			InvocationTargetException,
			InstantiationException {
	
		Object result = null;
		
		if( constructorFlag ) {
			
			Constructor< ? > constructor = getConstructor();
			if( constructor == null ) {
				throw new IllegalStateException( "No such constructor at getConstructor()." );
			}
			
			constructor.newInstance( args );
			
		} else {
			
			Method method = getMethod();
			if( method == null ) {
				throw new IllegalStateException( "No such method at getMethod()." );
			}
			
			result = method.invoke( instance, args );
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param args
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalStateException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public Object execute( Object[] args )
		throws NoSuchMethodException,
			SecurityException,
			IllegalStateException,
			IllegalAccessException,
			IllegalArgumentException,
			InvocationTargetException,
			InstantiationException {
	
		return execute( ( Class< ? > )null, args );
	}
}
