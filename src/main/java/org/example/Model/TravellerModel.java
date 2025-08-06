package org.example.Model;

import org.example.Service.ValidatorService;

import java.util.Scanner;

public final class TravellerModel {

    private String name;
    private String gender;
    private String phoneNo;
    private String email;

    public TravellerModel() {

    }

    public void collectFromUser(final Scanner scanner) {
        final ValidatorService validator = ValidatorService.getInstance();

        System.out.println("\n--- Enter Traveller Details ---");

        // Name
        while (true) {
            System.out.print("Name: ");
            name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                break;
            }
            System.out.println("Name cannot be empty.");
        }

        // Gender
        while (true) {
            System.out.print("Gender (Male/Female): ");
            gender = scanner.nextLine();
            if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) {
                break;
            }
            System.out.println("Please enter Male or Female.");
        }

        // Phone Number
        while (true) {
            System.out.print("Phone No: ");
            phoneNo = scanner.nextLine();
            if (phoneNo.matches("\\d{10}")) break;
            System.out.println("Invalid phone number. Must be 10 digits.");
        }

        // Email
        while (true) {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (validator.isValidEmail(email)) {
                break;
            }
            System.out.println("Invalid email. Must be a Gmail address.");
        }

    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmail() {
        return email;
    }

}

