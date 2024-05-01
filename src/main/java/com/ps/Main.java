package com.ps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    Transaction transaction;
    static ArrayList<Transaction> transactions = new ArrayList<>();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userIn;
        Main main = new Main();
        readTransactions();

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
                                main.displayAllEntries(transactions);
                                break;
                            case "D":
                                ArrayList<Transaction> positiveTransactions = main.filterPositiveTransactions(transactions);
                                main.displayAllEntries(positiveTransactions);
                                break;
                            case "P":
                                ArrayList<Transaction> negativeTransactions = main.filterNegativeTransactions(transactions);
                                main.displayAllEntries(negativeTransactions);
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

    //Display all entries
    private static void displayAllEntries(ArrayList<Transaction> transactions) {
        System.out.println("Transactions: ");
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            System.out.println(transaction.getDate() + " " + transaction.getTime() + " " + transaction.getDescription() + " " + transaction.getVendor() + " " + "$" + transaction.getAmount());
        }
    }

    //Read transactions from transactions.txt
    public static void readTransactions() {
        try (BufferedReader bufReader = new BufferedReader(new FileReader("transactions.txt"))) {
            String line;
            while ((line = bufReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    LocalDate date = LocalDate.parse((parts[0].trim()));
                    LocalTime time = LocalTime.parse(parts[1].trim());
                    String description = (parts[2].trim());
                    String vendor = parts[3].trim();
                    float amount = Float.parseFloat(parts[4].trim());
                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }


    //Filter positive transactions
    private ArrayList<Transaction> filterPositiveTransactions(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> positiveTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                positiveTransactions.add(transaction);
            }
        }
        return positiveTransactions;
    }

    //Filter negative transactions
    private ArrayList<Transaction> filterNegativeTransactions(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> negativeTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                negativeTransactions.add(transaction);
            }
        }
        return negativeTransactions;
    }
}