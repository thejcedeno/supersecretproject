package com.panicsoda.experimental.buildbattle.exceptions;

import lombok.SneakyThrows;

/**
 * A class that holds utility functions to easily throw exceptions
 */
public class ExceptionManager {


    /**
     * Not sure.
     * @param <T>
     * @param clazz
     * @param arguments
     * @return
     */
    @SneakyThrows
    @SuppressWarnings("all")
    public static <T extends Exception> T invalidArgument(Class<T> clazz, Object... arguments){
        return (T) clazz.getClass().getConstructors()[0].newInstance(arguments);
    }

    @SneakyThrows
    @SuppressWarnings("all")
    public static <T extends Exception> T invalidOrEmptyArgument(Class<T> clazz, Object... arguments){
        return (T) clazz.getClass().getConstructors()[0].newInstance(arguments);
    }
    
}
