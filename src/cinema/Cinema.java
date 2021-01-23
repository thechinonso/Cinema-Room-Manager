package cinema;

import java.util.Scanner;

public class Cinema {
    public static Scanner scanner = new Scanner(System.in);
    static int row;
    static int seat;
    static int choice;
    static int price;
    static String[][] seats;

    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seat = scanner.nextInt();
        seatArray();

        do {
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            choice = scanner.nextInt();
        } while (takeAction(choice));
    }


    public static boolean takeAction(int choice) {
        switch (choice) {
            case 1:
                showTheSeats();
                break;
            case 2:
                buyATicket();
                break;
            case 3:
                checkStats();
                break;
            case 0:
                return false;
            default:
                System.out.println("invalid input");
                break;
        }
        return true;
    }

    public static void seatArray() {
        seats = new String[row + 1][seat + 1];
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= seat; j++) {
                if (i == 0 && j == 0) {
                    seats[i][j] = " ";
                } else if (i == 0) {
                    seats[i][j] = String.valueOf(j);
                } else if (j == 0) {
                    seats[i][j] = String.valueOf(i);
                } else {
                    seats[i][j] = "S";

                }
            }

        }
    }

    public static void showTheSeats() {
        System.out.println(
                "Cinema:");
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= seat; j++) {
                System.out.print(" " + seats[i][j]);
            }
            System.out.println();

        }

    }

    public static void checkStats() {
        int count = 0;
        int income;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= seat; j++) {
                if (seats[i][j].equals("B")) {
                    count++;
                }
            }
        }



        if (row * seat <= 60) {
            income = (row * seat * 10);
        } else {
            if (row % 2 == 0) {
                income = ((row / 2) * seat * 10) + ((row / 2) * seat * 8);
            } else income = (((row - 1) / 2) *seat* 10) + (((row + 1) / 2) *seat* 8);
        }

        double percent = (((float) (count)) / (float) (row * seat)) * 100.0;
        System.out.println("Number of purchased tickets: " + count);
        System.out.printf("Percentage: %.2f%s", percent, "%");
        System.out.println();
        System.out.println("Current income: $" + price);
        System.out.println("Total income: $" + income);

    }


    public static void buyATicket() {
        int pickRow;
        int pickSeat;
        boolean check;

        do {
            System.out.println("Enter a row number:");
            pickRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            pickSeat = scanner.nextInt();

            check = ((pickRow > row || pickSeat > seat) || (seats[pickRow][pickSeat].equals("B")));

            if (pickRow > row || pickSeat > seat) {
                System.out.println("Wrong input!");
            } else if (seats[pickRow][pickSeat].equals("B")) {
                System.out.println("That ticket has already been purchased!");
            }
        } while (check);

        if (row * seat <= 60) {
            System.out.println("Ticket price: $" + 10);
            price+=10;
        } else {
            if (row % 2 == 0) {
                if (pickRow <= row / 2) {
                    System.out.println("Ticket price: $" + 10);
                    price+=10;
                } else {
                    System.out.println("Ticket price: $" + 8);
                    price+=8;
                }
            } else {
                if (pickRow <= (row - 1) / 2) {
                    System.out.println("Ticket price: $" + 10);
                    price+=10;
                } else {
                    System.out.println("Ticket price: $" + 8);
                    price+=8;
                }
            }
        }

        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= seat; j++) {
                if (i == pickRow && j == pickSeat) {
                    seats[i][j] = ("B");
                }

            }
        }
    }
}