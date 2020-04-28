package com.mindera.school.multiplayer.data.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.mindera.school.multiplayer.data.entities.Lobby_Entity;


@Repository
    public interface Lobby_Repository extends CrudRepository<Lobby_Entity, String> {}
