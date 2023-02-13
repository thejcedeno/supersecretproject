package com.panicsoda.experimental.buildbattle.events;

import org.bukkit.event.Event;

/**
 * A class designed to handle all events registrations/deregistrations and hold
 * all utility functions related to {@link Event} class.
 * 
 * @author jcedeno
 */
public class EventManager {

    /**
     * 
     * 
     * @param <T>
     * @param clazz
     * @param args
     */
    public static <T extends Event> void fireAndForgetEvent(Class<T> clazz, Object... args) {

    }
}
