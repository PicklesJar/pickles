package picklesjar.pickles.ut.runtime.routine.targeting;

import picklesjar.pickles.ut.core.PreparedTemporaryKey;
import picklesjar.pickles.ut.core.util.MethodQuery;
import picklesjar.pickles.ut.recipes.sequence.ShouldBeBefore;
import picklesjar.pickles.ut.runtime.routine.config.ConfigureRoutine;

public interface MethodTargetingRoutine
	extends TestTargetingRoutine {
	
	/**
	 * 
	 * 
	 * 
	 * @param lockKey
	 * @param clazz
	 */
	@ShouldBeBefore( ConfigureRoutine.class )
	public static void setToTargetMethod(
		String lockKey, Class< ? > clazz, MethodQuery method ) {
	
		ClassTargetingRoutine.setToTargetClass( lockKey, clazz );
		
		TestTargetingCommonUtil.setToTarget( lockKey,
			PreparedTemporaryKey.TEST_TARGET_METHOD_QUERY, method );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param lockKey
	 * @param className
	 */
	@ShouldBeBefore( ConfigureRoutine.class )
	public static void setToTargetMethod(
		String lockKey, String className, MethodQuery method ) {
	
		ClassTargetingRoutine.setToTargetClass( lockKey, className );
		
		TestTargetingCommonUtil.setToTarget( lockKey,
			PreparedTemporaryKey.TEST_TARGET_METHOD_QUERY, method );
	}
	
}
