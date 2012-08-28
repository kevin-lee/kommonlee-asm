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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.elixirian.kommonlee.util.CommonConstants;

/**
 * @author Lee, SeongHyun (Kevin)
 * @version 0.0.1 (2010-06-23)
 */
public final class ConstantsForTesting
{
  @Target({ ElementType.METHOD })
  @Retention(RetentionPolicy.RUNTIME)
  static @interface MethodAnnotation
  {
    String value() default "";
  }

  @Target({ ElementType.METHOD })
  @Retention(RetentionPolicy.RUNTIME)
  static @interface AnotherMethodAnnotation
  {
    String value() default "";
  }

  @Target({ ElementType.PARAMETER })
  @Retention(RetentionPolicy.RUNTIME)
  static @interface ParamAnnotation
  {
    String value() default "";

    int number() default 0;
  }

  @Target({ ElementType.PARAMETER })
  @Retention(RetentionPolicy.RUNTIME)
  static @interface AnotherParamAnnotation
  {
    String value() default "default";

    String[] arrayValue() default { "defaultArray" };
  }

  static class TestPojo
  {
    public TestPojo()
    {
    }

    public TestPojo(
        @SuppressWarnings("unused") @ParamAnnotation(value = "someName", number = 2) @AnotherParamAnnotation() String name)
    {
    }

    public TestPojo(
        @SuppressWarnings("unused") @ParamAnnotation(value = "someName", number = 2) @AnotherParamAnnotation() String name,
        @SuppressWarnings("unused") @ParamAnnotation int number)
    {
    }

    public TestPojo(@SuppressWarnings("unused") TestPojo another)
    {
    }

    @AnotherMethodAnnotation
    @MethodAnnotation("something")
    public void testMethod1(
        @SuppressWarnings("unused") @AnotherParamAnnotation(arrayValue = { "A", "B" }, value = "test") String name)
    {
    }

    public String testMethod2(@SuppressWarnings("unused") int number)
    {
      return null;
    }

    public TestPojo testMethod3(@SuppressWarnings("unused") Long id)
    {
      return null;
    }

    public void testMethod4(@SuppressWarnings("unused") TestPojo another)
    {
    }
  }

  /**
   * An empty class array.
   */
  public static final Class<?>[] PARAMS0 = new Class[0];

  /**
   * new Class[] { String.class }
   */
  public static final Class<?>[] PARAMS1 = new Class[] { String.class };

  /**
   * new Class[] { int.class }
   */
  public static final Class<?>[] PARAMS2 = new Class[] { int.class };

  /**
   * new Class[] { Long.class }
   */
  public static final Class<?>[] PARAMS3 = new Class[] { Long.class };

  /**
   * new Class[] { TestPojo.class }
   */
  public static final Class<?>[] PARAMS4 = new Class[] { TestPojo.class };

  private ConstantsForTesting()
  {
    throw new IllegalStateException(getClass() + CommonConstants.CANNOT_BE_INSTANTIATED);
  }
}
