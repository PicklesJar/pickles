package picklesjar.pickles.ut.runtime.routine.targeting;

import java.util.function.BiConsumer;

import picklesjar.pickles.ut.core.PreparedTemporaryKey;
import picklesjar.pickles.ut.core.util.MethodQuery;
import picklesjar.pickles.ut.runtime.UnitTestRuntimeFoundation;
import picklesjar.pickles.ut.runtime.UnitTestTemporary;

interface TestTargetingCommonUtil {
	
	/**
	 * 
	 * 
	 * 
	 * @param lockKey
	 * @param key
	 * @param className
	 */
	public static void setToTarget(
		String lockKey, PreparedTemporaryKey key, String className ) {
	
		UnitTestRuntimeFoundation.before( lockKey,
			( BiConsumer< UnitTestTemporary, String[] > )
			( temp, keyAndValue ) -> {
				
				temp.put( keyAndValue[ 0 ], keyAndValue[ 1 ] );
				
			}, new String[] {
				key.name(), className } );
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param lockKey
	 * @param key
	 * @param methodQuery
	 */
	public static void setToTarget(
		String lockKey, PreparedTemporaryKey key, MethodQuery methodQuery ) {
	
		UnitTestRuntimeFoundation.before( lockKey,
			( BiConsumer< UnitTestTemporary, Object[] > )
			( temp, keyAndValue ) -> {
				
				temp.put(
					( String )keyAndValue[ 0 ],
					( MethodQuery )keyAndValue[ 1 ] );
				
			},
			new Object[] {
				key.name(), methodQuery } );
		
	}
	
}
