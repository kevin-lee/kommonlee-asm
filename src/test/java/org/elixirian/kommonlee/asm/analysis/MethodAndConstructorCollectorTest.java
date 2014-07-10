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
import static org.elixirian.kommonlee.asm.analysis.ConstantsForTesting.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.elixirian.kommonlee.asm.analysis.ConstantsForTesting.TestPojo;
import org.elixirian.kommonlee.asm.util.AsmClasses;
import org.elixirian.kommonlee.lib3rd.asm5.Type;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-06-22)
 */
public class MethodAndConstructorCollectorTest
{
  private MethodAndConstructorCollector methodAndConstructorCollector;

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
    methodAndConstructorCollector = new MethodAndConstructorCollector();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception
  {
  }

  @Test
  public final void testIsCollectable()
  {
    assertTrue(methodAndConstructorCollector.isCollectable("testMethod1"));
    assertTrue(methodAndConstructorCollector.isCollectable("anyName"));
    assertTrue(methodAndConstructorCollector.isCollectable(AsmClasses.CONSTRUCTOR_NAME));
  }

  @Test
  public final void testMethodAndConstructorCollector()
  {
    final MethodAndConstructorCollector methodAndConstructorCollector = new MethodAndConstructorCollector();
    assertThat(methodAndConstructorCollector.getExternalTypeCacheMap()).isNull();
  }

  @SuppressWarnings("unchecked")
  @Test
  public final void testMethodAndConstructorCollectorMapOfStringClassOfQ() throws SecurityException,
      NoSuchMethodException
  {
    final Map<Member, String[]> memberToParameterNamesMap = new HashMap<Member, String[]>();

    final ConcurrentMap<String, Class<?>> classNameToClassMapMock = mock(ConcurrentHashMap.class);

    final MethodAndConstructorCollector methodAndConstructorCollector =
      new MethodAndConstructorCollector(classNameToClassMapMock);
    methodAndConstructorCollector.collect(TestPojo.class, "testMethod4", memberToParameterNamesMap,
        Type.getArgumentTypes(TestPojo.class.getDeclaredMethod("testMethod4", PARAMS4)), new String[] { "another" });
    verify(classNameToClassMapMock).get(any());

    reset(classNameToClassMapMock);
    methodAndConstructorCollector.collect(TestPojo.class, AsmClasses.CONSTRUCTOR_NAME, memberToParameterNamesMap,
        Type.getArgumentTypes(Type.getConstructorDescriptor(TestPojo.class.getDeclaredConstructor(PARAMS4))),
        new String[] { "another" });
    verify(classNameToClassMapMock).get(any());
  }

  @Test
  public void testGetOtherKnownTypesMap()
  {
    @SuppressWarnings("unchecked")
    final ConcurrentMap<String, Class<?>> classNameToClassMapMock = mock(ConcurrentHashMap.class);

    final MethodAndConstructorCollector methodAndConstructorCollector =
      new MethodAndConstructorCollector(classNameToClassMapMock);
    assertThat(methodAndConstructorCollector.getExternalTypeCacheMap()).isEqualTo(classNameToClassMapMock);
  }

  @Test
  public final void testResolveMemberClassClassOfQStringClassOfQArray() throws SecurityException, NoSuchMethodException
  {

    assertThat(methodAndConstructorCollector.resolveMemberClass(TestPojo.class, "testMethod1", PARAMS1)).isEqualTo(
        (TestPojo.class.getDeclaredMethod("testMethod1", PARAMS1)));
    assertThat(methodAndConstructorCollector.resolveMemberClass(TestPojo.class, "testMethod2", PARAMS2)).isEqualTo(
        (TestPojo.class.getDeclaredMethod("testMethod2", PARAMS2)));
    assertThat(methodAndConstructorCollector.resolveMemberClass(TestPojo.class, "testMethod3", PARAMS3)).isEqualTo(
        (TestPojo.class.getDeclaredMethod("testMethod3", PARAMS3)));

    assertThat(methodAndConstructorCollector.resolveMemberClass(TestPojo.class, AsmClasses.CONSTRUCTOR_NAME, PARAMS0)).isEqualTo(
        TestPojo.class.getDeclaredConstructor(PARAMS0));
    assertThat(methodAndConstructorCollector.resolveMemberClass(TestPojo.class, AsmClasses.CONSTRUCTOR_NAME, PARAMS1)).isEqualTo(
        TestPojo.class.getDeclaredConstructor(PARAMS1));

    boolean exceptionThrown = false;
    try
    {
      methodAndConstructorCollector.resolveMemberClass(TestPojo.class, AsmClasses.CONSTRUCTOR_NAME, PARAMS2);
    }
    catch (final NoSuchMethodException e)
    {
      exceptionThrown = true;
    }
    assertTrue(exceptionThrown);

    exceptionThrown = false;
    try
    {
      methodAndConstructorCollector.resolveMemberClass(TestPojo.class, "somethingDoesNotExist", PARAMS0);
    }
    catch (final NoSuchMethodException e)
    {
      exceptionThrown = true;
    }
    assertTrue(exceptionThrown);
  }
}
