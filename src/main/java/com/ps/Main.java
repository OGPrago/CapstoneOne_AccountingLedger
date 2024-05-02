package com.ps;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
                    main.addTransaction();
                    break;
                case "P":
                    main.addTransaction();
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
                                displayAllEntries(transactions);
                                break;
                            case "D":
                                ArrayList<Transaction> positiveTransactions = main.filterPositiveTransactions(transactions);
                                displayAllEntries(positiveTransactions);
                                break;
                            case "P":
                                ArrayList<Transaction> negativeTransactions = main.filterNegativeTransactions(transactions);
                                displayAllEntries(negativeTransactions);
                                break;
                            case "R": //Reports menu
                                int commandReports;

                                do {
                                    System.out.println("Select an option: ");
                                    System.out.println("\t1) Month To Date");
                                    System.out.println("\t2) Previous Month");
                                    System.out.println("\t3) Year To Date");
                                    System.out.println("\t4) Previous Year");
                                    System.out.println("\t5) Search by Vendor");
                                    System.out.println("\t0) Back");
                                    commandReports = scanner.nextInt();

                                    switch (commandReports) {
                                        case 1:
                                            ArrayList<Transaction> monthToDateTransactions = main.filterByMonthToDate(transactions);
                                            displayAllEntries(monthToDateTransactions);
                                            break;
                                        case 2:
                                            ArrayList<Transaction> filterByPreviousMonth = main.filterByPreviousMonth(transactions);
                                            displayAllEntries(filterByPreviousMonth);
                                            break;
                                        case 3:
                                            ArrayList<Transaction> yearToDateTransactions = main.filterByYearToDate(transactions);
                                            main.displayAllEntries(yearToDateTransactions);
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

    //Write transactions to transactions.txt that the user enters
    private void addTransaction() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter transaction details:");
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Amount: ");
        float amount = scanner.nextFloat();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt", true));
            LocalDate currentDate = LocalDate.now();
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            String transactionLine = String.format("%s|%s|%s|%s|%.2f%n",
                    currentDate.format(dateFormatter),
                    currentTime.format(timeFormatter),
                    description,
                    vendor,
                    amount);

            writer.write(transactionLine);
            System.out.println("Transaction recorded successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    //Filter transactions by Month to Date
    private ArrayList<Transaction> filterByMonthToDate(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> filteredTransactions = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        LocalDate firstDayOfMonth = LocalDate.of(currentDate.getYear(), currentDate.getMonthValue(), 1);

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();
            if (!transactionDate.isBefore(firstDayOfMonth) && !transactionDate.isAfter(currentDate)) {
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    //Filter transactions by previous month
    private ArrayList<Transaction> filterByPreviousMonth(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> filteredTransactions = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();

        LocalDate firstDayOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(1);

        LocalDate lastDayOfPreviousMonth = currentDate.minusMonths(1).withDayOfMonth(currentDate.minusMonths(1).lengthOfMonth());

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();
            if (!transactionDate.isBefore(firstDayOfPreviousMonth) && !transactionDate.isAfter(lastDayOfPreviousMonth)) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }

    //Filter transactions by year to date
    private ArrayList<Transaction> filterByYearToDate(ArrayList<Transaction> transactions) {
        ArrayList<Transaction> filteredTransactions = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();

        LocalDate firstDayOfCurrentYear = LocalDate.of(currentDate.getYear(), 1, 1);

        for (Transaction transaction : transactions) {
            LocalDate transactionDate = transaction.getDate();
            if (!transactionDate.isBefore(firstDayOfCurrentYear) && !transactionDate.isAfter(currentDate)) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }
}