package picklesjar.pickles.ut.runtime.routine.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import picklesjar.pickles.ut.recipes.restrain.ShouldBeCalledInAnnotatedBy;
import picklesjar.pickles.ut.runtime.routine.targeting.ClassTargetingRoutine;

public abstract class ClassTargetingOnMultiThreadRoutine
	extends MultiThreadConfigRoutine
		implements ClassTargetingRoutine {
	
	@ShouldBeCalledInAnnotatedBy( BeforeClass.class )
	public static void setUp( Class< ? > clazz ) {
	
		MultiThreadConfigRoutine.setUp();
		
		ClassTargetingRoutine.setToTargetClass( lockKey, clazz );
	}
	
	@ShouldBeCalledInAnnotatedBy( BeforeClass.class )
	public static void setUp( String className ) {
		
		MultiThreadConfigRoutine.setUp();
		
		ClassTargetingRoutine.setToTargetClass( lockKey, className );
	}
	
	@AfterClass
	public static void tearDown() {
	
		MultiThreadConfigRoutine.tearDown();
	}
	
}
