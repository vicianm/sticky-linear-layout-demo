package com.github.vicianm.dynamicform.demo.validation;

import java.util.Collection;

public interface ValidationCallback {

    void validated(Collection<ValidationResult> results);

}
