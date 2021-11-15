package services;

import dao.GradeDAO;
import dao.UserDAO;
import model.Grade;
import model.Person;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class GradesForPDFService {

    public GradesForPDFService() {
    }

    GradeDAO gradeDAO = new GradeDAO();
    UserDAO userDAO = new UserDAO();

    List<Grade> gradesOfPerson = new ArrayList<>();

    public List<Grade> gradesToPDF(String userName) {
        User user = userDAO.getOne(userName);
        if (user != null) {
            int counter = 1;
            Person person = user.getPerson();
            String fullNameOfPerson = person.getFirstname() + " " + person.getFamilyname();
            List<Grade> allGrades = gradeDAO.getAll();

            if (!allGrades.isEmpty()) {
                for (Grade grade : allGrades) {
                    if (grade.getPerson().getId() == (person.getId())) {
                        gradesOfPerson.add(grade);
                    }
                }
            } else System.out.println("No grades found for " + fullNameOfPerson);

        } else {
            System.out.println("No valid user to delete grades.");
        }
        return gradesOfPerson;
    }


}
