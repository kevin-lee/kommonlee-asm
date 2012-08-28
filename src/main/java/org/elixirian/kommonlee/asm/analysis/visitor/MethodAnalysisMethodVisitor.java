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
package org.elixirian.kommonlee.asm.analysis.visitor;

import static org.elixirian.kommonlee.asm.util.AsmClasses.*;

import java.lang.reflect.Member;
import java.util.Map;

import org.elixirian.kommonlee.asm.analysis.MemberCollector;
import org.elixirian.kommonlee.lib3rd.asm3.Label;
import org.elixirian.kommonlee.lib3rd.asm3.Opcodes;
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
 * @version 0.0.1 (2010-06-15)
 * @param <T>
 * @param <M>
 */
public class MethodAnalysisMethodVisitor<T, M extends Member> extends EmptyVisitor
{
  private final MemberCollector<M> memberCollector;
  private final Class<T> theClass;
  private final Map<M, String[]> memberToParameterNamesMap;
  private final boolean staticMethod;
  private final String methodName;

  private final Type[] params;
  private final String[] paramNames;
  private final int[] localVariableSlotIndices;

  // private boolean hasLocalVaribleSlots;

  /**
   * @param theClass
   * @param memberToParameterNamesMap
   * @param access
   * @param methodName
   * @param desc
   */
  public MethodAnalysisMethodVisitor(final MemberCollector<M> memberCollector, final Class<T> theClass,
      final Map<M, String[]> memberToParameterNamesMap, final int access, final String methodName, final String desc)
  {
    this.memberCollector = memberCollector;
    this.theClass = theClass;
    this.memberToParameterNamesMap = memberToParameterNamesMap;
    this.staticMethod = 0 != (access & Opcodes.ACC_STATIC);
    this.methodName = methodName;

    this.params = Type.getArgumentTypes(desc);
    this.paramNames = new String[params.length];
    this.localVariableSlotIndices = calculateLocalVariableSlotIndices(this.staticMethod, this.params);

    // this.hasLocalVaribleSlots = false;

  }

  @Override
  public void visitLocalVariable(final String name, @SuppressWarnings("unused") final String desc,
      @SuppressWarnings("unused") final String signature, @SuppressWarnings("unused") final Label start,
      @SuppressWarnings("unused") final Label end, final int index)
  {
    // hasLocalVaribleSlots = true;
    for (int i = 0, size = localVariableSlotIndices.length; i < size; i++)
    {
      if (index == localVariableSlotIndices[i])
      {
        paramNames[i] = name;
        // TODO test it!
        break;
      }
    }
  }

  @Override
  public void visitEnd()
  {
    if (shouldCollectMethodInfo())
    {
      memberCollector.collect(theClass, methodName, memberToParameterNamesMap, params, paramNames);
    }
  }

  private boolean shouldCollectMethodInfo()
  {
    /*
     * If the method is a static method with no parameters and has no local variables in it which means it has no local
     * variable slots, the visitLocalVariable() method might not be called so this case should be checked and if it is
     * the case then this method should return true.
     */
    // return hasLocalVaribleSlots || (staticMethod && 0 == paramNames.length);
    return !"<clinit>".equals(methodName);
  }

}