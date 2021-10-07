package soccer;

import soccer.player.Enum.PlayerType;
import soccer.player.Player;
import soccer.player.PlayerList;

import java.util.ArrayList;

public class FootballClub {
    private String name;
    private String city;
    private Stadium stadium;
    private ArrayList<Player> playerArray;


    public FootballClub(String name, String city, String stadiumName, int capacity) {
        this.name = name;
        this.city = city;
        this.stadium = new Stadium(stadiumName, capacity);
        playerArray = new ArrayList<Player>();
    }

    void addPlayer(String firstName, String lastName, int playerNumber, int age, PlayerType type){
        playerArray.add( new Player(firstName, lastName, playerNumber, age, type));
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

    public void print() {
        int i = 1;
        System.out.println("Клуб: " + getName());
        System.out.println("Стадион: " + stadium.getName() + ". На " + stadium.getCapacity() + " человек.");
        System.out.println("Игроки");
        for (Player playar : playerArray) {
            System.out.println(i++ + " " +playar);
        }
    }
}
