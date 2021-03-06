package picklesjar.pickles.ut.runtime.routine.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import picklesjar.pickles.ut.recipes.restrain.ShouldBeCalledInAnnotatedBy;
import picklesjar.pickles.ut.runtime.routine.targeting.MethodTargetingRoutine;

public abstract class MethodTargetingOnMultiThreadRoutine
	extends MultiThreadConfigRoutine
		implements MethodTargetingRoutine {
	
	@ShouldBeCalledInAnnotatedBy( BeforeClass.class )
	public static void setUp( Class< ? > clazz, String methodQuery ) {
		
		MultiThreadConfigRoutine.setUp();
		
		MethodTargetingRoutine.setToTargetMethod( lockKey, clazz, methodQuery );
	}
	
	@ShouldBeCalledInAnnotatedBy( BeforeClass.class )
	public static void setUp( String className, String methodQuery ) {
		
		MultiThreadConfigRoutine.setUp();
		
		MethodTargetingRoutine.setToTargetMethod( lockKey, className, methodQuery );
	}
	
	@AfterClass
	public static void tearDown() {
		
		MultiThreadConfigRoutine.tearDown();
	}
	
}
