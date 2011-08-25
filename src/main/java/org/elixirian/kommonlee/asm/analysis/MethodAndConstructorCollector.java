/**
 * 
 */
package org.elixirian.kommonlee.asm.analysis;

import java.lang.reflect.Member;
import java.util.concurrent.ConcurrentMap;

import org.elixirian.kommonlee.asm.util.AsmClasses;

/**
 * <pre>
 *     ____________    ___________  ____   _______ _________ _______ _______________  ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /     \/   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /          /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
 * </pre>
 * 
 * <pre>
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 * 
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-06-22)
 */
public class MethodAndConstructorCollector extends AbstractMemberCollector<Member>
{
  public MethodAndConstructorCollector()
  {
  }

  public MethodAndConstructorCollector(final ConcurrentMap<String, Class<?>> otherKnownTypesMap)
  {
    super(otherKnownTypesMap);
  }

  /**
   * Returns true to collect both methods and constructors.
   * 
   * @param memberName
   *          never used
   * @return true (always)
   */
  @Override
  protected boolean isCollectable(@SuppressWarnings("unused") final String memberName)
  {
    return true;
  }

  @Override
  protected <T> Member resolveMemberClass(final Class<T> theClass, final String memberName,
      final Class<?>[] parameterTypes) throws NoSuchMethodException
  {
    return AsmClasses.CONSTRUCTOR_NAME.equals(memberName) ? theClass.getDeclaredConstructor(parameterTypes)
        : theClass.getDeclaredMethod(memberName, parameterTypes);
  }

}
