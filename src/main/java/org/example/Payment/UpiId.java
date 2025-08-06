package org.example.Payment;

import org.example.Model.DestinationModel;

import java.util.Scanner;

public final class UpiId implements Payment {
    public void pay(final Scanner scanner, final DestinationModel chooseDestination) {
        System.out.print("Enter UPI ID: ");
        final String upiId = scanner.nextLine();
        System.out.printf("total Amount : %d " , chooseDestination.getPrice());
        System.out.printf("\nProcessing UPI payment to  %s ..... " , upiId  );
    }
}
