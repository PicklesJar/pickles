package picklesjar.pickles.ut.runtime;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * 
 * 
 * 
 * @author PicklesCooker
 *
 */
public final class UnitTestRuntimeFoundation {
	
	public static final int SINGLE_THREAD_MODE = 0x0;
	
	public static final int MULTI_THREAD_MODE = 0x1;
	
	private static final int THREAD_MODE_CHECK = 0x1;
	
	private static UTFoundationCore instance = new SingleThreadUTFoundation();
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static boolean switchMode( int mode ) {
	
		boolean result = false;
		
		if( !instance.isLocking() ) {
			
			if( ( mode & THREAD_MODE_CHECK ) == 0 ) {
				instance = new SingleThreadUTFoundation();
			} else if( ( mode & THREAD_MODE_CHECK ) == 1 ) {
				instance = new MultiThreadUTFoundation();
			}
			
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static String lock() {
	
		return instance.lock();
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static String lock( String scenarioName ) {
	
		return instance.lock( scenarioName );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static boolean putScenarioName( String key, String scenarioName ) {
	
		return instance.putScenarioName( key, scenarioName );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static boolean before( String key, Consumer< UnitTestTemporary > function ) {
	
		return instance.before( key, function );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static < T > boolean before( String key, BiConsumer< UnitTestTemporary, T > function, T arg ) {
	
		return instance.before( key, function, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param key
	 * @return
	 */
	public static boolean unlock( String key ) {
	
		return instance.unlock( key );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static boolean isLocking() {
	
		return instance.isLocking();
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	public static String getScenarioName() {
	
		return instance.getScenarioName();
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < A > void given( Consumer< A > function, A arg ) {
	
		instance.given( function, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static void given( Consumer< UnitTestTemporary > function ) {
	
		instance.given( function );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < A > void given( BiConsumer< UnitTestTemporary, A > function, A arg ) {
	
		instance.given( function, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < A > void given( Consumer< A > function, UnitTestTemporary temp, A arg ) {
	
		instance.given( function, temp, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static void given( Consumer< UnitTestTemporary > function, UnitTestTemporary temp ) {
	
		instance.given( function, temp );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < A > void given(
		BiConsumer< UnitTestTemporary, A > function, UnitTestTemporary temp, A arg ) {
	
		instance.given( function, temp, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static void given( UnaryOperator< UnitTestTemporary > function ) {
	
		instance.given( function );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < T > void given( Supplier< T > function ) {
	
		instance.given( function );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < T > void given( Function< UnitTestTemporary, T > function ) {
	
		instance.given( function );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < A, T > void given( Function< A, T > function, A arg ) {
	
		instance.given( function, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < A, T > void given( BiFunction< UnitTestTemporary, A, T > function, A arg ) {
	
		instance.given( function, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < T > void given( String key, Supplier< T > function ) {
	
		instance.given( function );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < T > void given( String key, Function< UnitTestTemporary, T > function ) {
	
		instance.given( key, function );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < A, T > void given( String key, Function< A, T > function, A arg ) {
	
		instance.given( key, function, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < A, T > void given( String key, BiFunction< UnitTestTemporary, A, T > function, A arg ) {
	
		instance.given( key, function, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < T > void given( Supplier< T > function, UnitTestTemporary temp ) {
	
		instance.given( function, temp );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < T > void given( Function< UnitTestTemporary, T > function, UnitTestTemporary temp ) {
	
		instance.given( function, temp );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < A, T > void given( Function< A, T > function, UnitTestTemporary temp, A arg ) {
	
		instance.given( function, temp, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < A, T > void given(
		BiFunction< UnitTestTemporary, A, T > function, UnitTestTemporary temp, A arg ) {
	
		instance.given( function, temp, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < T > void given( String key, Supplier< T > function, UnitTestTemporary temp ) {
	
		instance.given( key, function, temp );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < T > void given(
		String key, Function< UnitTestTemporary, T > function, UnitTestTemporary temp ) {
	
		instance.given( key, function, temp );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < A, T > void given(
		String key, Function< A, T > function, UnitTestTemporary temp, A arg ) {
	
		instance.given( key, function, temp, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < A, T > void given(
		String key, BiFunction< UnitTestTemporary, A, T > function, UnitTestTemporary temp, A arg ) {
	
		instance.given( key, function, temp, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static void when( Consumer< UnitTestResult > function ) {
	
		instance.when( function );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public static < A > void when( BiConsumer< UnitTestResult, A > function, A arg ) {
	
		instance.when( function, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static void when( BiConsumer< UnitTestResult, UnitTestTemporary > function ) {
	
		instance.when( function );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	public static < R > void when( Supplier< R > function ) {
	
		instance.when( function );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	public static < A, R > void when( Function< A, R > function, A arg ) {
	
		instance.when( function, arg );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	public static < R > void when( Function< UnitTestTemporary, R > function ) {
	
		instance.when( function );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 * @param arg
	 */
	public static < A, R > void when( BiFunction< UnitTestTemporary, A, R > function, A arg ) {
	
		instance.when( function, arg );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	public static < R > void when( String key, Supplier< R > function ) {
	
		instance.when( key, function );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	public static < A, R > void when( String key, Function< A, R > function, A arg ) {
	
		instance.when( key, function, arg );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	public static < R > void when( String key, Function< UnitTestTemporary, R > function ) {
	
		instance.when( key, function );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 * @param arg
	 */
	public static < A, R > void when( String key, BiFunction< UnitTestTemporary, A, R > function, A arg ) {
	
		instance.when( key, function, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static void then( Consumer< UnitTestResult > function ) {
	
		instance.then( function );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public static < A > void then( BiConsumer< UnitTestResult, A > function, A arg ) {
	
		instance.then( function, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < T > void then( Function< UnitTestResult, T > function ) {
	
		instance.then( function );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < A, T > void then( BiFunction< UnitTestResult, A, T > function, A arg ) {
	
		instance.then( function, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	public static < T > void then( String key, Function< UnitTestResult, T > function ) {
	
		instance.then( key, function );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param key
	 * @param function
	 * @param arg
	 */
	public static < A, T > void then( String key, BiFunction< UnitTestResult, A, T > function, A arg ) {
	
		instance.then( key, function, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public static void then(
		Predicate< UnitTestResult > function, Consumer< UnitTestTemporary > functionForOK,
		Consumer< UnitTestTemporary > functionForNG ) {
	
		instance.then( function, functionForOK, functionForNG );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public static < A > void then(
		Predicate< UnitTestResult > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		Consumer< UnitTestTemporary > functionForNG, A arg ) {
	
		instance.then( function, functionForOK, functionForNG, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public static < A > void then(
		Predicate< UnitTestResult > function, Consumer< UnitTestTemporary > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg ) {
	
		instance.then( function, functionForOK, functionForNG, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public static < A > void then(
		Predicate< UnitTestResult > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg ) {
	
		instance.then( function, functionForOK, functionForNG, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public static < A > void then(
		BiPredicate< UnitTestResult, A > function, Consumer< UnitTestTemporary > functionForOK,
		Consumer< UnitTestTemporary > functionForNG, A arg ) {
	
		instance.then( function, functionForOK, functionForNG, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public static < A > void then(
		BiPredicate< UnitTestResult, A > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		Consumer< UnitTestTemporary > functionForNG, A arg ) {
	
		instance.then( function, functionForOK, functionForNG, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param functionForOK
	 * @param functionForNG
	 * @param arg
	 */
	public static < A > void then(
		BiPredicate< UnitTestResult, A > function, Consumer< UnitTestTemporary > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg ) {
	
		instance.then( function, functionForOK, functionForNG, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	public static < A > void then(
		BiPredicate< UnitTestResult, A > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg ) {
	
		instance.then( function, functionForOK, functionForNG, arg );
	}
	
}
