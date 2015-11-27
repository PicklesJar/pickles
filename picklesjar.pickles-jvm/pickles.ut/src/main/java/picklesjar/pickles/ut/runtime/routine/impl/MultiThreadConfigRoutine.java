package picklesjar.pickles.ut.runtime.routine.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import picklesjar.pickles.ut.runtime.routine.config.ThreadConfigRoutine;

public abstract class MultiThreadConfigRoutine
	extends OnlyLockRoutine
	implements ThreadConfigRoutine {
	
	@BeforeClass
	public static void setUp() {
	
		OnlyLockRoutine.setUp();
		
		ThreadConfigRoutine.setToMultiThread();
	}
	
	@AfterClass
	public static void tearDown() {
	
		OnlyLockRoutine.tearDown();
	}
	
}
