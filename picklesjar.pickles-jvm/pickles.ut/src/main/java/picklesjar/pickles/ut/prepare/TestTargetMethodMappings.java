package picklesjar.pickles.ut.prepare;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 
 * 
 * @author PicklesCooker
 * 
 */
@Target( { ElementType.TYPE } )
@Retention( RetentionPolicy.RUNTIME )
public @interface TestTargetMethodMappings {
	
	/**
	 * 
	 * 
	 * @return
	 */
	public TestTargetMethodMapping[] value();
}
