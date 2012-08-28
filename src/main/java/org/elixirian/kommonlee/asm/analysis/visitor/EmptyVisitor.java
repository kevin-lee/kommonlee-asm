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

import org.elixirian.kommonlee.lib3rd.asm3.AnnotationVisitor;
import org.elixirian.kommonlee.lib3rd.asm3.Attribute;
import org.elixirian.kommonlee.lib3rd.asm3.ClassVisitor;
import org.elixirian.kommonlee.lib3rd.asm3.FieldVisitor;
import org.elixirian.kommonlee.lib3rd.asm3.Label;
import org.elixirian.kommonlee.lib3rd.asm3.MethodVisitor;

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
 * @version 0.0.1 (2010-07-01)
 */
public class EmptyVisitor implements ClassVisitor, FieldVisitor, MethodVisitor, AnnotationVisitor
{

  @Override
  public void visit(@SuppressWarnings("unused") final String name, @SuppressWarnings("unused") final Object value)
  {
  }

  @Override
  public void visitEnum(@SuppressWarnings("unused") final String name, @SuppressWarnings("unused") final String desc,
      @SuppressWarnings("unused") final String value)
  {
  }

  @Override
  public AnnotationVisitor visitAnnotation(@SuppressWarnings("unused") final String name,
      @SuppressWarnings("unused") final String desc)
  {
    return this;
  }

  @Override
  public AnnotationVisitor visitArray(@SuppressWarnings("unused") final String name)
  {
    return this;
  }

  @Override
  public AnnotationVisitor visitAnnotationDefault()
  {
    return this;
  }

  @Override
  public AnnotationVisitor visitParameterAnnotation(@SuppressWarnings("unused") final int parameter,
      @SuppressWarnings("unused") final String desc, @SuppressWarnings("unused") final boolean visible)
  {
    return this;
  }

  @Override
  public void visitCode()
  {
  }

  @Override
  public void visitFrame(@SuppressWarnings("unused") final int type, @SuppressWarnings("unused") final int nLocal,
      @SuppressWarnings("unused") final Object[] local, @SuppressWarnings("unused") final int nStack,
      @SuppressWarnings("unused") final Object[] stack)
  {
  }

  @Override
  public void visitInsn(@SuppressWarnings("unused") final int opcode)
  {
  }

  @Override
  public void visitIntInsn(@SuppressWarnings("unused") final int opcode, @SuppressWarnings("unused") final int operand)
  {
  }

  @Override
  public void visitVarInsn(@SuppressWarnings("unused") final int opcode, @SuppressWarnings("unused") final int var)
  {
  }

  @Override
  public void visitTypeInsn(@SuppressWarnings("unused") final int opcode, @SuppressWarnings("unused") final String type)
  {
  }

  @Override
  public void visitFieldInsn(@SuppressWarnings("unused") final int opcode,
      @SuppressWarnings("unused") final String owner, @SuppressWarnings("unused") final String name,
      @SuppressWarnings("unused") final String desc)
  {
  }

  @Override
  public void visitMethodInsn(@SuppressWarnings("unused") final int opcode,
      @SuppressWarnings("unused") final String owner, @SuppressWarnings("unused") final String name,
      @SuppressWarnings("unused") final String desc)
  {
  }

  @Override
  public void visitJumpInsn(@SuppressWarnings("unused") final int opcode, @SuppressWarnings("unused") final Label label)
  {
  }

  @Override
  public void visitLabel(@SuppressWarnings("unused") final Label label)
  {
  }

  @Override
  public void visitLdcInsn(@SuppressWarnings("unused") final Object cst)
  {
  }

  @Override
  public void visitIincInsn(@SuppressWarnings("unused") final int var, @SuppressWarnings("unused") final int increment)
  {
  }

  @Override
  public void visitTableSwitchInsn(@SuppressWarnings("unused") final int min,
      @SuppressWarnings("unused") final int max, @SuppressWarnings("unused") final Label dflt,
      @SuppressWarnings("unused") final Label[] labels)
  {
  }

  @Override
  public void visitLookupSwitchInsn(@SuppressWarnings("unused") final Label dflt,
      @SuppressWarnings("unused") final int[] keys, @SuppressWarnings("unused") final Label[] labels)
  {
  }

  @Override
  public void visitMultiANewArrayInsn(@SuppressWarnings("unused") final String desc,
      @SuppressWarnings("unused") final int dims)
  {
  }

  @Override
  public void visitTryCatchBlock(@SuppressWarnings("unused") final Label start,
      @SuppressWarnings("unused") final Label end, @SuppressWarnings("unused") final Label handler,
      @SuppressWarnings("unused") final String type)
  {
  }

  @Override
  public void visitLocalVariable(@SuppressWarnings("unused") final String name,
      @SuppressWarnings("unused") final String desc, @SuppressWarnings("unused") final String signature,
      @SuppressWarnings("unused") final Label start, @SuppressWarnings("unused") final Label end,
      @SuppressWarnings("unused") final int index)
  {
  }

  @Override
  public void visitLineNumber(@SuppressWarnings("unused") final int line, @SuppressWarnings("unused") final Label start)
  {
  }

  @Override
  public void visitMaxs(@SuppressWarnings("unused") final int maxStack, @SuppressWarnings("unused") final int maxLocals)
  {
  }

  @Override
  public void visit(@SuppressWarnings("unused") final int version, @SuppressWarnings("unused") final int access,
      @SuppressWarnings("unused") final String name, @SuppressWarnings("unused") final String signature,
      @SuppressWarnings("unused") final String superName, @SuppressWarnings("unused") final String[] interfaces)
  {
  }

  @Override
  public void visitSource(@SuppressWarnings("unused") final String source,
      @SuppressWarnings("unused") final String debug)
  {
  }

  @Override
  public void visitOuterClass(@SuppressWarnings("unused") final String owner,
      @SuppressWarnings("unused") final String name, @SuppressWarnings("unused") final String desc)
  {
  }

  @Override
  public AnnotationVisitor visitAnnotation(@SuppressWarnings("unused") final String desc,
      @SuppressWarnings("unused") final boolean visible)
  {
    return this;
  }

  @Override
  public void visitAttribute(@SuppressWarnings("unused") final Attribute attr)
  {
  }

  @Override
  public void visitInnerClass(@SuppressWarnings("unused") final String name,
      @SuppressWarnings("unused") final String outerName, @SuppressWarnings("unused") final String innerName,
      @SuppressWarnings("unused") final int access)
  {
  }

  @Override
  public FieldVisitor visitField(@SuppressWarnings("unused") final int access,
      @SuppressWarnings("unused") final String name, @SuppressWarnings("unused") final String desc,
      @SuppressWarnings("unused") final String signature, @SuppressWarnings("unused") final Object value)
  {
    return this;
  }

  @Override
  public MethodVisitor visitMethod(@SuppressWarnings("unused") final int access,
      @SuppressWarnings("unused") final String name, @SuppressWarnings("unused") final String desc,
      @SuppressWarnings("unused") final String signature, @SuppressWarnings("unused") final String[] exceptions)
  {
    return this;
  }

  @Override
  public void visitEnd()
  {
  }

}
