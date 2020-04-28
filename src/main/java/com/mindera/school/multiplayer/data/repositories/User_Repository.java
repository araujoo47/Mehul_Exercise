package com.mindera.school.multiplayer.data.repositories;

import com.mindera.school.multiplayer.data.entities.User_Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface User_Repository extends CrudRepository<User_Entity, String> {
}
