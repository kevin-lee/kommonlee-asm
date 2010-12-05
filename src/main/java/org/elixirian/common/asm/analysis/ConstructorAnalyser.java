/**
 * 
 */
package org.elixirian.common.asm.analysis;

import java.lang.reflect.Constructor;
import java.util.Map;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-06-22)
 */
public interface ConstructorAnalyser
{
	<T> Map<Constructor<T>, String[]> findConstructorsWithParameterNames(Class<T> theClass);
}
