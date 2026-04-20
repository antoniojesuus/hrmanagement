package com.ntt.hrmanagement.dto;

import com.ntt.hrmanagement.model.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

    private Long id;
    private String username;
    private Role role;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

}