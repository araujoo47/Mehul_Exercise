package com.mindera.school.multiplayer.http.advices;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.mindera.school.multiplayer.exceptions.No_User;
import com.mindera.school.multiplayer.http.models.API_Error;
import com.mindera.school.multiplayer.exceptions.No_Lobby;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(No_User.class)

    public API_Error userNotFound(No_User e) {
        return new API_Error("User not found...", e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)

    @ExceptionHandler(No_Lobby.class)

    public API_Error lobbyNotFound(No_Lobby e) {
        return new API_Error("Lobby not found...", e.getMessage());
    }
}
