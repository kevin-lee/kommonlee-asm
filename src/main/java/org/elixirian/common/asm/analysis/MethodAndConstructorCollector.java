/**
 * 
 */
package org.elixirian.common.asm.analysis;

import java.lang.reflect.Member;
import java.util.concurrent.ConcurrentMap;

import org.elixirian.common.asm.util.AsmClasses;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-06-22)
 */
public class MethodAndConstructorCollector extends AbstractMemberCollector<Member>
{
	public MethodAndConstructorCollector()
	{
	}

	public MethodAndConstructorCollector(ConcurrentMap<String, Class<?>> otherKnownTypesMap)
	{
		super(otherKnownTypesMap);
	}

	@Override
	protected boolean isCollectable(@SuppressWarnings("unused") String memberName)
	{
		return true;
	}

	@Override
	protected <T> Member resolveMemberClass(Class<T> theClass, String memberName, Class<?>[] parameterTypes)
			throws NoSuchMethodException
	{
		return AsmClasses.CONSTRUCTOR_NAME.equals(memberName) ? theClass.getDeclaredConstructor(parameterTypes)
				: theClass.getDeclaredMethod(memberName, parameterTypes);
	}

}
