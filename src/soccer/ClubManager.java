package soccer;

import soccer.player.Player;

import java.util.ArrayList;
import java.util.List;

public class ClubManager {
    private String name;
    private FootballClub footballClub;
    private boolean free = true;


    public ClubManager(String name) {
        this.name = name;
    }

    public void doTraining(){
        for(Player player : footballClub.getPlayerArray()){
            if(player.opportunityToTrain()){
                player.doTrain();
            }else {
                System.out.println(player.getName() + " Натренирован по максимуму");
            }
        }
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
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

    public void addSeveralPlayer(List<Player> list) {
        getPlayerArray().addAll(list);
    }

    public ArrayList<Player> getPlayerArray(){
        return footballClub.getPlayerArray();
    }


}
