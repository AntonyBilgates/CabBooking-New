package org.example.Controller;

import org.example.DataBase.DBConnection;
import org.example.Service.BookingService;
import org.example.Model.DestinationModel;

import java.util.Scanner;

public final class LogInController {

    private final DBConnection dbConnection = new DBConnection();

    public void logIn(final Scanner scanner, final BookingService bookingDetails,
                      final DestinationModel chooseDestination) {

        System.out.println(" ---login page--- ");

        while (true) {
            System.out.print("🧑‍💼 Username : ");
            final String userName = scanner.nextLine();

            System.out.print("🔑 Password : ");
            final String password = scanner.nextLine();

            if (dbConnection.checkCredentials(userName,password)){
                System.out.printf("%s %s%n " ,"Login successful " , userName);
                chooseDestination.SelectDestination(scanner, bookingDetails, chooseDestination);
                break;
            }
            else{
                System.out.println("Not valid userName and Password ");
            }
        }
    }
}
