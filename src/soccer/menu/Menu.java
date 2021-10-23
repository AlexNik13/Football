package soccer.menu;

import soccer.ClubManager;
import soccer.Creator;
import soccer.FootballClub;
import soccer.StoreDataBase;
import soccer.player.Enum.ConditionPlayer;
import soccer.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private final Scanner in = new Scanner(System.in);
    private StoreDataBase storeDataBase;

    public Menu() {
        this.storeDataBase = new StoreDataBase();
    }

    public boolean menuMain() {
        System.out.println("Главное меню\n____________");
        System.out.println("1. Создать клуб.");
        System.out.println("2. Получить список клубов.");
        System.out.println("3. Создать менеджера.");
        System.out.println("4. Получить список менеджеров.");
        System.out.println("5. Создать свободного игрока.");
        System.out.println("6. Получить список свободных игроков.");
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
                subMenuFootballClub();
                break;
            case 3:
                storeDataBase.addClubManager(Creator.createClubManager());
                break;
            case 4:
                subMenuManager();
                break;
            case 5:
                storeDataBase.addFreePlayer(Creator.createFreePlayer());
                break;
            case 6:
                storeDataBase.printFreePlayer();
                while (menuFreePlayer()) ;
                break;
        }
        return true;
    }


    private void createFootballClub() {
        FootballClub footballClub = Creator.createFootballClub();
        while (footballClub.getPlayerArray().size() < 11) {
            System.out.println("В клубе должно быть минимум 11 игроков. У вас " + (footballClub.getPlayerArray().size() + 1) + "игроков");
            addPlayerToClub(footballClub);
        }
        storeDataBase.addFootballClubsList(footballClub);
    }

    private void addPlayerToClub(FootballClub footballClub) {
        storeDataBase.printFreePlayer();
        checkForNumber();
        int playerPosition = in.nextInt();
        Player player;
        player = storeDataBase.deleteFreePlayer(playerPosition);
        player.setCondition(ConditionPlayer.IN_CLUB);
        footballClub.addPlayer(player);
    }

    private boolean menuFreePlayer() {
        System.out.println("Меню свободных игроков\n______________________");
        System.out.println("1. Добавить свободного игрока.");
        System.out.println("2. Удалить свободного игрока.");
        System.out.println("3. Удалить несколько свободных игрокв.");
        System.out.println("4. Переместить свободного игрока в клуб.");
        System.out.println("0. Вернуться в главное меню");
        checkForNumber();
        int choice = in.nextInt();

        switch (choice) {
            case 0:
                return false;
            case 1:
                storeDataBase.addFreePlayer(Creator.createFreePlayer());
                break;
            case 2:
                subMenuPlayerDeleteFreePlayer();
                break;
            case 3:
                subMenuPlayerDeleteSeveralFreePlayer();
                break;
            case 4:
                moveFreePlayerToClub();
                break;
        }
        storeDataBase.printFreePlayer();
        return true;
    }

    private boolean menuManager(ClubManager clubManager) {
        System.out.println("Главное меню");
        System.out.println("1. Создать игрока");
        System.out.println("2. Добавить игрока");
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
                return false;
            case 1:
                clubManager.addSeveralPlayer(Creator.createArrayPlayerInClub(clubManager));
                break;
            case 2:
                clubManager.addSeveralPlayer(addPlayerFromFree(clubManager));
                break;
            case 4:
                clubManager.doTraining();
                break;
            case 5:
                subMenuManagerDeletePlaeyr(clubManager);
                break;
            case 6:
                menuDeleteSeveralPlaeyr(clubManager);
                break;
            case 9:
                clubManager.getFootballClub().print();
                break;
        }
        return true;
    }

    void menuDeleteSeveralPlaeyr(ClubManager clubManager) {
        Player player;
        clubManager.getFootballClub().printPlayer();
        System.out.println("Удаляем несколько игроков.");
        System.out.print("С какой позиции начинаем? ");
        int start = in.nextInt();
        System.out.print("До какой позиции включительно? ");
        int end = in.nextInt();
        if ((clubManager.getPlayerArray().size() - Math.abs(end - start)) > 11) {
            for (int i = Math.min(start, end); i <= (Math.max(end, start)); i++) {
                player = clubManager.deletePlayer((Math.min(start, end)));
                player.setCondition(ConditionPlayer.FREE);
                storeDataBase.addFreePlayer(player);
            }
        } else {
            System.out.println("В клубе не может быть меньше 11 игроков.");
        }
    }

    void subMenuManagerDeletePlaeyr(ClubManager clubManager) {
        Player player;
        if (clubManager.getPlayerArray().size() > 11) {
            clubManager.getFootballClub().printPlayer();
            System.out.print("Введите номер игрока для удаления: ");
            int plaeyrNumber = in.nextInt();
            boolean noSuchNumber = true;

            for (int i = 0; i < clubManager.getPlayerArray().size(); i++) {
                if (clubManager.getPlayerArray().get(i).getPlayerNumber() == plaeyrNumber) {
                    player = clubManager.deletePlayer(i + 1);
                    player.setCondition(ConditionPlayer.FREE);
                    storeDataBase.addFreePlayer(player);
                    noSuchNumber = false;
                    break;
                }
            }

            if (noSuchNumber) {
                System.out.println("Игрока под таким номером нету в команде.");
            }
        } else {
            System.out.println("В клубе не может быть меньше 11 игроков.");
        }
    }

    private ArrayList<Player> addPlayerFromFree(ClubManager clubManager) {
        ArrayList<Player> playerList = new ArrayList<>();
        Player player;
        int choice = 0;
        int i = 1;
        do {
            if ((clubManager.getPlayerArray().size() + playerList.size()) < 21) {
                System.out.println("Игроки:");
                for (Player footballer : storeDataBase.getPlayerFreeList()) {
                    System.out.println(i++ + ". " + footballer.getInfo());
                }
                System.out.println("Выберете игрока: ");
                checkForNumber();
                int numberPlayer = in.nextInt();
                player = storeDataBase.deleteFreePlayer(numberPlayer);
                player.setCondition(ConditionPlayer.IN_CLUB);
                while (Creator.checkRepeatPlayerNumber(player.getPlayerNumber(), clubManager, playerList)) {
                    checkForNumber();
                    numberPlayer = in.nextInt();
                    player.setPlayerNumber(numberPlayer);
                }
                playerList.add(player);
                System.out.println("Добавить еще игрока? \n\t1. Да \n\t2. Нет");
                checkForNumber();
                choice = in.nextInt();
            } else {
                System.out.println("В клубе не может быть более 21 игрока.");
            }
        } while (choice != 2);
        clubManager.addSeveralPlayer(playerList);
        return playerList;
    }

    private void moveFreePlayerToClub() {
        System.out.print("Введите номер игрока: ");
        checkForNumber();
        int playerNumber = in.nextInt();
        printAllFootballClub();
        System.out.print("Введите имя клуба: ");
        String name = in.next();
        for (FootballClub footballClub : storeDataBase.getFootballClubsList()) {
            if (footballClub.getName().equalsIgnoreCase(name)) {
                if (footballClub.getPlayerArray().size() < 21) {
                    Player player = storeDataBase.getPlayerFreeList().get(playerNumber - 1);
                    player.setCondition(ConditionPlayer.IN_CLUB);
                    footballClub.addPlayer(player);
                    storeDataBase.deleteFreePlayer(playerNumber);
                } else {
                    System.out.println("В клубе не может быть более 21 игрока.");
                }
                break;
            }
        }
    }

    private void subMenuPlayerDeleteSeveralFreePlayer() {
        System.out.println("Удаляем несколько игроков.");
        System.out.print("С какой позиции начинаем? ");
        int start = in.nextInt();
        System.out.print("До какой позиции включительно? ");
        int end = in.nextInt();
        for (int i = Math.min(start, end); i <= (Math.max(end, start)); i++) {
            storeDataBase.deleteFreePlayer((Math.min(start, end)));
        }
    }

    private void subMenuPlayerDeleteFreePlayer() {
        System.out.println("Введите игрока для удаления от 1 до " + storeDataBase.getPlayerFreeList().size());
        checkForNumber();
        int playerNumber = in.nextInt();
        storeDataBase.deleteFreePlayer(playerNumber);
    }


    private void subMenuManager() {
        int i = 1;
        for (ClubManager clubManager : storeDataBase.getClubManagerList()) {
            System.out.println(i++ + ". " + clubManager.getName() + ". Клуб: " + (clubManager.isFree() ? "нет" : clubManager.getFootballClub().getName()));
        }

        System.out.print("Введите номер менеджера или 0. для возврата назад: ");
        checkForNumber();
        int numberManager = in.nextInt();
        if (!storeDataBase.getClubManagerList().get(numberManager - 1).isFree()) {
            while (menuManager(storeDataBase.getClubManagerList().get(numberManager - 1))) ;
        } else {
            System.out.println("У менеджера нет клуба.");
        }
    }

    public boolean menuFootballClub(FootballClub footballClub) {
        //footballClub.printInfo();
        System.out.println("\nМеню футбольного клуба\n______________________");
        System.out.println("1. Получить общее инфо по выбраному клубу.");
        System.out.println("2. Получить список игроков клуба.");
        System.out.println("3. Добавить менеджера в клуб.");
        System.out.println("0. Вернуться в главное меню");
        checkForNumber();
        int choice = in.nextInt();
        switch (choice) {
            case 0:
                return false;
            case 1:
                footballClub.printInfo();
                break;
            case 2:
                footballClub.printPlayer();
                break;
            case 3:
                addManagerToTheClub(footballClub);
                break;
        }
        return true;
    }

    private void addManagerToTheClub(FootballClub footballClub) {
        int id = 1;
        if (footballClub.isManager()) {
            System.out.println("В клубе есть менеджер!");
            return;
        }

        if(!storeDataBase.chekFreeManager()){
            return;
        }

        for (ClubManager clubManager : storeDataBase.getClubManagerList()) {
            System.out.println(id++ + " " + clubManager.getName() + " " + (clubManager.isFree() ? "Свободен" : "Занят"));
        }
        System.out.print("\nВыберете менеджера или введите 0 для возвращения назад:");
        int inManager;
        checkForNumber();
        inManager = in.nextInt();
        if (storeDataBase.getClubManagerList().get(inManager - 1).isFree()) {
            storeDataBase.getClubManagerList().get(inManager - 1).setFootballClub(footballClub);
            footballClub.setManager(true);
        }
    }

    private void subMenuFootballClub() {
        printAllFootballClub();
        System.out.println("Введите имя клуба или 0 чтоб вернуться назад.\n");
        String name = in.next();

        if (!name.equals("0")) {
            for (FootballClub footballClub : storeDataBase.getFootballClubsList()) {
                if (footballClub.getName().equalsIgnoreCase(name)) {
                    while (menuFootballClub(footballClub)) ;
                    break;
                }
            }
        }
    }

    public void printAllFootballClub() {
        int i = 1;
        if (storeDataBase.getFootballClubsList().size() == 0) System.out.println("Нет не одного клуба");
        for (FootballClub footballClubs : storeDataBase.getFootballClubsList()) {
            System.out.println(i++ + " " + footballClubs.getName() + " - Менеджер: " + (footballClubs.isManager() ? "есть" : "нет"));
        }
    }

    private void checkForNumber() {
        while (!in.hasNextInt()) {
            System.out.println("Некорректный ввод.");
            in.next();
        }
    }
}
