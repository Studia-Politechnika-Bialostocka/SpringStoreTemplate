package pawww.example.store.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CategoryValidator implements
        ConstraintValidator<CategoryValidation, String> {

    public void initialize(CategoryValidator categoryValidator) {
    }

    @Override
    public boolean isValid(String category,
                           ConstraintValidatorContext cxt) {
        return category != null && !category.contains(" ")
                && (category.length() > 2);
    }

}
