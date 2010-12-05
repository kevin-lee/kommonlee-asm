/**
 * 
 */
package org.elixirian.common.asm.analysis;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.elixirian.common.asm.analysis.ConstantsForTesting.TestPojo;
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
		assertThat(asmMethodAndConstructorAnalyser.getMethodAndConstructorCollector(),
				is(instanceOf(MethodAndConstructorCollector.class)));
		assertThat(asmMethodAndConstructorAnalyser.getMethodAndConstructorCollector()
				.getExternalTypeCacheMap(), is(nullValue()));
		assertThat(asmMethodAndConstructorAnalyser.getMethodCollector(), is(instanceOf(MethodCollector.class)));
		assertThat(asmMethodAndConstructorAnalyser.getMethodCollector()
				.getExternalTypeCacheMap(), is(nullValue()));
		assertThat(asmMethodAndConstructorAnalyser.getConstructorCollector(),
				is(instanceOf(ConstructorCollector.class)));
		assertThat(asmMethodAndConstructorAnalyser.getConstructorCollector()
				.getExternalTypeCacheMap(), is(nullValue()));
	}

	@Test
	public final void testAsmMethodAndConstructorAnalyserMapOfStringClassOfQ()
	{
		@SuppressWarnings("unchecked")
		final ConcurrentMap<String, Class<?>> otherKnownTypesMap = mock(ConcurrentMap.class);

		final AsmMethodAndConstructorAnalyser asmMethodAndConstructorAnalyser =
			new AsmMethodAndConstructorAnalyser(otherKnownTypesMap);
		assertThat(asmMethodAndConstructorAnalyser.getMethodAndConstructorCollector(),
				is(instanceOf(MethodAndConstructorCollector.class)));
		assertThat(asmMethodAndConstructorAnalyser.getMethodAndConstructorCollector()
				.getExternalTypeCacheMap(), is(otherKnownTypesMap));
		assertThat(asmMethodAndConstructorAnalyser.getMethodCollector(), is(instanceOf(MethodCollector.class)));
		assertThat(asmMethodAndConstructorAnalyser.getMethodCollector()
				.getExternalTypeCacheMap(), is(otherKnownTypesMap));
		assertThat(asmMethodAndConstructorAnalyser.getConstructorCollector(),
				is(instanceOf(ConstructorCollector.class)));
		assertThat(asmMethodAndConstructorAnalyser.getConstructorCollector()
				.getExternalTypeCacheMap(), is(otherKnownTypesMap));
	}

	@Test
	public final void testFindMethodsAndConstructorsWithParameterNames()
	{
		final Constructor<?>[] constructors = TestPojo.class.getDeclaredConstructors();
		final Method[] methods = TestPojo.class.getDeclaredMethods();

		final Map<Member, String[]> methodsAndConstructorsMap =
			asmMethodAndConstructorAnalyser.findMethodsAndConstructorsWithParameterNames(TestPojo.class);

		assertEquals(constructors.length + methods.length, methodsAndConstructorsMap.size());

		for (Constructor<?> constructor : constructors)
		{
			assertTrue(methodsAndConstructorsMap.containsKey(constructor));
		}

		for (Method method : methods)
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

		for (Method method : methods)
		{
			assertTrue(methodsAndConstructorsMap.containsKey(method));
		}
	}

	@Test
	public final void testFindConstructorsWithParameterNames()
	{
		Map<Constructor<TestPojo>, String[]> methodsAndConstructorsMap =
			asmMethodAndConstructorAnalyser.findConstructorsWithParameterNames(TestPojo.class);

		final Constructor<?>[] constructors = TestPojo.class.getDeclaredConstructors();

		assertEquals(constructors.length, methodsAndConstructorsMap.size());

		for (Constructor<?> constructor : constructors)
		{
			assertTrue(methodsAndConstructorsMap.containsKey(constructor));
		}
	}
}
