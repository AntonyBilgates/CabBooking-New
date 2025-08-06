package org.example.Service;

import org.example.Model.DestinationModel;
import org.example.Payment.CashPayment;
import org.example.Payment.CreditCardPayment;
import org.example.Payment.Payment;
import org.example.Payment.UpiId;
import org.example.Model.TravellerModel;

import java.util.Scanner;

public final class BookingService {

    private final Payment upiId = new UpiId();
    private final Payment creditCard = new CreditCardPayment();
    private final Payment cash = CashPayment.getInstance();

    public void showBookingDetails(final Scanner scanner, final TravellerModel traveller, final DestinationModel destination) {
        System.out.println("\n------ Traveller Details ------");
        System.out.printf("Name     : %s%n", traveller.getName());
        System.out.printf("Gender   : %s%n", traveller.getGender());
        System.out.printf("Phone No : %s%n", traveller.getPhoneNo());
        System.out.printf("Email    : %s%n", traveller.getEmail());

        System.out.println("\n------ Booking Summary ------");
        System.out.printf("Route      : %s%n", destination.getDestination());
        System.out.printf("Distance   : %d km%n", destination.getDistance());
        System.out.printf("Total Cost : ₹%d%n", destination.getPrice());

        confirmBooking(scanner, destination);
    }

    private void confirmBooking(final Scanner scanner, final DestinationModel destination) {
        System.out.print("\nConfirm Booking (Yes/No): ");
        final String confirmBooking = scanner.nextLine();

        if (confirmBooking.equalsIgnoreCase("yes")) {
            System.out.println("\nPayment process initiated...");

            boolean flag = true;
            while (flag) {
                System.out.println("Choose payment method :\n1. UPI\n2. Card\n3. Cash");
                final int paymentOption = scanner.nextInt();
                scanner.nextLine();

                switch (paymentOption) {
                    case 1 -> {
                        upiId.pay(scanner, destination);
                        flag=false;
                    }
                    case 2 -> {
                        creditCard.pay(scanner, destination);
                        flag=false;
                    }
                    case 3 -> {
                        cash.pay(scanner, destination);
                        flag=false;
                    }
                    default -> System.out.print("not valid . please try again ");
                }
            }
        }
    }
}
