package com.mindera.school.multiplayer.http.controllers;

import com.mindera.school.multiplayer.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import com.mindera.school.multiplayer.http.models.Lobby;
import org.springframework.http.ResponseEntity;
import com.mindera.school.multiplayer.http.models.User;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lobbies")

public class Lobby_Control {

    private LobbyService lobbyService;

    @Autowired
    public Lobby_Control(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createLobby() {
        var new_Lobby = lobbyService.createLobby();
        return ResponseEntity.created(getLobbyURI(new_Lobby.getID())).build();
    }

    @GetMapping("")
    public List<Lobby> getLobby() {
        return lobbyService.getLobby();
    }

    @DeleteMapping("/{lobbyID}")
    public void deleteLobby(@PathVariable("lobbyID") String lobbyID) {
        lobbyService.deleteLobby(lobbyID);
    }

    @PostMapping("/{lobbyID}/users")
    public void addUserOnLobby(
            @PathVariable("lobbyID") String lobbyID,
            @RequestBody User user
    )
    {
        lobbyService.add_User_Lobby(user, lobbyID);
    }

    @DeleteMapping("/{lobby_ID}/users/{user_ID}")
    public void removeUserFromLobby(@PathVariable("lobby_ID") String lobbyID, @PathVariable("user_ID") String userID) {
        lobbyService.remove_User_Lobby(userID, lobbyID);
    }

    private URI getLobbyURI(String id) {
        return URI.create(String.format("/lobbies/%s", id));
    }
}