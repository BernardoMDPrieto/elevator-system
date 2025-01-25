public class Main {
    public static void main(String[] args) {

        Elevator elevator = new Elevator(12,6);

        int cannotEnter = 0;

        cannotEnter += elevator.addPassenger(4);
        System.out.println(cannotEnter);

        cannotEnter += elevator.addPassenger(3);

        System.out.println("Não conseguiram entrar " + cannotEnter);

        elevator.removePassenger(4);

        elevator.addPassenger(cannotEnter);


        elevator.addRoute(5);
        elevator.addRoute(1);
        elevator.addRoute(9);
        elevator.addRoute(1);
        elevator.addRoute(8);
        elevator.addRoute(-3);


        System.out.println(elevator);

        System.out.println("Total de passageiros dentro do elevador " + elevator.getCurrentPassenger());
    }
}