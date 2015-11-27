package picklesjar.pickles.ut.runtime.routine.targeting;

import picklesjar.pickles.ut.core.PreparedTemporaryKey;
import picklesjar.pickles.ut.recipes.sequence.ShouldBeBefore;
import picklesjar.pickles.ut.runtime.routine.config.ConfigureRoutine;

public interface ClassTargetingRoutine
	extends TestTargetingRoutine {
	
	/**
	 * 
	 * 
	 * 
	 * @param lockKey
	 * @param clazz
	 */
	@ShouldBeBefore( ConfigureRoutine.class )
	public static void setToTargetClass( String lockKey, Class< ? > clazz ) {
	
		String className = null;
		if( clazz != null ) {
			className = clazz.getName();
		}
		
		setToTargetClass( lockKey, className );
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param lockKey
	 * @param className
	 */
	@ShouldBeBefore( ConfigureRoutine.class )
	public static void setToTargetClass( String lockKey, String className ) {
	
		TestTargetingCommonUtil.setToTarget( lockKey,
			PreparedTemporaryKey.TEST_TARGET_CLASS_FULLNAME, className );
	}
	
}
