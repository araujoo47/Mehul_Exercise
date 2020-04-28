package com.mindera.school.multiplayer.services.implementations;

import com.mindera.school.multiplayer.data.entities.Lobby_Entity;
import com.mindera.school.multiplayer.data.entities.User_Entity;
import com.mindera.school.multiplayer.data.repositories.Lobby_Repository;
import com.mindera.school.multiplayer.data.repositories.User_Repository;
import com.mindera.school.multiplayer.exceptions.No_Lobby;
import com.mindera.school.multiplayer.http.models.Lobby;
import com.mindera.school.multiplayer.http.models.User;
import com.mindera.school.multiplayer.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class Lobby_Implementations implements LobbyService {

    private Lobby_Repository lobbyRepository;

    private User_Repository userRepository;

    @Autowired
    public Lobby_Implementations(Lobby_Repository lobbyRepository, User_Repository userRepository) {
        this.lobbyRepository = lobbyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Lobby createLobby() {
        var lobby = new Lobby_Entity();
        lobby.setId(UUID.randomUUID().toString());

        return mapFromLobbyEntity(this.lobbyRepository.save(lobby));
    }

    @Override
    public List<Lobby> getLobby() {
        var allLobbies = new ArrayList<Lobby>();
        lobbyRepository.findAll()
                .forEach(lobbyEntity -> allLobbies.add(mapFromLobbyEntity(lobbyEntity)));
        return allLobbies;
    }

    @Override
    public void deleteLobby(String lobbyId) {
        try {
            lobbyRepository.deleteById(lobbyId);
        } catch (EmptyResultDataAccessException e) {
            throw new No_Lobby(lobbyId);
        }
    }

    @Override
    public void add_User_Lobby(User user, String lobbyID) {
        var lobby_Entity = new Lobby_Entity();
        lobby_Entity.setId(lobbyID);

        var userEntity = mapFromUser(user);
        userEntity.setLobby(lobby_Entity);

        userRepository.save(userEntity);
    }


    @Override
    public void remove_User_Lobby(String userId, String lobbyId) {
        var user_Entity = this.userRepository.findById(userId);
        user_Entity.ifPresent(entity -> {
            entity.setLobby(null);
            userRepository.save(entity);
        });
    }

    private Lobby mapFromLobbyEntity(Lobby_Entity lobbyEntity) {
        return new Lobby(lobbyEntity.getId());
    }

    private Lobby_Entity mapFromLobby(Lobby lobby) {
        var lobbyEntity = new Lobby_Entity();
        lobbyEntity.setId(lobby.getID());
        return lobbyEntity;
    }

    private User_Entity mapFromUser(User user) {
        var userEntity = new User_Entity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        return userEntity;
    }
}
