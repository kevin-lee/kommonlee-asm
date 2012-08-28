/**
 * This project is licensed under the Apache License, Version 2.0
 * if the following condition is met:
 * (otherwise it cannot be used by anyone but the author, Kevin, only)
 *
 * The original KommonLee project is owned by Lee, Seong Hyun (Kevin).
 *
 * -What does it mean to you?
 * Nothing, unless you want to take the ownership of
 * "the original project" (not yours or forked & modified one).
 * You are free to use it for both non-commercial and commercial projects
 * and free to modify it as the Apache License allows.
 *
 * -So why is this condition necessary?
 * It is only to protect the original project (See the case of Java).
 *
 *
 * Copyright 2009 Lee, Seong Hyun (Kevin)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.elixirian.kommonlee.asm.analysis;

import java.lang.reflect.Method;
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
 *     ___  _____                                _____
 *    /   \/    /_________  ___ ____ __ ______  /    /   ______  ______
 *   /        / /  ___ \  \/  //___// //     / /    /   /  ___ \/  ___ \
 *  /        \ /  _____/\    //   //   __   / /    /___/  _____/  _____/
 * /____/\____\\_____/   \__//___//___/ /__/ /________/\_____/ \_____/
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
