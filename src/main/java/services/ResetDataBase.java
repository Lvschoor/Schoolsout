package services;

import dao.JDBCConnectionClass;
import dao.*;
import model.*;
import model.Module;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// during testing, to be able to quickly delete old database and set up a clean database with basic data

public class ResetDataBase {

    public static void reset() {
        {
            try {
                Statement statement = JDBCConnectionClass.getConnection().createStatement();
                List<String> sqlList = new ArrayList<>();
                String sql1 = "DROP TABLE hibernate_sequence";
                String sql2 = "DROP TABLE Exam";
                String sql3 = "DROP TABLE User";
                String sql4 = "DROP TABLE Person";
                String sql5 = "DROP TABLE Module";
                String sql6 = "DROP TABLE Course";

                sqlList.add(sql1);
                sqlList.add(sql2);
                sqlList.add(sql3);
                sqlList.add(sql4);
                sqlList.add(sql5);
                sqlList.add(sql6);


                for (String sql : sqlList) {
                    statement.executeUpdate(sql);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            User user1 = new User("usrlogin1", "pasw1");
            User user2 = new User("usrlogin2", "pasw2");
            User user3 = new User("usrlogin3", "pasw3");

            UserDAO userDAO = new UserDAO();
            userDAO.createOne(user1);
            userDAO.createOne(user2);
            userDAO.createOne(user3);

            Person person1 = new Person("Jan", "Janssens", Gender.MALE);
            Person person2 = new Person("Extra", "Terrestrial", Gender.OTHER);
            Person person3 = new Person("Petra", "Peters", Gender.FEMALE);

            PersonDAO personDAO = new PersonDAO();
            personDAO.createOne(person1);
            personDAO.createOne(person2);
            personDAO.createOne(person3);

            Course course1 = new Course("Backend developer");
            Course course2 = new Course("Full stack developer");
            Course course3 = new Course("Frontend developer");

            CourseDAO courseDAO = new CourseDAO();
            courseDAO.createOne(course1);
            courseDAO.createOne(course2);
            courseDAO.createOne(course3);

            Module module1 = new model.Module("JAVA");
            Module module2 = new model.Module("Python");
            Module module3 = new model.Module("HTML");
            Module module4 = new model.Module("CSS");
            Module module5 = new model.Module("GitHub");

            ModuleDAO moduleDAO = new ModuleDAO();
            moduleDAO.createOne(module1);
            moduleDAO.createOne(module2);
            moduleDAO.createOne(module3);
            moduleDAO.createOne(module4);
            moduleDAO.createOne(module5);

            Exam exam1 = new Exam("Test1-Java");
            Exam exam2 = new Exam("Test2-Java");
            Exam exam3 = new Exam("Test1-HTML");
            Exam exam4 = new Exam("Test2-HTML");
            Exam exam5 = new Exam("Test1-CSS");
            Exam exam6 = new Exam("Test1-GitHub");

            ExamDAO examDAO = new ExamDAO();
            examDAO.createOne(exam1);
            examDAO.createOne(exam2);
            examDAO.createOne(exam3);
            examDAO.createOne(exam4);
            examDAO.createOne(exam5);
            examDAO.createOne(exam6);
        }
    }
}
