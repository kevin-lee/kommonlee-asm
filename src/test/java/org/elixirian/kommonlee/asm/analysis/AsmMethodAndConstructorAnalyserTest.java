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

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.elixirian.kommonlee.asm.analysis.ConstantsForTesting.TestPojo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-06-22)
 */
public class AsmMethodAndConstructorAnalyserTest
{
  private AsmMethodAndConstructorAnalyser asmMethodAndConstructorAnalyser;

  /**
   * @throws java.lang.Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception
  {
    asmMethodAndConstructorAnalyser = new AsmMethodAndConstructorAnalyser();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public final void testAsmMethodAndConstructorAnalyser()
  {
    final AsmMethodAndConstructorAnalyser asmMethodAndConstructorAnalyser = new AsmMethodAndConstructorAnalyser();
    assertThat(asmMethodAndConstructorAnalyser.getMethodAndConstructorCollector()).isInstanceOf(
        MethodAndConstructorCollector.class);
    assertThat(asmMethodAndConstructorAnalyser.getMethodAndConstructorCollector()
        .getExternalTypeCacheMap()).isNull();
    assertThat(asmMethodAndConstructorAnalyser.getMethodCollector()).isInstanceOf(MethodCollector.class);
    assertThat(asmMethodAndConstructorAnalyser.getMethodCollector()
        .getExternalTypeCacheMap()).isNull();
    assertThat(asmMethodAndConstructorAnalyser.getConstructorCollector()).isInstanceOf(ConstructorCollector.class);
    assertThat(asmMethodAndConstructorAnalyser.getConstructorCollector()
        .getExternalTypeCacheMap()).isNull();
  }

  @Test
  public final void testAsmMethodAndConstructorAnalyserMapOfStringClassOfQ()
  {
    @SuppressWarnings("unchecked")
    /* given */
    final ConcurrentMap<String, Class<?>> otherKnownTypesMap = mock(ConcurrentMap.class);

    /* when */
    final AsmMethodAndConstructorAnalyser asmMethodAndConstructorAnalyser =
      new AsmMethodAndConstructorAnalyser(otherKnownTypesMap);

    /* then */
    assertThat(asmMethodAndConstructorAnalyser.getMethodAndConstructorCollector()).isInstanceOf(
        MethodAndConstructorCollector.class);

    assertThat(asmMethodAndConstructorAnalyser.getMethodAndConstructorCollector()
        .getExternalTypeCacheMap()).isSameAs(otherKnownTypesMap);
    assertThat(asmMethodAndConstructorAnalyser.getMethodAndConstructorCollector()
        .getExternalTypeCacheMap()).isEqualTo(otherKnownTypesMap);
    assertThat(asmMethodAndConstructorAnalyser.getMethodAndConstructorCollector()
        .getExternalTypeCacheMap()).hasSize(otherKnownTypesMap.size());

    assertThat(asmMethodAndConstructorAnalyser.getMethodCollector()).isInstanceOf(MethodCollector.class);
    assertThat(asmMethodAndConstructorAnalyser.getMethodCollector()
        .getExternalTypeCacheMap()).isSameAs(otherKnownTypesMap);
    assertThat(asmMethodAndConstructorAnalyser.getMethodCollector()
        .getExternalTypeCacheMap()).isEqualTo(otherKnownTypesMap);
    assertThat(asmMethodAndConstructorAnalyser.getMethodCollector()
        .getExternalTypeCacheMap()).hasSize(otherKnownTypesMap.size());

    assertThat(asmMethodAndConstructorAnalyser.getConstructorCollector()).isInstanceOf(ConstructorCollector.class);
    assertThat(asmMethodAndConstructorAnalyser.getConstructorCollector()
        .getExternalTypeCacheMap()).isSameAs(otherKnownTypesMap);
    assertThat(asmMethodAndConstructorAnalyser.getConstructorCollector()
        .getExternalTypeCacheMap()).isEqualTo(otherKnownTypesMap);
    assertThat(asmMethodAndConstructorAnalyser.getConstructorCollector()
        .getExternalTypeCacheMap()).hasSize(otherKnownTypesMap.size());
  }

  @Test
  public final void testFindMethodsAndConstructorsWithParameterNames()
  {
    final Constructor<?>[] constructors = TestPojo.class.getDeclaredConstructors();
    final Method[] methods = TestPojo.class.getDeclaredMethods();

    final Map<Member, String[]> methodsAndConstructorsMap =
      asmMethodAndConstructorAnalyser.findMethodsAndConstructorsWithParameterNames(TestPojo.class);

    assertEquals(constructors.length + methods.length, methodsAndConstructorsMap.size());

    for (final Constructor<?> constructor : constructors)
    {
      assertTrue(methodsAndConstructorsMap.containsKey(constructor));
    }

    for (final Method method : methods)
    {
      assertTrue(methodsAndConstructorsMap.containsKey(method));
    }
  }

  @Test
  public final void testFindMethodsWithParameterNames()
  {
    final Method[] methods = TestPojo.class.getDeclaredMethods();
    final Map<Method, String[]> methodsAndConstructorsMap =
      asmMethodAndConstructorAnalyser.findMethodsWithParameterNames(TestPojo.class);

    assertEquals(methods.length, methodsAndConstructorsMap.size());

    for (final Method method : methods)
    {
      assertTrue(methodsAndConstructorsMap.containsKey(method));
    }
  }

  @Test
  public final void testFindConstructorsWithParameterNames()
  {
    final Map<Constructor<TestPojo>, String[]> methodsAndConstructorsMap =
      asmMethodAndConstructorAnalyser.findConstructorsWithParameterNames(TestPojo.class);

    final Constructor<?>[] constructors = TestPojo.class.getDeclaredConstructors();

    assertEquals(constructors.length, methodsAndConstructorsMap.size());

    for (final Constructor<?> constructor : constructors)
    {
      assertTrue(methodsAndConstructorsMap.containsKey(constructor));
    }
  }
}
