package services;

import dao.PersonDAO;
import dao.UserDAO;
import model.Gender;
import model.Person;
import model.User;

import java.util.List;
import java.util.Scanner;

public class UserService {

    private UserDAO userDAO;
    private PersonDAO personDAO;
    private PersonService personService;
    private Scanner scanner;

    public UserService() {
        userDAO = new UserDAO();
        personDAO = new PersonDAO();
        scanner = new Scanner(System.in);
        personService = new PersonService();
    }

    //HARD
    //1.Zorg ervoor dat een persoon ook aangemaakt wordt
    //2.Maak een controle methode die vraagt om een gebruiker een passwoord twee keer in te geven
    //3. Het wachtwoord wordt hier geincrypteerd
    public void createUser() {
        Person person = new Person();
        System.out.println("Create your user");
        System.out.print("Give your first name: ");
        person.setFirstname(scanner.nextLine());
        System.out.print("Give your last name: ");
        person.setFamilyname(scanner.nextLine());
        System.out.print("Choose your gender: \n1. Other \n2. Female \n3. Male\n");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                person.setGender(Gender.OTHER);
                break;
            case 2:
                person.setGender(Gender.FEMALE);
                break;
            default:
                person.setGender(Gender.MALE);
        }
        scanner.nextLine();
        System.out.print("Choose a login name: ");
        User user = new User();
        user.setLogin(scanner.nextLine());
        user.setPasswordhash(checkPassword());
        user.setActive(true);
        user.setPerson(person);
        userDAO.createOne(user);


    }

    //EASY
    //Als er geen User terug gegeven wordt, stuur als bericht "User does not exist"
    public User getOneUserByName() {
        System.out.print("Enter the username: ");
        String username = scanner.next();
        User user = userDAO.getOne(username);
        if (user != null) System.out.println(user);
        else System.out.println("User does not exist");
        return user;
    }

    //EASY
    //print een lijst uit van alle users.
    public void getAllUsers() {
        List<User> allUsers = userDAO.getAll();
        for (User user : allUsers) {
            System.out.println(user.getLogin() + " is " + (user.isActive() ? "active" : "not active"));
        }


    }

    //EASY
    //Een username mag niet aangepast worden
    //Bonus HARD
    //De Person hoef je niet te updaten (als je dat wilt, doe je dat best via een aparte personservice,
    // via een aprte updatePersonMethode)
    public void updateUser() {
        System.out.print("Which user does you want to modify: ");
        User user = userDAO.getOne(scanner.nextLine());

        System.out.print("Change password? Y/N: ");
        String answer = scanner.nextLine();
        if (answer.equals("Y")) {
            user.setPasswordhash(checkPassword());
        }
        System.out.print("Do you want to modify active status? Y/N: ");
        answer = scanner.nextLine();
        if (answer.equals("Y")) {
            user.setActive(!user.isActive());
        }
        System.out.print("Do you want to modify the user details? Y/N: ");
        answer = scanner.nextLine();
        if (answer.equals("Y")) {
            personService.updatePerson(user.getPerson());

        }
        userDAO.updateOne(user);

    }

    //MEDIUM
    //Vraag de User een passwoord in te geven voor dat hij zijn account kan verwijderen.
    //De Person moet ook mee gedeleted worden
    public void deleteUser() {

        System.out.print("Give the username to be deleted: ");
        User user = userDAO.getOne(scanner.nextLine());
        if (isCorrectPassword(user)) {
            System.out.print("Confirm you want to delete user " + user.getLogin() + "? (Y/N): ");
            String answer = scanner.nextLine();
            if (answer.equals("Y")) {
                userDAO.deleteOne(user);
            }
        }
    }

    //----
    //extra private methodes hieronder

    private String checkPassword() {
        boolean samePassword = false;
        String password = null;

        while (samePassword == false) {
            System.out.print("Choose a password: ");
            password = scanner.nextLine();
            System.out.print("Confirm your password: ");
            String password2 = scanner.nextLine();
            if (password.equals(password2)) {
                samePassword = true;
            }
        }
        return password;
    }

    private boolean isCorrectPassword(User user) {
        boolean correctPassword = false;
        String password = null;

        System.out.print("Give password: ");
        password = scanner.nextLine();

        if (password.equals(user.getPasswordhash())) {
            correctPassword = true;
        }

        return correctPassword;
    }


}

