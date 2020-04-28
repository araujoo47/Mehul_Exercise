package com.mindera.school.multiplayer.services.implementations;

import com.mindera.school.multiplayer.data.entities.User_Entity;
import com.mindera.school.multiplayer.data.repositories.User_Repository;
import com.mindera.school.multiplayer.exceptions.No_User;
import com.mindera.school.multiplayer.http.models.User;
import com.mindera.school.multiplayer.services.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class User_Implementations implements User_Service {
    private User_Repository userRepository;

    @Autowired
    public User_Implementations(User_Repository user_Repository) {
        this.userRepository = user_Repository;
    }

    @Override
    public User createUser(User newUser) {
        var user_entity = new User_Entity();
        user_entity.setId(UUID.randomUUID().toString());
        user_entity.setName(newUser.getName());

        return mapFromUserEntity(this.userRepository.save(user_entity));
    }

    @Override
    public User getUser(String userId) {
        return this.userRepository
                .findById(userId)
                .map(this::mapFromUserEntity).orElseThrow(() -> new No_User(userId));
    }

    @Override
    public List<User> getAllUsers() {
        var all_Users = new ArrayList<User>();
        userRepository.findAll()
                .forEach(user -> all_Users.add(mapFromUserEntity(user)));
        return all_Users;
    }

    @Override
    public void deleteUser(String userID) {
        try {
            userRepository.deleteById(userID);
        }

        catch (EmptyResultDataAccessException e) {
            throw new No_User(userID);
        }
    }

    @Override
    public User replaceUser(String userId, User user) {
        if (userRepository.findById(userId).isEmpty())
            throw new No_User(userId);

        user.setId(userId);

        return mapFromUserEntity(
                userRepository.save(mapFromUser(user))
        );
    }

    private User mapFromUserEntity(User_Entity userEntity) {
        return new User(userEntity.getId(), userEntity.getName());
    }

    private User_Entity mapFromUser(User user) {
        var user_Entity = new User_Entity();
        user_Entity.setId(user.getId());
        user_Entity.setName(user.getName());
        return user_Entity;
    }
}
