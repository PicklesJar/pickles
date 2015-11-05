package picklesjar.pickles.ut.runtime.routine.targeting;

import picklesjar.pickles.ut.core.TemporaryPreparedKey;
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
	public static void setToTargetMethod( String lockKey, Class< ? > clazz, String methodQuery ) {
	
		ClassTargetingRoutine.setToTargetClass( lockKey, clazz );
		
		TestTargetingCommonUtil.setToTarget( lockKey,
				TemporaryPreparedKey.TEST_TARGET_METHOD_NAME, methodQuery );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param lockKey
	 * @param className
	 */
	@ShouldBeBefore( ConfigureRoutine.class )
	public static void setToTargetMethod( String lockKey, String className, String methodQuery ) {
	
		ClassTargetingRoutine.setToTargetClass( lockKey, className );
		
		TestTargetingCommonUtil.setToTarget( lockKey,
			TemporaryPreparedKey.TEST_TARGET_METHOD_NAME, methodQuery );
	}
	
}
