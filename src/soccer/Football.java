package soccer;

import soccer.menu.MainMenu;
import soccer.menu.Menu;

public class Football {

    public static void main(String[] args) {


        // Menu menu = new Menu();

        /*MainMenu mainMenu = new MainMenu();

        while (mainMenu.menuMain()) ;
*/

        Menu menu = new Menu();
        while (menu.menuMain());

    }
}
