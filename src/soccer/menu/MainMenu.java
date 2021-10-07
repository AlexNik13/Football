package soccer.menu;

import soccer.ClubManager;
import soccer.FootballClub;
import soccer.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class MainMenu {
    private final Scanner in = new Scanner(System.in);
    private ArrayList<Player> playerFreeList;
    private ArrayList<FootballClub> footballClubsList;
    private ArrayList<ClubManager> clubManagerList;

    public MainMenu() {
        playerFreeList = new ArrayList<>();
        footballClubsList = new ArrayList<>();
        clubManagerList = new ArrayList<>();
    }


    boolean mainMenu() {
        System.out.println("Главное меню\n____________");
        System.out.println("1. Создать клуб.");
        System.out.println("2. Создать менеджера.");
        System.out.println("3. Получить список клубов."); //do main club
        System.out.println("4. Получить список менеджеров."); //do main manager
        System.out.println("5. Меню игроков."); //do main player

        checkForNumber();
        int choice = in.nextInt();

        switch (choice) {
            case 0:
                return false;

            case 1:
                createFootballClub();
                break;
            case 2:
                createClubManager();
                break;

            case 3:
                printAllFootballClub();
                //TODO menu club
                break;

            case 4:
                printAllFootballClubClubManager();
                //TODO menu Manager
                break;
        }

        /*System.out.println("4. Создать свободного игрока.");
        System.out.println("5. Получить список свободных игроков.");
        System.out.println("6. Удалить свободного игрока.");
        System.out.println("7. Добавить свободного игрока в клуб.");
        System.out.println("8. Получить общее инфо по выбраному клубу.");
        System.out.println("9. Получить список игроков клуба.");
        System.out.println("0. Выход");*/

        return true;
    }

    private void printAllFootballClubClubManager() {
        int i = 1;
        for (ClubManager clubManager : clubManagerList){
            System.out.println(i++ + " " + clubManager.getName() + " Клуб: " + clubManager.getFootballClub().getName());
        }
    }

    void createClubManager(){
        System.out.print("Введите ФИО менеджера. ");
        String name = in.next();
        clubManagerList.add(new ClubManager(name));
    }

    public void printAllFootballClub(){
        int i = 1;
        for(FootballClub footballClubs : footballClubsList){
            System.out.println(i++ + " " + footballClubs.getName());
        }
    }

    void createFootballClub(){
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

    private void checkForNumber() {
        while (!in.hasNextInt()) {
            System.out.println("Некорректный ввод.");
            in.next();
        }
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

}
