package soccer.controller;

import soccer.ClubManager;
import soccer.Creator;
import soccer.FootballClub;
import soccer.StoreDataBase;
import soccer.player.Enum.ConditionPlayer;
import soccer.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private final Scanner in = new Scanner(System.in);
    private StoreDataBase storeDataBase;

    public Controller() {
        this.storeDataBase = new StoreDataBase();
    }

    public Scanner getIn() {
        return in;
    }

    protected void createFootballClub() {
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

    protected void createClubManager() {
        storeDataBase.addClubManager(Creator.createClubManager());
    }

    protected void createFreePlayer() {
        storeDataBase.addFreePlayer(Creator.createFreePlayer());
    }

    protected void deleteFreePlayer() {
        System.out.println("Введите игрока для удаления от 1 до " + storeDataBase.getPlayerFreeList().size());
        checkForNumber();
        int playerNumber = in.nextInt();
        storeDataBase.deleteFreePlayer(playerNumber);
    }

    protected void deleteSeveralFreePlayer() {
        System.out.println("Удаляем несколько игроков.");
        System.out.print("С какой позиции начинаем? ");
        int start = in.nextInt();
        System.out.print("До какой позиции включительно? ");
        int end = in.nextInt();
        for (int i = Math.min(start, end); i <= (Math.max(end, start)); i++) {
            storeDataBase.deleteFreePlayer((Math.min(start, end)));
        }
    }

    protected void moveFreePlayerToClub() {
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

    protected void createPlayerInClub(ClubManager clubManager) {
        ArrayList<Player> playerList = Creator.createPlayerInClub(clubManager);
        clubManager.addSeveralPlayer(playerList);
    }

    protected void addPlayerFromFree(ClubManager clubManager) {
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
    }

    protected void deletePlaeyrFromClub(ClubManager clubManager) {
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

    protected void deleteSeveralPlaeyrFromClub(ClubManager clubManager) {
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

    protected void addManagerToTheClub(FootballClub footballClub) {
        int id = 1;
        if (footballClub.isManager()) {
            System.out.println("В клубе есть менеджер!");
            return;
        }
        if (!storeDataBase.chekFreeManager()) {
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

    protected void checkForNumber() {
        while (!in.hasNextInt()) {
            System.out.println("Некорректный ввод.");
            in.next();
        }
    }

    public void printAllFootballClub() {
        int i = 1;
        if (storeDataBase.getFootballClubsList().size() == 0) System.out.println("Нет не одного клуба");
        for (FootballClub footballClubs : storeDataBase.getFootballClubsList()) {
            System.out.println(i++ + " " + footballClubs.getName() + " - Менеджер: " + (footballClubs.isManager() ? "есть" : "нет"));
        }
    }

    protected ArrayList<ClubManager> getClubManagerList() {
        return storeDataBase.getClubManagerList();
    }

    protected ArrayList<FootballClub> getFootballClubsList() {
        return storeDataBase.getFootballClubsList();
    }

    protected void printFreePlayer() {
        storeDataBase.printFreePlayer();
    }
}