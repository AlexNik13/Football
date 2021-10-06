package Soccer.Player;

import Soccer.Player.Enum.PlayerType;
import Soccer.Player.Enum.PositionType;
import Soccer.Player.Enum.Skill;

import java.text.Format;
import java.util.*;

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
        switch (type){
            case ATTACKER -> addNewCharacteristicAttacker();
            case DEFENDER -> addNewCharacteristicDefender();
            case GOALKEEPER ->  addNewCharacteristicGoalkeeper();
            case MIDFIELDER ->  addNewCharacteristicMidfielder();
        }
    }

    public void addNewCharacteristicAttacker(){
        characteristic.put(Skill.SPEED, 1);
        characteristic.put(Skill.STRENGTH, 1);
        characteristic.put(Skill.ACCURACY, 1);
    }
    public void addNewCharacteristicDefender(){
        characteristic.put(Skill.SPEED, 1);
        characteristic.put(Skill.PASS, 1);
        characteristic.put(Skill.INTERCEPTION, 1);
    }
    public void addNewCharacteristicMidfielder(){
        characteristic.put(Skill.SPEED, 1);
        characteristic.put(Skill.TACKLE, 1);
        characteristic.put(Skill.INTERCEPTION, 1);
    }
    public void addNewCharacteristicGoalkeeper(){
        characteristic.put(Skill.MASTERY, 1);
        position.add(PositionType.GATE);
    }

    public Map<Skill, Integer> changeCharacteristic(Skill key, Integer value) {
        this.characteristic.replace(key, value);
        return this.characteristic;
    }


    public void changeCharacteristicAttacker(int characteristicA, int characteristicB, int characteristicC){
        characteristic.put(Skill.SPEED, characteristicA);
        characteristic.put(Skill.STRENGTH, characteristicB);
        characteristic.put(Skill.ACCURACY, characteristicC);
    }
    public void changeCharacteristicDefender(int characteristicA, int characteristicB, int characteristicC){
        characteristic.put(Skill.SPEED, characteristicA);
        characteristic.put(Skill.PASS, characteristicB);
        characteristic.put(Skill.INTERCEPTION, characteristicC);
    }
    public void changeCharacteristicMidfielder(int characteristicA, int characteristicB, int characteristicC){
        characteristic.put(Skill.SPEED, characteristicA);
        characteristic.put(Skill.TACKLE, characteristicB);
        characteristic.put(Skill.INTERCEPTION, characteristicC);
    }
    public void changeCharacteristicGoalkeeper(int characteristicA){
        characteristic.put(Skill.MASTERY, characteristicA);
    }

    public boolean opportunityToTrain(){
        for (Skill key : characteristic.keySet()) {
           int value = characteristic.get(key);
           if (value < 10) {
               return true;
           }
        }
        return false;

//        int size = characteristic.size() * 10;
//        for (int i = 0; i < characteristic.size(); i++){
//            size -= characteristic.get(characteristic.keySet().toArray()[i]) ;
//        }
//        return size;
        }

    public void doTrain(){
       Set<Skill> skills = characteristic.keySet();
       List<Skill> skills1 = new ArrayList<>(skills);

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
        String characteristic = "";

        for (Map.Entry<Skill, Integer> playerCharacteristic : this.getCharacteristic().entrySet()){
            characteristic += playerCharacteristic + " ";
        }

        Formatter str = new Formatter();
        str.format(this.getName() + ". Номер: " + this.getPlayerNumber() + " " + this.getType().toString().toLowerCase() + ". Позиция: " + this.getPosition()
                + ". Характеристики: " + characteristic);
        return str.toString();
    }
}
