package com.example.demo.entity;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RoleValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Role.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Role st = (Role) o;
        if(st.getName().equals("Truong")){
            errors.rejectValue("name",null,"Rieng anh ko dang ky dc");
        }
    }
}
