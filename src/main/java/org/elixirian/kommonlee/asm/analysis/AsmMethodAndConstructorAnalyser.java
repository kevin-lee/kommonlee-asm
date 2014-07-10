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

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.elixirian.kommonlee.asm.analysis.visitor.MethodAnalysisClassVisitor;
import org.elixirian.kommonlee.lib3rd.asm5.ClassReader;
import org.elixirian.kommonlee.lib3rd.asm5.Opcodes;

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
public class AsmMethodAndConstructorAnalyser implements MethodAndConstructorAnalyser {
  private final MethodAndConstructorCollector methodAndConstructorCollector;
  private final MethodCollector methodCollector;
  private final ConstructorCollector constructorCollector;

  public AsmMethodAndConstructorAnalyser() {
    this.methodAndConstructorCollector = new MethodAndConstructorCollector();
    this.methodCollector = new MethodCollector();
    this.constructorCollector = new ConstructorCollector();
  }

  public AsmMethodAndConstructorAnalyser(final ConcurrentMap<String, Class<?>> otherKnownTypesMap) {
    this.methodAndConstructorCollector = new MethodAndConstructorCollector(otherKnownTypesMap);
    this.methodCollector = new MethodCollector(otherKnownTypesMap);
    this.constructorCollector = new ConstructorCollector(otherKnownTypesMap);
  }

  MethodAndConstructorCollector getMethodAndConstructorCollector() {
    return methodAndConstructorCollector;
  }

  MethodCollector getMethodCollector() {
    return methodCollector;
  }

  ConstructorCollector getConstructorCollector() {
    return constructorCollector;
  }

  private ClassReader getClassReader(final Class<?> theClass) throws IllegalArgumentException {
    try {
      return new ClassReader(getResourceAsStream(theClass));
    }
    catch (final IOException e) {
      throw new IllegalArgumentException(format("%s cannot be read by %s", theClass, getClass()), e);
    }
  }

  @Override
  public <T> Map<Member, String[]> findMethodsAndConstructorsWithParameterNames(final Class<T> theClass) throws IllegalArgumentException {
    final Map<Member, String[]> memberToParameterNamesMap = new LinkedHashMap<Member, String[]>();
    getClassReader(theClass).accept(
        new MethodAnalysisClassVisitor<T, Member>(Opcodes.ASM5, methodAndConstructorCollector, theClass, memberToParameterNamesMap), 0);
    return memberToParameterNamesMap;
  }

  @Override
  public <T> Map<Method, String[]> findMethodsWithParameterNames(final Class<T> theClass) throws IllegalArgumentException {
    final Map<Method, String[]> methodToParameterNamesMap = new LinkedHashMap<Method, String[]>();
    getClassReader(theClass).accept(
        new MethodAnalysisClassVisitor<T, Method>(Opcodes.ASM5, methodCollector, theClass, methodToParameterNamesMap), 0);
    return methodToParameterNamesMap;
  }

  @Override
  public <T> Map<Constructor<T>, String[]> findConstructorsWithParameterNames(final Class<T> theClass) throws IllegalArgumentException {
    final Map<Constructor<T>, String[]> constructorToParameterNamesMap = new LinkedHashMap<Constructor<T>, String[]>();

    @SuppressWarnings({ "cast", "unchecked", "rawtypes" })
    final Map<Constructor<?>, String[]> map = ((Map) constructorToParameterNamesMap);

    getClassReader(theClass).accept(new MethodAnalysisClassVisitor<T, Constructor<?>>(Opcodes.ASM5, constructorCollector, theClass, map), 0);
    return constructorToParameterNamesMap;
  }

  public static AsmMethodAndConstructorAnalyser newAsmMethodAndConstructorAnalyser(
      final ConcurrentMap<String, Class<?>> externalTypeCacheMap) {
    return new AsmMethodAndConstructorAnalyser(externalTypeCacheMap);
  }
}
