/**
 * 
 */
package org.elixirian.common.asm.analysis.visitor;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-07-01)
 */
public class EmptyVisitor implements ClassVisitor, FieldVisitor, MethodVisitor, AnnotationVisitor
{

	@Override
	public void visit(String name, Object value)
	{
	}

	@Override
	public void visitEnum(String name, String desc, String value)
	{
	}

	@Override
	public AnnotationVisitor visitAnnotation(String name, String desc)
	{
		return this;
	}

	@Override
	public AnnotationVisitor visitArray(String name)
	{
		return this;
	}

	@Override
	public AnnotationVisitor visitAnnotationDefault()
	{
		return this;
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible)
	{
		return this;
	}

	@Override
	public void visitCode()
	{
	}

	@Override
	public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack)
	{
	}

	@Override
	public void visitInsn(int opcode)
	{
	}

	@Override
	public void visitIntInsn(int opcode, int operand)
	{
	}

	@Override
	public void visitVarInsn(int opcode, int var)
	{
	}

	@Override
	public void visitTypeInsn(int opcode, String type)
	{
	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String desc)
	{
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc)
	{
	}

	@Override
	public void visitJumpInsn(int opcode, Label label)
	{
	}

	@Override
	public void visitLabel(Label label)
	{
	}

	@Override
	public void visitLdcInsn(Object cst)
	{
	}

	@Override
	public void visitIincInsn(int var, int increment)
	{
	}

	@Override
	public void visitTableSwitchInsn(int min, int max, Label dflt, Label[] labels)
	{
	}

	@Override
	public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels)
	{
	}

	@Override
	public void visitMultiANewArrayInsn(String desc, int dims)
	{
	}

	@Override
	public void visitTryCatchBlock(Label start, Label end, Label handler, String type)
	{
	}

	@Override
	public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index)
	{
	}

	@Override
	public void visitLineNumber(int line, Label start)
	{
	}

	@Override
	public void visitMaxs(int maxStack, int maxLocals)
	{
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
	{
	}

	@Override
	public void visitSource(String source, String debug)
	{
	}

	@Override
	public void visitOuterClass(String owner, String name, String desc)
	{
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible)
	{
		return this;
	}

	@Override
	public void visitAttribute(Attribute attr)
	{
	}

	@Override
	public void visitInnerClass(String name, String outerName, String innerName, int access)
	{
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value)
	{
		return this;
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
	{
		return this;
	}

	@Override
	public void visitEnd()
	{
	}

}
