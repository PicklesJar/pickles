package picklesjar.pickles.ut.runtime.routine.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import picklesjar.pickles.ut.core.util.MethodQuery;
import picklesjar.pickles.ut.recipes.restrain.ShouldBeCalledInAnnotatedBy;
import picklesjar.pickles.ut.runtime.routine.targeting.MethodTargetingRoutine;

public abstract class MethodTargetingOnMultiThreadRoutine
	extends MultiThreadConfigRoutine
	implements MethodTargetingRoutine {
	
	@ShouldBeCalledInAnnotatedBy( BeforeClass.class )
	protected static void setUp( Class< ? > clazz, MethodQuery methodQuery ) {
	
		MultiThreadConfigRoutine.setUp();
		
		MethodTargetingRoutine.setToTargetMethod( lockKey, clazz, methodQuery );
	}
	
	@ShouldBeCalledInAnnotatedBy( BeforeClass.class )
	protected static void setUp( String className, MethodQuery methodQuery ) {
	
		MultiThreadConfigRoutine.setUp();
		
		MethodTargetingRoutine.setToTargetMethod( lockKey, className, methodQuery );
	}
	
	@AfterClass
	public static void tearDown() {
	
		MultiThreadConfigRoutine.tearDown();
	}
	
}
