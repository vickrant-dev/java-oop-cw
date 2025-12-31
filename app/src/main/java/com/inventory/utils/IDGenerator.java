package com.inventory.utils;

import java.util.UUID;

public class IDGenerator {

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }

}
