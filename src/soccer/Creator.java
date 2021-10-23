package soccer;

import soccer.player.Enum.ConditionPlayer;
import soccer.player.Enum.PlayerType;
import soccer.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Creator {
    private static Scanner in = new Scanner(System.in);

    public static ClubManager createClubManager() {
        System.out.print("Введите ФИО менеджера. ");
        String name = in.next();
        return new ClubManager(name);
    }

    public static FootballClub createFootballClub() {
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

    public static boolean checkRepeatPlayerNumber(int number, ClubManager clubManager, List<Player> playerList) {
        try {
            for (Player player : clubManager.getPlayerArray()) {
                if (player.getPlayerNumber() == number) {
                    System.out.println("Игрок под таким номером уже есть");
                    System.out.print("Введите другой номер игрока: ");
                    return true;
                }
            }
            for (Player player : playerList) {
                if (player.getPlayerNumber() == number) {
                    System.out.println("Игрок под таким номером уже есть");
                    System.out.print("Введите другой номер игрока: ");
                    return true;
                }
            }
        } catch (NullPointerException ex) {
            return false;
        }
        return false;
    }

    public static ArrayList<Player> createArrayPlayerInClub(ClubManager clubManager) {
        ArrayList<Player> playerList = new ArrayList<>();
        int choice = 0;

        do {
            if ((clubManager.getPlayerArray().size() + playerList.size()) < 21) {
                Player player = createFreePlayer();
                player.setCondition(ConditionPlayer.IN_CLUB);
                while (checkRepeatPlayerNumber(player.getPlayerNumber(), clubManager, playerList)) ;
                playerList.add(player);
                System.out.println("Создать еще игрока? \n\t1. Да \n\t2. Нет");
                checkForNumber();
                choice = in.nextInt();
            } else {
                System.out.println("В клубе не может быть более 21 игрока.");
            }
        } while (choice == 1);

        return playerList;
    }


    public static Player createFreePlayer() {
        Player player;
        System.out.print("Введите имя игрока: ");
        String firstName = in.next();
        System.out.print("Введите фамилию игрока: ");
        String lastName = in.next();
        System.out.print("Введите возраст игрока: ");
        checkForNumber();
        int age = in.nextInt();
        System.out.print("Введите номер игрока: ");

        int number = 0;
        int choice;
        do {
            printMenuType();
            checkForNumber();
            choice = in.nextInt();
        } while (choice < 1 || choice > 4);

        switch (choice) {
            case 1:
                return new Player(firstName, lastName, number, age, PlayerType.GOALKEEPER);

            case 2:
                return new Player(firstName, lastName, number, age, PlayerType.DEFENDER);

            case 3:
                return new Player(firstName, lastName, number, age, PlayerType.MIDFIELDER);

            case 4:
                return new Player(firstName, lastName, number, age, PlayerType.ATTACKER);
        }

        return null;
    }

    private static void printMenuType() {
        System.out.println("Выберети тип игрока.");
        System.out.println("1. Голкипер");
        System.out.println("2. Защитники");
        System.out.println("3. Полузащитники");
        System.out.println("4. Нападающие");
    }


    private static void checkForNumber() {
        while (!in.hasNextInt()) {
            System.out.println("Некорректный ввод.");
            in.next();
        }
    }
}
