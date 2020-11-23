package com.example.kp_assignment.util;

import java.security.SecureRandom;

public class Util {

    private static SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String createToken() {
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int index = SECURE_RANDOM.nextInt(3);
            token.append(turn(index));
        }
        return token.toString();
    }
    private static Object turn(int index) {
        switch (index) {
            case 0:
                return SECURE_RANDOM.nextInt(10);
            case 1:
                return (char) (SECURE_RANDOM.nextInt(26) + 65);
            default:
                return (char) (SECURE_RANDOM.nextInt(62) + 65);
        }
    }
}
