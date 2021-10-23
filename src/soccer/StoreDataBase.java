package soccer;

import soccer.player.Enum.PlayerType;
import soccer.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
* add: free player, football club, club manager
* delete: free player, football club, club manager
* */

public class StoreDataBase {
    private ArrayList<Player> playerFreeList;
    private ArrayList<FootballClub> footballClubsList;
    private ArrayList<ClubManager> clubManagerList;

    public StoreDataBase() {
        playerFreeList = new ArrayList<>();
        footballClubsList = new ArrayList<>();
        clubManagerList = new ArrayList<>();
        generatePlayer();
    }

    public void addFootballClubsList(FootballClub footballClubs) {

        footballClubsList.add(footballClubs);
    }

    public void addClubManager(ClubManager clubManager){
        clubManagerList.add(clubManager);
    }

    public void addFreePlayer(Player player){
        playerFreeList.add(player);
    }

    public Player deleteFreePlayer(int numPlayer){
        return playerFreeList.remove(numPlayer - 1);
    }

    public void printFreePlayer(){
        int i = 1;
        for (Player player : playerFreeList){
            System.out.println(i++ + " " + player.getInfo());
        }
    }

    public boolean chekFreeManager(){
        if (clubManagerList.size() == 0) {
            System.out.println("Нет менеджеров!");
            return false;
        }

        //chek for free manager
        for (ClubManager manager : clubManagerList) {
            if (manager.isFree()) {
                return true;
            }
        }
        System.out.println("Нет свободных менеджеров.");
        return false;
    }

    public ArrayList<Player> getPlayerFreeList() {
        return playerFreeList;
    }

    public void setPlayerFreeList(ArrayList<Player> playerFreeList) {
        this.playerFreeList = playerFreeList;
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
