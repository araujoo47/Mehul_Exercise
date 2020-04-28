package com.mindera.school.multiplayer.data.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.FetchType;

@Entity
public class User_Entity {

    @ManyToOne(fetch = FetchType.LAZY)

    @Id
        private String id;

    private String name;
    private Lobby_Entity lobby;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Lobby_Entity getLobby() {
        return lobby;
    }

    public void setLobby(Lobby_Entity lobby) {
        this.lobby = lobby;
    }

}
