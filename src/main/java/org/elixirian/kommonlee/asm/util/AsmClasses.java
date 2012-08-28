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
package org.elixirian.kommonlee.asm.util;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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
public final class AsmClasses
{
  public static final String CONSTRUCTOR_NAME = "<init>";

  public static final String ASM_TYPE_ARRAY_AFFIX = "[]";

  private final static Map<String, Class<?>> PRIMITIVE_TYPES;
  private final static Map<String, Class<?>> KNOWN_ARRAY_TYPES;
  private final static Map<String, Class<?>> COMMON_REFERENCE_TYPES;

  static
  {
    Map<String, Class<?>> tempMap = new HashMap<String, Class<?>>(8);
    tempMap.put(int.class.getName(), int.class);
    tempMap.put(byte.class.getName(), byte.class);
    tempMap.put(short.class.getName(), short.class);
    tempMap.put(char.class.getName(), char.class);
    tempMap.put(long.class.getName(), long.class);
    tempMap.put(float.class.getName(), float.class);
    tempMap.put(double.class.getName(), double.class);
    tempMap.put(boolean.class.getName(), boolean.class);
    PRIMITIVE_TYPES = Collections.unmodifiableMap(tempMap);

    tempMap = new HashMap<String, Class<?>>();
    tempMap.put(int.class.getName() + ASM_TYPE_ARRAY_AFFIX, int[].class);
    tempMap.put(byte.class.getName() + ASM_TYPE_ARRAY_AFFIX, byte[].class);
    tempMap.put(short.class.getName() + ASM_TYPE_ARRAY_AFFIX, short[].class);
    tempMap.put(char.class.getName() + ASM_TYPE_ARRAY_AFFIX, char[].class);
    tempMap.put(long.class.getName() + ASM_TYPE_ARRAY_AFFIX, long[].class);
    tempMap.put(float.class.getName() + ASM_TYPE_ARRAY_AFFIX, float[].class);
    tempMap.put(double.class.getName() + ASM_TYPE_ARRAY_AFFIX, double[].class);
    tempMap.put(boolean.class.getName() + ASM_TYPE_ARRAY_AFFIX, boolean[].class);

    tempMap.put(Integer.class.getName() + ASM_TYPE_ARRAY_AFFIX, Integer[].class);
    tempMap.put(Byte.class.getName() + ASM_TYPE_ARRAY_AFFIX, Byte[].class);
    tempMap.put(Short.class.getName() + ASM_TYPE_ARRAY_AFFIX, Short[].class);
    tempMap.put(Character.class.getName() + ASM_TYPE_ARRAY_AFFIX, Character[].class);
    tempMap.put(Long.class.getName() + ASM_TYPE_ARRAY_AFFIX, Long[].class);
    tempMap.put(Float.class.getName() + ASM_TYPE_ARRAY_AFFIX, Float[].class);
    tempMap.put(Double.class.getName() + ASM_TYPE_ARRAY_AFFIX, Double[].class);
    tempMap.put(Boolean.class.getName() + ASM_TYPE_ARRAY_AFFIX, Boolean[].class);
    tempMap.put(Number.class.getName() + ASM_TYPE_ARRAY_AFFIX, Number[].class);
    tempMap.put(Object.class.getName() + ASM_TYPE_ARRAY_AFFIX, Object[].class);
    tempMap.put(Class.class.getName() + ASM_TYPE_ARRAY_AFFIX, Class[].class);

    tempMap.put(String[].class.getName(), String[].class);
    KNOWN_ARRAY_TYPES = Collections.unmodifiableMap(tempMap);

    tempMap = new HashMap<String, Class<?>>();
    tempMap.put(Integer.class.getName(), Integer.class);
    tempMap.put(Byte.class.getName(), Byte.class);
    tempMap.put(Short.class.getName(), Short.class);
    tempMap.put(Character.class.getName(), Character.class);
    tempMap.put(Long.class.getName(), Long.class);
    tempMap.put(Float.class.getName(), Float.class);
    tempMap.put(Double.class.getName(), Double.class);
    tempMap.put(Boolean.class.getName(), Boolean.class);
    tempMap.put(String.class.getName(), String.class);
    tempMap.put(BigInteger.class.getName(), BigInteger.class);
    tempMap.put(BigDecimal.class.getName(), BigDecimal.class);
    tempMap.put(Date.class.getName(), Date.class);
    tempMap.put(Calendar.class.getName(), Calendar.class);
    tempMap.put(Object.class.getName(), Object.class);
    tempMap.put(Class.class.getName(), Class.class);
    COMMON_REFERENCE_TYPES = Collections.unmodifiableMap(tempMap);
  }

  private AsmClasses()
  {
  }

  public static Class<?> getClassByType(final Type memberType, final Class<?> theClass)
  {
    return getClassByName(memberType.getClassName(), theClass.getClassLoader());
  }

  public static Class<?> getClassByType(final Type memberType, final Class<?> theClass,
      final ConcurrentMap<String, Class<?>> otherKnownTypesMap)
  {
    return getClassByName(memberType.getClassName(), theClass.getClassLoader(), otherKnownTypesMap);
  }

  private static Class<?> findKnownType(final String className)
  {
    Class<?> memberClass = PRIMITIVE_TYPES.get(className);
    if (null != memberClass)
      return memberClass;

    memberClass = COMMON_REFERENCE_TYPES.get(className);
    if (null != memberClass)
      return memberClass;

    return KNOWN_ARRAY_TYPES.get(className);
  }

  private static Class<?> findKnownType(final String className, final Map<String, Class<?>> externalTypeCacheMap)
  {
    final Class<?> memberClass = findKnownType(className);
    if (null == memberClass)
    {
      return externalTypeCacheMap.get(className);
    }
    return memberClass;
  }

  public static Class<?> getClassByName(final String className, final ClassLoader classLoader)
  {
    Class<?> memberClass = findKnownType(className);
    if (null != memberClass)
    {
      return memberClass;
    }

    if (className.endsWith(ASM_TYPE_ARRAY_AFFIX))
    {
      memberClass =
        getClassByName(className.substring(0, className.length() - ASM_TYPE_ARRAY_AFFIX.length()), classLoader);
      return Array.newInstance(memberClass, 0)
          .getClass();
    }
    try
    {
      ClassLoader classLoaderToUse = (null == classLoader ? getCurrentThreadContextClassLoader() : classLoader);
      if (null == classLoaderToUse)
      {
        classLoaderToUse = AsmClasses.class.getClassLoader();
      }
      return classLoaderToUse.loadClass(className);
    }
    catch (final ClassNotFoundException e)
    {
      e.printStackTrace();
      throw new UnsupportedOperationException(e);
    }

  }

  public static Class<?> getClassByName(final String className, final ClassLoader classLoader,
      final ConcurrentMap<String, Class<?>> externalTypeCacheMap)
  {
    Class<?> memberClass = findKnownType(className, externalTypeCacheMap);
    if (null != memberClass)
    {
      return memberClass;
    }

    if (className.endsWith(ASM_TYPE_ARRAY_AFFIX))
    {
      memberClass =
        getClassByName(className.substring(0, className.length() - ASM_TYPE_ARRAY_AFFIX.length()), classLoader,
            externalTypeCacheMap);
      return Array.newInstance(memberClass, 0)
          .getClass();
    }
    try
    {
      ClassLoader classLoaderToUse = (null == classLoader ? getCurrentThreadContextClassLoader() : classLoader);
      if (null == classLoader)
      {
        classLoaderToUse = AsmClasses.class.getClassLoader();
      }
      final Class<?> theType = classLoaderToUse.loadClass(className);
      final Class<?> theTypeFromCache = externalTypeCacheMap.putIfAbsent(className, theType);
      return (null == theTypeFromCache ? theType : theTypeFromCache);
    }
    catch (final ClassNotFoundException e)
    {
      e.printStackTrace();
      throw new UnsupportedOperationException(e);
    }

  }

  public static ClassLoader getCurrentThreadContextClassLoader()
  {
    return Thread.currentThread()
        .getContextClassLoader();
  }

  public static InputStream getResourceAsStream(final Class<?> theClass)
  {
    return theClass.getResourceAsStream("/" + theClass.getName()
        .replace('.', '/') + ".class");
  }

  /**
   * Each slot in the local variables and operand stack parts can hold any Java value, except long and double values.
   * These values require two slots.
   *
   * @param type
   * @return
   */
  public static int calculateLocalVariableSlotSize(final Type type)
  {
    return (Type.LONG_TYPE == type || Type.DOUBLE_TYPE == type ? 2 : 1);
  }

  public static int[] calculateLocalVariableSlotIndices(final boolean isStaticMethod, final Type[] params)
  {
    final int[] localVariableSlotIndices = new int[params.length];
    int next = isStaticMethod ? 0 : 1;
    for (int i = 0, size = params.length; i < size; i++)
    {
      localVariableSlotIndices[i] = next;
      next += calculateLocalVariableSlotSize(params[i]);
    }
    return localVariableSlotIndices;
  }
}
