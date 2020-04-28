package com.mindera.school.multiplayer.exceptions;

public class No_Lobby extends RuntimeException {
    public No_Lobby(String id) {
        super(String.format("Lobby %s not found...", id));
    }
}