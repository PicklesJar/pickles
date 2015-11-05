package picklesjar.pickles.ut.runtime;

import java.util.UUID;
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

/**
 * 
 * 
 * 
 * @author PicklesCooker
 *
 */
class SingleThreadUTFoundation
	implements UTFoundationCore {
	
	private String lockKey = null;
	
	private String scenarioName = null;
	
	private UnitTestTemporary temp = null;
	
	private UnitTestResult result = null;
	
	SingleThreadUTFoundation() {
	
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	@Override
	public String lock() {
	
		String result = null;
		
		if( !isLocking() ) {
			
			result = UUID.randomUUID().toString();
			this.lockKey = result;
			this.temp = null;
			this.result = null;
			this.scenarioName = null;
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	@Override
	public String lock( String scenarioName ) {
	
		String result = lock();
		
		if( result != null ) {
			
			this.scenarioName = scenarioName;
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	@Override
	public boolean putScenarioName( String key, String scenarioName ) {
	
		boolean result = false;
		
		if( ( isLocking() )
			&& ( lockKey.equals( key ) ) ) {
			
			this.scenarioName = scenarioName;
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param key
	 * @param function
	 * @return
	 */
	@Override
	public boolean before( String key, Consumer< UnitTestTemporary > function ) {
		
		boolean result = false;
		
		if( ( isLocking() )
			&& ( lockKey.equals( key ) )
			&& ( function != null ) ) {
			
			temp = getUnitTestTemporaryWithoutNull();
			
			function.accept( temp );
			result = true;
		}
		
		return result;
	}
	
	@Override
	public < T > boolean before( String key, BiConsumer< UnitTestTemporary, T > function, T arg ) {
		
		boolean result = false;
		
		if( ( isLocking() )
			&& ( lockKey.equals( key ) )
			&& ( function != null ) ) {
			
			temp = getUnitTestTemporaryWithoutNull();
			
			function.accept( temp, arg );
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public boolean unlock( String key ) {
	
		boolean result = false;
		
		if( ( isLocking() )
			&& ( lockKey.equals( key ) ) ) {
			
			this.lockKey = null;
			this.temp = null;
			this.result = null;
			this.scenarioName = null;
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	@Override
	public boolean isLocking() {
	
		return lockKey != null;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	@Override
	public String getScenarioName() {
	
		String result = null;
		
		if( isLocking() ) {
			
			result = scenarioName;
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < A > void given( Consumer< A > function, A arg ) {
	
		checkExecutable( function );
		
		function.accept( arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public void given( Consumer< UnitTestTemporary > function ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		function.accept( temp );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < A > void given( BiConsumer< UnitTestTemporary, A > function, A arg ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		function.accept( temp, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < A > void given( Consumer< A > function, UnitTestTemporary temp, A arg ) {
	
		checkExecutable( function, temp );
		
		function.accept( arg );
		
		this.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public void given( Consumer< UnitTestTemporary > function, UnitTestTemporary temp ) {
	
		checkExecutable( function, temp );
		
		function.accept( temp );
		
		this.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < A > void given(
		BiConsumer< UnitTestTemporary, A > function, UnitTestTemporary temp, A arg ) {
	
		checkExecutable( function, temp );
		
		function.accept( temp, arg );
		
		this.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public void given( UnaryOperator< UnitTestTemporary > function ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		function.apply( temp );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < T > void given( Supplier< T > function ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		putDataTo( temp, scenarioName, function.get() );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < T > void given( Function< UnitTestTemporary, T > function ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		putDataTo( temp, scenarioName, function.apply( temp ) );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < A, T > void given( Function< A, T > function, A arg ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		putDataTo( temp, scenarioName, function.apply( arg ) );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < A, T > void given( BiFunction< UnitTestTemporary, A, T > function, A arg ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		putDataTo( temp, scenarioName, function.apply( temp, arg ) );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < T > void given( String key, Supplier< T > function ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		putDataTo( temp, key, function.get() );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < T > void given( String key, Function< UnitTestTemporary, T > function ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		putDataTo( temp, key, function.apply( temp ) );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < A, T > void given( String key, Function< A, T > function, A arg ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		putDataTo( temp, key, function.apply( arg ) );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < A, T > void given( String key, BiFunction< UnitTestTemporary, A, T > function, A arg ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		putDataTo( temp, key, function.apply( temp, arg ) );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < T > void given( Supplier< T > function, UnitTestTemporary temp ) {
	
		checkExecutable( function, temp );
		
		putDataTo( temp, scenarioName, function.get() );
		
		this.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < T > void given( Function< UnitTestTemporary, T > function, UnitTestTemporary temp ) {
	
		checkExecutable( function, temp );
		
		putDataTo( temp, scenarioName, function.apply( temp ) );
		
		this.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < A, T > void given( Function< A, T > function, UnitTestTemporary temp, A arg ) {
	
		checkExecutable( function, temp );
		
		putDataTo( temp, scenarioName, function.apply( arg ) );
		
		this.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < A, T > void given(
		BiFunction< UnitTestTemporary, A, T > function, UnitTestTemporary temp, A arg ) {
	
		checkExecutable( function, temp );
		
		putDataTo( temp, scenarioName, function.apply( temp, arg ) );
		
		this.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < T > void given( String key, Supplier< T > function, UnitTestTemporary temp ) {
	
		checkExecutable( function, temp );
		
		putDataTo( temp, key, function.get() );
		
		this.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < T > void given(
		String key, Function< UnitTestTemporary, T > function, UnitTestTemporary temp ) {
	
		checkExecutable( function, temp );
		
		putDataTo( temp, key, function.apply( temp ) );
		
		this.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < A, T > void given(
		String key, Function< A, T > function, UnitTestTemporary temp, A arg ) {
	
		checkExecutable( function, temp );
		
		putDataTo( temp, key, function.apply( arg ) );
		
		this.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < A, T > void given(
		String key, BiFunction< UnitTestTemporary, A, T > function, UnitTestTemporary temp, A arg ) {
	
		checkExecutable( function, temp );
		
		putDataTo( temp, key, function.apply( temp, arg ) );
		
		this.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public void when( Consumer< UnitTestResult > function ) {
	
		checkExecutable( function );
		
		result = getUnitTestResultWithoutNull();
		
		function.accept( result );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public < A > void when( BiConsumer< UnitTestResult, A > function, A arg ) {
	
		checkExecutable( function );
		
		result = getUnitTestResultWithoutNull();
		
		function.accept( result, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public void when( BiConsumer< UnitTestResult, UnitTestTemporary > function ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		result = getUnitTestResultWithoutNull();
		
		function.accept( result, temp );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	@Override
	public < R > void when( Supplier< R > function ) {
	
		checkExecutable( function );
		
		result = getUnitTestResultWithoutNull();
		
		putDataTo( result, scenarioName, function.get() );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	@Override
	public < A, R > void when( Function< A, R > function, A arg ) {
	
		checkExecutable( function );
		
		result = getUnitTestResultWithoutNull();
		
		putDataTo( result, scenarioName, function.apply( arg ) );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	@Override
	public < R > void when( Function< UnitTestTemporary, R > function ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		result = getUnitTestResultWithoutNull();
		
		putDataTo( result, scenarioName, function.apply( temp ) );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 * @param arg
	 */
	@Override
	public < A, R > void when( BiFunction< UnitTestTemporary, A, R > function, A arg ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		result = getUnitTestResultWithoutNull();
		
		putDataTo( result, scenarioName, function.apply( temp, arg ) );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	@Override
	public < R > void when( String key, Supplier< R > function ) {
	
		checkExecutable( function );
		
		result = getUnitTestResultWithoutNull();
		
		putDataTo( result, key, function.get() );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	@Override
	public < A, R > void when( String key, Function< A, R > function, A arg ) {
	
		checkExecutable( function );
		
		result = getUnitTestResultWithoutNull();
		
		putDataTo( result, key, function.apply( arg ) );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	@Override
	public < R > void when( String key, Function< UnitTestTemporary, R > function ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		result = getUnitTestResultWithoutNull();
		
		putDataTo( result, key, function.apply( temp ) );
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 * @param arg
	 */
	@Override
	public < A, R > void when( String key, BiFunction< UnitTestTemporary, A, R > function, A arg ) {
	
		checkExecutable( function );
		
		temp = getUnitTestTemporaryWithoutNull();
		result = getUnitTestResultWithoutNull();
		
		putDataTo( result, key, function.apply( temp, arg ) );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public void then( Consumer< UnitTestResult > function ) {
	
		checkExecutable( function, result );
		
		function.accept( result );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public < A > void then( BiConsumer< UnitTestResult, A > function, A arg ) {
	
		checkExecutable( function, result );
		
		function.accept( result, arg );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < T > void then( Function< UnitTestResult, T > function ) {
	
		checkExecutable( function, result );
		
		temp = getUnitTestTemporaryWithoutNull();
		putDataTo( temp, scenarioName, function.apply( result ) );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < A, T > void then( BiFunction< UnitTestResult, A, T > function, A arg ) {
	
		checkExecutable( function, result );
		
		temp = getUnitTestTemporaryWithoutNull();
		putDataTo( temp, scenarioName, function.apply( result, arg ) );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public < T > void then( String key, Function< UnitTestResult, T > function ) {
	
		checkExecutable( function, result );
		
		temp = getUnitTestTemporaryWithoutNull();
		putDataTo( temp, key, function.apply( result ) );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param key
	 * @param function
	 * @param arg
	 */
	@Override
	public < A, T > void then( String key, BiFunction< UnitTestResult, A, T > function, A arg ) {
	
		checkExecutable( function, result );
		
		temp = getUnitTestTemporaryWithoutNull();
		putDataTo( temp, key, function.apply( result, arg ) );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public void then(
		Predicate< UnitTestResult > function, Consumer< UnitTestTemporary > functionForOK,
		Consumer< UnitTestTemporary > functionForNG ) {
	
		checkExecutable( function, result, functionForOK, functionForNG );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result ) ) {
			functionForOK.accept( temp );
		} else {
			functionForNG.accept( temp );
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public < A > void then(
		Predicate< UnitTestResult > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		Consumer< UnitTestTemporary > functionForNG, A arg ) {
	
		checkExecutable( function, result, functionForOK, functionForNG );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result ) ) {
			functionForOK.accept( temp, arg );
		} else {
			functionForNG.accept( temp );
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public < A > void then(
		Predicate< UnitTestResult > function, Consumer< UnitTestTemporary > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg ) {
	
		checkExecutable( function, result, functionForOK, functionForNG );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result ) ) {
			functionForOK.accept( temp );
		} else {
			functionForNG.accept( temp, arg );
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public < A > void then(
		Predicate< UnitTestResult > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg ) {
	
		checkExecutable( function, result, functionForOK, functionForNG );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result ) ) {
			functionForOK.accept( temp, arg );
		} else {
			functionForNG.accept( temp, arg );
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public < A > void then(
		BiPredicate< UnitTestResult, A > function, Consumer< UnitTestTemporary > functionForOK,
		Consumer< UnitTestTemporary > functionForNG, A arg ) {
	
		checkExecutable( function, result, functionForOK, functionForNG );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result, arg ) ) {
			functionForOK.accept( temp );
		} else {
			functionForNG.accept( temp );
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public < A > void then(
		BiPredicate< UnitTestResult, A > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		Consumer< UnitTestTemporary > functionForNG, A arg ) {
	
		checkExecutable( function, result, functionForOK, functionForNG );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result, arg ) ) {
			functionForOK.accept( temp, arg );
		} else {
			functionForNG.accept( temp );
		}
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
	@Override
	public < A > void then(
		BiPredicate< UnitTestResult, A > function, Consumer< UnitTestTemporary > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg ) {
	
		checkExecutable( function, result, functionForOK, functionForNG );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result, arg ) ) {
			functionForOK.accept( temp );
		} else {
			functionForNG.accept( temp, arg );
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public < A > void then(
		BiPredicate< UnitTestResult, A > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg ) {
	
		checkExecutable( function, result, functionForOK, functionForNG );
		
		temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result, arg ) ) {
			functionForOK.accept( temp, arg );
		} else {
			functionForNG.accept( temp, arg );
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @throws IllegalTestStateException
	 */
	@Override
	public void checkExecutable( Object function )
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
	@Override
	public void checkExecutable( Object function, UnitTestTemporary temp )
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
	@Override
	public void checkExecutable( Object function, UnitTestResult result )
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
	@Override
	public void checkExecutable(
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
	private UnitTestTemporary getUnitTestTemporaryWithoutNull() {
	
		return getUnitTestTemporaryWithoutNull( temp );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	private UnitTestResult getUnitTestResultWithoutNull() {
	
		return getUnitTestResultWithoutNull( result );
	}
}
