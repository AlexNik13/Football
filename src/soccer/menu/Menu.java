package soccer.menu;

import soccer.ClubManager;
import soccer.FootballClub;
import soccer.controller.Controller;

import java.util.Scanner;

public class Menu extends Controller {
    //private final Scanner in = new Scanner(System.in);
    private final Scanner in;

    public Menu() {
        in = getIn();
    }

    public boolean menuMain() {
        System.out.println("\nГлавное меню\n____________");
        System.out.println("1. Создать клуб.");
        System.out.println("2. Получить список клубов.");
        System.out.println("3. Создать менеджера.");
        System.out.println("4. Получить список менеджеров.");
        System.out.println("5. Создать свободного игрока.");
        System.out.println("6. Получить список свободных игроков.");
        System.out.println("0. Выход");
        checkForNumber();
        int choice = in.nextInt();
        switch (choice) {
            case 0:
                return false;
            case 1:
                createFootballClub();
                break;
            case 2:
                subMenuFootballClub();
                break;
            case 3:
                createClubManager();
                break;
            case 4:
                subMenuManager();
                break;
            case 5:
                createFreePlayer();
                break;
            case 6:
                while (menuFreePlayer()) ;
                break;
        }
        return true;
    }

    protected void subMenuFootballClub() {
        printAllFootballClub();
        System.out.println("Введите имя клуба или 0 чтоб вернуться назад.\n");
        String name = in.next();

        if (!name.equals("0")) {
            for (FootballClub footballClub : getFootballClubsList()) {
                if (footballClub.getName().equalsIgnoreCase(name)) {
                    while (menuFootballClub(footballClub)) ;
                    break;
                }
            }
        }
    }

    private boolean menuFreePlayer() {
        printFreePlayer();
        System.out.println("\nМеню свободных игроков\n______________________");
        System.out.println("1. Добавить свободного игрока.");
        System.out.println("2. Удалить свободного игрока.");
        System.out.println("3. Удалить несколько свободных игрокв.");
        System.out.println("4. Переместить свободного игрока в клуб.");
        System.out.println("0. Вернуться в главное меню");
        checkForNumber();
        int choice = in.nextInt();

        switch (choice) {
            case 0:
                return false;
            case 1:
                createFreePlayer();
                break;
            case 2:
                deleteFreePlayer();
                break;
            case 3:
                deleteSeveralFreePlayer();
                break;
            case 4:
                moveFreePlayerToClub();
                break;
        }
        return true;
    }

    private boolean menuManager(ClubManager clubManager) {
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
                createPlayerInClub(clubManager);
                break;
            case 2:
                addPlayerFromFree(clubManager);
                break;
            case 4:
                clubManager.doTraining();
                break;
            case 5:
                deletePlaeyrFromClub(clubManager);
                break;
            case 6:
                deleteSeveralPlaeyrFromClub(clubManager);
                break;
            case 9:
                clubManager.getFootballClub().print();
                break;
        }
        return true;
    }

    private void subMenuManager() {
        int i = 1;
        for (ClubManager clubManager : getClubManagerList()) {
            System.out.println(i++ + ". " + clubManager.getName() + ". Клуб: " + (clubManager.isFree() ? "нет" : clubManager.getFootballClub().getName()));
        }
        System.out.print("Введите номер менеджера или 0. для возврата назад: ");
        checkForNumber();
        int numberManager = in.nextInt();
        try {
            if (!getClubManagerList().get(numberManager - 1).isFree()) {
                while (menuManager(getClubManagerList().get(numberManager - 1))) ;
            } else {
                System.out.println("У менеджера нет клуба.");
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Неверный выбор");
        }

    }

    public boolean menuFootballClub(FootballClub footballClub) {
        System.out.println("\nМеню футбольного клуба\n______________________");
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
                addManagerToTheClub(footballClub);
                break;
        }
        return true;
    }
}
