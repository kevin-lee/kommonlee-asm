/**
 *
 */
package org.elixirian.kommonlee.asm.analysis;

import static org.elixirian.kommonlee.util.collect.Lists.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import org.elixirian.kommonlee.util.CommonConstants;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2014-07-05)
 *
 */
public final class TestUtil {
  private TestUtil() throws IllegalAccessException {
    throw new IllegalAccessException(getClass().getName() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }

  public static List<Constructor<?>> getNonSynthetic(final Constructor<?>... constructors) {
    final List<Constructor<?>> result = newArrayList();
    for (final Constructor<?> constructor : constructors) {
      if (!constructor.isSynthetic()) {
        result.add(constructor);
      }
    }
    return Collections.unmodifiableList(result);
  }

  public static List<Method> getNonSyntheticNonBridge(final Method... methods) {
    final List<Method> result = newArrayList();
    for (final Method method : methods) {
      if (!method.isSynthetic() && !method.isBridge()) {
        result.add(method);
      }
    }
    return Collections.unmodifiableList(result);
  }
}
