package services;

import dao.UserDAO;
import entities.User;

import java.io.Console;

// created this LoginService to test hidden login from console
// Scanner does not have hidden character possibilities -> need to use Console
// when running the program through Intellij (or other IDE's), use of Console gives nullpointerexception
// this can be solved by using maven from the cmd line
// first built the project with maven (from within project folder) : > mvn verify
// then run it (from the project folder) with: > mvn exec: java -Dexec.mainClass=MainApp
//

public class LoginService {

    public static void login() {
        Console console = System.console();

        if (console == null) {
            System.out.println("Couldn't get Console instance");
            System.exit(0);
        }
        String username = console.readLine("Enter username : ");

/*        Scanner kbd = new Scanner(System.in);
        String username = kbd.nextLine();*/


        UserDAO userDAO = new UserDAO();
        User user = userDAO.getOne(username);
        String password = user.getPasswordhash();
        char[] passwordArray = console.readPassword("Enter your  password: ");
        String passwordToBeChecked = new String(passwordArray);

        if (password.equals(passwordToBeChecked)) {
            System.out.println("Login successful !");
        } else {
            System.out.printf("Incorrect combination of username and password.");
        }

    }

    public static boolean loginCheck(String userName, String passwordToBeChecked) {
        String password;
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getOne(userName);
        if (user != null) {
            password = user.getPasswordhash();
            return password.equals(passwordToBeChecked);
        } else return false;


    }
}
