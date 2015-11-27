package picklesjar.pickles.ut.runtime.routine.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import picklesjar.pickles.ut.runtime.routine.lock.LockRoutine;

public abstract class OnlyLockRoutine
	implements LockRoutine {
	
	protected static String lockKey = null;
	
	@BeforeClass
	public static void setUp() {
	
		lockKey = LockRoutine.lock();
	}
	
	@AfterClass
	public static void tearDown() {
	
		LockRoutine.unlock( lockKey );
	}
	
}
