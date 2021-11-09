package services;

import dao.ExamDAO;
import dao.GradeDAO;
import entities.Exam;
import entities.Grade;

import java.util.List;

public class ExamService {
    ExamDAO examDAO = new ExamDAO();
    GradeDAO gradeDAO = new GradeDAO();


// requested method from part 2 of School's out task
    public void outputExam(Long id) {
        Exam exam = examDAO.getOne(id);
        printGradeIfExamHasNoSubExams(exam);
    }

    private void printGradeIfExamHasNoSubExams(Exam exam) {
        // list of all the grades in the database
        List<Grade> listOfAllGrades = gradeDAO.getAll();

        // check of exam has sub exams;
        // if yes -> print it as a parent exam and go one step deeper in the hierarchy
        // if no -> find the grades related to this exam and print the out
        if (exam.getSubExams().isEmpty() ) {
            System.out.println(exam.getName()+" has following grades:");
            for (Grade grade : listOfAllGrades) {
                if (grade.getExam().getId().equals(exam.getId())) {
                    System.out.print(grade.getGradeValue() + "/" + exam.getTotal() + " ; ");
                }
            }
        } else {
            System.out.println(exam.getName() + " is a parent exam.");
            List<Exam> listOfSubExams = exam.getSubExams();
            for (Exam subExam : listOfSubExams) {
                printGradeIfExamHasNoSubExams(subExam);
            }

        }

    }
}
