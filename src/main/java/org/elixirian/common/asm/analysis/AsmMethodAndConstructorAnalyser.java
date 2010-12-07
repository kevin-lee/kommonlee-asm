/**
 * 
 */
package org.elixirian.common.asm.analysis;

import static org.elixirian.common.asm.util.AsmClasses.*;
import static org.elixirian.common.util.MessageFormatter.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.objectweb.asm.ClassReader;

import org.elixirian.common.asm.analysis.visitor.MethodAnalysisClassVisitor;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-06-22)
 */
public class AsmMethodAndConstructorAnalyser implements MethodAndConstructorAnalyser
{
	private final MethodAndConstructorCollector methodAndConstructorCollector;
	private final MethodCollector methodCollector;
	private final ConstructorCollector constructorCollector;

	public AsmMethodAndConstructorAnalyser()
	{
		this.methodAndConstructorCollector = new MethodAndConstructorCollector();
		this.methodCollector = new MethodCollector();
		this.constructorCollector = new ConstructorCollector();
	}

	public AsmMethodAndConstructorAnalyser(final ConcurrentMap<String, Class<?>> otherKnownTypesMap)
	{
		this.methodAndConstructorCollector = new MethodAndConstructorCollector(otherKnownTypesMap);
		this.methodCollector = new MethodCollector(otherKnownTypesMap);
		this.constructorCollector = new ConstructorCollector(otherKnownTypesMap);
	}

	MethodAndConstructorCollector getMethodAndConstructorCollector()
	{
		return methodAndConstructorCollector;
	}

	MethodCollector getMethodCollector()
	{
		return methodCollector;
	}

	ConstructorCollector getConstructorCollector()
	{
		return constructorCollector;
	}

	private ClassReader getClassReader(final Class<?> theClass) throws IllegalArgumentException
	{
		try
		{
			return new ClassReader(getResourceAsStream(theClass));
		}
		catch (IOException e)
		{
			throw new IllegalArgumentException(format("%s cannot be read by %s", theClass, getClass()), e);
		}
	}

	@Override
	public <T> Map<Member, String[]> findMethodsAndConstructorsWithParameterNames(final Class<T> theClass)
			throws IllegalArgumentException
	{
		final Map<Member, String[]> memberToParameterNamesMap = new HashMap<Member, String[]>();
		getClassReader(theClass).accept(
				new MethodAnalysisClassVisitor<T, Member>(methodAndConstructorCollector, theClass,
						memberToParameterNamesMap), 0);
		return memberToParameterNamesMap;
	}

	@Override
	public <T> Map<Method, String[]> findMethodsWithParameterNames(final Class<T> theClass) throws IllegalArgumentException
	{
		final Map<Method, String[]> methodToParameterNamesMap = new HashMap<Method, String[]>();
		getClassReader(theClass).accept(
				new MethodAnalysisClassVisitor<T, Method>(methodCollector, theClass, methodToParameterNamesMap), 0);
		return methodToParameterNamesMap;
	}

	@Override
	public <T> Map<Constructor<T>, String[]> findConstructorsWithParameterNames(final Class<T> theClass)
			throws IllegalArgumentException
	{
		final Map<Constructor<T>, String[]> constructorToParameterNamesMap = new HashMap<Constructor<T>, String[]>();

		@SuppressWarnings({ "cast", "unchecked", "rawtypes" })
		final Map<Constructor<?>, String[]> map =
			(Map<Constructor<?>, String[]>) ((Map) constructorToParameterNamesMap);

		getClassReader(theClass).accept(
				new MethodAnalysisClassVisitor<T, Constructor<?>>(constructorCollector, theClass, map), 0);
		return constructorToParameterNamesMap;
	}

	public static AsmMethodAndConstructorAnalyser newAsmMethodAndConstructorAnalyser(
			final ConcurrentMap<String, Class<?>> externalTypeCacheMap)
	{
		return new AsmMethodAndConstructorAnalyser(externalTypeCacheMap);
	}
}
