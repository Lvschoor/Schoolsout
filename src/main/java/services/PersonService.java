package services;

import dao.PersonDAO;
import model.Gender;
import model.Person;

import java.util.Scanner;

public class PersonService {

    private PersonDAO personDAO;
    private Scanner scanner;

    public PersonService() {

        personDAO = new PersonDAO();
        scanner = new Scanner(System.in);
    }

    public void updatePerson(Person person) {
        System.out.print("Change first name? Y/N: ");
        String answer = scanner.nextLine();
        if (answer.equals("Y")) {
            System.out.print("Give new first name: ");
            person.setFirstname(scanner.nextLine());
        }
        System.out.print("Change last name? Y/N: ");
        answer = scanner.nextLine();
        if (answer.equals("Y")) {
            System.out.print("Give new last name: ");
            person.setFamilyname(scanner.nextLine());
        }

        System.out.print("Change gender? Y/N: ");
        answer = scanner.nextLine();
        if (answer.equals("Y")) {

            System.out.print("Choose your gender: \n1. Other \n2. Female \n3. Male\n");
            //
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
        }
        System.out.println(person);
        personDAO.updateOne(person);
    }

}
