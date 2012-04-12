/**
 * 
 */
package org.elixirian.kommonlee.asm.analysis;

import static org.elixirian.kommonlee.asm.util.AsmClasses.*;
import static org.elixirian.kommonlee.util.MessageFormatter.*;

import java.lang.reflect.Member;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.elixirian.kommonlee.lib3rd.asm3.Type;

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
    Class<?>[] parameterTypes = new Class<?>[params.length];
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
    catch (NoSuchMethodException e)
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
