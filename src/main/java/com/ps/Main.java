package com.ps;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userIn;
        do {
            System.out.println("Welcome. Please select an option: ");
            System.out.println("\tD) Add Deposit");
            System.out.println("\tP) Make Payment (Debit)");
            System.out.println("\tL) Ledger");
            System.out.println("\tX) Exit");
            userIn = scanner.nextLine().toUpperCase();

            switch (userIn) {
                case "D":
                    break;
                case "P":
                    break;
                case "L":
                    break;
                case "X":
                    System.out.println("Exiting....");
                    break;
                default:
                    System.out.println("Command not found");

            }

        } while (!userIn.equalsIgnoreCase("X"));
    }
}