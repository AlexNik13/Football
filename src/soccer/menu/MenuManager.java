package soccer.menu;

import soccer.ClubManager;
import soccer.player.Enum.ConditionPlayer;
import soccer.player.Enum.PlayerType;
import soccer.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuManager {
    private final Scanner in = new Scanner(System.in);
    private ArrayList<Player> playerList;
    private Player player;

    public MenuManager() {
        playerList = new ArrayList<>();
    }

    public boolean menu(ClubManager clubManager, ArrayList<Player> playerFreeList) {
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
                menuCreatePlayer(clubManager);
                break;
            case 2:
                addPlayerFromFree(clubManager, playerFreeList);
                break;
            case 4:
                clubManager.doTraining();
                break;
            case 5:
                menuDeletePlaeyr(clubManager, playerFreeList);
                break;
            case 6:
                menuDeleteSeveralPlaeyr(clubManager, playerFreeList);
                break;
            case 9:
                clubManager.getFootballClub().print();
                break;
        }
        return true;
    }

    private void addPlayerFromFree(ClubManager clubManager, ArrayList<Player> playerFreeList) {
        int choice = 0;
        int i = 1;
        if ((clubManager.getPlayerArray().size() + playerList.size()) < 21) {
            System.out.println("Игроки:");
            for (Player player : playerFreeList) {
                System.out.println(i++ + ". " + player.getInfo());
            }
            System.out.println("Выберете игрока: ");
            checkForNumber();
            int numberPlayer = in.nextInt();
            player = playerFreeList.remove(numberPlayer - 1);
            player.setCondition(ConditionPlayer.IN_CLUB);
            while (checkRepeatPlayerNumber(player.getPlayerNumber(), clubManager, playerList)) {
                checkForNumber();
                numberPlayer = in.nextInt();
                player.setPlayerNumber(numberPlayer);
            }
            playerList.add(player);
        } else {
            System.out.println("В клубе не может быть более 21 игрока.");
        }
        System.out.println("Добавить еще игрока? \n\t1. Да \n\t2. Нет");
        checkForNumber();
        choice = in.nextInt();
        if (choice == 1) {
            addPlayerFromFree(clubManager, playerFreeList);
        } else {
            clubManager.addSeveralPlayer(playerList);
            playerList.clear();
        }
    }

    void menuDeleteSeveralPlaeyr(ClubManager clubManager, ArrayList<Player> playerFreeList) {
        clubManager.getFootballClub().printPlayer();
        System.out.println("Удаляем несколько игроков.");
        System.out.print("С какой позиции начинаем? ");
        int start = in.nextInt();
        System.out.print("До какой позиции включительно? ");
        int end = in.nextInt();
        if ((clubManager.getPlayerArray().size() - Math.abs(end - start)) > 11) {
            for (int i = Math.min(start, end); i <= (Math.max(end, start)); i++) {
                player = clubManager.getPlayerArray().remove((Math.min(start, end)) - 1);
                player.setCondition(ConditionPlayer.FREE);
                playerFreeList.add(player);
            }
        } else {
            System.out.println("В клубе не может быть меньше 11 игроков.");
        }
    }

    void menuDeletePlaeyr(ClubManager clubManager, ArrayList<Player> playerFreeList) {
        if (clubManager.getPlayerArray().size() > 11) {
            clubManager.getFootballClub().printPlayer();
            System.out.print("Введите номер игрока для удаления: ");
            int plaeyrNumber = in.nextInt();
            boolean noSuchNumber = true;
            for (int i = 0; i < clubManager.getPlayerArray().size(); i++) {
                if (clubManager.getPlayerArray().get(i).getPlayerNumber() == plaeyrNumber) {
                    player = clubManager.getPlayerArray().remove(i);
                    player.setCondition(ConditionPlayer.FREE);
                    playerFreeList.add(player);
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

    private void printMenuType() {
        System.out.println("Выберети тип игрока.");
        System.out.println("1. Голкипер");
        System.out.println("2. Защитники");
        System.out.println("3. Полузащитники");
        System.out.println("4. Нападающие");
    }

    private void menuCreatePlayer(ClubManager clubManager) {
        int choice = 0;
        if ((clubManager.getPlayerArray().size() + playerList.size()) < 21) {
            playerList.add(createPlayer(clubManager));
            System.out.println("Создать еще игрока? \n\t1. Да \n\t2. Нет");
            checkForNumber();
            choice = in.nextInt();
        } else {
            System.out.println("В клубе не может быть более 21 игрока.");
        }
        if (choice == 1) {
            menuCreatePlayer(clubManager);
        } else {
            clubManager.addSeveralPlayer(playerList);
            playerList.clear();
        }
    }

    private Player createPlayer(ClubManager clubManager) {
        System.out.print("Введите имя игрока: ");
        String firstName = in.next();
        System.out.print("Введите фамилию игрока: ");
        String lastName = in.next();
        System.out.print("Введите возраст игрока: ");
        checkForNumber();
        int age = in.nextInt();
        System.out.print("Введите номер игрока: ");
        checkForNumber();
        int number = in.nextInt();
        while (checkRepeatPlayerNumber(number, clubManager, playerList)) {
            checkForNumber();
            number = in.nextInt();
        }
        printMenuType();
        checkForNumber();
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                player = new Player(firstName, lastName, number, age, PlayerType.GOALKEEPER);
                break;
            case 2:
                player = new Player(firstName, lastName, number, age, PlayerType.DEFENDER);
                break;
            case 3:
                player = new Player(firstName, lastName, number, age, PlayerType.MIDFIELDER);
                break;
            case 4:
                player = new Player(firstName, lastName, number, age, PlayerType.ATTACKER);
                break;
        }
        player.setCondition(ConditionPlayer.IN_CLUB);
        return player;
    }

    private boolean checkRepeatPlayerNumber(int number, ClubManager clubManager, List<Player> playerList) {
        try {
            for (Player player : clubManager.getPlayerArray()) {
                if (player.getPlayerNumber() == number) {
                    System.out.println("Игрок под таким номером уже есть");
                    System.out.print("Введите другой номер игрока: ");
                    return true;
                }
            }
            for (Player player : playerList) {
                if (player.getPlayerNumber() == number) {
                    System.out.println("Игрок под таким номером уже есть");
                    System.out.print("Введите другой номер игрока: ");
                    return true;
                }
            }
        } catch (NullPointerException ex) {
            return false;
        }
        return false;
    }

    private void checkForNumber() {
        while (!in.hasNextInt()) {
            System.out.println("Некорректный ввод.");
            in.next();
        }
    }
}