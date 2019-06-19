package ru.shapovalov.sdm;

import org.reflections.Reflections;

import java.util.Collection;

public class GeneratorUtils {
    private static final String PACKAGE = "ru.shapovalov.sdm.model";

    protected static Collection<Class<?>> getModelClasses() {
        Reflections reflections = new Reflections(PACKAGE);
        return reflections.getSubTypesOf(Object.class);
    }

}
