package org.elixirian.common.asm.analysis.visitor;

import java.lang.reflect.Member;
import java.util.Map;

import org.elixirian.common.asm.analysis.MemberCollector;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-06-15)
 */
public class MethodAnalysisClassVisitor<T, M extends Member> extends EmptyVisitor
{
	private final MemberCollector<M> memberCollector;
	private final Class<T> theClass;
	private final Map<M, String[]> memberToParameterNamesMap;

	/**
	 * @param theClass
	 * @param memberToParameterNamesMap
	 */
	public MethodAnalysisClassVisitor(MemberCollector<M> memberCollector, Class<T> theClass,
			Map<M, String[]> memberToParameterNamesMap)
	{
		this.memberCollector = memberCollector;
		this.theClass = theClass;
		this.memberToParameterNamesMap = memberToParameterNamesMap;
	}

	/*
	 * (non-Javadoc)
	 * @see org.objectweb.asm.ClassVisitor#visitMethod(int, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String[])
	 */
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			@SuppressWarnings("unused") String signature, @SuppressWarnings("unused") String[] exceptions)
	{
		if (0 == ((access & Opcodes.ACC_SYNTHETIC) | (access & Opcodes.ACC_BRIDGE)))
		{
			return new MethodAnalysisMethodVisitor<T, M>(memberCollector, theClass, memberToParameterNamesMap, access,
					name, desc);
		}
		return null;
	}

}
