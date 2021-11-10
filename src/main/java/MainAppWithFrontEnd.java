import model.User;
import services.GradeService;
import services.UserService;

import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;


public class MainAppWithFrontEnd {
    private static int choiceOne = 9;
    private static int choiceTwo = 9;
    private static boolean continueThis = true;
    private static User user;

    public static void main(String[] args) {

        try {

            while (continueThis) {
                getChoice();
                choices();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void getChoice() {
        Scanner scanner = new Scanner(System.in);

        while (choiceOne == 9) {
            System.out.println("What do you want to look at? \n1: Users \n2: Grades\n0: End");
            choiceOne = scanner.nextInt();
            if (choiceOne == 0) break;
            if (choiceOne < 1 || choiceOne > 2) {
                choiceOne = 9;
                System.out.println("Invalid choice.");
            } else {
                while (choiceTwo == 9) {
                    System.out.println("What do you want to look at? \n1: See All \n2: See One \n3: Add One \n4: Edit One \n5: Delete One\n0: End");
                    choiceTwo = scanner.nextInt();
                    if (choiceTwo == 0) break;
                    if (choiceTwo < 1 || choiceTwo > 5) {
                        choiceTwo = 9;
                        System.out.println("Invalid choice.");
                    }
                }
            }
        }

    }


    private static void choices() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        GradeService gradeService = new GradeService();
        UserService userService = new UserService();
        if (choiceTwo != 0)
            if (choiceOne == 1) {

                switch (choiceTwo) {
                    case 1:
                        userService.getAllUsers();
                        break;//see All
                    case 2:
                        user = userService.getOneUserByName();
                        break;//see
                    case 3:
                        userService.createUser();
                        break;//add One
                    case 4:
                        userService.updateUser();
                        break;//edit One
                    case 5:
                        userService.deleteUser();
                        break;//delete One
                }
                System.out.println("We did a user thing!");


            } else if (choiceOne == 2) {
                switch (choiceTwo) {
                    case 1:
                        gradeService.getAllGradeByPerson(user);
                        break;//see All
                    case 2:
                        gradeService.getOneGradeById();
                        break;//see One
                    case 3:
                        gradeService.createGrade(user);
                        break;//add One
                    case 4:
                        gradeService.updateGrade(user);
                        break;//edit One
                    case 5:
                        gradeService.deleteGrade(user);
                        break;//delete One
                }
                System.out.println("We did a grade thing!");

            }
        choiceOne = 9;
        choiceTwo = 9;
        boolean goodAnswer;
        do {
            System.out.println("Do you want to Try again? Y/N");
            String answer = scanner.next();
            if (answer.toUpperCase(Locale.ROOT).equals("N")) {
                System.out.println("Bye!");
                continueThis = false;
                break;
            }
            if (!answer.toUpperCase(Locale.ROOT).equals("Y")) {
                goodAnswer = false;
                System.out.println(answer + " is not a good answer.");
            } else goodAnswer = true;
        } while (!goodAnswer);


    }
}


