package org.example.Payment;

import org.example.Model.DestinationModel;

import java.util.Scanner;

public final class CashPayment implements Payment {

    private static final CashPayment instance = new CashPayment();

    private CashPayment() {

    }

    public static CashPayment getInstance() {
        return instance;
    }

    @Override
    public void pay(final Scanner scanner, final DestinationModel chooseDestination) {
        System.out.printf("Please pay ₹%d in cash to the driver. ", chooseDestination.getPrice());
    }
}
