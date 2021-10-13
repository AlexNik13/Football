package soccer;

import soccer.player.Enum.PlayerType;
import soccer.player.Player;

import java.util.ArrayList;

public class FootballClub {
    private String name;
    private String city;
    private Stadium stadium;
    private ArrayList<Player> playerArray;
    private boolean manager = false;
    private ClubManager clubManager;

    public FootballClub(String name, String city, String stadiumName, int capacity) {
        this.name = name;
        this.city = city;
        this.stadium = new Stadium(stadiumName, capacity);
    }
    {
        playerArray = new ArrayList<Player>();
    }

    public void addPlayer(Player player){
        playerArray.add(player);
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public ArrayList<Player> getPlayerArray() {
        return playerArray;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public ClubManager getClubManager() {
        return clubManager;
    }

    public void setClubManager(ClubManager clubManager) {
        this.clubManager = clubManager;
    }

    public void print() {
        int i = 1;
        System.out.println("Клуб: " + getName());
        System.out.println("Стадион: " + stadium.getName() + ". На " + stadium.getCapacity() + " человек.");
        System.out.println("Игроки");
        for (Player playar : playerArray) {
            System.out.println(i++ + " " +playar);
        }
    }

    public void printPlayer() {
        int i = 1;
        System.out.println("Игроки");
        for (Player playar : playerArray) {
            System.out.println(i++ + " " +playar);
        }
    }

    public void printInfo(){
        System.out.println("Клуб: " + getName());
        System.out.println("Стадион: " + stadium.getName() + ". На " + stadium.getCapacity() + " человек.");
        System.out.println("Количество игроков: " + playerArray.size());
    }
}
