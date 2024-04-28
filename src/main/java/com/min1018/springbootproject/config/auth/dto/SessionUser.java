package com.min1018.springbootproject.config.auth.dto;

import com.min1018.springbootproject.domain.user.UserInfo;
import lombok.Getter;
import org.apache.catalina.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(UserInfo user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
