package services;

import dao.ExamDAO;
import dao.GradeDAO;
import model.Exam;
import model.Grade;
import model.Person;
import model.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeService {


    private Scanner scanner;
    private GradeDAO gradeDAO;
    private ExamDAO examDAO;
    private ExamService examService;

    public GradeService() {

        scanner = new Scanner(System.in);
        gradeDAO = new GradeDAO();
        examDAO = new ExamDAO();
        examService = new ExamService();
    }

    //SUPER HARD
    //De datum staat automatisch op vandaag
    //de grade mag niet minder dan 0 zijn, en mag niet meer zijn dan de punten van het examen
    //Je gaat een Exam moeten oproepen van de bestaande lijst van examens, Eman mag dus niet op null staan
    //Je gaat een Person moeten terugvinden met User
    public void createGrade(User user) {
        Person person = user.getPerson();
        String fullNameOfPerson = person.getFirstname() + " " + person.getFamilyname();
        List<Exam> gradableExams = examService.gradableExams();
        System.out.println("Add a grade for " + fullNameOfPerson);
        System.out.println("Choose the correct exam:");
        int counter = 1;
        if (!gradableExams.isEmpty()) {
            for (Exam exam : gradableExams) {
                System.out.println(counter + ". " + "Exam: " + exam.getName());
                counter++;
            }
        } else System.out.println("No exams found.");
        int choice = scanner.nextInt();
        Exam examToBeGraded = gradableExams.get(choice - 1);
        Grade grade = new Grade();
        grade.setPerson(person);
        grade.setExam(examToBeGraded);
        grade.setDate(LocalDate.now());
        boolean correctGradeValue = false;
        BigDecimal gradeValue = null;
        while (!correctGradeValue) {
            System.out.print("Give the grade: ");
            gradeValue = scanner.nextBigDecimal();
            if (gradeValue.compareTo(BigDecimal.valueOf(0)) >= 0
                    && gradeValue.compareTo(BigDecimal.valueOf(examToBeGraded.getTotal())) <= 0) {
                grade.setGradeValue(gradeValue);
                correctGradeValue = true;
            }
        }
        grade.setGradeValue(gradeValue);
        scanner.nextLine();
        System.out.println("Give the comment:");
        grade.setComment(scanner.nextLine());
        System.out.println("Give the internal comment:");
        grade.setInternalComment(scanner.nextLine());

        System.out.print("Absent? Y/N: ");
        String answer = scanner.nextLine();
        if (answer.equals("Y")) {
            grade.setAbsent(true);
        } else grade.setAbsent(false);

        System.out.print("Postponed? Y/N: ");
        answer = scanner.nextLine();
        if (answer.equals("Y")) {
            grade.setPostponed(true);
        } else grade.setPostponed(false);

        gradeDAO.createOne(grade);
    }

    //EASY
    public Grade getOneGradeById() {
        System.out.print("Enter the id of the grade: ");
        Long gradeId = Long.valueOf(scanner.next());
        Grade grade = gradeDAO.getOne(gradeId);
        if (grade != null) {
            System.out.println("The grade for exam " + grade.getExam().getName() + " is: " + grade.getGradeValue() + "/" + grade.getExam().getTotal());
        } else System.out.println("No grade found for this id");
        return grade;
    }

    //MEDIUM
    //Controleer eerst of de user niet 'null' is
    //Gebruik een user.getPerson methode,
    //en maak een extra methode in je DAO/repository om resultaten op te vragen met person
    public List<Grade> getAllGradeByPerson(User user) {
        List<Grade> allGradesOfPerson = new ArrayList<>();
        List<Grade> allGrades = gradeDAO.getAll();
        if (user != null) {
            int counter = 1;
            Person person = user.getPerson();
            String fullNameOfPerson = person.getFirstname() + " " + person.getFamilyname();
            System.out.println("All grades for " + fullNameOfPerson + ":");
            if (!allGrades.isEmpty()) {
                for (Grade grade : allGrades) {
                    if (grade.getPerson().getId() == (person.getId())) {
                        System.out.println(counter + ". "
                                + "Exam: " + grade.getExam().getName() + " with grade: "
                                + grade.getGradeValue() + "/"
                                + grade.getExam().getTotal());
                        allGradesOfPerson.add(grade);
                        counter++;
                    }
                }
                return allGradesOfPerson;
            } else {
                System.out.println("No grades found for " + fullNameOfPerson);
            }

        } else {
            System.out.println("No valid user to delete grades; Please first use the 'See One' option from User menu.");
            return null;
        }
        return allGradesOfPerson;
    }

    //HARD
    //Controleer eerst of de user niet 'null' is
    //vraag alle grades op van een Person en kies de Grade die je wilt aanpassen
    //Enkel de gradeValue en de comment mogen aangepast worden
    //de grade mag niet minder dan 0 zijn, en mag niet meer zijn dan de punten van het examen
    public void updateGrade(User user) {

        if (user != null) {
            int counter = 1;
            Person person = user.getPerson();
            String fullNameOfPerson = person.getFirstname() + " " + person.getFamilyname();
            List<Grade> allGrades = gradeDAO.getAll();
            List<Grade> allGradesOfPerson = new ArrayList<>();
            System.out.println("Choose the grade you want to update:");
            if (!allGrades.isEmpty()) {
                for (Grade grade : allGrades) {
                    if (grade.getPerson().getId() == (person.getId())) {
                        System.out.println(counter + ". "
                                + "Exam: " + grade.getExam().getName() + " with grade: "
                                + grade.getGradeValue() + "/"
                                + grade.getExam().getTotal());
                        allGradesOfPerson.add(grade);
                        counter++;
                    }
                }
            } else System.out.println("No grades found for " + fullNameOfPerson);

            int choice = scanner.nextInt();

            Grade gradeToBeUpdated = allGradesOfPerson.get(choice - 1);
            System.out.print("Confirm you want to update this grade: Exam: "
                    + gradeToBeUpdated.getExam().getName() + " with grade: "
                    + gradeToBeUpdated.getGradeValue() + "/"
                    + gradeToBeUpdated.getExam().getTotal() + "? Y/N: ");
            scanner.nextLine();
            String answer = scanner.nextLine();
            if (answer.equals("Y")) {
                System.out.print("Give the new grade: ");
                gradeToBeUpdated.setGradeValue(scanner.nextBigDecimal());
                scanner.nextLine();
                System.out.println("Give the new comment:");
                gradeToBeUpdated.setComment(scanner.nextLine());
                System.out.println("Give the new internal comment:");
                gradeToBeUpdated.setInternalComment(scanner.nextLine());
                gradeDAO.updateOne(gradeToBeUpdated);
            }

        } else {
            System.out.println("No valid user to delete grades; Please first use the 'See One' option from User menu.");
        }
    }

    //EASY
    //Controleer eerst of de user niet 'null' is
    //vraag alle grades op van een Person en kies de Grade die je wilt aanpassen
    public void deleteGrade(User user) {

        if (user != null) {

            List<Grade> gradesOfPerson = getAllGradeByPerson(user);
            Person person = user.getPerson();
            String fullNameOfPerson = person.getFirstname() + " " + person.getFamilyname();
            System.out.println("Choose the grade you want to delete of " + fullNameOfPerson);
            int choice = scanner.nextInt();

            Grade gradeToBeDeleted = gradesOfPerson.get(choice - 1);
            System.out.print("Confirm you want to delete this grade: Exam: "
                    + gradeToBeDeleted.getExam().getName() + " with grade: "
                    + gradeToBeDeleted.getGradeValue() + "/"
                    + gradeToBeDeleted.getExam().getTotal() + "? Y/N: ");
            scanner.nextLine();
            String answer = scanner.nextLine();
            if (answer.equals("Y")) {
                gradeDAO.deleteOne(gradeToBeDeleted);
            }


        } else {
            System.out.println("No valid user to delete grades; Please first use the 'See One' option from User menu.");
        }

    }

    //----
    //extra private methodes hieronder
}
