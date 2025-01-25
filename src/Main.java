public class Main {
    public static void main(String[] args) {

        Elevator elevator = new Elevator(3);

        int cannotEnter = 0;

        cannotEnter += elevator.addPassenger(1);
        System.out.println(cannotEnter);

        cannotEnter += elevator.addPassenger(1);
        System.out.println(cannotEnter);

        cannotEnter += elevator.addPassenger(3);

        System.out.println("Não conseguiram entrar " + cannotEnter);

        System.out.println("Total de passageiros dentro do elevador " + elevator.getCurrentPassenger());
    }
}