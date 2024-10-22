import java.util.*;

public class KinoVerwaltungV2Version2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final String adminEntry = "admin1234";
        final int pin = 5555;
        final int rafflePrice = 5;

        String[][] cinemaData = {
                {"1", "Batman", "20:15", "1", "14.25 ", "5"},
                {"2", "Matrix", "22:00", "2", "12.50", "2"},
                {"3", "Inception", "17:00", "3", "9.99", "0"},
                {"4", "Forest Gump", "19:00", "4", "10.99", "10"},
                {"5", "Garfield 3D", "21:00", "5", "16.99", "7"},
        };

        ArrayList<Integer> chosenMovies = new ArrayList<>(); //array list of bought tickets

        double cheapestTicketPrice = 1000; //initialize with a high value so that it won't affect the comparison on the loop
        double ticketPrice; //price of cinema Tickets
        int ticketAvailable;

        //to determine the cheapest movie but exclude the fully booked ones
        for (int i = 0; i < cinemaData.length; i++) {

            ticketPrice = Double.parseDouble(cinemaData[i][cinemaData[i].length - 2]);
            ticketAvailable = Integer.parseInt(cinemaData[i][cinemaData[i].length - 1]);


            if (ticketPrice < cheapestTicketPrice && ticketAvailable != 0) {
                cheapestTicketPrice = ticketPrice;
            }


        }

        double userBudget;
        boolean transactionCompleted = false; // admin possible way of restarting the program
        String userInput;


        while (!transactionCompleted) {

            System.out.println("-----------------------------------------------");
            System.out.println("         **** Willkommen bei KinoPlexx ***");
            System.out.println("-----------------------------------------------\n");

            System.out.println("Wie viel Geld hast du dabei? Bitte gib einen positiven Betrag ein: ");
            userInput = sc.nextLine();


            //admin Area
            if (userInput.equals(adminEntry)) {
                System.out.println("-----------------------------------");
                System.out.println("  ** Willkommen auf der Admin-Seite!! **");
                System.out.println("-----------------------------------");

                System.out.println("\nGeben Sie Ihren 4-stelligen PIN-Code ein");

                int pinCode = 0;

                while (pinCode != pin) {
                    pinCode = sc.nextInt();

                    if (pinCode == pin) {
                        System.out.println("\n*** Anmeldung erfolgreich! ***\n");
                    } else {
                        System.out.println("Bitte versuchen Sie es erneut. Geben Sie Ihren 4-stelligen PIN-Code ein.");
                    }
                }


                char adminEdit;
                boolean isAdmin = false;

                do {
                    System.out.println("\"Möchten Sie einen Film bearbeiten? Geben Sie [y/n] ein?");
                    System.out.println("Programm neu zu starten.? Geben Sie [b] ein, um das Programm herunterzufahren.");
                    adminEdit = sc.next().charAt(0);

                    sc.nextLine();

                } while (adminEdit != 'n' && adminEdit != 'y' && adminEdit != 'b');

                if (adminEdit == 'b') {
                    System.out.println("Das Programm wird heruntergefahren");
                    transactionCompleted = true;
                }

                if (adminEdit == 'y') {
                    isAdmin = true;
                }

                while (isAdmin) {
                    //admin view of the cinema board
                    System.out.printf("\n%-12s %-20s %-10s %-6s %-10s %-15s%n",
                            "Film Number", "Movie Title", "Time", "Hall", "Price", "Available Seats");
                    System.out.println("----------------------------------------------------------------------");

                    for (String[] movie : cinemaData) {
                        System.out.printf("%-12s %-20s %-10s %-6s %-10s %-18s%n",
                                movie[0], movie[1], movie[2], movie[3], movie[4], movie[5]);
                    }
                    System.out.println("----------------------------------------------------------------------\n");

                    System.out.println("Welchen Film möchten Sie bearbeiten? Wählen Sie zwischen 1 und " + cinemaData.length);
                    int movieID = sc.nextInt();

                    //edge case warning for movieID
                    if (movieID <= 0) {
                        System.out.println("Sie haben eine ungültige Nummer eingegeben.");
                    } else if (movieID > cinemaData.length) {
                        System.out.println("Derzeit werden nur " + cinemaData.length + " Filme vorgeführt.");
                    }

                    //if movieID is within the range
                    if (movieID >= 0 && movieID <= cinemaData.length) {
                        System.out.println("\nSie bearbeiten gleich Film Nr. " + movieID + "\n");

                        String[] movieDetails = cinemaData[movieID - 1];

                        System.out.println("1. Filmtitel: " + movieDetails[1]);
                        System.out.println("2. Uhrzeit: " + movieDetails[2]);
                        System.out.println("3. Saal: " + movieDetails[3]);
                        System.out.println("4. Preis: " + movieDetails[4]);
                        System.out.println("5. Restplätze: " + movieDetails[5]);


                        int movieDetailsToEdit;


                        do {
                            System.out.println("Bitte wählen Sie, welche Filmdetails Sie bearbeiten möchten (Geben Sie eine Zahl zwischen 1 und 5 ein, z.B. 1 für den Filmtitel.");
                            movieDetailsToEdit = sc.nextInt();


                            switch (movieDetailsToEdit) {
                                case 1:
                                    System.out.println("Geben Sie den Filmtitel ein.");
                                    sc.nextLine();
                                    String movieTitle = sc.nextLine();
                                    cinemaData[movieID - 1][1] = movieTitle;
                                    break;

                                case 2:
                                    System.out.println("Geben Sie die Uhrzeit ein");
                                    cinemaData[movieID - 1][2] = sc.next();
                                    break;

                                case 3:
                                    System.out.println("Geben Sie die Saalnummer ein.");
                                    cinemaData[movieID - 1][3] = sc.next();
                                    break;

                                case 4:
                                    System.out.println("Geben Sie den Ticketpreis ein.");
                                    cinemaData[movieID - 1][4] = String.format(Locale.US, "%.2f", Double.parseDouble(sc.next()));

                                    break;

                                case 5:
                                    System.out.println("Geben Sie die Anzahl der verfügbaren Sitzplätze ein:");
                                    cinemaData[movieID - 1][5] = sc.next();
                                    break;


                                default:
                                    System.out.println("Sie haben eine ungültige Wahl getroffen. Bitte wählen Sie eine Zahl zwischen 1 und 5.");

                            }

                            System.out.println("Sie haben den Film erfolgreich bearbeitet. Filmnummer " + movieDetailsToEdit + ": " + Arrays.deepToString(cinemaData[movieID - 1]));


                        } while (movieDetailsToEdit <= 0 || movieDetailsToEdit > 5);

                        System.out.println("\nMöchten Sie weitere Filmdetails bearbeiten? [y/n]");
                        adminEdit = sc.next().charAt(0);

                        sc.nextLine(); // added to consume the leftover newline character after next

                        if (adminEdit == 'n') {
                            System.out.println("\n****** Exiting the admin page... *******\n");
                            isAdmin = false;
                        }
                    }

                }

            } //end of admin Area

            boolean isNumber = true; // to determine if the input is only numbers //admin can input string
            boolean foundDecimal = false;

            for (int i = 0; i < userInput.length() && isNumber; i++) {

                if (userInput.charAt(i) >= '0' && userInput.charAt(i) <= '9') { //alternative checking (Character.isDigit(userInput.charAt(i)))
                    isNumber = true;
                } else if (userInput.charAt(i) == '.' && !foundDecimal) {
                    foundDecimal = true;
                } else if (userInput.charAt(i) == '-') {
                    System.out.println("Bitte geben Sie einen gültigen Betrag ein.");
                    isNumber = false;
                } else {
                    isNumber = false;
                }
            }

            if (isNumber) {
                userBudget = Double.parseDouble(userInput); // Parse the input as a double
                //String formattedUserBudget = String.format(Locale.US, "%.2f", userBudget);


                // Check if the amount is positive
                if (userBudget >= rafflePrice) {


                    boolean menuOption = false; //to exit from menuOption

                    do {

                        System.out.println("\nWas möchten Sie als Nächstes tun? Bitte wählen Sie eine der folgenden Optionen:");

                        String menu = """
                                ------------------------------
                                       1. Ticket kaufen
                                       2. Film ansehen
                                       3. Gewinnspiel
                                       4. Kino verlassen
                                ------------------------------
                                """;

                        System.out.print(menu);
                        System.out.println("\nIhr aktuelles Guthaben: " + String.format(Locale.US, "%.2f", Double.parseDouble(userInput)) + " €");

                        System.out.println("Geben Sie eine Zahl zwischen 1 und 4 ein: ( z.B. 1 für Ticket kaufen)");
                        int userChoice; // choice of the user from the Menu Options


                        do {
                            userChoice = sc.nextInt();
                            sc.nextLine();

                            if (userChoice == 1 && Double.parseDouble(userInput) < cheapestTicketPrice) {

                                System.out.println("Derzeit Guthaben: " + Double.parseDouble(userInput) + "\nDerzeit günstigstes Kinoticket: " + cheapestTicketPrice);
                                System.out.println("Geben Sie eine Zahl zwischen 2 und 4 ein: ( z.B. 2 für Film Ansehen)");
                                userChoice = 0;

                            } else if (userChoice > 4) {
                                System.out.println("\nBitte geben Sie eine gültige Auswahl ein. Wir haben nur 4 Auswahlmöglichkeiten");

                            } else if (userChoice < 1) {
                                System.out.println("\nBitte geben Sie eine gültige Zahl zwischen 1 und 4 ein: ");
                            }

                        } while ((userChoice < 1 || userChoice > 4));


                        switch (userChoice) {

                            //ticket kaufen choice
                            case 1:

                                int movieChoice; //userInput which movie they will choose to buy

                                do {
                                    // print the Kino Board
                                    System.out.println("--------------------------------------------------------------------");
                                    System.out.printf("%-8s %-18s %-10s %-6s %-8s %-12s%n",
                                            "Filmnr", "Filmname", "Uhrzeit", "Saal", "Preis", "Restplätze");
                                    System.out.println("--------------------------------------------------------------------");

                                    String ticketStatus;

                                    for (String[] cinemaDatum : cinemaData) {

                                        ticketAvailable = Integer.parseInt(cinemaDatum[5]);

                                        if (ticketAvailable > 0) {
                                            ticketStatus = "verfügbar";
                                        } else {
                                            ticketStatus = "ausgebucht";
                                        }

                                        System.out.printf("%-8s %-18s %-10s %-6s %-8s %-12s%n",
                                                cinemaDatum[0] + ".", cinemaDatum[1], cinemaDatum[2], cinemaDatum[3], cinemaDatum[4], ticketStatus);
                                    }
                                    System.out.println("--------------------------------------------------------------------\n");

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
                                        ticketPrice = Double.parseDouble(cinemaData[movieChoice - 1][4]);
                                        ticketAvailable = Integer.parseInt(cinemaData[movieChoice - 1][cinemaData[0].length - 1]); // to update the current ticket Availability

                                        //System.out.println("ticketAvailable: " + ticketAvailable);

                                        if (userBudget < ticketPrice) {
                                            System.out.println("Sie haben nicht genug Guthaben für diesen Film. Der Ticketpreis ist " + ticketPrice + "€, und Sie haben nur " + String.format("%.2f", userBudget) + "€.");
                                        } else {

                                            if (ticketAvailable == 0) {
                                                System.out.println("\nIhr ausgewählter Film " + movieChoice + " ist leider vollständig ausgebucht.");
                                            }

                                            if (ticketAvailable > 0) {
                                                int ticketCount; //the number of tickets the user entered for a certain movie
                                                double totalTicketCost; //the total amount of the purchased Tickets


                                                do {
                                                    System.out.println("Es sind noch " + ticketAvailable + " Tickets um jeweils " + String.format(Locale.US, "%.2f", ticketPrice) + " € dafür verfügbar. Wie viele möchtest du kaufen?");
                                                    ticketCount = sc.nextInt();

                                                    totalTicketCost = ticketCount * ticketPrice;
                                                    int ticketsWithinBudget = (int) ((int) userBudget / ticketPrice); // to determine how many tickets the user can buy still

                                                    //edge cases warning for amount of tickets input
                                                    if (ticketCount <= 0) {
                                                        System.out.println("Sie haben eine ungültige Nummer eingegeben.");
                                                    } else if (ticketCount > ticketAvailable) {
                                                        System.out.println("Sie haben mehr Tickets eingegeben, als verfügbar sind.");
                                                    } else if (totalTicketCost > userBudget) {
                                                        System.out.println(
                                                                "Gesamtkosten von " + (double) ticketCount + " Tickets: " + totalTicketCost + "\nSie haben nur " + String.format("%.2f", userBudget) +
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

                                                System.out.println("Sie kaufen " + ticketCount + " Tickets um " + totalTicketCost + "€ und hast jetzt noch " + String.format(Locale.US, "%.2f", userBudget) + "€.");
                                            }
                                        }

                                    }

                                    //shows the actual user Credit and the movies Chosen
                                    System.out.println("==========================");
                                    System.out.println("Aktuelles Guthaben: " + String.format(Locale.US, "%.2f", userBudget) + "€");
                                    System.out.println("Gekaufter Tickets: " + chosenMovies);
                                    System.out.println("==========================\n");


                                    if (userBudget >= rafflePrice && userBudget < cheapestTicketPrice) {

                                        System.out.println("Ihr Budget reicht nicht aus, um das günstigste Ticket zu kaufen, das kostet " + cheapestTicketPrice +
                                                "€.\nAber Sie können für " + rafflePrice + "€ an gewinnspiel teilnehmen."
                                        );

                                        movieChoice = 0;

                                    }

                                    if (userBudget < rafflePrice) {
                                        System.out.println(" Sie haben nicht genug Guthaben für weiteren Einkauf.\n Vielen Dank für den Ticketkauf. Ticketverkauf endet..");
                                        movieChoice = 0;
                                    }


                                } while (movieChoice != 0);

                                userInput = String.valueOf(userBudget);

                                break;


                            case 2: //To Show Movie Tickets

                                if (chosenMovies.isEmpty()) {
                                    System.out.println("Sie haben keine Kinokarten, bitte kaufen Sie zuerst");
                                } else {

                                    int selectedFilmToWatch; //the movie that the user will watch

                                    ArrayList<String> scheduledMovies = new ArrayList<>(); //movies chosen to be watch alone or with Friends


                                    do {
                                        System.out.println("Liste der Filme, die Sie noch haben:");
                                        System.out.println("............................................................");

                                        // just to show the chosenMovies in tabular way
                                        for (int i = 0; i < cinemaData.length; i++) {

                                            int filmNumber = Integer.parseInt(cinemaData[i][0]);
                                            String movieTitle = cinemaData[i][1];

                                            int ticketsBought = 0;

                                            for (int chosenMovie : chosenMovies) {
                                                if (chosenMovie == filmNumber) {
                                                    ticketsBought++;
                                                }
                                            }


                                            if (ticketsBought > 0) {

                                                System.out.println("Filmnr: " + filmNumber + " | Movie Titel: " + movieTitle + " | Ticketsanzahl: " + ticketsBought + ".");
                                                System.out.println("............................................................");
                                            }

                                        }

                                        System.out.println("\nWelchen Film (für den du noch ein Ticket hast) möchtest du sehen? (0 für abbrechen)\n");
                                        selectedFilmToWatch = sc.nextInt();


                                        if (chosenMovies.contains(selectedFilmToWatch)) {

                                            int remainingTickets = 0;
                                            for (int i = 0; i < chosenMovies.size(); i++) {
                                                if (chosenMovies.get(i) == selectedFilmToWatch) {
                                                    remainingTickets++;
                                                }
                                            }

                                            if (remainingTickets > 0) {
                                                System.out.println("You have " + remainingTickets + " ticket(s) for Filmnr " + selectedFilmToWatch);

                                                System.out.println("Wie viele Tickets wird sie nutzen");
                                                int ticketsToWatch = sc.nextInt(); //to ask user how many tickets he will use in one time viewing.

                                                if (ticketsToWatch > remainingTickets) {
                                                    System.out.println("You have only " + remainingTickets + " ticket(s) for Filmnr " + selectedFilmToWatch);
                                                } else {
                                                    for (int i = 0; i < ticketsToWatch; i++) {
                                                        chosenMovies.remove(Integer.valueOf(selectedFilmToWatch));
                                                        scheduledMovies.add(String.valueOf(cinemaData[selectedFilmToWatch - 1][1]));
                                                    }

                                                    remainingTickets -= ticketsToWatch;
                                                    System.out.println("You now have " + remainingTickets + " ticket(s) left for Filmnr " + selectedFilmToWatch);
                                                }


                                            } else {
                                                System.out.println("Sie haben keine Tickets mehr für Filmnr: " + selectedFilmToWatch);
                                            }

                                            System.out.println("\nDu schaust dir den Film " + scheduledMovies + " an. Viel Spaß!");

                                            if (chosenMovies.isEmpty()) {
                                                System.out.println("Keine weiteren Kinotickets zum Auschecken.");
                                                selectedFilmToWatch = 0;
                                            }

                                        }

                                    } while (selectedFilmToWatch != 0);
                                } // end of else
                                break;

                            //raffle section
                            case 3:

                                if (userBudget < rafflePrice) {
                                    System.out.println("Gewinnspiel Preis: " + rafflePrice);
                                    System.out.println("Dein Guthaben ist leider nicht genug.");
                                }

                                if (userBudget > rafflePrice) {
                                    int raffleChoice = 1;

                                    while (raffleChoice != 0) {

                                        do {
                                            System.out.println(" *** Welcome to Kinoplex Gewinnspiel ***");
                                            System.out.println("-----------------------------------------");
                                            System.out.println("Möchten Sie an unserem Gewinnspiel teilnehmen.? \n1. Teilnehmen\n2. Das Gewinnspiel beenden ");
                                            System.out.println("\nGeben Sie 1 oder 2 ein");
                                            raffleChoice = sc.nextInt();

                                            if (raffleChoice < 1 || raffleChoice > 2) {
                                                System.out.println("Ungültige Eingabe, bitte geben Sie nur 1 oder 2 ein\n");
                                            }

                                            if (raffleChoice == 2) {
                                                System.out.println("\nVielen Dank für Ihren Besuch bei Kinoplex Gewinnspiel");
                                                raffleChoice = 0;
                                            }

                                            if (raffleChoice == 1) {

                                                userBudget = userBudget - rafflePrice;

                                                Random rand = new Random();

                                                int luckyNumber = 0;

                                                for (int i = 0; i < 200; i++) {
                                                    luckyNumber = rand.nextInt(201);
                                                }

                                                System.out.println("Du machst beim Gewinnspiel mit!\n" +
                                                        "Deine Glückszahl ist: " + luckyNumber + "\n");

                                                int a = 1;
                                                int b = 1;
                                                int fibonacciNummer = 0;
                                                int counter = 10; //fibonnaci less than 200 is 144
                                                boolean isMatch = false;

                                                for (int i = 0; i < counter && !isMatch; i++) {
                                                    fibonacciNummer = a + b;
                                                    System.out.println(a + " " + "+ " + b + " " + "= " + fibonacciNummer);
                                                    //System.out.println(fibonacciNummer);
                                                    a = b;
                                                    b = fibonacciNummer;

                                                    if (fibonacciNummer == luckyNumber) {
                                                        isMatch = true;
                                                        System.out.println(luckyNumber + " ist eine Fibonacci-Nummer! Du gewinnst 20€!");
                                                        userBudget = userBudget + 20;

                                                    }
                                                }

                                                System.out.println("\nWenn er kein Glück hatte, kostet ihn das 5€ für das Lotterielos (sein Geldbetrag wird also -5€ gerechnet).");
                                                System.out.println("Derzeit Guthaben: " +  String.format("%.2f",userBudget) + " €\n");

                                                if (userBudget < rafflePrice) {
                                                    raffleChoice = 0;
                                                    System.out.println("Derzeit Guthaben: " +  String.format("%.2f",userBudget));
                                                    System.out.println("Das aktuelle Guthaben reicht nicht zum Spielen. Danke fürs Spielen.");
                                                }

                                            }

                                        } while (raffleChoice < 0 || raffleChoice > 2);

                                    }

                                }

                                userInput = String.valueOf(userBudget);
                                break;

                            //exit area
                            case 4:
                                System.out.println("\nVielen Dank für Ihren Besuch bei Kinoplex. Wir wünschen Ihnen einen schönen Tag. ");
                                menuOption = true;
                                userBudget = 0;
                                //userInput = String.valueOf(0);
                                break;

                            default:
                                System.out.println("Ungültige Eingabe ");
                        }

                    } while (!menuOption); // end of menu Option

                } else if (userBudget < rafflePrice && userBudget > 0) {
                    System.out.println("Gewinnspiel Preis: " + (double) rafflePrice + " €");
                    System.out.println("Das günstigste Ticket ist: " + cheapestTicketPrice + " €");
                    System.out.println("Der von Ihnen eingegebene Betrag reicht nicht zur Teilnahme an unserem Gewinnspiel oder Ticketankauf.  \nGeben Sie einen ausreichenden Betrag ein.\n");
                } else {
                    System.out.println("Bitte geben Sie einen gültigen Betrag ein.");
                }

            } //end if(isNumber)

        }
    }
}
