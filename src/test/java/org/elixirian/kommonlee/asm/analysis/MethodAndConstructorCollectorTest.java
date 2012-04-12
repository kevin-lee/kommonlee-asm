/**
 * 
 */
package org.elixirian.kommonlee.asm.analysis;

import static org.elixirian.kommonlee.asm.analysis.ConstantsForTesting.*;
import static org.hamcrest.CoreMatchers.*;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.elixirian.kommonlee.lib3rd.asm3.Type;

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
    assertThat(methodAndConstructorCollector.getExternalTypeCacheMap(), is(nullValue()));
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
    assertThat(methodAndConstructorCollector.getExternalTypeCacheMap(), equalTo(classNameToClassMapMock));
  }

  @Test
  public final void testResolveMemberClassClassOfQStringClassOfQArray() throws SecurityException, NoSuchMethodException
  {

    assertThat(methodAndConstructorCollector.resolveMemberClass(TestPojo.class, "testMethod1", PARAMS1),
        equalTo(((Member) TestPojo.class.getDeclaredMethod("testMethod1", PARAMS1))));
    assertThat(methodAndConstructorCollector.resolveMemberClass(TestPojo.class, "testMethod2", PARAMS2),
        equalTo(((Member) TestPojo.class.getDeclaredMethod("testMethod2", PARAMS2))));
    assertThat(methodAndConstructorCollector.resolveMemberClass(TestPojo.class, "testMethod3", PARAMS3),
        equalTo(((Member) TestPojo.class.getDeclaredMethod("testMethod3", PARAMS3))));

    assertThat(methodAndConstructorCollector.resolveMemberClass(TestPojo.class, AsmClasses.CONSTRUCTOR_NAME, PARAMS0),
        equalTo((Member) TestPojo.class.getDeclaredConstructor(PARAMS0)));
    assertThat(methodAndConstructorCollector.resolveMemberClass(TestPojo.class, AsmClasses.CONSTRUCTOR_NAME, PARAMS1),
        equalTo((Member) TestPojo.class.getDeclaredConstructor(PARAMS1)));

    boolean exceptionThrown = false;
    try
    {
      methodAndConstructorCollector.resolveMemberClass(TestPojo.class, AsmClasses.CONSTRUCTOR_NAME, PARAMS2);
    }
    catch (NoSuchMethodException e)
    {
      exceptionThrown = true;
    }
    assertTrue(exceptionThrown);

    exceptionThrown = false;
    try
    {
      methodAndConstructorCollector.resolveMemberClass(TestPojo.class, "somethingDoesNotExist", PARAMS0);
    }
    catch (NoSuchMethodException e)
    {
      exceptionThrown = true;
    }
    assertTrue(exceptionThrown);
  }
}
