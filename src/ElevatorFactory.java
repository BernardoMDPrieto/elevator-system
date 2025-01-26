public class ElevatorFactory {

    public static Elevator createElevator(){
        return new Elevator();
    }

    public static Elevator createElevator(int superiorFloor, int lowerFloor, int capacityPassengers){
        return new Elevator(superiorFloor, lowerFloor, capacityPassengers);
    }
}
