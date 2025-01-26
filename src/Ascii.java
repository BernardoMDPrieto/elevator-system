public class Ascii {
    final static String[] ELEVATOR_CLOSED_DOORS = {
            "        |       |  ",
            "        |       |  ",
            "        |       |  ",
            "        |       |  ",
            "        |       |  ",
            "        |       |  ",
            "        |       |  ",
            "        |       |  ",
            "        |       |  ",
            "        ┌───────┐  ",
            "        │  ⬆ ⬇  │  ",
            "        │       │  ",
            "┌───────┴───────┴──┐",
            "│  ██████████████  │",
            "│  █     ||      █ │",
            "│  █     ||      █ │",
            "│  █     ||      █ │",
            "│  █     ||      █ │",
            "│  ██████████████  │",
            "└──────────────────┘",
            "\n\n"
    };

    final static String PASSENGER_ELEVATOR_OPENED_DOORS =
                    "        ┌───────┐  \n"+
                    "        │  ⬆ ⬇  │  \n"+
                    "        │       │  \n"+
                    "┌───────┴───────┴──┐\n"+
                    "│  ██████████████  │\n"+
                    "│  █  [        ] █ │\n"+
                    "│  █  [   O    ] █ │\n"+
                    "│  █  [  /|\\   ] █ │\n"+
                    "│  █  [  / \\   ] █ │\n"+
                    "│  ██████████████  │\n"+
                    "└──────────────────┘\n";

    final static String EMPTY_ELEVATOR_OPENED_DOORS =
                    "        ┌───────┐  \n"+
                    "        │  ⬆ ⬇  │  \n"+
                    "        │       │  \n"+
                    "┌───────┴───────┴──┐\n"+
                    "│  ██████████████  │\n"+
                    "│  █  [        ] █ │\n"+
                    "│  █  [        ] █ │\n"+
                    "│  █  [        ] █ │\n"+
                    "│  █  [        ] █ │\n"+
                    "│  ██████████████  │\n"+
                    "└──────────────────┘\n";

    final static String SYSTEM_TITLE = "\n" +
            "   _____ _     _                             _        ______ _                     _            \n" +
            "  / ____(_)   | |                           | |      |  ____| |                   | |           \n" +
            " | (___  _ ___| |_ ___ _ __ ___   __ _    __| | ___  | |__  | | _____   ____ _  __| | ___  _ __ \n" +
            "  \\___ \\| / __| __/ _ \\ '_ ` _ \\ / _` |  / _` |/ _ \\ |  __| | |/ _ \\ \\ / / _` |/ _` |/ _ \\| '__|\n" +
            "  ____) | \\__ \\ ||  __/ | | | | | (_| | | (_| |  __/ | |____| |  __/\\ V / (_| | (_| | (_) | |   \n" +
            " |_____/|_|___/\\__\\___|_| |_| |_|\\__,_|  \\__,_|\\___| |______|_|\\___| \\_/ \\__,_|\\__,_|\\___/|_|   \n" +
            "                                                                                                \n" +
            "                                                                                                \n";


    static String[] PASSENGER = {
            " O  ",
            "/|\\ ",
            "/ \\ "};

    static String DIVISOR = "===========================================================================";

    static String GO_DOWN = "\n" +
            "  ____                               _        \n" +
            " |  _ \\  ___  ___  ___ ___ _ __   __| | ___   \n" +
            " | | | |/ _ \\/ __|/ __/ _ \\ '_ \\ / _` |/ _ \\  \n" +
            " | |_| |  __/\\__ \\ (_|  __/ | | | (_| | (_) | \n" +
            " |____/ \\___||___/\\___\\___|_| |_|\\__,_|\\___/  \n" +
            "                                              \n";

    static String GO_UP = "\n" +
            "  ____        _     _           _       \n" +
            " / ___| _   _| |__ (_)_ __   __| | ___  \n" +
            " \\___ \\| | | | '_ \\| | '_ \\ / _` |/ _ \\ \n" +
            "  ___) | |_| | |_) | | | | | (_| | (_) |\n" +
            " |____/ \\__,_|_.__/|_|_| |_|\\__,_|\\___/ \n" +
            "                                        \n";

    public static void totalPassengers(int totalPassengers){
        if(totalPassengers > 1) {
            System.out.println("\n\nTotal de passageiros dentro do elevador");
            for (String line : Ascii.PASSENGER) {
                for (int i = 0; i < totalPassengers; i++) {
                    System.out.print(line);
                }
                System.out.println();
            }
        }
    }

    public static void printGoDown(){
        System.out.println(GO_DOWN);
    }

    public static void printGoUp(){
        System.out.println(GO_UP);
    }

    public static void printElevatorOpenedDoors() {
        System.out.println(PASSENGER_ELEVATOR_OPENED_DOORS);
    }

    public static void printEmptyElevatorOpenedDoors() {
        System.out.println(EMPTY_ELEVATOR_OPENED_DOORS);
    }
}
