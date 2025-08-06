package org.example.Controller;

import org.example.DataBase.DBConnection;
import org.example.Model.DestinationModel;
import org.example.Service.BookingService;
import org.example.Service.ValidatorService;

import java.util.Scanner;

public final class SignInController {

    private static final SignInController INSTANCE = new SignInController();

    private final ValidatorService passwordValidCheck = ValidatorService.getInstance();
    private final LogInController logIn = new LogInController();
    private final DBConnection dbConnection = new DBConnection();


    private SignInController() {

    }

    public static SignInController getInstance() {

        return INSTANCE;
    }


    public void signIn(final Scanner scanner, final BookingService bookingDetails, final DestinationModel chooseDestination) throws ClassNotFoundException {

        while (true) {
            System.out.print("🧑‍💼 Enter the name : ");
            final String userName = scanner.next();
            System.out.print("🔑 Enter the password : ");
            final String password = scanner.next();
            System.out.print("📩 Enter the Email : ");
            final String email = scanner.next();
            scanner.nextLine();

            if (!passwordValidCheck.isValidPassword(password)) {
                System.out.println("Password must contain one capital letter, one number, and one special character.");
                continue;
            }
            if (!passwordValidCheck.isValidEmail(email)) {
                System.out.println("Enter a valid email.");
                continue;
            }

            boolean inserted = dbConnection.insertUserIntoDB(userName, password, email);
            if (inserted) {
                System.out.println("SignIn SuccessFull.");
                break;
            } else {
                System.out.println("Failed to save user. Try again.");
            }

        }

        logIn.logIn(scanner, bookingDetails, chooseDestination);
    }
}
