/**
 * 
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
