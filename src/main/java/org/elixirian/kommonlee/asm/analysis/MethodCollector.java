/**
 * 
 */
package org.elixirian.kommonlee.asm.analysis;

import java.lang.reflect.Method;
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
public class MethodCollector extends AbstractMemberCollector<Method>
{
  public MethodCollector()
  {
  }

  public MethodCollector(final ConcurrentMap<String, Class<?>> otherKnownTypesMap)
  {
    super(otherKnownTypesMap);
  }

  /**
   * Checks if the method with the given memberName is collectable or not. It means if the given method name is the
   * constructor name that is "&lt;init&gt;", it is a constructor so not collectable.
   * 
   * @param the
   *          given method name.
   * @return true if the given methodName is not equal to the name for constructors ("&lt;init&gt;"). false otherwise.
   */
  @Override
  protected boolean isCollectable(final String memberName)
  {
    return isCollectable0(memberName);
  }

  private boolean isCollectable0(final String memberName)
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
   *           If no method with the given memberName and the given parameter types is found.
   */
  @Override
  protected <T> Method resolveMemberClass(final Class<T> theClass, final String memberName,
      final Class<?>[] parameterTypes) throws NoSuchMethodException
  {
    return isCollectable0(memberName) ? theClass.getDeclaredMethod(memberName, parameterTypes) : null;
  }

}
