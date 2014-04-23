package westbahn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;

import westbahn.model.Bahnhof;

public class BahnhofValidatorImpl implements ConstraintValidator<BahnhofValidator, Object> 
{
	private String bahnhof1;
	private String bahnhof2;

	public void initialize(BahnhofValidator constraintAnnotation) 
	{
		this.bahnhof1 = constraintAnnotation.bahnhof1();
		this.bahnhof2= constraintAnnotation.bahnhof2();
	}

	public boolean isValid(Object value, ConstraintValidatorContext context) 
	{
		try
		{
			Bahnhof b1 = (Bahnhof) PropertyUtils.getProperty(value, bahnhof1);
			Bahnhof b2 = (Bahnhof) PropertyUtils.getProperty(value, bahnhof2);
			
		    if((b1 == null) && (b2 == null)) return true;
		    boolean matches = (b1.getName().equals(b2.getName()));
		
		    if (matches)
		    {
		    	context.disableDefaultConstraintViolation();
		    	context.buildConstraintViolationWithTemplate("message")
		    		.addNode("Start und Endbahnhof sind dieselben!")
		    		.addConstraintViolation();
		    }
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
}