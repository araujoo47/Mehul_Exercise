package com.mindera.school.multiplayer.exceptions;

public class No_User extends RuntimeException {
    public No_User(String id) {
        super(String.format("User %s not found...", id));
    }
}