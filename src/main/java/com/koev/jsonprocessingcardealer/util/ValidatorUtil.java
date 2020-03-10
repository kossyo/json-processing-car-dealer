package com.koev.jsonprocessingcardealer.util;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidatorUtil {

    <E> Set<ConstraintViolation<E>> getViolations(E entity);


}
