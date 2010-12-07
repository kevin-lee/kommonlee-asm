/**
 * 
 */
package org.elixirian.common.asm.analysis;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentMap;

import org.elixirian.common.asm.util.AsmClasses;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-06-22)
 */
public class ConstructorCollector extends AbstractMemberCollector<Constructor<? extends Object>>
{
	public ConstructorCollector()
	{
	}

	public ConstructorCollector(final ConcurrentMap<String, Class<?>> knownTypeMap)
	{
		super(knownTypeMap);
	}

	@Override
	protected boolean isCollectable(final String memberName)
	{
		return isCollectable0(memberName);
	}

	private boolean isCollectable0(final String memberName)
	{
		return AsmClasses.CONSTRUCTOR_NAME.equals(memberName);
	}

	/**
	 * Return the {@link Constructor} class of the given class. It can be found based on the given parameter types if
	 * the given memberName is {@code <init>}. Otherwise it returns null.
	 * 
	 * @return the {@link Constructor} class of the given class. It can be found based on the given parameter types if
	 *         the given memberName is {@code <init>}. Otherwise it returns null.
	 * @throws NoSuchMethodException
	 *             if the given memberName is {@code <init>} but no parameter with the given parameterTypes is found.
	 */
	@Override
	protected <T> Constructor<T> resolveMemberClass(final Class<T> theClass, final String memberName,
			final Class<?>[] parameterTypes) throws NoSuchMethodException
	{
		return isCollectable0(memberName) ? theClass.getDeclaredConstructor(parameterTypes) : null;
	}

}
