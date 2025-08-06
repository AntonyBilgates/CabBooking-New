package org.example;

import org.example.Controller.LogInController;
import org.example.Controller.SignInController;
import org.example.Service.BookingService;
import org.example.Model.DestinationModel;

import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CabBooking {

    public static void main(String[] args) throws ClassNotFoundException {
        final SignInController signIn = SignInController.getInstance();

        final LogInController logIn = new LogInController();
        final BookingService bookingDetails = new BookingService();
        final DestinationModel chooseDestination = new DestinationModel();

        try {
            final Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Fast🚖🚕 Cabs ");

            System.out.println("click the signIn page(New User) 1️⃣ or LogIn 2️⃣ or exit #️⃣ ");
            final int Entry = scanner.nextInt();
            scanner.nextLine();

            if (Entry == 1) {
                signIn.signIn(scanner, bookingDetails, chooseDestination);

                while (true) {
                    System.out.print("\nDo you want to book again? (yes/no): ");
                    final String again = scanner.nextLine();

                    if (!again.equalsIgnoreCase("yes")) {
                        System.out.println("Thank you for using Fast Cabs. Have a nice day!");
                        break;
                    }
                    chooseDestination.SelectDestination(scanner, bookingDetails, chooseDestination);
                }

            } else if (Entry == 2) {
                logIn.logIn(scanner,bookingDetails,chooseDestination);
            } else {
                System.out.println("Thank you visit again ");
            }

        } catch (InputMismatchException e) {
            System.out.println("Input is miss match ");
        }
    }
}