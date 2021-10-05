package Soccer;

import Soccer.Player.Enum.PlayerType;
import Soccer.Player.Player;

import java.util.ArrayList;

public class ClubManager {
    private String name;
    private FootballClub footballClub;


    public ClubManager(String name) {
        this.name = name;
    }

    public void doTraining(){
        for(Player player : footballClub.getPlayerArray()){
            if(player.opportunityToTrain() > 0){
                player.doTrain();
            }else {
                System.out.println(player.getName() + " Натренирован по максимуму");
            }
        }
    }



    public FootballClub getFootballClub() {
        return footballClub;
    }

    public void setFootballClub(FootballClub footballClub) {
        this.footballClub = footballClub;
    }

    public String getName() {
        return name;
    }

    void addSeveralPlayer(ArrayList<Player> list) {
        getPlayerArray().addAll(list);
    }

    void addPlayer(String firstName, String lastName, int playerNumber, int age, PlayerType type) {
        footballClub.addPlayer(firstName, lastName, playerNumber, age, type);
    }

    public ArrayList<Player> getPlayerArray(){
        return footballClub.getPlayerArray();
    }

    public Player getLastPlaeyr(){
        return footballClub.getPlayerArray().get(footballClub.getPlayerArray().size() - 1);
    }



}
