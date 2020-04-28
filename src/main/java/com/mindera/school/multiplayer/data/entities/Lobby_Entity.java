package com.mindera.school.multiplayer.data.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lobby_Entity {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "lobby")

    @Id
        private String id;

    private List<User_Entity> users;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}