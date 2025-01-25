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

    public void removePassenger(int passenger) {
        if(!this.doorOpen) {
            System.out.println("A porta está fechada");
        }else{

            if(this.currentPassenger < 1){
                System.out.println("Todos já descereram");
            }else{
                this.currentPassenger -= passenger;
                System.out.println(this.currentPassenger + " pessoas no elevador");
            }


        }

    }

    Elevator(int capacityPassengers ){
        this.capacityPassengers = capacityPassengers;
        this.doorOpen = true;
    }

    public int getCurrentPassenger() {
        return currentPassenger;
    }
}
