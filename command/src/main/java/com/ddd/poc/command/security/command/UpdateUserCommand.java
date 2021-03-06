package com.ddd.poc.command.security.command;

import com.ddd.poc.domain.core.command.DomainCommand;
import com.ddd.poc.domain.security.dto.UserDTO;

public class UpdateUserCommand extends DomainCommand {

    private final UserDTO userDTO;

    public UpdateUserCommand(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }
}
