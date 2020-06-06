/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.service.impl;

import tr.com.heartsapiens.tedis.util.Validator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ersin
 */
public abstract class AbstractService {

    @Autowired
    protected Validator validator;

    ///////////////////////////////////////////////////////////////////////////
    protected void validateBeforeSave(Object object) throws IllegalArgumentException {
        List<String> validList = validator.check(object);
        if (!validList.isEmpty()) {
            throw new IllegalArgumentException(validList.toString());
        }
    }

}
