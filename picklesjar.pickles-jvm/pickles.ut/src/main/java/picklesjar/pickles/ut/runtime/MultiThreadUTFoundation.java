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
class MultiThreadUTFoundation
	implements UTFoundationCore {
	
	private static final ThreadLocal< ThreadLocalData > localdata = new ThreadLocal<>();
	
	MultiThreadUTFoundation() {
	
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	@Override
	public synchronized String lock() {
	
		String result = null;
		
		if( !isLocking() ) {
			
			result = UUID.randomUUID().toString();
			
			ThreadLocalData data = localdata.get();
			if( data == null ) {
				data = new ThreadLocalData();
			}
			
			data.lockKey = result;
			data.temp = null;
			data.result = null;
			data.scenarioName = null;
			
			localdata.set( data );
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
	public synchronized String lock( String scenarioName ) {
	
		String result = lock();
		
		if( result != null ) {
			
			ThreadLocalData data = localdata.get();
			data.scenarioName = scenarioName;
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
	public synchronized boolean putScenarioName( String key, String scenarioName ) {
	
		boolean result = false;
		
		if( isLocking() ) {
			
			ThreadLocalData data = localdata.get();
			if( data.lockKey.equals( key ) ) {
				
				data.scenarioName = scenarioName;
				result = true;
			}
			
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
	public synchronized boolean before( String key, Consumer< UnitTestTemporary > function ) {
		
		boolean result = false;
		
		if( ( isLocking() )
			&& ( function != null ) ) {
			
			ThreadLocalData data = localdata.get();
			if( data.lockKey.equals( key ) ) {
				
				data.temp = getUnitTestTemporaryWithoutNull();
				
				function.accept( data.temp );
				result = true;
			}
			
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param key
	 * @param function
	 * @param arg
	 * @return
	 */
	@Override
	public synchronized < T > boolean before( String key, BiConsumer< UnitTestTemporary, T > function, T arg ) {
		
		boolean result = false;
		
		if( ( isLocking() )
			&& ( function != null ) ) {
			
			ThreadLocalData data = localdata.get();
			if( data.lockKey.equals( key ) ) {
				
				data.temp = getUnitTestTemporaryWithoutNull();
				
				function.accept( data.temp, arg );
				result = true;
			}
			
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
	public synchronized boolean unlock( String key ) {
	
		boolean result = false;
		
		if( isLocking() ) {
			
			ThreadLocalData data = localdata.get();
			if( data.lockKey.equals( key ) ) {
				
				data.lockKey = null;
				data.temp = null;
				data.result = null;
				data.scenarioName = null;
				result = true;
				
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
	@Override
	public synchronized boolean isLocking() {
	
		ThreadLocalData data = localdata.get();
		return data != null
			&& data.lockKey != null;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @return
	 */
	@Override
	public synchronized String getScenarioName() {
	
		String result = null;
		
		if( isLocking() ) {
			
			result = localdata.get().scenarioName;
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
	public synchronized < A > void given( Consumer< A > function, A arg ) {
	
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
	public synchronized void given( Consumer< UnitTestTemporary > function ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		function.accept( temp );
		
		localdata.get().temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < A > void given( BiConsumer< UnitTestTemporary, A > function, A arg ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		function.accept( temp, arg );
		
		localdata.get().temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < A > void given( Consumer< A > function, UnitTestTemporary temp, A arg ) {
	
		checkExecutable( function, temp );
		
		function.accept( arg );
		
		localdata.get().temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized void given( Consumer< UnitTestTemporary > function, UnitTestTemporary temp ) {
	
		checkExecutable( function, temp );
		
		function.accept( temp );
		
		localdata.get().temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < A > void given(
		BiConsumer< UnitTestTemporary, A > function, UnitTestTemporary temp, A arg ) {
	
		checkExecutable( function, temp );
		
		function.accept( temp, arg );
		
		localdata.get().temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized void given( UnaryOperator< UnitTestTemporary > function ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		localdata.get().temp = function.apply( temp );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < T > void given( Supplier< T > function ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		ThreadLocalData data = localdata.get();
		putDataTo( temp, data.scenarioName, function.get() );
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < T > void given( Function< UnitTestTemporary, T > function ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		ThreadLocalData data = localdata.get();
		putDataTo( temp, data.scenarioName, function.apply( temp ) );
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < A, T > void given( Function< A, T > function, A arg ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		ThreadLocalData data = localdata.get();
		putDataTo( temp, data.scenarioName, function.apply( arg ) );
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < A, T > void given( BiFunction< UnitTestTemporary, A, T > function, A arg ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		ThreadLocalData data = localdata.get();
		putDataTo( temp, data.scenarioName, function.apply( temp, arg ) );
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < T > void given( String key, Supplier< T > function ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		putDataTo( temp, key, function.get() );
		
		localdata.get().temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < T > void given( String key, Function< UnitTestTemporary, T > function ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		putDataTo( temp, key, function.apply( temp ) );
		
		localdata.get().temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < A, T > void given( String key, Function< A, T > function, A arg ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		putDataTo( temp, key, function.apply( arg ) );
		
		localdata.get().temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < A, T > void given( String key, BiFunction< UnitTestTemporary, A, T > function, A arg ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		putDataTo( temp, key, function.apply( temp, arg ) );
		
		localdata.get().temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < T > void given( Supplier< T > function, UnitTestTemporary temp ) {
	
		checkExecutable( function, temp );
		
		ThreadLocalData data = localdata.get();
		putDataTo( temp, data.scenarioName, function.get() );
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < T > void given( Function< UnitTestTemporary, T > function, UnitTestTemporary temp ) {
	
		checkExecutable( function, temp );
		
		ThreadLocalData data = localdata.get();
		putDataTo( temp, data.scenarioName, function.apply( temp ) );
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < A, T > void given( Function< A, T > function, UnitTestTemporary temp, A arg ) {
	
		checkExecutable( function, temp );
		
		ThreadLocalData data = localdata.get();
		putDataTo( temp, data.scenarioName, function.apply( arg ) );
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < A, T > void given(
		BiFunction< UnitTestTemporary, A, T > function, UnitTestTemporary temp, A arg ) {
	
		checkExecutable( function, temp );
		
		ThreadLocalData data = localdata.get();
		putDataTo( temp, data.scenarioName, function.apply( temp, arg ) );
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < T > void given( String key, Supplier< T > function, UnitTestTemporary temp ) {
	
		checkExecutable( function, temp );
		
		putDataTo( temp, key, function.get() );
		
		localdata.get().temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < T > void given(
		String key, Function< UnitTestTemporary, T > function, UnitTestTemporary temp ) {
	
		checkExecutable( function, temp );
		
		putDataTo( temp, key, function.apply( temp ) );
		
		localdata.get().temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < A, T > void given(
		String key, Function< A, T > function, UnitTestTemporary temp, A arg ) {
	
		checkExecutable( function, temp );
		
		putDataTo( temp, key, function.apply( arg ) );
		
		localdata.get().temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < A, T > void given(
		String key, BiFunction< UnitTestTemporary, A, T > function, UnitTestTemporary temp, A arg ) {
	
		checkExecutable( function, temp );
		
		putDataTo( temp, key, function.apply( temp, arg ) );
		
		localdata.get().temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized void when( Consumer< UnitTestResult > function ) {
	
		checkExecutable( function );
		
		UnitTestResult result = getUnitTestResultWithoutNull();
		
		function.accept( result );
		
		localdata.get().result = result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public synchronized < A > void when( BiConsumer< UnitTestResult, A > function, A arg ) {
	
		checkExecutable( function );
		
		UnitTestResult result = getUnitTestResultWithoutNull();
		
		function.accept( result, arg );
		
		localdata.get().result = result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized void when( BiConsumer< UnitTestResult, UnitTestTemporary > function ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		UnitTestResult result = getUnitTestResultWithoutNull();
		
		function.accept( result, temp );
		
		ThreadLocalData data = localdata.get();
		data.temp = temp;
		data.result = result;
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	@Override
	public synchronized < R > void when( Supplier< R > function ) {
	
		checkExecutable( function );
		
		UnitTestResult result = getUnitTestResultWithoutNull();
		
		ThreadLocalData data = localdata.get();
		putDataTo( result, data.scenarioName, function.get() );
		
		data.result = result;
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	@Override
	public synchronized < A, R > void when( Function< A, R > function, A arg ) {
	
		checkExecutable( function );
		
		UnitTestResult result = getUnitTestResultWithoutNull();
		
		ThreadLocalData data = localdata.get();
		putDataTo( result, data.scenarioName, function.apply( arg ) );
		
		data.result = result;
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	@Override
	public synchronized < R > void when( Function< UnitTestTemporary, R > function ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		UnitTestResult result = getUnitTestResultWithoutNull();
		
		ThreadLocalData data = localdata.get();
		
		putDataTo( result, data.scenarioName, function.apply( temp ) );
		
		data.temp = temp;
		data.result = result;
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 * @param arg
	 */
	@Override
	public synchronized < A, R > void when( BiFunction< UnitTestTemporary, A, R > function, A arg ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		UnitTestResult result = getUnitTestResultWithoutNull();
		
		ThreadLocalData data = localdata.get();
		putDataTo( result, data.scenarioName, function.apply( temp, arg ) );
		
		data.temp = temp;
		data.result = result;
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	@Override
	public synchronized < R > void when( String key, Supplier< R > function ) {
	
		checkExecutable( function );
		
		UnitTestResult result = getUnitTestResultWithoutNull();
		
		putDataTo( result, key, function.get() );
		
		localdata.get().result = result;
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	@Override
	public synchronized < A, R > void when( String key, Function< A, R > function, A arg ) {
	
		checkExecutable( function );
		
		UnitTestResult result = getUnitTestResultWithoutNull();
		
		putDataTo( result, key, function.apply( arg ) );
		
		localdata.get().result = result;
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 */
	@Override
	public synchronized < R > void when( String key, Function< UnitTestTemporary, R > function ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		UnitTestResult result = getUnitTestResultWithoutNull();
		
		putDataTo( result, key, function.apply( temp ) );
		
		ThreadLocalData data = localdata.get();
		data.temp = temp;
		data.result = result;
	}
	
	/**
	 *
	 *
	 *
	 * @param function
	 * @param arg
	 */
	@Override
	public synchronized < A, R > void when( String key, BiFunction< UnitTestTemporary, A, R > function, A arg ) {
	
		checkExecutable( function );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		UnitTestResult result = getUnitTestResultWithoutNull();
		
		putDataTo( result, key, function.apply( temp, arg ) );
		
		ThreadLocalData data = localdata.get();
		data.temp = temp;
		data.result = result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized void then( Consumer< UnitTestResult > function ) {
	
		UnitTestResult result = localdata.get().result;
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
	public synchronized < A > void then( BiConsumer< UnitTestResult, A > function, A arg ) {
	
		UnitTestResult result = localdata.get().result;
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
	public synchronized < T > void then( Function< UnitTestResult, T > function ) {
	
		ThreadLocalData data = localdata.get();
		UnitTestResult result = data.result;
		checkExecutable( function, result );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		putDataTo( temp, data.scenarioName, function.apply( result ) );
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < A, T > void then( BiFunction< UnitTestResult, A, T > function, A arg ) {
	
		ThreadLocalData data = localdata.get();
		UnitTestResult result = data.result;
		checkExecutable( function, result );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		putDataTo( temp, data.scenarioName, function.apply( result, arg ) );
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 */
	@Override
	public synchronized < T > void then( String key, Function< UnitTestResult, T > function ) {
	
		ThreadLocalData data = localdata.get();
		UnitTestResult result = data.result;
		checkExecutable( function, result );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		putDataTo( temp, key, function.apply( result ) );
		
		data.temp = temp;
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
	public synchronized < A, T > void then( String key, BiFunction< UnitTestResult, A, T > function, A arg ) {
	
		ThreadLocalData data = localdata.get();
		UnitTestResult result = data.result;
		checkExecutable( function, result );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		putDataTo( temp, key, function.apply( result, arg ) );
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public synchronized void then(
		Predicate< UnitTestResult > function, Consumer< UnitTestTemporary > functionForOK,
		Consumer< UnitTestTemporary > functionForNG ) {
	
		ThreadLocalData data = localdata.get();
		UnitTestResult result = data.result;
		checkExecutable( function, result, functionForOK, functionForNG );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result ) ) {
			functionForOK.accept( temp );
		} else {
			functionForNG.accept( temp );
		}
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public synchronized < A > void then(
		Predicate< UnitTestResult > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		Consumer< UnitTestTemporary > functionForNG, A arg ) {
	
		ThreadLocalData data = localdata.get();
		UnitTestResult result = data.result;
		checkExecutable( function, result, functionForOK, functionForNG );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result ) ) {
			functionForOK.accept( temp, arg );
		} else {
			functionForNG.accept( temp );
		}
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public synchronized < A > void then(
		Predicate< UnitTestResult > function, Consumer< UnitTestTemporary > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg ) {
	
		ThreadLocalData data = localdata.get();
		UnitTestResult result = data.result;
		checkExecutable( function, result, functionForOK, functionForNG );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result ) ) {
			functionForOK.accept( temp );
		} else {
			functionForNG.accept( temp, arg );
		}
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public synchronized < A > void then(
		Predicate< UnitTestResult > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg ) {
	
		ThreadLocalData data = localdata.get();
		UnitTestResult result = data.result;
		checkExecutable( function, result, functionForOK, functionForNG );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result ) ) {
			functionForOK.accept( temp, arg );
		} else {
			functionForNG.accept( temp, arg );
		}
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public synchronized < A > void then(
		BiPredicate< UnitTestResult, A > function, Consumer< UnitTestTemporary > functionForOK,
		Consumer< UnitTestTemporary > functionForNG, A arg ) {
	
		ThreadLocalData data = localdata.get();
		UnitTestResult result = data.result;
		checkExecutable( function, result, functionForOK, functionForNG );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result, arg ) ) {
			functionForOK.accept( temp );
		} else {
			functionForNG.accept( temp );
		}
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public synchronized < A > void then(
		BiPredicate< UnitTestResult, A > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		Consumer< UnitTestTemporary > functionForNG, A arg ) {
	
		ThreadLocalData data = localdata.get();
		UnitTestResult result = data.result;
		checkExecutable( function, result, functionForOK, functionForNG );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result, arg ) ) {
			functionForOK.accept( temp, arg );
		} else {
			functionForNG.accept( temp );
		}
		
		data.temp = temp;
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
	public synchronized < A > void then(
		BiPredicate< UnitTestResult, A > function, Consumer< UnitTestTemporary > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg ) {
	
		ThreadLocalData data = localdata.get();
		UnitTestResult result = data.result;
		checkExecutable( function, result, functionForOK, functionForNG );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result, arg ) ) {
			functionForOK.accept( temp );
		} else {
			functionForNG.accept( temp, arg );
		}
		
		data.temp = temp;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param function
	 * @param arg
	 */
	@Override
	public synchronized < A > void then(
		BiPredicate< UnitTestResult, A > function, BiConsumer< UnitTestTemporary, A > functionForOK,
		BiConsumer< UnitTestTemporary, A > functionForNG, A arg ) {
	
		ThreadLocalData data = localdata.get();
		UnitTestResult result = data.result;
		checkExecutable( function, result, functionForOK, functionForNG );
		
		UnitTestTemporary temp = getUnitTestTemporaryWithoutNull();
		
		if( function.test( result, arg ) ) {
			functionForOK.accept( temp, arg );
		} else {
			functionForNG.accept( temp, arg );
		}
		
		data.temp = temp;
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
	private static UnitTestTemporary getUnitTestTemporaryWithoutNull() {
	
		UnitTestTemporary result = localdata.get().temp;
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
	private static UnitTestResult getUnitTestResultWithoutNull() {
	
		UnitTestResult result = localdata.get().result;
		if( result == null ) {
			result = new UnitTestResult();
		}
		
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 */
	private class ThreadLocalData {
		
		public String lockKey = null;
		
		public String scenarioName = null;
		
		public UnitTestTemporary temp = null;
		
		public UnitTestResult result = null;
	}
}
