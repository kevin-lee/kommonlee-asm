/**
 * 
 */
package org.elixirian.common.asm.analysis;

import static org.elixirian.common.asm.analysis.ConstantsForTesting.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.elixirian.common.asm.analysis.ConstantsForTesting.TestPojo;
import org.elixirian.common.asm.util.AsmClasses;
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
public class MethodCollectorTest
{
	private MethodCollector methodCollector;

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
		methodCollector = new MethodCollector();
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
		assertTrue(methodCollector.isCollectable("someMethod"));
		assertFalse(methodCollector.isCollectable(AsmClasses.CONSTRUCTOR_NAME));
	}

	@Test
	public final void testMethodCollector()
	{
		final MethodCollector methodCollector = new MethodCollector();
		assertThat(methodCollector.getExternalTypeCacheMap(), is(nullValue()));
	}

	@Test
	public final void testMethodCollectorMapOfStringClassOfQ() throws NoSuchMethodException
	{
		final Map<Method, String[]> memberToParameterNamesMap = new HashMap<Method, String[]>();

		@SuppressWarnings("unchecked")
		final ConcurrentMap<String, Class<?>> classNameToClassMapMock = mock(ConcurrentHashMap.class);

		final MethodCollector methodCollector = new MethodCollector(classNameToClassMapMock);
		methodCollector.collect(TestPojo.class, "testMethod4", memberToParameterNamesMap,
				Type.getArgumentTypes(TestPojo.class.getDeclaredMethod("testMethod4", PARAMS4)),
				new String[] { "another" });
		verify(classNameToClassMapMock).get(any());
	}

	@Test
	public void testGetOtherKnownTypesMap()
	{
		@SuppressWarnings("unchecked")
		final ConcurrentMap<String, Class<?>> classNameToClassMapMock = mock(ConcurrentHashMap.class);

		final MethodCollector methodCollector = new MethodCollector(classNameToClassMapMock);
		assertThat(methodCollector.getExternalTypeCacheMap(), equalTo(classNameToClassMapMock));
	}

	@Test
	public final void testResolveMemberClassClassOfQStringClassOfQArray() throws NoSuchMethodException
	{
		assertThat(methodCollector.resolveMemberClass(TestPojo.class, "testMethod1", PARAMS1),
				equalTo(TestPojo.class.getDeclaredMethod("testMethod1", PARAMS1)));
		assertThat(methodCollector.resolveMemberClass(TestPojo.class, "testMethod2", PARAMS2),
				equalTo(TestPojo.class.getDeclaredMethod("testMethod2", PARAMS2)));
		assertThat(methodCollector.resolveMemberClass(TestPojo.class, "testMethod3", PARAMS3),
				equalTo(TestPojo.class.getDeclaredMethod("testMethod3", PARAMS3)));
		assertThat(methodCollector.resolveMemberClass(TestPojo.class, AsmClasses.CONSTRUCTOR_NAME, PARAMS0),
				is(nullValue()));

		boolean exceptionThrown = false;
		try
		{
			methodCollector.resolveMemberClass(TestPojo.class, "somethingDoesNotExist", PARAMS1);
		}
		catch (NoSuchMethodException e)
		{
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);

		exceptionThrown = false;
		try
		{
			/* no matching param types. */
			methodCollector.resolveMemberClass(TestPojo.class, "testMethod1", new Class[] { double.class });
		}
		catch (NoSuchMethodException e)
		{
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

}
