/**
 * 
 */
package org.elixirian.common.asm.analysis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-06-22)
 */
public interface MethodAndConstructorAnalyser extends MethodAnalyser, ConstructorAnalyser
{
	<T> Map<Member, String[]> findMethodsAndConstructorsWithParameterNames(Class<T> theClass);

	@Override
	<T> Map<Method, String[]> findMethodsWithParameterNames(Class<T> theClass);

	@Override
	<T> Map<Constructor<T>, String[]> findConstructorsWithParameterNames(Class<T> theClass);

}
