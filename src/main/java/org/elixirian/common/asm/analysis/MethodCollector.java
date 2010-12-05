/**
 * 
 */
package org.elixirian.common.asm.analysis;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentMap;

import org.elixirian.common.asm.util.AsmClasses;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-06-22)
 */
public class MethodCollector extends AbstractMemberCollector<Method>
{
	public MethodCollector()
	{
	}

	public MethodCollector(ConcurrentMap<String, Class<?>> otherKnownTypesMap)
	{
		super(otherKnownTypesMap);
	}

	@Override
	protected boolean isCollectable(String memberName)
	{
		return isCollectable0(memberName);
	}

	private boolean isCollectable0(String memberName)
	{
		return !AsmClasses.CONSTRUCTOR_NAME.equals(memberName);
	}

	/**
	 * Returns the {@link Method} class found in the given class based on the given memberName that is indeed the method
	 * name and the given parameter types if the memberName is not {@code <init>}. Otherwise it returns null.
	 * 
	 * @return the {@link Method} class found in the given class based on the given memberName that is indeed the method
	 *         name and the given parameter types if the memberName is not {@code <init>}. Otherwise it returns null.
	 * @throws NoSuchMethodException
	 *             If no method with the given memberName and the given parameter types is found.
	 */
	@Override
	protected <T> Method resolveMemberClass(Class<T> theClass, String memberName, Class<?>[] parameterTypes)
			throws NoSuchMethodException
	{
		return isCollectable0(memberName) ? theClass.getDeclaredMethod(memberName, parameterTypes) : null;
	}

}
