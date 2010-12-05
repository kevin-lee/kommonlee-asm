package org.elixirian.common.asm.analysis.visitor;

import static org.elixirian.common.asm.util.AsmClasses.*;

import java.lang.reflect.Member;
import java.util.Map;

import org.elixirian.common.asm.analysis.MemberCollector;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

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
	public MethodAnalysisMethodVisitor(MemberCollector<M> memberCollector, Class<T> theClass,
			Map<M, String[]> memberToParameterNamesMap, int access, String methodName, String desc)
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
		 * If the method is a static method with no parameters and has no local variables in it which means it has no
		 * local variable shots, the visitLocalVariable() method might not be called so this case should be checked and
		 * if it is the case then this method should return true.
		 */
		// return hasLocalVaribleSlots || (staticMethod && 0 == paramNames.length);
		return !"<clinit>".equals(methodName);
	}

}