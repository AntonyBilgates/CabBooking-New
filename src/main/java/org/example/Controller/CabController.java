package org.example.Controller;

import org.example.DataBase.DBConnection;
import org.example.Model.CabModel;
import org.example.Model.TravellerModel;
import org.example.Service.BookingService;
import org.example.Model.DestinationModel;


import java.util.Map;
import java.util.Scanner;

public final class CabController {

    private final TravellerModel traveller = new TravellerModel();
    private final Map<Integer, CabModel> cabOptions ;

    public CabController() {
        this.cabOptions = new DBConnection().loadCabsFromDatabase();
    }

    public void cabTypes(final Scanner scanner, final BookingService bookingDetails, final DestinationModel chooseDestination) {
        while (true) {
            System.out.println("\n--- Available Cab Types ---");
            for (Map.Entry<Integer, CabModel> entry : cabOptions.entrySet()) {
                System.out.printf("%d. %s\n", entry.getKey(), entry.getValue().getCabName());
            }

            System.out.print("\nSelect a cab by number: ");
            final int selected = scanner.nextInt();
            scanner.nextLine();

            final CabModel selectedCab = cabOptions.get(selected);
            if (selectedCab == null) {
                System.out.println("Invalid selection. Try again.");
                continue;
            }

            System.out.println("\n--- Cab Details ---");
            System.out.println("Cab Name     : " + selectedCab.getCabName());
            System.out.println("AC Available : " + (selectedCab.isAC() ? "Yes" : "No"));
            System.out.println("Seats        : " + selectedCab.getSeats());
            System.out.println("Available    : " + selectedCab.getAvailableCount());

            if (selectedCab.getAvailableCount() == 0) {
                System.out.println("Sorry, this cab is fully booked. ");
                System.out.print("Do you want to try another cab? (yes/no): ");
                if (!scanner.nextLine().equalsIgnoreCase("yes")) {
                    System.out.println("Thank you for visiting. Come back later! ");
                    break;
                }
                continue;
            }

            System.out.print("\nDo you want to book this cab? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                if (selectedCab.book()) {
                    System.out.println("Booking confirmed! ");
                    traveller.collectFromUser(scanner);
                    bookingDetails.showBookingDetails(scanner, traveller, chooseDestination);
                } else {
                    System.out.println("Booking failed. Try again. ");
                }
            } else {
                System.out.println("Booking cancelled.");
            }

            System.out.print("\nDo you want to book another cab? (yes/no): ");
            if (!scanner.nextLine().equalsIgnoreCase("yes")) {
                System.out.println("Thank you! Safe travels.");
                break;
            }
        }
    }
}

