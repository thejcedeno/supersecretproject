package com.panicsoda.experimental.buildbattle.teams;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Builder.Default;

/**
 * A class that contains all the utility functions to interact with the
 * underlaying team data.
 * 
 * @author jcedeno
 */
public class TeamManager<T extends JavaPlugin> {
    // A java plugin instance.
    private T instance;
    // This map will contain a constant time lookup table for player's teams.
    private Map<UUID, Team> playerTeampMap = new HashMap<>();
    // Map of list of invites, we probably can use a cache for this, and when the
    // cache expires, then react to that event and notify of expiration.
    // The key in this map is the inviter, the value is another K,V pair containing
    // the invitee and their invitation.
    private Map<UUID, Map.Entry<UUID, TeamInvite>> teamInvitesMap = new HashMap<>();

    /*
     * A data class to hold all the info for team invites.
     */
    @RequiredArgsConstructor
    @Builder
    @Data
    private static class TeamInvite {
        private @NonNull @Default Long createdTs = System.currentTimeMillis();
        private @NonNull @Default Long expirationMs = 15000L;
        private @NonNull UUID inviterTeamId;
        private @NonNull UUID potentialMemberId;

        /**
         * A static constructor that uses the default values.
         * @param team A valid team object
         * @param target A Valid 
         * @return
         */
        public static TeamInvite fromTeamWithDefaults(@NonNull Team team, UUID target) {
            return builder().potentialMemberId(target).inviterTeamId(team.getTeamId()).build();
        }

        /*
         * Updates the created ts, ergo it also updates when the invite will expire
         * (newCreatedTs + expirationMs).
         */
        public void createdTs(Long newCreatedTs) {
            this.createdTs = newCreatedTs;
        }

    }

}
