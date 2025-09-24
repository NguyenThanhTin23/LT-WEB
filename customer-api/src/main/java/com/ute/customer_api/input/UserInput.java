package com.ute.customer_api.input;

import lombok.Data;

@Data
public class UserInput {
    private String fullname;
    private String email;
    private String password;
    private String phone;
}