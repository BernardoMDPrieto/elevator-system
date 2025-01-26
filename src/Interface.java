import java.util.InputMismatchException;
import java.util.Scanner;

public class Interface {
    static Scanner sc = new Scanner(System.in);
    private static Elevator elevator;


    public static void showMenu() {
        System.out.print(Ascii.SYSTEM_TITLE);

        try {
            System.out.println("SEJA MUITO BEM-VINDO!\n" +
                    "Antes de iniciar o sistema, escolha uma das opções abaixo:\n\n");
            elevatorSettings();
        } catch (InputMismatchException exception) {
            System.out.println("Erro: Entrada inválida! Digite um número inteiro.");
            System.out.println(Ascii.DIVISOR);
            sc.nextLine();
            elevatorSettings();
        }

        int systemActions = 0;
        do {
            try {
                System.out.printf(Ascii.DIVISOR +
                        "\nMENU DE AÇÕES DO ELEVADOR - SELECIONE O NÚMERO DA OPÇÃO DESEJADA\n" +
                        "1 - Embarcar pessoas no elevador\n" +
                        "2 - Selecionar um andar para ir\n" +
                        "3 - Chamar o elevador para algum andar\n" +
                        "4 - Começar a rota do elevador\n" +
                        "5 - Consultar os andares selecionados\n" +
                        "6 - Sair\n");
                System.out.println("O elevador está atualmente no andar: " + elevator.getCurrentFloor());
                if(elevator.getCurrentPassenger() > 0){

                    Ascii.printElevatorOpenedDoors();
                }else{
                    Ascii.printEmptyElevatorOpenedDoors();
                }


                systemActions = sc.nextInt();
                startAction(systemActions);
            } catch (InputMismatchException exception) {
                System.out.println("Erro: Entrada inválida! Digite um número inteiro.");
                sc.nextLine();
            }
        } while (systemActions != 6);
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
                break;
            case 5:
                getRoutes();
                break;
            case 6:
                break;
            default:
                System.out.println("Insira um valor válido");
        }
    }

    public static void runRoute() {
        if (elevator.getAllRoutes() > 0) {
            elevator.start();
        } else {
            System.out.println("Adicione uma rota primeiro");
        }
    }

    public static void getRoutes() {
        int[] routes = elevator.getRoutes();
        if (elevator.getAllRoutes() > 0) {
            System.out.print("Andares selecionados: [ ");
            for (int i = 0; i < elevator.getAllRoutes(); i++) {
                System.out.print(routes[i] + " ");
            }
            System.out.println("]");
        }else{
            System.out.println("Você ainda não selecionou nenhuma rota");
        }
    }

    public static void callElevator() {
        try {
            System.out.println("Insira para qual andar você quer chamar o elevador");
            int callFloor = sc.nextInt();

            if (isInvalidFloor(callFloor)) {
                System.out.println("Este andar não existe");
            } else if (callFloor == elevator.getCurrentFloor()) {
                System.out.println("Já tem um elevador neste andar");
            } else {
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
                return;
            }

            if(elevator.isDoorOpen()) {
                int leftBehind = elevator.addPassenger(passenger);

                if (leftBehind > 0) {
                    System.out.println("Opa! Parece que o elevador atingiu o limite de peso");
                    System.out.println(leftBehind + " Pessoa(s) ficaram para a próxima!");
                }
            }else{
                System.out.println("O elevador está com a porta fechada!");
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
            getTotalFloors(elevator.getTotalFloors());
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
                || floor > elevator.getSuperiorFloors();
    }

    private static void getTotalFloors(int[] totalFloors){
        System.out.print("[ ");
        for(int i = 0; i < totalFloors.length; i++){
            System.out.print(totalFloors[i] + " ");
        }
        System.out.println("]");
    }

    public static void elevatorSettings() {
        System.out.println(
                "Escolha uma opção:\n" +
                        "1. Configurar as especificações do prédio para o elevador.\n" +
                        "2. Utilizar um modelo padrão para testes.\n\n" +
                        "O modelo padrão possui:\n" +
                        "- 9 andares acima do térreo\n" +
                        "- 2 andares no subsolo\n" +
                        "- Capacidade máxima para 6 pessoas\n");

        int modelOfSystem = sc.nextInt();

        switch (modelOfSystem) {
            case 1:
                customElevatorSettings();
                break;
            case 2:
                elevator = ElevatorFactory.createElevator(9, 2, 6);
                System.out.println("Modelo padrão configurado com sucesso!");
                break;
            default:
                System.out.println("Opção inválida. O modelo padrão será utilizado.");
                elevator = ElevatorFactory.createElevator();
                break;
        }

    }

    public static void customElevatorSettings() {
        boolean repeatForm;

        do {
            repeatForm = false;
            try {
                System.out.println(Ascii.DIVISOR);
                System.out.println("Configuração personalizada do elevador");

                int supFloors = getPositiveInteger();
                int subFloors = getNonNegativeInteger();

                int passengers = getValidPassengerCapacity();

                elevator = ElevatorFactory.createElevator(supFloors, subFloors, passengers);
                System.out.println("Seu elevador terá as seguintes especificações: " + elevator);

                repeatForm = !confirmAction();

            } catch (InputMismatchException exception) {
                System.out.println("Erro: Entrada inválida! Digite um número inteiro.");
                repeatForm = true;
                sc.nextLine();
            }
        } while (repeatForm);
    }

    private static int getPositiveInteger() {
        int value;
        do {
            System.out.println("Digite o número de andares acima do nível do solo:");
            value = sc.nextInt();
            if (value < 1) {
                System.out.println("Erro: O valor deve ser pelo menos 1.");
            }
        } while (value < 1);
        return value;
    }

    private static int getNonNegativeInteger() {
        int value;
        do {
            System.out.println("Digite o número de andares no subsolo (se não houver, digite 0):");
            value = sc.nextInt();
            if (value < 0) {
                System.out.println("Número negativo detectado. Ajustando para positivo: " + (-value));
                value = -value;
            }
        } while (value < 0);
        return value;
    }

    private static int getValidPassengerCapacity() {
        int capacity;
        do {
            System.out.println("Digite a capacidade máxima de pessoas no elevador (mínimo 1):");
            capacity = sc.nextInt();
            if (capacity < 1) {
                System.out.println("Erro: A capacidade deve ser pelo menos 1 passageiro.");
            }
        } while (capacity < 1);
        return capacity;
    }

    private static boolean confirmAction() {
        sc.nextLine();
        while (true) {
            System.out.println("Deseja prosseguir com este modelo? (S/N)");
            String confirmationQuestion = sc.nextLine().trim().toUpperCase();
            if (confirmationQuestion.equals("S")) return true;
            if (confirmationQuestion.equals("N")) return false;
            System.out.println("Opção inválida. Digite 'S' para Sim ou 'N' para Não.");
        }
    }

}
