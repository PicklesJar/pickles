package picklesjar.pickles.ut.runtime;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import picklesjar.pickles.ut.core.IllegalTestCodeStateException;
import picklesjar.pickles.ut.core.IllegalTestLockingStateException;
import picklesjar.pickles.ut.core.IllegalTestResultStateException;
import picklesjar.pickles.ut.core.IllegalTestStateException;

interface UTFoundationCore {
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public String lock();
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public String lock( String scenarioName );
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public boolean putScenarioName( String key, String tag );
	
	/**
	 * 
	 * 
	 * 
	 * @param key
	 * @param function
	 * @return
	 */
	public boolean before( String key, Consumer< UnitTestTemporary > function );
	
	/**
	 * 
	 * 
	 * 
	 * @param key
	 * @param function
	 * @param arg
	 * @return
	 */
	public < T > boolean before( String key, BiConsumer< UnitTestTemporary, T > function, T arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param key
	 * @return
	 */
	public boolean unlock( String key );
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public boolean isLocking();
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public String getScenarioName();
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < A > void given( Consumer< A > function, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public void given( Consumer< UnitTestTemporary > function );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < A > void given( BiConsumer< UnitTestTemporary, A > function, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < A > void given( Consumer< A > function, UnitTestTemporary temp, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public void given( Consumer< UnitTestTemporary > function, UnitTestTemporary temp );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < A > void given(
		BiConsumer< UnitTestTemporary, A > function, UnitTestTemporary temp, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public void given( UnaryOperator< UnitTestTemporary > function );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < T > void given( Supplier< T > function );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < T > void given( Function< UnitTestTemporary, T > function );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < A, T > void given( Function< A, T > function, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < A, T > void given( BiFunction< UnitTestTemporary, A, T > function, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < T > void given( String key, Supplier< T > function );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < T > void given( String key, Function< UnitTestTemporary, T > function );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < A, T > void given( String key, Function< A, T > function, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < A, T > void given( String key, BiFunction< UnitTestTemporary, A, T > function, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < T > void given( Supplier< T > function, UnitTestTemporary temp );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < T > void given( Function< UnitTestTemporary, T > function, UnitTestTemporary temp );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < A, T > void given( Function< A, T > function, UnitTestTemporary temp, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < A, T > void given(
		BiFunction< UnitTestTemporary, A, T > function, UnitTestTemporary temp, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < T > void given( String key, Supplier< T > function, UnitTestTemporary temp );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < T > void given(
		String key, Function< UnitTestTemporary, T > function, UnitTestTemporary temp );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < A, T > void given(
		String key, Function< A, T > function, UnitTestTemporary temp, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < A, T > void given(
		String key, BiFunction< UnitTestTemporary, A, T > function, UnitTestTemporary temp, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public void when( Consumer< UnitTestResult > function );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public < A > void when( BiConsumer< UnitTestResult, A > function, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public void when( BiConsumer< UnitTestResult, UnitTestTemporary > function );
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	public < R > void when( Supplier< R > function );
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	public < A, R > void when( Function< A, R > function, A arg );
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	public < R > void when( Function< UnitTestTemporary, R > function );
	
	/**
	 *
	 *
	 *
	 * @param function
	 * @param arg
	 */
	public < A, R > void when( BiFunction< UnitTestTemporary, A, R > function, A arg );
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	public < R > void when( String key, Supplier< R > function );
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	public < A, R > void when( String key, Function< A, R > function, A arg );
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	public < R > void when( String key, Function< UnitTestTemporary, R > function );
	
	/**
	 *
	 *
	 *
	 * @param function
	 * @param arg
	 */
	public < A, R > void when( String key, BiFunction< UnitTestTemporary, A, R > function, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public void then( Consumer< UnitTestResult > function );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public < A > void then( BiConsumer< UnitTestResult, A > function, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < T > void then( Function< UnitTestResult, T > function );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < A, T > void then( BiFunction< UnitTestResult, A, T > function, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public < T > void then( String key, Function< UnitTestResult, T > function );
	
	/**
	 * 
	 * 
	 * 
	 * @param key
	 * @param function
	 * @param arg
	 */
	public < A, T > void then( String key, BiFunction< UnitTestResult, A, T > function, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public void then(
		Predicate< UnitTestResult > function, Consumer< UnitTestTemporary > functionForOK,
		Consumer< UnitTestTemporary > functionForNG );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public < A > void then(
		Predicate< UnitTestResult > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		Consumer< UnitTestTemporary > functionForNG, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public < A > void then(
		Predicate< UnitTestResult > function, Consumer< UnitTestTemporary > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public < A > void then(
		Predicate< UnitTestResult > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public < A > void then(
		BiPredicate< UnitTestResult, A > function, Consumer< UnitTestTemporary > functionForOK,
		Consumer< UnitTestTemporary > functionForNG, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public < A > void then(
		BiPredicate< UnitTestResult, A > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		Consumer< UnitTestTemporary > functionForNG, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param functionForOK
	 * @param functionForNG
	 * @param arg
	 */
	public < A > void then(
		BiPredicate< UnitTestResult, A > function, Consumer< UnitTestTemporary > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public < A > void then(
		BiPredicate< UnitTestResult, A > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg );
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @throws IllegalTestStateException
	 */
	public default void checkExecutable( Object function )
		throws IllegalTestStateException {
	
		if( function == null ) {
			throw new IllegalTestCodeStateException();
		}
		
		if( !isLocking() ) {
			throw new IllegalTestLockingStateException();
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @throws IllegalTestStateException
	 */
	public default void checkExecutable( Object function, UnitTestTemporary temp )
		throws IllegalTestStateException {
	
		checkExecutable( function );
		
		if( temp == null ) {
			throw new IllegalTestCodeStateException( "Empty test temporary." );
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param result
	 * @throws IllegalTestStateException
	 */
	public default void checkExecutable( Object function, UnitTestResult result )
		throws IllegalTestStateException {
	
		checkExecutable( function );
		
		if( result == null ) {
			throw new IllegalTestResultStateException( "Empty test result." );
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param result
	 * @param functionForOK
	 * @param functionForNG
	 * @throws IllegalTestStateException
	 */
	public default void checkExecutable(
		Object function, UnitTestResult result, Object functionForOK, Object functionForNG )
		throws IllegalTestStateException {
	
		checkExecutable( function, result );
		
		if( ( functionForOK == null ) || ( functionForNG == null ) ) {
			throw new IllegalTestCodeStateException( "Empty assert function(s)." );
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public default UnitTestTemporary getUnitTestTemporaryWithoutNull( UnitTestTemporary param ) {
	
		UnitTestTemporary result = param;
		if( result == null ) {
			result = new UnitTestTemporary();
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public default UnitTestResult getUnitTestResultWithoutNull( UnitTestResult param ) {
	
		UnitTestResult result = param;
		if( param == null ) {
			result = new UnitTestResult();
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param buffer
	 * @param data
	 */
	public default void putDataTo( Map< String, Object > buffer, String name, Object data ) {
	
		if( buffer != null ) {
			
			if( name != null ) {
				
				buffer.put( name, data );
				
			} else {
				
				int intKey = -1;
				int max = -1;
				Entry< String, Object > entry = null;
				
				Iterator< Entry< String, Object > > iterator = buffer.entrySet().iterator();
				while( iterator.hasNext() ) {
					entry = iterator.next();
					intKey = -1;
					try {
						intKey = Integer.parseInt( entry.getKey() );
					} catch( NumberFormatException exp ) {}
					
					if( intKey > max ) {
						max = intKey;
					}
				}
				
				buffer.put( Integer.toString( ++max ), data );
			}
			
		}
	}
	
}
