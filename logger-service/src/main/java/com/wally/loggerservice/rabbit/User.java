package com.wally.loggerservice.rabbit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class User {

    private String userName;
    private String passWord;

}
