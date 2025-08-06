package org.example.Payment;

import org.example.Model.DestinationModel;

import java.util.Scanner;

public interface Payment {

    void pay(Scanner scanner, DestinationModel chooseDestination);
}

