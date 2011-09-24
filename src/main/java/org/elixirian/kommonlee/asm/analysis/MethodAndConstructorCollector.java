/**
 * 
 */
package org.elixirian.kommonlee.asm.analysis;

import java.lang.reflect.Member;
import java.util.concurrent.ConcurrentMap;

import org.elixirian.kommonlee.asm.util.AsmClasses;

/**
 * <pre>
 *     ___  _____                                              _____
 *    /   \/    / ______ __________________  ______ __ ______ /    /   ______  ______  
 *   /        / _/ __  // /  /   / /  /   /_/ __  // //     //    /   /  ___ \/  ___ \ 
 *  /        \ /  /_/ _/  _  _  /  _  _  //  /_/ _/   __   //    /___/  _____/  _____/
 * /____/\____\/_____//__//_//_/__//_//_/ /_____//___/ /__//________/\_____/ \_____/
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
