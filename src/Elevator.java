import java.util.Arrays;

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



    public void start(){
        while(this.allRoutes > 0) {
            if (this.direction.equals(Directions.FIRST_DOWN) ||
                    this.direction.equals(Directions.TO_DOWN)) {
                goDown();
            } else if (this.direction.equals(Directions.FIRST_UP) ||
                    this.direction.equals(Directions.TO_UP)) {
                goUp();
            }
        }

        this.direction = Directions.STOPPED;
    }

    public void goUp() {
        for (int i = 0; i < allRoutes; i++) {
            if (routes[i] > currentFloor) {
                currentFloor = routes[i];
                System.out.println("Chegamos ao andar: " + currentFloor);
                removePassenger(1);
                removeRoute(i);
                i--;
            }
        }
        this.direction = Directions.TO_DOWN;
    }

    public void goDown(){
        for(int i = this.currentFloor -1; i >= this.routes[0]; i-- ){
            for (int j = 0; j < allRoutes; j++){
                if(routes[j] == i && routes[j] != this.currentFloor){
                    this.currentFloor = i;
                    System.out.println("Chegamos no andar: " + i);
                    removePassenger(1);
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


    //Quem chamar esse precisa adicionar uma validação para retorno e confirmar se todos passageiros conseguiram entrar
    public int addPassenger(int passenger) {

        int cannotEnter = 0;

        if(!this.doorOpen) {
            System.out.println("A porta está fechada");
            return passenger;
        }

        int total = this.currentPassenger += passenger;

        if(total > this.capacityPassengers){
            cannotEnter = total - this.capacityPassengers;
            this.currentPassenger -= cannotEnter;
        }

        return cannotEnter;
    }

    public void removePassenger(int passenger) {
        if(!this.doorOpen) {
            System.out.println("A porta está fechada");
        }else{
            if(this.currentPassenger <= 0){
                this.currentPassenger = 0;
                System.out.println("Todos já descereram");
            }else{
                this.currentPassenger -= passenger;
                System.out.println(this.currentPassenger + " pessoas no elevador");
            }
        }
    }

    public void addRoute(int floor){

        boolean isDuplicated = false;

        if(floor == this.currentFloor){
            System.out.println("Já tem um elevador neste andar");
            return;
        }

        if(this.allRoutes == 0){
            this.direction = (this.currentFloor < floor) ? Directions.FIRST_UP : Directions.FIRST_DOWN;
        }

        //TODO Desacoplar estes dois trechos para uma classe de utils

        for(int i = 0; i < allRoutes; i++){
            if (floor == routes[i]){
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

    public void callElevator(int callFloor){
        if(this.currentFloor > callFloor){
            System.out.println("O elevador está descendo");
            addRoute(callFloor);
            start();
        }else if(this.currentFloor < callFloor){
            System.out.println("O elevador está subindo");
            addRoute(callFloor);
            start();
        }
    }



    Elevator(int superiorFloors, int lowerFloors,  int capacityPassengers){

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

    @Override
    public String toString() {
        return "Elevator{" +
                "direction=" + direction +
                ", currentPassenger=" + currentPassenger +
                ", currentFloor=" + currentFloor +
                ", doorOpen=" + doorOpen +
                ", routes=" + Arrays.toString(routes) +
                ", allRoutes=" + allRoutes +
                ", capacityPassengers=" + capacityPassengers +
                ", superiorFloors=" + superiorFloors +
                ", lowerFloors=" + lowerFloors +
                ", totalFloors=" + Arrays.toString(totalFloors) +
                '}';
    }
}
