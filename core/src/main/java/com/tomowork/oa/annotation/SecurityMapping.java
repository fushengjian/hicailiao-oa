package com.tomowork.oa.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.METHOD })
public @interface SecurityMapping {
	String title() default "";

	String value() default "";

	String rname() default "";

	String rcode() default "";

	int rsequence() default 0;

	String rgroup() default "";

	String rtype() default "";

	boolean display() default false;
}
