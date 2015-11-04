package picklesjar.pickles.ut.runtime.routine.lock;

import picklesjar.pickles.ut.recipes.sequence.ShouldBeAfter;
import picklesjar.pickles.ut.recipes.sequence.ShouldBeBefore;
import picklesjar.pickles.ut.runtime.UnitTestRuntimeFoundation;
import picklesjar.pickles.ut.runtime.routine.Routine;
import picklesjar.pickles.ut.runtime.routine.config.ConfigureRoutine;

public interface LockRoutine
	extends Routine {
	
	@ShouldBeAfter( ConfigureRoutine.class )
	public static String lock() {
	
		return UnitTestRuntimeFoundation.lock();
	}
	
	@ShouldBeBefore( ConfigureRoutine.class )
	public static void unlock( String key ) {
	
		UnitTestRuntimeFoundation.unlock( key );
	}
	
}
