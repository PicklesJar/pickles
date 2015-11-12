package picklesjar.pickles.ut.core;

import picklesjar.pickles.ut.runtime.UnitTestTemporary;

public interface UnitTestTemporaryKey {
	
	public Object getValueFrom( UnitTestTemporary temp );
	
	public < T > T valueOf( UnitTestTemporary temp );
	
	public < T > T valueOf( UnitTestTemporary temp, Class< T > castTarget );
	
	public Class< ? > getExchangeTargetClass();
	
}
