package com.warframepda.www.services;

import com.warframepda.www.models.ValidationError;

import java.util.List;

public interface HelperFunctions {

    List<ValidationError> getConstraintViolation(Throwable cause);
}
