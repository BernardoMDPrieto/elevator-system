import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Elevator {
    private Directions direction;
    private int currentPassenger;
    private int currentFloor;
    private boolean doorOpen;
    private final int[] routes;
    private int allRoutes;
    private final int capacityPassengers;
    private final int superiorFloors;
    private final int lowerFloors;
    private final int[] totalFloors;


    public void start() {
        this.doorOpen = false;
        while (this.allRoutes > 0) {
            if (this.direction.equals(Directions.FIRST_DOWN) ||
                    this.direction.equals(Directions.TO_DOWN)) {
                goDown();
            } else if (this.direction.equals(Directions.FIRST_UP) ||
                    this.direction.equals(Directions.TO_UP)) {
                goUp();
            }
        }

        this.direction = Directions.STOPPED;
        this.doorOpen = true;
    }

    public void goUp() {
        this.doorOpen = false;
        for (int i = 0; i < allRoutes; i++) {
            if (routes[i] > currentFloor) {
                Ascii.printGoUp();
                Ascii.printElevator();
                currentFloor = routes[i];
                this.doorOpen = true;
                System.out.println("Chegamos ao andar: " + currentFloor);
                if (this.currentPassenger < 1) {
                    Ascii.printEmptyElevatorOpenedDoors();
                } else {
                    Ascii.printElevatorOpenedDoors();
                }
                removePassenger();
                removeRoute(i);
                i--;
            }
        }
        this.direction = Directions.TO_DOWN;
    }

    public void goDown() {
        this.doorOpen = false;
        for (int i = this.currentFloor - 1; i >= this.routes[0]; i--) {
            for (int j = 0; j < allRoutes; j++) {
                if (routes[j] == i && routes[j] != this.currentFloor) {
                    Ascii.printGoDown();
                    Ascii.printElevator();
                    this.currentFloor = i;
                    this.doorOpen = true;
                    System.out.println("Chegamos no andar: " + i);
                    if (this.currentPassenger < 1) {
                        Ascii.printEmptyElevatorOpenedDoors();
                    } else {
                        Ascii.printElevatorOpenedDoors();
                    }
                    removePassenger();
                    removeRoute(j);
                    break;
                }
            }
        }
        this.direction = Directions.TO_UP;
    }

    private void removeRoute(int index) {
        for (int i = index; i < allRoutes - 1; i++) {
            routes[i] = routes[i + 1];
        }
        routes[allRoutes - 1] = 0;
        allRoutes--;
    }

    public int addPassenger(int passenger) {
        int cannotEnter = 0;

        int total = this.currentPassenger += passenger;

        if (total > this.capacityPassengers) {
            cannotEnter = total - this.capacityPassengers;
            this.currentPassenger -= cannotEnter;
        }

        Ascii.totalPassengers(this.getCurrentPassenger());

        return cannotEnter;
    }

    public void removePassenger() {
        Scanner sc = new Scanner(System.in);
        int passenger = 0;
        boolean invalidOperation = false;
        try {
            if (!this.doorOpen) {
                System.out.println("A porta está fechada");
            } else {
                if (this.currentPassenger  <= 0) {
                    System.out.println("O elevador está vazio");
                } else {
                    do {
                        invalidOperation = false;
                        System.out.println("Quantos passageiros irão descer neste andar?");
                        passenger = sc.nextInt();

                        if (passenger < 0) {
                            System.out.println("Erro: O número de passageiros não pode ser negativo.");
                            invalidOperation = true;
                        } else if ((this.getCurrentPassenger() - passenger) < 0) {
                            invalidOperation = true;
                        }
                    } while (invalidOperation);
                    this.doorOpen = true;
                    this.currentPassenger -= passenger;
                    System.out.println(this.currentPassenger + " pessoas no elevador");
                }
                Ascii.totalPassengers(this.getCurrentPassenger());
            }
        } catch (InputMismatchException exception) {
            System.out.println("Erro: Entrada inválida! Digite um número inteiro.");
            sc.nextLine();
        }
    }

    public void addRoute(int floor) {

        boolean isDuplicated = false;

        if (floor == this.currentFloor) {
            System.out.println("Já tem um elevador neste andar");
            return;
        }

        if (this.allRoutes == 0) {
            this.direction = (this.currentFloor < floor) ? Directions.FIRST_UP : Directions.FIRST_DOWN;
        }

        //TODO Desacoplar estes dois trechos para uma classe de utils

        for (int i = 0; i < allRoutes; i++) {
            if (floor == routes[i]) {
                isDuplicated = true;
                break;
            }
        }

        if (!isDuplicated) {
            routes[allRoutes] = floor;
            allRoutes++;
            for (int i = 0; i < allRoutes - 1; i++) {
                for (int j = i + 1; j < allRoutes; j++) {
                    if (routes[i] > routes[j]) {
                        int pile = routes[i];
                        routes[i] = routes[j];
                        routes[j] = pile;
                    }
                }
            }
        }
    }

    public void callElevator(int callFloor) {
        if (this.currentFloor > callFloor) {
            System.out.println("O elevador está descendo");
            addRoute(callFloor);
            start();
        } else if (this.currentFloor < callFloor) {
            System.out.println("O elevador está subindo");
            addRoute(callFloor);
            start();
        }
    }


    Elevator(int superiorFloors, int lowerFloors, int capacityPassengers) {

        this.capacityPassengers = capacityPassengers;
        int[] totalFloors = new int[lowerFloors + superiorFloors + 1];

        for (int i = 0, currentFloor = (lowerFloors * -1); currentFloor <= superiorFloors; i++, currentFloor++) {
            totalFloors[i] = currentFloor;
        }

        this.lowerFloors = lowerFloors;
        this.superiorFloors = superiorFloors;
        this.totalFloors = totalFloors;
        this.routes = new int[totalFloors.length];
        this.direction = Directions.STOPPED;
        this.doorOpen = true;
        this.currentFloor = 0;
        this.allRoutes = 0;
    }

    public Elevator() {

        this.superiorFloors = 9;
        this.lowerFloors = -2;
        int[] totalFloors = new int[lowerFloors + superiorFloors + 1];

        for (int i = 0, currentFloor = (lowerFloors * -1); currentFloor <= superiorFloors; i++, currentFloor++) {
            totalFloors[i] = currentFloor;
        }
        this.totalFloors = totalFloors;
        this.capacityPassengers = 6;
        this.routes = new int[12];
        this.direction = Directions.STOPPED;
        this.doorOpen = true;
        this.currentFloor = 0;
        this.allRoutes = 0;
    }

    public Directions getDirection() {
        return direction;
    }

    public int getLowerFloors() {
        return lowerFloors * -1;
    }

    public int getSuperiorFloors() {
        return superiorFloors;
    }

    public int getAllRoutes() {
        return allRoutes;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int[] getRoutes() {
        return routes;
    }

    public boolean isDoorOpen() {
        return doorOpen;
    }

    public int getCurrentPassenger() {
        return currentPassenger;
    }

    public int[] getTotalFloors() {
        return totalFloors;
    }

    @Override
    public String toString() {
        return "Elevador:\n" +
                "Capacidade de passageiros: " + capacityPassengers +
                "\nTotal de andares: " + Arrays.toString(totalFloors);
    }
}
