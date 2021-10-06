package Soccer;

import Soccer.Player.Enum.PlayerType;
import Soccer.Player.Enum.Skill;
import Soccer.Player.Player;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private Scanner in = new Scanner(System.in);
    private ClubManager clubManager;
    private ArrayList<Player> playerList;
    private Player player;
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
        playerList = new ArrayList<>();
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
        checkForNumber();
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
            checkForNumber();
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

    void menuDeleteSeveralPlaeyr() {
        System.out.println("Удаляем несколько игроков.");
        System.out.print("С какой позиции начинаем? ");
        int start = in.nextInt();
        System.out.print("До какой позиции включительно? ");
        int end = in.nextInt();

        for (int i = Math.min(start, end); i <= (Math.max(end, start)); i++) {
            clubManager.getPlayerArray().remove((Math.min(start, end)) - 1);
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
        if (noSuchNumber) {
            System.out.println("Игрока под таким номером нету в команде.");
        }
    }

    private void menuAddPlayer() {
        player = createPlayer();
        player = changeCharacter(player);
        playerList.add(player);

        System.out.println("Добавить еще игрока? \n\t1. Да \n\t2. Нет");
        checkForNumber();
        int choice = in.nextInt();

        if (choice == 1) {
            menuAddPlayer();
        } else {
            clubManager.addSeveralPlayer(playerList);
            playerList.clear();
        }
    }

    private void printMenuType() {
        System.out.println("Выберети тип игрока.");
        System.out.println("1. Голкипер");
        System.out.println("2. Защитники");
        System.out.println("3. Полузащитники");
        System.out.println("4. Нападающие");
    }

    private void menuCreatePlayer() {
        playerList.add(createPlayer());

        System.out.println("Создать еще игрока? \n\t1. Да \n\t2. Нет");
        checkForNumber();
        int choice = in.nextInt();

        if (choice == 1) {
            menuCreatePlayer();
        } else {
            clubManager.addSeveralPlayer(playerList);
            playerList.clear();
        }
    }

    private Player createPlayer(){
        System.out.print("Введите имя игрока: ");
        String firstName = in.next();

        System.out.print("Введите фамилию игрока: ");
        String lastName = in.next();

        System.out.print("Введите возраст игрока: ");
        checkForNumber();
        int age = in.nextInt();

        System.out.print("Введите номер игрока: ");
        checkForNumber();
        int number = in.nextInt();

        while (checkRepeatPlayerNumber(number)) {
            checkForNumber();
            number = in.nextInt();
        }

        printMenuType();
        checkForNumber();
        int choice = in.nextInt();

        switch (choice) {
            case 1:
                player = new Player(firstName, lastName, number, age, PlayerType.GOALKEEPER);
                break;
            case 2:
                player = new Player(firstName, lastName, number, age, PlayerType.DEFENDER);
                break;
            case 3:
                player = new Player(firstName, lastName, number, age, PlayerType.MIDFIELDER);
                break;
            case 4:
                player = new Player(firstName, lastName, number, age, PlayerType.ATTACKER);
                break;
        }
        return player;
    }

    private Player changeCharacter(Player player) {
        System.out.print("Характеристики? \n");
        for (Map.Entry<Skill, Integer> skil : player.getCharacteristic().entrySet()) {
            System.out.print(skil.getKey() + " = ");
            checkForNumber();
            player.getCharacteristic().put(skil.getKey(), Math.min(10, in.nextInt()));
        }
        return player;
    }

    private boolean checkRepeatPlayerNumber(int number) {
        for (Player player : clubManager.getPlayerArray()) {
            if (player.getPlayerNumber() == number) {
                System.out.println("Игрок под таким номером уже есть");
                System.out.print("Введите другой номер игрока: ");
                return true;
            }
        }
        return false;
    }

    private void checkForNumber() {
        while (!in.hasNextInt()) {
            System.out.println("Некорректный ввод.");
            in.next();
        }
    }
}
