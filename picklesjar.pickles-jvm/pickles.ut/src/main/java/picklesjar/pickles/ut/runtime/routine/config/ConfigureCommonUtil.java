package picklesjar.pickles.ut.runtime.routine.config;

import picklesjar.pickles.ut.runtime.UnitTestRuntimeFoundation;

interface ConfigureCommonUtil {
	
	public static void config( int flag ) {
		
		UnitTestRuntimeFoundation.switchMode( flag );
	}
	
}
