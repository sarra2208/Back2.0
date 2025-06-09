package com.example.test.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserApp {
    private String username;
    private String password;
}
