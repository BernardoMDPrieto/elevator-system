import java.util.Arrays;

public class Elevator {
    private Directions direction;
    private int currentPassenger;
    private int currentFloor;
    private boolean doorOpen;
    private int[] routes;
    private int allRoutes;
    private int capacityPassengers;



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



    Elevator(int totalFloors,  int capacityPassengers){

        this.capacityPassengers = capacityPassengers;
        this.routes = new int[totalFloors];

        this.direction = Directions.STOPPED;
        this.doorOpen = true;
        this.currentFloor = 0;
        this.allRoutes = 0;
    }

    public int getCurrentPassenger() {
        return currentPassenger;
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
                '}';
    }
}
