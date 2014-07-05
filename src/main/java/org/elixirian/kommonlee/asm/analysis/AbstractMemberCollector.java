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

import static org.elixirian.kommonlee.asm.util.AsmClasses.*;
import static org.elixirian.kommonlee.util.MessageFormatter.*;

import java.lang.reflect.Member;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.elixirian.kommonlee.lib3rd.asm5.Type;

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
public abstract class AbstractMemberCollector<M extends Member> implements MemberCollector<M>
{
  private interface AsmClassesUserToGetClassByType
  {
    Class<?> getClassByTypeWithAsmClasses(Type memberType, Class<?> theClass);

    ConcurrentMap<String, Class<?>> getExternalTypeCacheMap();
  }

  private static final class ClassByTypeFinderWithCache implements AsmClassesUserToGetClassByType
  {
    private final ConcurrentMap<String, Class<?>> externalTypeCacheMap;

    public ClassByTypeFinderWithCache(final ConcurrentMap<String, Class<?>> otherKnownTypesMap)
    {
      this.externalTypeCacheMap = otherKnownTypesMap;
    }

    @Override
    public Class<?> getClassByTypeWithAsmClasses(final Type memberType, final Class<?> theClass)
    {
      return getClassByType(memberType, theClass, externalTypeCacheMap);
    }

    @Override
    public ConcurrentMap<String, Class<?>> getExternalTypeCacheMap()
    {
      return externalTypeCacheMap;
    }
  }

  private static final class ClassByTypeFinderWithoutCache implements AsmClassesUserToGetClassByType
  {
    @Override
    public Class<?> getClassByTypeWithAsmClasses(final Type memberType, final Class<?> theClass)
    {
      return getClassByType(memberType, theClass);
    }

    @Override
    public ConcurrentMap<String, Class<?>> getExternalTypeCacheMap()
    {
      return null;
    }
  }

  private final AsmClassesUserToGetClassByType asmClassesUserToGetClassByType;

  public AbstractMemberCollector()
  {
    asmClassesUserToGetClassByType = new ClassByTypeFinderWithoutCache();
  }

  public AbstractMemberCollector(final ConcurrentMap<String, Class<?>> externalTypeCacheMap)
  {
    asmClassesUserToGetClassByType = new ClassByTypeFinderWithCache(externalTypeCacheMap);
  }

  ConcurrentMap<String, Class<?>> getExternalTypeCacheMap()
  {
    return asmClassesUserToGetClassByType.getExternalTypeCacheMap();
  }

  @Override
  public void collect(final Class<?> theClass, final String memberName,
      final Map<M, String[]> memberToParameterNamesMap, final Type[] params, final String[] paramNames)
  {
    final Class<?>[] parameterTypes = new Class<?>[params.length];
    for (int i = 0, size = params.length; i < size; i++)
    {
      parameterTypes[i] = asmClassesUserToGetClassByType.getClassByTypeWithAsmClasses(params[i], theClass);
    }

    try
    {
      if (isCollectable(memberName))
      {
        memberToParameterNamesMap.put(resolveMemberClass(theClass, memberName, parameterTypes), paramNames);
      }
    }
    catch (final NoSuchMethodException e)
    {
      final StringBuilder stringBuilder = new StringBuilder();
      for (int i = 0, size = params.length; i < size; i++)
      {
        stringBuilder.append("Type: ")
            .append(parameterTypes[i])
            .append(" | Name: ")
            .append(paramNames[i])
            .append("\n");
      }
      stringBuilder.append("]");
      throw new IllegalStateException(
          format(
              "method [name: %s] is found in the class bytecode yet cannot be resolved in the class [%s]. \n[methodInfo: \n%s",
              memberName, theClass, stringBuilder));
    }
  }

  protected abstract boolean isCollectable(String memberName);

  /**
   * @param <T>
   *          the type of the given {@link Class}
   * @param theClass
   *          the given {@link Class}
   * @param memberName
   *          the name of member which should be either the construct name that is {@code <init>} or the method name.
   * @param parameterTypes
   *          the {@link Class} array containing the given parameter types of the given member.
   * @return
   * @throws NoSuchMethodException
   */
  protected abstract <T> M resolveMemberClass(Class<T> theClass, String memberName, Class<?>[] parameterTypes)
      throws NoSuchMethodException;

}
