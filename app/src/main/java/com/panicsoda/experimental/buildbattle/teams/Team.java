package com.panicsoda.experimental.buildbattle.teams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.panicsoda.experimental.buildbattle.events.EventManager;
import com.panicsoda.experimental.buildbattle.events.events.TeamMemberAddedEvent;
import com.panicsoda.experimental.buildbattle.players.PlayerManager;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * A data class containing all the information pertaining to a Team of players.
 * 
 * @author jcedeno.
 */
@Data
@Builder
@RequiredArgsConstructor(staticName = "create")
public class Team {
    private @NonNull @Default UUID teamId = UUID.randomUUID();
    private @NonNull String displayName;
    private @NonNull UUID teamLeader;
    private @NonNull @Default Collection<UUID> teamMembers = new ArrayList<>();
    private @NonNull TeamSettings teamSettings;

    /**
     * @return a key-value pair of UUIDs and Optional Players. If a player is
     *         offline, the optional will be empty.
     */
    public Map<UUID, Optional<Player>> onlineTeamMembers() {
        return PlayerManager.validOnlinePlayersFromList(this.teamMembers);
    }

    /**
     * @return a key-value pair of UUIDs and OfflinePlayers.
     */
    public Map<UUID, Optional<OfflinePlayer>> offlineMembers() {
        return PlayerManager.validOfflinePlayersFromList(teamMembers);
    }

    /**
     * A function that validates the newLeader, verifies if they are part of the
     * team, and notifies all members
     * 
     * @param newLeader The player that should be promoted to leader.
     */
    public void setTeamLeader(@NonNull UUID newLeader, boolean fireEvent) {
        // Verify UUID is valid.
        assert PlayerManager.isValidPlayerUUID(newLeader);
        // Verify Player is member of the team.
        assert teamMember(newLeader);

        this.teamLeader = newLeader;
        // Fire event if needed
        if (fireEvent)
            System.out.println("Implement later"); // <--- TODO: Implement event logic later.

        // Notify all members of the change.
        sendChatMessage(String.format("%s<green> has been promoted to <white>team leader</white>.", displayName));
    }

    /**
     * An overload method that updated the team leader and fires an event for it.
     * 
     * @param newLeader The player that should be promoted to leader.
     */
    public void teamLeader(UUID newLeader) {
        this.setTeamLeader(newLeader, true);
    }

    /*
     * Adds the provided list of valid uuids to the team members list. We need to
     * validate that team members are not dupes and not in more than one team at
     * once.
     * It will fire a bukkit event is the provided boolean parameter is true.
     */
    public void teamMembers(boolean fireEvent, UUID... members) throws Exception {
        if (members == null || members.length < 1)
            throw new Exception("invalid argument");// TODO: Change thos

        // TODO: Polish this too.
        for (var m : members) {
            if (!this.teamMember(m))
                continue;
            // add them to array
            this.teamMembers.add(m);
            // Fire event if needed
            if (fireEvent)
                EventManager.fireAndForgetEvent(TeamMemberAddedEvent.class, members); // TODO: Figure this out later.

        }

    }

    /**
     * A utility function that sends a parsed message to all online team members.
     * 
     * @param input The string you want to send to all members.
     */
    public void sendChatMessage(String input) {
        // TODO: Implement later.
    }

    /**
     * A utility function that checks if the provided {@link UUID} is a member of
     * this team.
     * 
     * @param id The id to be checked
     * @return True if the provided Id is part of the team, false otherwise.
     */
    public boolean teamMember(UUID id) {
        return teamMembers.contains(id);
    }

    /*
     * A data class to hold all extra info about the team
     */
    public static @Data class TeamSettings {
        private String teamPreffix;
        private String teamSuffix;
        private boolean friendlyFire = false;

    }

}
