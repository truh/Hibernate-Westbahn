package westbahn;

import java.lang.annotation.Documented;
import javax.validation.Constraint;
import javax.validation.Payload;

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