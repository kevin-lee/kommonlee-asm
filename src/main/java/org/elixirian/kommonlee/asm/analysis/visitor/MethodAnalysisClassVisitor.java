package org.elixirian.kommonlee.asm.analysis.visitor;

import java.lang.reflect.Member;
import java.util.Map;

import org.elixirian.kommonlee.asm.analysis.MemberCollector;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

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
 *     ___  _____  __________  ___________ _____  ____
 *    /   \/    / /      \   \/   /_    _//     \/   /
 *   /        /  /    ___/\      / /   / /          /
 *  /        \  /    ___/  \    /_/   /_/          /
 * /____/\____\/_______/    \__//______/___/\_____/
 * </pre>
 * 
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
  public MethodAnalysisClassVisitor(final MemberCollector<M> memberCollector, final Class<T> theClass,
      final Map<M, String[]> memberToParameterNamesMap)
  {
    this.memberCollector = memberCollector;
    this.theClass = theClass;
    this.memberToParameterNamesMap = memberToParameterNamesMap;
  }

  @Override
  public MethodVisitor visitMethod(final int access, final String name, final String desc,
      @SuppressWarnings("unused") final String signature, @SuppressWarnings("unused") final String[] exceptions)
  {
    if (0 == ((access & Opcodes.ACC_SYNTHETIC) | (access & Opcodes.ACC_BRIDGE)))
    {
      return new MethodAnalysisMethodVisitor<T, M>(memberCollector, theClass, memberToParameterNamesMap, access, name,
          desc);
    }
    return null;
  }

}
