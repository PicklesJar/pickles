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
public @interface UnitTestWithCucumber {
	
	/**
	 * pickles package name.
	 * 
	 * @return
	 */
	public String value();
	
	/**
	 * Test target class.
	 * Developer will find Class by this annotated class name if not set.
	 * 
	 * @return
	 */
	public Class< ? > targetClass() default UnitTestWithCucumber.class;
}
