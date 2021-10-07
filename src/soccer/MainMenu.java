package soccer;

import soccer.player.Enum.PlayerType;
import soccer.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MainMenu {
    private final Scanner in = new Scanner(System.in);
    private ArrayList<Player> playerFreeList;
    private ArrayList<FootballClub> footballClubsList;
    private ArrayList<ClubManager> clubManagerList;
    private Player player;


    public MainMenu() {
        playerFreeList = new ArrayList<>();
        footballClubsList = new ArrayList<>();
        clubManagerList = new ArrayList<>();
        generatePlayer();
        System.out.println("Добро пожаловать в игру\n");
    }


    /*boolean mainMenu() {
        System.out.println("Главное меню\n____________");
        System.out.println("1. Управление игроками.");
        System.out.println("2. Управление менеджерами.");
        System.out.println("3. Управление клубами.");
        checkForNumber();
        int choice = in.nextInt();

        switch (choice) {
            case 0:
                return false;

            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

        }
        return true;
    }*/

    boolean menuPlayer() {
        System.out.println("Меню свободных игроков\n______________________");
        System.out.println("1. Добавить свободного игрока.");
        System.out.println("2. Удалить свободного игрока.");
        System.out.println("3. Удалить несколько свободных игрокв.");
        System.out.println("4. Добавить свободного игрока в клуб.");
        System.out.println("0. Вернуться в главное меню");

        checkForNumber();
        int choice = in.nextInt();

        switch (choice) {
            case 0:
                return false;
            case 1:
                createFreePlayer();
                break;
            case 2:
                menuDeleteFreePlaeyr();
                break;

            case 3:
                menuDeleteSeveralFreePlaeyr();
                break;

            case 4:
                addPlayerToClub();
                break;
        }
        printFreePlayer();
        return true;
    }

    boolean menuManager() {
        System.out.println("Меню менеджеров\n______________________");
        System.out.println("1. Создать менеджера.");
        System.out.println("2. Удалить менеджера");
        System.out.println("3. Показать всех менеджеров.");
        System.out.println("4. Добавить менеджера в клуб.");

        return true;
    }

    boolean menuClub() {
        System.out.println("Меню менеджеров\n______________________");
        System.out.println("1. Получить общее инфо по выбраному клубу.");
        System.out.println("2. Получить список игроков клуба.");
        System.out.println("0. Вернуться в главное меню");

        checkForNumber();
        int choice = in.nextInt();

        switch (choice) {
            case 0:
                return false;

            case 1:
                System.out.println("Получить общее инфо по выбраному клубу\n");
                System.out.print("Введите имя клуба: ");
                String name = in.next();
                if (!printFootballClub(name)) {
                    System.out.println("Не правильное имя клуба.");
                }
                break;
        }
        return true;
    }

    boolean menuMain() {
        System.out.println("Главное меню\n____________");
        System.out.println("1. Создать клуб.");
        System.out.println("2. Получить список клубов.");
        System.out.println("3. Cоздать свободного игрока");
        System.out.println("4. Получить список свободных игроков");

        System.out.println("0. Выход");

        checkForNumber();
        int choice = in.nextInt();

        switch (choice) {
            case 0:
                return false;

            case 1:
                createFootballClub();
                break;
            case 2:
                printAllFootballClub();
                menuClub();
                break;

            case 3:
                createFreePlayer();
                break;

            case 4:
                printFreePlayer();
                while (menuPlayer()) ;
                break;

            case 5:
                break;
        }

        return true;
    }

    private void addPlayerToClub() {
        System.out.print("Введите номер игрока: ");
        checkForNumber();
        int choice = in.nextInt();

    }

    private void menuDeleteSeveralFreePlaeyr() {
        System.out.println("Удаляем несколько игроков.");
        System.out.print("С какой позиции начинаем? ");
        int start = in.nextInt();
        System.out.print("До какой позиции включительно? ");
        int end = in.nextInt();

        for (int i = Math.min(start, end); i <= (Math.max(end, start)); i++) {
            playerFreeList.remove((Math.min(start, end)) - 1);
        }
    }

    private void menuDeleteFreePlaeyr() {
        System.out.print("Введите номер игрока для удаления: ");
        checkForNumber();
        int playerNumber = in.nextInt();

        boolean noSuchNumber = true;
        for (Player player : playerFreeList) {
            if (player.getPlayerNumber() == playerNumber) {
                playerFreeList.remove(player);
                noSuchNumber = false;
                break;
            }
        }
        if (noSuchNumber) {
            System.out.println("Игрока под таким номером нету.");
        }
    }

    private void createFreePlayer() {
        playerFreeList.add(createPlayer());

        System.out.println("Создать еще игрока? \n\t1. Да \n\t2. Нет");
        checkForNumber();
        int choice = in.nextInt();

        if (choice == 1) {
            createFreePlayer();
        }
    }

    private Player createPlayer() {
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

    public void printAllFootballClubClubManager() {
        int i = 1;
        for (ClubManager clubManager : clubManagerList) {
            System.out.println(i++ + " " + clubManager.getName() + " Клуб: " + clubManager.getFootballClub().getName());
        }
    }

    private void createClubManager() {
        System.out.print("Введите ФИО менеджера. ");
        String name = in.next();
        clubManagerList.add(new ClubManager(name));
    }

    public void printAllFootballClub() {
        int i = 1;
        for (FootballClub footballClubs : footballClubsList) {
            System.out.println(i++ + " " + footballClubs.getName());
        }


    }


    public boolean printFootballClub(String name) {
        for (FootballClub footballClub : footballClubsList) {
            if (footballClub.getName().toLowerCase().equals(name.toLowerCase())) {
                footballClub.printInfo();
                System.out.println("Показать игроков \n1. Да \n2. Нет");
                checkForNumber();
                int choice = in.nextInt();
                if (choice == 1){
                    footballClub.printPlayer();
                }
                    return true;
            }
        }
        return false;
    }

    private void createFootballClub() {
        System.out.print("Введите название вашего футбольного клуба. ");
        String name = in.next();

        System.out.print("В каком городе находиться ваш клуб. ");
        String city = in.next();

        System.out.print("Название вашего стадиона? ");
        String stadiumName = in.next();

        System.out.print("Вместимость вашего стадиона: ");
        checkForNumber();
        int capacity = in.nextInt();

        footballClubsList.add(new FootballClub(name, city, stadiumName, capacity));
    }

    private boolean checkRepeatPlayerNumber(int number) {
        for (Player player : playerFreeList) {
            if (player.getPlayerNumber() == number) {
                System.out.println("Игрок под таким номером уже есть");
                System.out.print("Введите другой номер игрока: ");
                return true;
            }
        }
        return false;
    }

    private void printFreePlayer() {
        System.out.println("Игроки");
        int i = 1;
        for (Player player : playerFreeList) {
            System.out.println(i++ + " " + player.toString());
        }
    }

    private void checkForNumber() {
        while (!in.hasNextInt()) {
            System.out.println("Некорректный ввод.");
            in.next();
        }
    }

    private void printMenuType() {
        System.out.println("Выберети тип игрока.");
        System.out.println("1. Голкипер");
        System.out.println("2. Защитники");
        System.out.println("3. Полузащитники");
        System.out.println("4. Нападающие");
    }

    public ArrayList<Player> getPlayerList() {
        return playerFreeList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerFreeList = playerList;
    }

    public ArrayList<FootballClub> getFootballClubsList() {
        return footballClubsList;
    }

    public void setFootballClubsList(ArrayList<FootballClub> footballClubsList) {
        this.footballClubsList = footballClubsList;
    }

    public ArrayList<ClubManager> getClubManagerList() {
        return clubManagerList;
    }

    public void setClubManagerList(ArrayList<ClubManager> clubManagerList) {
        this.clubManagerList = clubManagerList;
    }

    private void generatePlayer() {
        String str[] = {"Аарон", "Абрам", "Аваз", "Августин", "Авраам", "Агап", "Агапит", "Агат", "Агафон", "Адам", "Адриан", "Азамат", "Азат", "Азиз", "Аид", "Айдар", "Айрат", "Акакий", "Аким", "Алан", "Александр", "Алексей", "Али", "Алик", "Алим", "Алихан", "Алишер", "Алмаз", "Альберт", "Амир", "Амирам", "Амиран", "Анар", "Анастасий", "Анатолий", "Анвар", "Ангел", "Андрей", "Анзор", "Антон", "Анфим", "Арам", "Аристарх", "Аркадий", "Арман", "Армен", "Арсен", "Арсений", "Арслан", "Артём", "Артемий", "Артур", "Архип", "Аскар", "Аслан", "Асхан"};
        Random rnd = new Random();
        List<PlayerType> type = new ArrayList<>();

        type.add(PlayerType.ATTACKER);
        type.add(PlayerType.DEFENDER);
        type.add(PlayerType.GOALKEEPER);
        type.add(PlayerType.MIDFIELDER);

        for (int i = 0; i < 50; i++) {
            playerFreeList.add(new Player(str[rnd.nextInt(56)], str[rnd.nextInt(56)], i + 1, rnd.nextInt(20) + 16, type.get(rnd.nextInt(4))));
        }
    }

}
