package com.panicsoda.experimental.buildbattle.players;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * A class that holds common utility functions and other functions that are core
 * to the build battle minigame. Ideally this class will be swapped with
 * Richie's game lib.
 * 
 * @author jcedeno
 */
public class PlayerManager {

    /**
     * A utility function that takes in one or more UUIDS and returns a key-value
     * pair of <UUID, Optional<Player>>.
     * 
     * @param players A collection or a single player(s) to query for validity
     * 
     * @return A map of key-value pairs containing all the provided UUIDs with an
     *         entry of an optional {@link Player}. If the player
     *         is valid and online, it will be in the present optional, otherwise
     *         the optional will be empty.
     */
    public static Map<UUID, Optional<Player>> validOnlinePlayersFromList(UUID... players) {
        var map = new HashMap<UUID, Optional<Player>>();

        for (var p : players)
            map.putIfAbsent(p, Optional.ofNullable(isValidPlayerUUID(p) ? Bukkit.getPlayer(p) : null));

        return null;
    }

    /**
     * A utility function that takes in one or more UUIDS and returns a key-value
     * pair of <UUID, Optional<Player>>.
     * 
     * @param players A collection or a single player(s) to query for validity
     * 
     * @return A map of key-value pairs containing all the provided UUIDs with an
     *         entry of an optional {@link Player}. If the player
     *         is valid and online, it will be in the present optional, otherwise
     *         the optional will be empty.
     */
    public static Map<UUID, Optional<Player>> validOnlinePlayersFromList(Collection<UUID> players) {
        return validOnlinePlayersFromList(players.toArray(new UUID[] {}));
    }

    /**
     * A utility function that takes in one or more UUIDS and and returns a
     * key-value
     * pair of <UUID, Optional<OfflinePlayer>>.
     * 
     * @param players A collection or a single player(s) to query for validity
     * 
     * @return A map of key-value pairs containing all the provided UUIDs with an
     *         entry of an optional {@link OfflinePlayer}. If the player
     *         is valid, it will be in the present optional, otherwise the optional
     *         will be empty.
     */
    public static Map<UUID, Optional<OfflinePlayer>> validOfflinePlayersFromList(UUID... players) {
        var map = new HashMap<UUID, Optional<OfflinePlayer>>();

        for (var ofp : players)
            map.putIfAbsent(ofp, Optional.ofNullable(isValidPlayerUUID(ofp) ? Bukkit.getOfflinePlayer(ofp) : null));

        return null;
    }

    /**
     * A utility function that takes in one or more UUIDS and and returns a
     * key-value
     * pair of <UUID, Optional<OfflinePlayer>>.
     * 
     * @param players A collection or a single player(s) to query for validity
     * 
     * @return A map of key-value pairs containing all the provided UUIDs with an
     *         entry of an optional {@link OfflinePlayer}. If the player
     *         is valid, it will be in the present optional, otherwise the optional
     *         will be empty.
     */
    public static Map<UUID, Optional<OfflinePlayer>> validOfflinePlayersFromList(Collection<UUID> players) {
        return validOfflinePlayersFromList(players.toArray(new UUID[] {}));
    }

    /**
     * A utility function that verifies if the player is a real, valid player UUID
     * and not just a random UUID.
     * 
     * @param id The player to be validated.
     * @return True is the player is valid, false otherwise.
     */
    public static boolean isValidPlayerUUID(UUID id) {
        return Bukkit.getOfflinePlayer(id).getName() != null;
    }

    /**
     * A utility function that returns the display name of a player from a provided
     * uuid.
     * 
     * @param uuid The id of the player to be queried.
     * @return The name of the player, if the given id is valid, otherwise an
     *         exception will be thrown.
     */
    public static String getPlayerNameFromId(UUID id) {
        assert isValidPlayerUUID(id);

        return Bukkit.getOfflinePlayer(id).getName();
    }

}
