package com.hackdog.springbootlearn;

import org.springframework.util.Base64Utils;

import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class RSAUtil {
    private static final String charset = "utf-8";
    public static final String RSA_ALGORITHM = "RSA";

    /**
     * 生成私钥和公钥
     * @param keySize
     * @return
     * @throws Exception
     */
    public static Map<String,String> genreateKey(int keySize) throws Exception {
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("no such algorithm ==>[" + RSA_ALGORITHM + "]");
        }
        kpg.initialize(keySize);
        KeyPair keyPair = kpg.generateKeyPair();
        Key publicKey = keyPair.getPublic();

        String publicKeyStr = Base64Utils.encodeToUrlSafeString(publicKey.getEncoded());
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64Utils.encodeToUrlSafeString(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);
        return keyPairMap;
    }

    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64Utils.decodeFromUrlSafeString(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509EncodedKeySpec);
        return key;
    }

}
