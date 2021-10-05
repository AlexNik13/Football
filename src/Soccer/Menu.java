package Soccer;

import Soccer.Player.Enum.PlayerType;
import Soccer.Player.Player;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;

public class Menu {
    private Scanner in = new Scanner(System.in);
    private ClubManager clubManager;

    ArrayList<Player> testPlayer = new ArrayList<>(); // for tests


    public Menu() {
        clubManager = new ClubManager("My club");
        clubManager.setFootballClub(newFootballClub());
        // for tests
        testPlayer.add(new Player("Vasil", "Maradonna", 11, 38, PlayerType.ATTACKER));
        testPlayer.add(new Player("Pile", "Pilevich", 13, 18, PlayerType.DEFENDER));
        testPlayer.add(new Player("Паша", "Красовский", 11, 42, PlayerType.GOALKEEPER));
        testPlayer.add(new Player("Павел", "Воля", 31, 19, PlayerType.MIDFIELDER));
        testPlayer.add(new Player("Максим", "Ужасный", 54, 45, PlayerType.ATTACKER));
        //

        mainMenu();
    }

    private FootballClub newFootballClub() {
        System.out.print("Введите название вашего футбольного клуба. ");
        String name = in.next();

        System.out.print("В каком городе находиться ваш клуб. ");
        String city = in.next();

        System.out.print("Название вашего стадиона? ");
        String stadiumName = in.next();

        System.out.print("Вместимость вашего стадиона: ");
        int capacity = in.nextInt();

        return new FootballClub(name, city, stadiumName, capacity);
    }

    void mainMenu() {
        boolean menu = true;

        while (menu) {
            System.out.println("Главное меню");
            System.out.println("1. Создать игрока");
            System.out.println("2. Добавить игрока");
            System.out.println("3. Добавить играков");
            System.out.println("4. Провести тренеровку");
            System.out.println("5. Удалить игрока");
            System.out.println("6. Удалить несколько игроков");
            System.out.println("9. Показать команду");
            System.out.println("0. Выход");
            System.out.println("----------------------");

            int choice = in.nextInt();

            switch (choice) {
                case 0:
                    menu = false;
                    break;
                case 1:
                    menuCreatePlayer();
                    break;
                case 2:
                    menuAddPlayer();
                    break;
                case 3:
                    clubManager.addSeveralPlayer(testPlayer);
                    break;
                case 4:
                    clubManager.doTraining();
                    break;
                case 5:
                    menuDeletePlaeyr();
                    break;
                case 6:
                    menuDeleteSeveralPlaeyr();
                    break;
                case 9:
                    clubManager.getFootballClub().print();
                    break;
            }
        }
    }

    void menuDeleteSeveralPlaeyr(){
        System.out.println("Удаляем несколько игроков.");
        System.out.print("С какой позиции начинаем? ");
        int start = in.nextInt();
        System.out.print("До какой позиции включительно? ");
        int end = in.nextInt();

        for (int i = start < end? start:end; i <= (end > start? end:start); i++){
            clubManager.getPlayerArray().remove((start < end? start:end) - 1);
        }
    }

    void menuDeletePlaeyr() {
        System.out.print("Введите номер игрока для удаления: ");
        int plaeyrNumber = in.nextInt();
        boolean noSuchNumber = true;
        for (int i = 0; i < clubManager.getPlayerArray().size(); i++) {
            if (clubManager.getPlayerArray().get(i).getPlayerNumber() == plaeyrNumber) {
                clubManager.getPlayerArray().remove(i);
                noSuchNumber = false;
                break;
            }
        }
        if(noSuchNumber){
            System.out.println("Игрока под таким номером нету в команде.");
        }
    }

    void menuAddPlayer() {
        int characteristicA;
        int characteristicB;
        int characteristicC;
        System.out.print("Введите имя игрока: ");
        String firstName = in.next();

        System.out.print("Введите фамилию игрока: ");
        String lastName = in.next();

        System.out.print("Введите возраст игрока: ");
        int age = in.nextInt();

        System.out.print("Введите номер игрока: ");
        int number = in.nextInt();

        while (checkRepeatPlayerNumber(number)) {
            number = in.nextInt();
        }

        printMenuType();
        int choice = in.nextInt();

        switch (choice) {
            case 1:
                clubManager.addPlayer(firstName, lastName, number, age, PlayerType.GOALKEEPER);
                System.out.print("Характеристики? ");
                System.out.print("Мастерство: ");
                characteristicA = in.nextInt();
                clubManager.getLastPlaeyr().changeCharacteristicGoalkeeper(characteristicA > 10 ? 10 : characteristicA);
                break;
            case 2:
                clubManager.addPlayer(firstName, lastName, number, age, PlayerType.DEFENDER);
                System.out.print("Характеристики? ");
                System.out.print("Скорость: ");
                characteristicA = in.nextInt();
                System.out.print("Подкат: ");
                characteristicB = in.nextInt();
                System.out.print("Перехват: ");
                characteristicC = in.nextInt();
                clubManager.getLastPlaeyr().changeCharacteristicDefender(
                        characteristicA > 10 ? 10 : characteristicA,
                        characteristicB > 10 ? 10 : characteristicB,
                        characteristicB > 10 ? 10 : characteristicB);

                break;
            case 3:
                clubManager.addPlayer(firstName, lastName, number, age, PlayerType.MIDFIELDER);
                System.out.print("Характеристики? ");
                System.out.print("Скорость: ");
                characteristicA = in.nextInt();
                System.out.print("Пас: ");
                characteristicB = in.nextInt();
                System.out.print("Перехват: ");
                characteristicC = in.nextInt();
                clubManager.getLastPlaeyr().changeCharacteristicMidfielder(
                        characteristicA > 10 ? 10 : characteristicA,
                        characteristicB > 10 ? 10 : characteristicB,
                        characteristicB > 10 ? 10 : characteristicB);
                break;
            case 4:
                clubManager.addPlayer(firstName, lastName, number, age, PlayerType.ATTACKER);
                System.out.print("Характеристики? ");
                System.out.print("Скорость: ");
                characteristicA = in.nextInt();
                System.out.print("Сила удара: ");
                characteristicB = in.nextInt();
                System.out.print("Точность удара: ");
                characteristicC = in.nextInt();
                clubManager.getLastPlaeyr().changeCharacteristicAttacker(
                        characteristicA > 10 ? 10 : characteristicA,
                        characteristicB > 10 ? 10 : characteristicB,
                        characteristicB > 10 ? 10 : characteristicB);
                break;
        }
    }

    void printMenuType() {
        System.out.println("Выберети тип игрока.");
        System.out.println("1. Голкипер");
        System.out.println("2. Защитники");
        System.out.println("3. Полузащитники");
        System.out.println("4. Нападающие");
    }

    void menuCreatePlayer() {
        System.out.print("Введите имя игрока: ");
        String firstName = in.next();

        System.out.print("Введите фамилию игрока: ");
        String lastName = in.next();

        System.out.print("Введите возраст игрока: ");
        int age = in.nextInt();

        System.out.print("Введите номер игрока: ");
        int number = in.nextInt();


        while (checkRepeatPlayerNumber(number)) {
            number = in.nextInt();
        }

        printMenuType();
        int choice = in.nextInt();

        switch (choice) {
            case 1:
                clubManager.addPlayer(firstName, lastName, number, age, PlayerType.GOALKEEPER);
                break;
            case 2:
                clubManager.addPlayer(firstName, lastName, number, age, PlayerType.DEFENDER);
                break;
            case 3:
                clubManager.addPlayer(firstName, lastName, number, age, PlayerType.MIDFIELDER);
                break;
            case 4:
                clubManager.addPlayer(firstName, lastName, number, age, PlayerType.ATTACKER);
                break;
        }
    }

    boolean checkRepeatPlayerNumber(int number) {
        for (Player player : clubManager.getPlayerArray()) {
            if (player.getPlayerNumber() == number) {
                System.out.println("Игрок под таким номером уже есть");
                System.out.print("Введите другой номер игрока: ");
                return true;
            }
        }
        return false;
    }


}
