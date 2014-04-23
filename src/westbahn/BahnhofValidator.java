package westbahn;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = BahnhofValidatorImpl.class)
@Documented
public @interface BahnhofValidator 
{
	String message() default "{westbahn.BahnhofValidator}";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

	String bahnhof1();
	String bahnhof2();
}