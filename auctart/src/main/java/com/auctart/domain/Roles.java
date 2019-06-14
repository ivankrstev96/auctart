package com.auctart.domain;

public enum Roles {

    ADMIN("ADMIN"),
    USER("USER");

    String roleName;

    Roles(String roleName){
        this.roleName = roleName;
    }
}
