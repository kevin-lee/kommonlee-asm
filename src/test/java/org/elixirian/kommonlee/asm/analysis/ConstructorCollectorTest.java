/**
 * 
 */
package org.elixirian.kommonlee.asm.analysis;

import static org.elixirian.kommonlee.asm.analysis.ConstantsForTesting.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Constructor;
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
import org.objectweb.asm.Type;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-06-22)
 */
public class ConstructorCollectorTest
{
  private ConstructorCollector constructorCollector;

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
    constructorCollector = new ConstructorCollector();
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
    assertTrue(constructorCollector.isCollectable(AsmClasses.CONSTRUCTOR_NAME));
    assertFalse(constructorCollector.isCollectable("someMethod"));
  }

  @Test
  public final void testConstructorCollector()
  {
    final ConstructorCollector constructorCollector = new ConstructorCollector();
    assertThat(constructorCollector.getExternalTypeCacheMap(), is(nullValue()));
  }

  @Test
  public final void testConstructorCollectorMapOfStringClassOfQ() throws SecurityException, NoSuchMethodException
  {
    final Map<Constructor<?>, String[]> memberToParameterNamesMap = new HashMap<Constructor<?>, String[]>();

    @SuppressWarnings("unchecked")
    final ConcurrentMap<String, Class<?>> classNameToClassMapMock = mock(ConcurrentHashMap.class);

    final ConstructorCollector constructorCollector = new ConstructorCollector(classNameToClassMapMock);
    constructorCollector.collect(TestPojo.class, AsmClasses.CONSTRUCTOR_NAME, memberToParameterNamesMap,
        Type.getArgumentTypes(Type.getConstructorDescriptor(TestPojo.class.getDeclaredConstructor(PARAMS4))),
        new String[] { "another" });
    verify(classNameToClassMapMock).get(any());
  }

  @Test
  public void testGetOtherKnownTypesMap()
  {
    @SuppressWarnings("unchecked")
    final ConcurrentMap<String, Class<?>> classNameToClassMapMock = mock(ConcurrentHashMap.class);

    final ConstructorCollector constructorCollector = new ConstructorCollector(classNameToClassMapMock);
    assertThat(constructorCollector.getExternalTypeCacheMap(), equalTo(classNameToClassMapMock));
  }

  @Test
  public final void testResolveMemberClassClassOfQStringClassOfQArray() throws SecurityException, NoSuchMethodException
  {
    assertThat(constructorCollector.resolveMemberClass(TestPojo.class, AsmClasses.CONSTRUCTOR_NAME, PARAMS0),
        equalTo(TestPojo.class.getConstructor(PARAMS0)));
    assertThat(constructorCollector.resolveMemberClass(TestPojo.class, AsmClasses.CONSTRUCTOR_NAME, PARAMS1),
        equalTo(TestPojo.class.getConstructor(PARAMS1)));

    assertThat(constructorCollector.resolveMemberClass(TestPojo.class, "testMethod1", PARAMS1), is(nullValue()));
    assertThat(constructorCollector.resolveMemberClass(TestPojo.class, "testMethod2", PARAMS2), is(nullValue()));
    assertThat(constructorCollector.resolveMemberClass(TestPojo.class, "testMethod3", PARAMS3), is(nullValue()));

    boolean exceptionThrown = false;
    try
    {
      constructorCollector.resolveMemberClass(TestPojo.class, AsmClasses.CONSTRUCTOR_NAME, PARAMS2);
    }
    catch (NoSuchMethodException e)
    {
      exceptionThrown = true;
    }
    assertTrue(exceptionThrown);

  }

}
