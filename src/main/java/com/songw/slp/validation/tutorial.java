package com.songw.slp.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * custom annotation
 * @author song-wan
 */
@Documented
@Constraint(validatedBy = tutorialConstraintValidator.class)	// 이 어노테이션이 지정됐을 때 어떤 validator(class)가 처리를 담당할지 지정해준다.
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })				// Target을 통해서 메서드나 필드에 적용되는 annotation임을 지
@Retention(RetentionPolicy.RUNTIME)
public @interface tutorial {
	String message() default "Invalid tutorial";				// 문제 발생시에 기본적으로 보이는 메세지가 		
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
