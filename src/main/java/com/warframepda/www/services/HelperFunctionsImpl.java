package com.warframepda.www.services;

import com.warframepda.www.models.ValidationError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions{

    @Override
    public List<ValidationError> getConstraintViolation(Throwable cause) {

        while((cause != null) && !(cause instanceof org.hibernate.exception.ConstraintViolationException || cause instanceof MethodArgumentNotValidException)) {

            cause = cause.getCause();
        }

        List<ValidationError> listVE = new ArrayList<>();
        if (cause != null) {

            if (cause instanceof org.hibernate.exception.ConstraintViolationException) {

                org.hibernate.exception.ConstraintViolationException ex = (ConstraintViolationException) cause;

                ValidationError newVe = new ValidationError();

                newVe.setCode(ex.getMessage());
                newVe.setMessage(ex.getConstraintName());
                listVE.add(newVe);

            } else {
                // Must be a MethodArgumentNotValidException
                MethodArgumentNotValidException ex = (MethodArgumentNotValidException) cause;
                List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
                for (FieldError err : fieldErrors) {

                    ValidationError newVe = new ValidationError();
                    newVe.setCode(err.getField());
                    newVe.setMessage(err.getDefaultMessage());
                    listVE.add(newVe);
                }
            }
        }

        return listVE;
    }
}
