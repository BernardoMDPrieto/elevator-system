import java.util.InputMismatchException;
import java.util.Scanner;

public class Interface {
    static Scanner sc = new Scanner(System.in);
    private static Elevator elevator;
    static String divisor = "===========================================================================";

    public static void showMenu() {
        System.out.print(Ascii.systemTitle);

        try {
            System.out.println("SEJA MUITO BEM-VINDO!\n" +
                    "Antes de iniciar o sistema, escolha uma das opções abaixo:\n\n");
            elevatorSettings();
        } catch (InputMismatchException exception) {
            System.out.println("Erro: Entrada inválida! Digite um número inteiro.");
            System.out.println(divisor);
            sc.nextLine();
            elevatorSettings();
        }

        int systemActions = 0;
        do {
            try {
                //TODO Criar um método para listar minhas rotas
                System.out.printf(divisor +
                        "\nMENU DE AÇÕES DO ELEVADOR - SELECIONE O NÚMERO DA OPÇÃO DESEJADA\n" +
                        "1 - Embarcar pessoas no elevador\n" +
                        "2 - Selecionar um andar para ir\n" +
                        "3 - Chamar o elevador para algum andar\n" +
                        "4 - Começar a rota do elevador\n" +
                        "5 - Sair\n");

                systemActions = sc.nextInt();
                startAction(systemActions);
            } catch (InputMismatchException exception) {
                System.out.println("Erro: Entrada inválida! Digite um número inteiro.");
                sc.nextLine();
            }
        } while (systemActions != 5);
    }

    public static void startAction(int systemActions) {
        switch (systemActions) {
            case 1:
                addPassengers();
                break;
            case 2:
                goToFloor();
                break;
            case 3:
                callElevator();
                break;
            case 4:
                runRoute();
            case 5:
                break;
            default:
                System.out.println("Insira um valor válido");
        }
    }

    public static void runRoute() {
        if(elevator.getAllRoutes() > 0) {
            for (int i = 0; i < Ascii.elevatorClosedDoors.length; i++) {
                System.out.println(Ascii.elevatorClosedDoors[i]);

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            elevator.start();
        }else{
            System.out.println("Adicione uma rota primeiro");
        }
    }

    public static void callElevator() {
        try {
            System.out.println("Insira para qual andar você quer chamar o elevador");
            int callFloor = sc.nextInt();

            if (isInvalidFloor(callFloor)) {
                System.out.println("Este andar não existe");
            }else if(callFloor == elevator.getCurrentFloor()){
                System.out.println("Já tem um elevador neste andar");
            }else {
                System.out.println();
                elevator.callElevator(callFloor);
            }
        } catch (InputMismatchException exception) {
            System.out.println("Erro: Entrada inválida! Digite um número inteiro.");
            sc.nextLine();
        }
    }

    public static void addPassengers() {
        try {
            System.out.println("Quantos passageiros irão entrar no elevador?");

            int passenger = sc.nextInt();

            if (passenger < 1) {
                System.out.println("Ops! parece que você inseriu um valor inválido, tente um valor válido");
            }

            int leftBehind = elevator.addPassenger(passenger);

            if (leftBehind > 0) {
                System.out.println("Opa! Parece que o elevador atingiu o limite de peso");
                System.out.println(leftBehind + " Pessoa(s) ficaram para a próxima!");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Erro: Entrada inválida! Digite um número inteiro.");
            sc.nextLine();
        }
    }


    public static void goToFloor() {
        try {
            System.out.println("Indique para qual andar você deseja ir:");
            System.out.println("Caso queira descer para o subsolo coloque o número negativo representando o andar");
            int floorToGo = sc.nextInt();

            if (isInvalidFloor(floorToGo)) {
                System.out.println("Este andar não existe");
            } else {
                elevator.addRoute(floorToGo);
                System.out.println("Adicionado a rota do elevador: " + floorToGo);
            }
        } catch (InputMismatchException exception) {
            System.out.println("Erro: Entrada inválida! Digite um número inteiro.");
            sc.nextLine();
        }
    }

    private static boolean isInvalidFloor(int floor) {
        return floor < elevator.getLowerFloors()
                || floor >= elevator.getSuperiorFloors();
    }

    public static void elevatorSettings() {
        System.out.println(
                "1. Definir as especificações do prédio para o uso do elevador.\n" +
                        "2. Utilizar um modelo padrão para testes.\n\n" +
                        "O modelo padrão conta com um total de 12 andares: \n" +
                        "- 2 andares no subsolo\n" +
                        "- 9 andares acima do térreo.\n" +
                        "Além de que neste modelo só são permitidas no máximo 6 pessoas no elevador");

        int modelOfSystem = sc.nextInt();

        switch (modelOfSystem) {
            case 1:
                customElevatorSettings();
                break;
            case 2:
                elevator = ElevatorFactory.createElevator(9, 2, 6);
                System.out.println("Modelo padrão criado com sucesso!");
                break;
            default:
                System.out.println("Opção inválida. O modelo padrão será utilizado.");
                elevator = ElevatorFactory.createElevator();
                break;
        }

    }

    public static void customElevatorSettings() {
        System.out.println(divisor);
        System.out.println("Você selecionou que deseja inserir o seu modelo de prédio");
        System.out.println("Primeiro insira quantos andares acima do nível do solo terá no seu prédio:");
        int supFloors = sc.nextInt();
        System.out.println("Agora insira quantos andares no subterrâneo terá no seu prédio:");
        int subFloors = sc.nextInt();
        System.out.println("Por fim selecione quanto será a carga máxima de pessoas no elevador");
        int passangers = sc.nextInt();

        elevator = ElevatorFactory.createElevator(supFloors, subFloors, passangers);
    }

}
