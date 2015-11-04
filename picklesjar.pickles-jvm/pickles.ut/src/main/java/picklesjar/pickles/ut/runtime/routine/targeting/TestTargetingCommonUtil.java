package picklesjar.pickles.ut.runtime.routine.targeting;

import java.util.function.BiConsumer;

import picklesjar.pickles.ut.core.TemporaryPreparedKey;
import picklesjar.pickles.ut.runtime.UnitTestRuntimeFoundation;
import picklesjar.pickles.ut.runtime.UnitTestTemporary;

interface TestTargetingCommonUtil {
	
	public static void setToTarget(
		String lockKey, TemporaryPreparedKey key, String className ) {
		
		UnitTestRuntimeFoundation.before( lockKey,
			( BiConsumer< UnitTestTemporary, String[] > )
			( temp, keyAndValue ) -> {
				
				temp.put( keyAndValue[ 0 ], keyAndValue[ 1 ] );
				
			}, new String[] { key.name(), className } );
		
	}
	
}
