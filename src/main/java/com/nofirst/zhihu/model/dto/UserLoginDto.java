package com.nofirst.zhihu.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginDto implements Serializable {
    private static final long serialVersionUID = -1L;

    private String username;
    private String password;
}
