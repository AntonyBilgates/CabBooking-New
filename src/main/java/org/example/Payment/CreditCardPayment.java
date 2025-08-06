package org.example.Payment;

import org.example.Model.DestinationModel;

import java.util.Scanner;

public final class CreditCardPayment implements Payment {

    public void pay(Scanner scanner, DestinationModel chooseDestination) {
        System.out.print("Enter Card Number: ");
        final String cardNumber = scanner.nextLine();
        System.out.printf("total Amount : %d " , chooseDestination.getPrice());
        System.out.printf("\nProcessing card payment for card ending with  %s ..... " ,
                cardNumber.substring(cardNumber.length() - 4)  );
    }
}

