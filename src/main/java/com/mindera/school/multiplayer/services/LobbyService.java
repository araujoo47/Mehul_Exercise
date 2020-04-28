package com.mindera.school.multiplayer.services;

import com.mindera.school.multiplayer.http.models.Lobby;
import com.mindera.school.multiplayer.http.models.User;
import java.util.List;

public interface LobbyService {
    Lobby createLobby();
    List<Lobby> getLobby();
    void deleteLobby(String lobbyId);
    void add_User_Lobby(User user, String lobbyId);
    void remove_User_Lobby(String userId, String lobbyId);

}
