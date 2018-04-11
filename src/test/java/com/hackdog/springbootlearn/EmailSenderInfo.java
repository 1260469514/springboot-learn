package com.hackdog.springbootlearn;

import lombok.Data;

@Data
public class EmailSenderInfo {

    private String mailServerHost;
    private String mailServerPort;
    private boolean vaildate;
    private String username;
    private String password;
    private String fromAddress;
    private String toAddress;
    private String title;
    private String content;

}
