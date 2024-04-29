package com.ps;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    ArrayList<Transaction> transactions;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userIn;

        //Home Screen
        do {
            System.out.println("Welcome. Please select an option: ");
            System.out.println("\tD) Add Deposit");
            System.out.println("\tP) Make Payment (Debit)");
            System.out.println("\tL) Ledger");
            System.out.println("\tX) Exit");
            userIn = scanner.nextLine().toUpperCase().trim();

            switch (userIn) {
                case "D":
                    break;
                case "P":
                    break;
                case "L": //Ledger menu
                    String commandLedger;

                    do {
                    System.out.println("Select an option: ");
                    System.out.println("\tA) All entries");
                    System.out.println("\tD) Deposits");
                    System.out.println("\tP) Payments");
                    System.out.println("\tR) Reports");
                    System.out.println("\tB) Back Home");
                    commandLedger = scanner.nextLine().toUpperCase().trim();

                    switch (commandLedger) {
                        case "A":
                            break;
                        case "D":
                            break;
                        case "P":
                            break;
                        case "R": //Reports menu
                            int commandReports;

                            do {
                                System.out.println("Select an option: ");
                                System.out.println("\t1) Month To Date");
                                System.out.println("\t2) Previous Month");
                                System.out.println("\t3) Year To Date");
                                System.out.println("\t4) Previous Date");
                                System.out.println("\t5) Search by Vendor");
                                System.out.println("\t0) Back");
                                commandReports = scanner.nextInt();

                                switch (commandReports) {
                                    case 1:
                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                        break;
                                    case 4:
                                        break;
                                    case 5:
                                        break;
                                    case 0:
                                        break;
                                    default:
                                        System.out.println("Command not found.");
                                }

                            } while (commandReports != 0);
                            break;
                        case "B":
                            break;
                        default:
                            System.out.println("Command not found.");
                    }
                    } while (!commandLedger.equalsIgnoreCase("B"));
                    break;
                case "X":
                    System.out.println("Exiting....");
                    break;
                default:
                    System.out.println("Command not found.");

            }

        } while (!userIn.equalsIgnoreCase("X")); //Ends loop and exits application
    }
}