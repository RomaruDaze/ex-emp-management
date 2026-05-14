package com.example.domain;

import lombok.Lombok;

@lombok.Getter
@lombok.Setter
@lombok.ToString
public class Administrator {
    private Integer id;
    private String name;
    private String mailAddress;
    private String password;
}
