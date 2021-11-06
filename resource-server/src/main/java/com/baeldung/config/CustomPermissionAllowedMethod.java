package com.baeldung.config;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.method.AbstractFallbackMethodSecurityMetadataSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.security.access.annotation.Jsr250SecurityConfig.DENY_ALL_ATTRIBUTE;

public class CustomPermissionAllowedMethod
        extends AbstractFallbackMethodSecurityMetadataSource {
    @Override
    protected Collection findAttributes(Class<?> clazz) { return null; }

    @Override
    protected Collection findAttributes(Method method, Class<?> targetClass) {
        List attributes = new ArrayList<>();

        // if the class is annotated as @Controller we should by default deny access to all methods
        if (AnnotationUtils.findAnnotation(targetClass, Controller.class) != null) {
            attributes.add(DENY_ALL_ATTRIBUTE);
        }

        if(AnnotationUtils.getAnnotation(method, PreAuthorize.class) != null) {
            return null;
        }

        return attributes;
    }

    @Override
    public Collection getAllConfigAttributes() { return null; }
}
