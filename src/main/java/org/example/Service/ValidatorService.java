package org.example.Service;

import java.util.regex.Pattern;

public final class ValidatorService {

    private static final ValidatorService INSTANCE = new ValidatorService();

    private ValidatorService() {

    }

    public static ValidatorService getInstance() {
        return INSTANCE;
    }

    public boolean isValidPassword(final String password) {
        if (password == null || password.isEmpty()) return false;
        final String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$&]).{8,}$";
        return Pattern.matches(passwordRegex, password);
    }

    public boolean isValidEmail(final String email) {
        if (email == null || email.isEmpty()) return false;
        final String emailRegex = "^[A-Za-z0-9+_.-]+@gmail\\.com$";
        return Pattern.matches(emailRegex, email);
    }
}
