package com.panicsoda.experimental.buildbattle.chat;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

import net.kyori.adventure.text.Component;

/**
 * A class designed to contained all the utility functions for sending chat messages
 * 
 * @author jcedeno
 */
public class ChatHandler {

    /**
     * A utility function that takes a string as an input and returns a {@link Component}.
     * 
     * @param input The string to be formatted
     * @return A {@link Component}.
     */
    public Component parsedMessage(String input){
        return miniMessage().deserialize(input);
    }
    
}
