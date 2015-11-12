package picklesjar.pickles.ut.prepare;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import picklesjar.pickles.ut.recipes.MethodQueryString;

/**
 * 
 * 
 * 
 * @author PicklesCooker
 * 
 */
@Target( { ElementType.TYPE } )
@Retention( RetentionPolicy.RUNTIME )
@Repeatable( TestTargetMethodMappings.class )
public @interface TestTargetMethodMapping {
	
	/**
	 * Target method search query string.
	 * 
	 * @return
	 */
	@MethodQueryString
	public String query();
	
	/**
	 * Execute feature file paths.
	 * 
	 * @return
	 */
	public String[] features() default {};
	
	/**
	 * Execute tag names.
	 * 
	 * @return
	 */
	public String[] tags() default {};
	
}
