/**
 * 
 */
package org.elixirian.kommonlee.asm.analysis;

import java.lang.reflect.Constructor;
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
public class ConstructorCollector extends AbstractMemberCollector<Constructor<? extends Object>>
{
  public ConstructorCollector()
  {
  }

  public ConstructorCollector(final ConcurrentMap<String, Class<?>> knownTypeMap)
  {
    super(knownTypeMap);
  }

  /**
   * Checks if the method with the given memberName is collectable or not. It means if the given method name is the
   * constructor name that is "&lt;init&gt;", it is a constructor so collectable.
   * 
   * @param the
   *          given method name.
   * @return true if the given methodName is equal to the name for constructors ("&lt;init&gt;"). false otherwise.
   */
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
   * Return the {@link Constructor} class of the given class. It can be found based on the given parameter types if the
   * given memberName is {@code <init>}. Otherwise it returns null.
   * 
   * @return the {@link Constructor} class of the given class. It can be found based on the given parameter types if the
   *         given memberName is {@code <init>}. Otherwise it returns null.
   * @throws NoSuchMethodException
   *           if the given memberName is {@code <init>} but no parameter with the given parameterTypes is found.
   */
  @Override
  protected <T> Constructor<T> resolveMemberClass(final Class<T> theClass, final String memberName,
      final Class<?>[] parameterTypes) throws NoSuchMethodException
  {
    return isCollectable0(memberName) ? theClass.getDeclaredConstructor(parameterTypes) : null;
  }

}
