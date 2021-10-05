package Soccer.Player;

import Soccer.Player.Enum.PlayerType;
import Soccer.Player.Enum.PositionType;
import Soccer.Player.Enum.Skill;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Player {
    private String firstName;
    private String lastName;
    private int playerNumber;
    private int age;
    private PlayerType type;
    private Map<Skill, Integer> characteristic;
    private Set<PositionType> position;

    public Player(String firstName, String lastName, int playerNumber, int age, PlayerType type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.playerNumber = playerNumber;
        this.age = age;
        this.type = type;
        position = new HashSet<>();
        characteristic = new TreeMap<>();
    }

    public int opportunityToTrain(){
        int size = characteristic.size() * 10;
        for (int i = 0; i < characteristic.size(); i++){
            size -= characteristic.get(characteristic.keySet().toArray()[i]) ;
        }
        return size;
    }

    public void doTrain(){
        while (true){
           int i = (int)( Math.random()*1000) % characteristic.size();

           if(characteristic.get(characteristic.keySet().toArray()[i]) < 10){
              characteristic.put((Skill) characteristic.keySet().toArray()[i], characteristic.get(characteristic.keySet().toArray()[i]) + 1);
               System.out.println(this.getName() + " " + characteristic.keySet().toArray()[i] + " +1");
              break;
           }
        }
    }

    public PlayerType getType() {
        return type;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<Skill, Integer> getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Skill skill, int skillNum) {
        characteristic.put(skill, skillNum);
    }

    public Set<PositionType> getPosition() {
        return position;
    }

    public void setPosition(PositionType type) {
        this.position.add(type);
    }

    @Override
    public String toString() {

        return this.getName() + ". Номер: " + this.getPlayerNumber() + " " + this.getType().toString().toLowerCase() + ". Позиция: " + this.getPosition()
                + ". Характеристики: " + this.getCharacteristic();
    }
}
