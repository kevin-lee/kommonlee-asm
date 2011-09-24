/**
 * 
 */
package org.elixirian.kommonlee.asm.analysis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Map;

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
 * @version 0.0.1 (2010-06-22)
 */
public interface MethodAndConstructorAnalyser extends MethodAnalyser, ConstructorAnalyser
{
  <T> Map<Member, String[]> findMethodsAndConstructorsWithParameterNames(Class<T> theClass);

  @Override
  <T> Map<Method, String[]> findMethodsWithParameterNames(Class<T> theClass);

  @Override
  <T> Map<Constructor<T>, String[]> findConstructorsWithParameterNames(Class<T> theClass);

}
