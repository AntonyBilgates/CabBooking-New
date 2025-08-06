package org.example.Model;

import org.example.Controller.CabController;
import org.example.DataBase.DBConnection;
import org.example.Service.BookingService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class DestinationModel {

    private final CabController cabTypes = new CabController();
    private String destination;
    private int price;
    private int distance;

    public void SelectDestination(final Scanner scanner, final BookingService bookingDetails, final DestinationModel chooseDestination) {
        final DBConnection databaseConnection = new DBConnection();

        System.out.println("\nWe are ready with a ride for you 🚖, select the Destination ✈️");

        while (true) {
            System.out.print("\nFrom : ");
            final String from = scanner.nextLine().trim();
            System.out.print("To   : ");
            final String to = scanner.nextLine().trim();

            this.destination = String.format("%s-%s", capitalize(from), capitalize(to));

            // Fetch distance and price from DB
            int[] details = databaseConnection.getRouteDetails(from,to);

            if (details != null) {
                this.distance = details[0];
                this.price = details[1];

                cabTypes.cabTypes(scanner, bookingDetails, chooseDestination);
                break;
            } else {
                System.out.println("\nCurrently not available for this route.");
                System.out.println("Please try again with one of the valid routes in the database.");
            }
        }
    }

    public String getDestination() {
        return destination;
    }

    public int getPrice() {
        return price;
    }

    public int getDistance() {
        return distance;
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}






