import java.util.ArrayList;
import java.util.Scanner;

public class KinoVerwaltungV1FinalVersion {

    public static void main(String[] args) {
        final int ticketPrice = 15;
        Scanner sc = new Scanner(System.in);

        String[][] cinemaData = {
                {"1", "Batman", "20:15", "1", "5"},
                {"2", "Matrix", "22:00", "2", "2"},
                {"3", "Matrix2", "17:00", "3", "0"},
                {"4", "Matrix2A", "19:00", "3", "10"},
        };

        //User Budget Input
        int userBudget;

        //User can only input more than the price of the ticket
        do {
            System.out.println("Wie viel Geld hast du dabei? Bitte gib einen positiven Betrag ein: ");

            userBudget = sc.nextInt();

            //warning when the user enters an invalid number
            if (userBudget < ticketPrice && userBudget > 0) {
                System.out.println("Ihr Geld reicht nicht aus, ein Ticket kostet " + ticketPrice + "€.");
            } else if (userBudget <= 0) {
                System.out.println("Sie haben eine ungültige Nummer eingegeben.");
            } else if (userBudget > 500) {
                System.out.println("Für eine einmalige Transaktion akzeptieren wir nur bis zu 500 Euro.");
            }


        } while (userBudget < ticketPrice || userBudget > 500);

        int movieChoice; // user choice of Movie

        ArrayList<Integer> chosenMovies = new ArrayList<>(); // Arraylist of Chosen Movie

        do {
            // print the Kino Board
            System.out.println("Filmnr   Filmname        Uhrzeit    Saal     Restplätze");
            System.out.println("--------------------------------------------------------------");

            String ticketStatus;
            int ticketAvailable;

            for (String[] cinemaDatum : cinemaData) {

                ticketAvailable = Integer.parseInt(cinemaDatum[4]);

                if (ticketAvailable > 0) {
                    ticketStatus = "verfügbar";
                } else {
                    ticketStatus = "ausgebucht";
                }

                System.out.printf("%-8s %-15s %-10s %-8s %-10s\n",
                        cinemaDatum[0] + ".", cinemaDatum[1], cinemaDatum[2], cinemaDatum[3], ticketStatus);
            }

            System.out.println("--------------------------------------------------------------");

            //user Input for Movie Choice
            System.out.println("Welchen (nicht ausgebuchten) Film möchtest du sehen 1 - " + cinemaData.length + " ? (0 zum abbrechen)");
            movieChoice = sc.nextInt();

            if (movieChoice < 0) {
                System.out.println("Sie haben eine ungültige Nummer eingegeben.");
            } else if (movieChoice > cinemaData.length) {
                System.out.println("Derzeit werden nur " + cinemaData.length + " Filme vorgeführt.");
            } else if (movieChoice == 0) {
                System.out.print("Ticketverkauf endet... Danke für Ihren Besuch.\n");
            }

            //if movieChoice between the choices only 1 - to array length this will run
            if (movieChoice > 0 && movieChoice <= cinemaData.length) {

                ticketAvailable = Integer.parseInt(cinemaData[movieChoice - 1][cinemaData[0].length - 1]); // to update the current ticket Availability

                System.out.println("ticketAvailable: " + ticketAvailable);

                if (ticketAvailable == 0) {
                    System.out.println("\nIhr ausgewählter Film " + movieChoice + " ist leider vollständig ausgebucht.");
                }

                if (ticketAvailable > 0) {
                    int ticketCount; //the number of tickets the user entered for a certain movie
                    int totalTicketCost; //the total amount of the purchased Tickets

                    do {
                        System.out.println("Es sind noch " + ticketAvailable + " Tickets um jeweils " + ticketPrice + "€ dafür verfügbar. Wie viele möchtest du kaufen?");
                        ticketCount = sc.nextInt();

                        totalTicketCost = ticketCount * ticketPrice;
                        int ticketsWithinBudget = userBudget / ticketPrice; // to determine how many tickets the user can buy still

                        //edge cases warning for amount of tickets input
                        if (ticketCount <= 0) {
                            System.out.println("Sie haben eine ungültige Nummer eingegeben.");
                        } else if (ticketCount > ticketAvailable) {
                            System.out.println("Sie haben mehr Tickets eingegeben, als verfügbar sind.");
                        } else if (totalTicketCost > userBudget) {
                            System.out.println(
                                    "Gesamtkosten von " + ticketCount + " Tickets: " + totalTicketCost + "\nSie haben nur " + userBudget +
                                            "€. Sie können nur noch " + ticketsWithinBudget + " Tickets kaufen. Bitte geben Sie eine geringere Anzahl an Tickets ein."
                            );
                        }

                    } while (ticketCount > ticketAvailable || ticketCount <= 0 || totalTicketCost > userBudget);

                    //to add chosen Movies to the chosenMovies Arraylist
                    for (int i = 0; i < ticketCount; i++) {
                        chosenMovies.add(movieChoice);
                    }

                    ticketAvailable -= ticketCount; //update the available ticket
                    cinemaData[movieChoice - 1][cinemaData[0].length - 1] = String.valueOf(ticketAvailable); //updates the monitor if ausgebucht or verfügbar
                    totalTicketCost = ticketCount * ticketPrice;
                    userBudget -= totalTicketCost; //update the remaining user Budget

                    System.out.println("Sie kaufen " + ticketCount + " Tickets um " + totalTicketCost + "€ und hast jetzt noch " + userBudget + "€.");
                }
            }
            //shows the actual user Credit and the movies Chosen
            System.out.println("==========================");
            System.out.println("Aktuelles Guthaben: " + userBudget + "€");
            System.out.println("Gekaufter Tickets: " + chosenMovies);
            System.out.println("==========================\n");

            if (userBudget < ticketPrice) {
                System.out.println("--------------------------------------------------------------");
                System.out.println(" Sie haben nicht genug Guthaben für weiteren Einkauf.\n Vielen Dank für den Ticketkauf. Ticketverkauf endet.. ");
                movieChoice = 0; // this will exit the ticketverkauf
                System.out.println("--------------------------------------------------------------");
            }

        } while (movieChoice != 0);
    }
}

