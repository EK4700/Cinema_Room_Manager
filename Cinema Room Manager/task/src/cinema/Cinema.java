package cinema;

import java.util.*;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        String[][] cinema; 
        int r = -1;
        int c = -1;
        String menu = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = sc.nextInt();
        System.out.println("Enter the number of seats in each row");
        int col = sc.nextInt();
        sc.nextLine();
        cinema = fill(row, col);
        
        
        
        // Menu Options
        while (!menu.equals("0")) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            menu = sc.nextLine();
            if (menu.equals("1")) {
                outputer(cinema, r, c);
            } else if (menu.equals("2")) {
                System.out.println("Enter a row number:");
                r = sc.nextInt();
                System.out.println("Enter a seat number in that row:");
                c = sc.nextInt();
                while (r < 0 || r > row || c > col || c < 0 || cinema[r][c].equals("B")) {
                    if (r <= row && c <= col && r >= 0 && c >= 0 && cinema[r][c].equals("B")) {
                        System.out.println("That ticket has already been purchased!");
                    } else {
                        System.out.println("Wrong input!");
                    }
                    
                    System.out.println("Enter a row number:");
                    r = sc.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    c = sc.nextInt();
                }

                sc.nextLine();
                int price = getPrice(r, c, row, col);
                cinema[r][c] = "B";

                System.out.println("Ticket price: $" + price);
                //outputer(row, col, r, c);
            } else if (menu.equals("3")) {
                getStatistics(cinema);
            }
        }
    }
    

    private static void getStatistics(String[][] arr) {
        /*
            The number of purchased tickets;
            The number of purchased tickets represented as a percentage. Percentages should be rounded to 2 decimal places;
            Current income;
            The total income that shows how much money the theatre will get if all the tickets are sold.
            Number of purchased tickets: 1
            Percentage: 2.78%
            Current income: $10
            Total income: $360
        */
        int numTickets = 0;
        int currIncome = 0;
        int totalIncome = 0;
        
        int row = arr.length - 1;
        int col = arr.length - 1;
        
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (arr[i][j].equals("B")) {
                    numTickets++;
                    int price = getPrice(i, j, row, col);
                    currIncome += price;
                }
            }
        }
        
        int numberOfSeats = row * col;
        
        if (numberOfSeats <= 60) {
            totalIncome = numberOfSeats * 10;
        } else {
            totalIncome = row / 2 * 10 * col + (row - row /2) * 8 * col;
        }
        
        System.out.println("Number of purchased tickets: " + numTickets);
        System.out.println(String.format("Percentage: %.2f%%", (double) numTickets * 100 / numberOfSeats));
        System.out.println("Current income: $" + currIncome);
        System.out.println("Total income: $" + totalIncome);
        
    }
    
    private static String[][] fill(int row, int col) {
         String[][] cinema = new String[row + 1][col + 1];
        
        for (int i = 0; i <= col; i++) {
            cinema[0][i] = "" + (i == 0 ? " " : i);
        }
        
        for (int i = 1; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if (j == 0) {
                    cinema[i][j] = "" + i;
                } else {
                    cinema[i][j] = "S";
                }
            }
        }
        
        return cinema;
    }
    
    private static void outputer(String[][] cinema, int r, int c) {
        if (r == -1) {
            System.out.println("Cinema:");
            for (int i = 0; i < cinema.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < cinema[i].length; j++) {
                    if (j != 0) {
                        sb.append(" ");
                    }
                    sb.append(cinema[i][j]);
                }
                System.out.println(sb.toString());
            }
        } else {
            cinema[r][c] = "B";
            outputer(cinema, -1, -1);
        }
    }
    
    private static int getPrice(int r, int c, int row, int col) {
        int numberOfSeats = row * col;
        int price = 0;
        
        if (numberOfSeats <= 60) {
            price = 10;
        } else {
            if (r <= row / 2) {
                price = 10;
            } else {
                price = 8;
            }
        }
        return price;
    }
    
    
}