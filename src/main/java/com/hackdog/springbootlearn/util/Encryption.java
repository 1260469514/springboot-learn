package com.hackdog.springbootlearn.util;

import org.apache.shiro.crypto.hash.Sha256Hash;

import java.util.UUID;

public class Encryption {

    public static void main(String[] args) {
        String salt = UUID.randomUUID().toString().replaceAll("-", "").substring(3, 8);
        System.out.println(salt);
        Sha256Hash hash = new Sha256Hash("123456", salt, 1024);
        String enctyptionPwd = hash.toString();
        System.out.println(enctyptionPwd);

    }
}
