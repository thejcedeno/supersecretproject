package com.panicsoda.experimental.buildbattle;

import org.bukkit.event.player.PlayerJoinEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;

public class BuildBattleTest {

    protected ServerMock server;
    protected BuildBattle plugin;

    @BeforeEach
    public void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(BuildBattle.class);
    }

    @Test
    public void shouldFirePlayerJoinEvent() {
        //Mock a player joining the server
        server.addPlayer();

        server.getPluginManager().assertEventFired(PlayerJoinEvent.class);
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }

    
}
