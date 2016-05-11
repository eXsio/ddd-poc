package com.ddd.poc.command.event;

import com.ddd.poc.domain.core.event.DomainEvent;
import com.ddd.poc.domain.security.dto.UserDTO;

public class UserCreatedEvent implements DomainEvent {

    private final UserDTO userDTO;

    public UserCreatedEvent(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }
}
