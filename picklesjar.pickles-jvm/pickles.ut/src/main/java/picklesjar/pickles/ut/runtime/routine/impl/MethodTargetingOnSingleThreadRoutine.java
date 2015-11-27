package picklesjar.pickles.ut.runtime.routine.impl;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import picklesjar.pickles.ut.core.util.MethodQuery;
import picklesjar.pickles.ut.prepare.design.StereotypeDesign;
import picklesjar.pickles.ut.recipes.restrain.ShouldBeCalledInAnnotatedBy;
import picklesjar.pickles.ut.runtime.routine.targeting.MethodTargetingRoutine;

public abstract class MethodTargetingOnSingleThreadRoutine
	extends SingleThreadConfigRoutine
	implements MethodTargetingRoutine {
	
	@ShouldBeCalledInAnnotatedBy( BeforeClass.class )
	protected static void setUp( Class< ? > clazz, MethodQuery methodQuery ) {
	
		SingleThreadConfigRoutine.setUp();
		
		MethodTargetingRoutine.setToTargetMethod( lockKey, clazz, methodQuery );
	}
	
	@ShouldBeCalledInAnnotatedBy( BeforeClass.class )
	protected static void setUp( Class< ? > clazz, MethodQuery methodQuery, StereotypeDesign stereotype ) {
	
		SingleThreadConfigRoutine.setUp();
		
		MethodTargetingRoutine.setToTargetMethod( lockKey, clazz, methodQuery, stereotype );
	}
	
	@ShouldBeCalledInAnnotatedBy( BeforeClass.class )
	protected static void setUp( String className, MethodQuery methodQuery ) {
	
		SingleThreadConfigRoutine.setUp();
		
		MethodTargetingRoutine.setToTargetMethod( lockKey, className, methodQuery );
	}
	
	@ShouldBeCalledInAnnotatedBy( BeforeClass.class )
	protected static void setUp( String className, MethodQuery methodQuery, StereotypeDesign stereotype ) {
	
		SingleThreadConfigRoutine.setUp();
		
		MethodTargetingRoutine.setToTargetMethod( lockKey, className, methodQuery, stereotype );
	}
	
	@AfterClass
	public static void tearDown() {
	
		SingleThreadConfigRoutine.tearDown();
	}
	
}
