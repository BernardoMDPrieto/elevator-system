public class Elevator {
    private Directions direction;
    private int currentPassenger;
    private int currentFloor;
    private boolean doorOpen;
    private int[] routes;
    private int allRoutes;
    private int capacityPassengers;


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

    Elevator(int capacityPassengers ){
        this.capacityPassengers = capacityPassengers;
        this.doorOpen = true;
    }

    public int getCurrentPassenger() {
        return currentPassenger;
    }
}
