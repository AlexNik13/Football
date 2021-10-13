package soccer.menu;

import soccer.ClubManager;
import soccer.FootballClub;
import soccer.player.Player;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuFootballClub {
    private final Scanner in = new Scanner(System.in);

    public MenuFootballClub() {
    }

    public boolean menu(FootballClub footballClub, ArrayList<Player> playerFreeList, ArrayList<ClubManager> clubManagerList) {
        //footballClub.printInfo();
        System.out.println("Меню менеджеров\n______________________");
        System.out.println("1. Получить общее инфо по выбраному клубу.");
        System.out.println("2. Получить список игроков клуба.");
        System.out.println("3. Добавить менеджера в клуб.");
        System.out.println("0. Вернуться в главное меню");
        checkForNumber();
        int choice = in.nextInt();
        switch (choice) {
            case 0:
                return false;
            case 1:
                footballClub.printInfo();
                break;
            case 2:
                footballClub.printPlayer();
                break;
            case 3:
                if (clubManagerList.size() > 0) {
                    addManagerToTheClub(footballClub, clubManagerList);
                } else {
                    System.out.println("Менеджеров нет");
                }
                break;
        }
        return true;
    }

    private void addManagerToTheClub(FootballClub footballClub, ArrayList<ClubManager> clubManagerList) {
        int id = 1;
        for (ClubManager clubManager : clubManagerList) {
            System.out.println(id++ + " " + clubManager.getName() + " " + (clubManager.isFree() ? "Свободен" : "Занят"));
        }
        System.out.print("\nВведите номер менеджера или 0 для возвращения назад: ");
        int inManager;
        do {
            checkForNumber();
            inManager = in.nextInt();
            try {
                if (!clubManagerList.get(inManager - 1).isFree()) {
                    System.out.print("Менеджер занят/nВыберите другого менеджера или 0 для возвращения назад: ");
                }
            } catch (IndexOutOfBoundsException exception) {
                inManager = 0;
            }
            if (inManager == 0) break;
        } while (!clubManagerList.get(inManager - 1).isFree());

        if (inManager != 0) {
            clubManagerList.get(inManager - 1).setFree(false);
            clubManagerList.get(inManager - 1).setFootballClub(footballClub);
            footballClub.setManager(true);
        }
    }

    private void checkForNumber() {
        while (!in.hasNextInt()) {
            System.out.println("Некорректный ввод.");
            in.next();
        }
    }
}
