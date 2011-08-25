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
 *     ____________    ___________  ____   _______ _________ _______ _______________  ____
 *    /       /   /   /_    _/\   \/   /  /_    _//  __    //_    _//   __    /     \/   /
 *   /    ___/   /     /   /   \      /    /   / /  /_/   /  /   / /   /_/   /          /
 *  /    ___/   /_____/   /_   /      \  _/   /_/       _/ _/   /_/   __    /          /
 * /_______/________/______/  /___/\___\/______/___/\___\ /______/___/ /___/___/\_____/
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
