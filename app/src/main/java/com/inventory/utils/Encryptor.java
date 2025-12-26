package com.inventory.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

    public String encrypt(String input) throws NoSuchAlgorithmException {
        // MessageDigest is part of java.security library
        // We create a simple MessageDigest object which takes an instance and based of a
        // specific hashing method like SHA-256, SHA-512, MD-5 etc...
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // byte[] - byte array is just a sequence of bytes that we can use to manipulate,
        // especially here for hashing

        // Turning the input into a hashed byte array by using "digest()"
        // The input is turned into bytes (returning a byte array)
        // -> hashes it (md.digest) -> returns a binary array to messageDigest.
        byte[] messageDigest = md.digest(input.getBytes());

        // we change the byte array into a big integer.
        // signum in the BigInteger param returns an int that indicates whether positive, negative or zero.
        BigInteger bigInt = new BigInteger(1, messageDigest);

        // We change this big integer into a string based on hex code that gives a hexadecimal string.
        // hexadecimal string contains 0-9 and a-f
        return bigInt.toString(16);
    }

}
