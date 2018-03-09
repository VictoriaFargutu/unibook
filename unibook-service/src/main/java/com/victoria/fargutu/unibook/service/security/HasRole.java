package com.victoria.fargutu.unibook.service.security;

import com.victoria.fargutu.unibook.repository.commons.UserRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HasRole {
    UserRole value();
}
