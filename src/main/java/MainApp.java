import dao.*;
import entities.Grade;
import services.ExamService;
import view.LoginPage;

import java.math.BigDecimal;

public class MainApp {

    public static void main(String[] args) {

        ExamDAO examDAO = new ExamDAO();
        ModuleDAO moduleDAO = new ModuleDAO();
        CourseDAO courseDAO = new CourseDAO();
        PersonDAO personDAO = new PersonDAO();
        UserDAO userDAO = new UserDAO();
        GradeDAO gradeDAO = new GradeDAO();


        // Deleting old tables and creating a new database with test values
        // ResetDataBase.reset();


        //**************************************
        //Testing code for updating the database
        //**************************************


/*        Exam exam = examDAO.getOne(12L);
        Module module = moduleDAO.getOne(7L);
        Course course = courseDAO.getOne(4L);
        exam.setModule(module);
        module.setCourse(course);
        examDAO.updateOne(exam);
        moduleDAO.updateOne(module);*/


        //Code to check if the encrypted password is correctly read from the database

/*
        User user = userDAO.getOne("usrlogin2");
        System.out.println("Password for "+ user.getLogin()+ " is " +user.getPasswordhash());
*/


        //More testing ....

/*        User user = userDAO.getOne("usrlogin2");
        user.setPerson(personDAO.getOne(1));
        userDAO.updateOne(user);*/

/*        Course courseFrontEnd = courseDAO.getOne(6L);
        Course courseBackEnd = courseDAO.getOne(4L);
        Course courseFullStack = courseDAO.getOne(5L);

        List<Module> moduleList = moduleDAO.getAll();
        List<Module> moduleListFrontEnd = new ArrayList<>();
        List<Module> moduleListBackEnd = new ArrayList<>();
        List<Module> moduleListFullStack = new ArrayList<>();*/

/*
        Module module = moduleDAO.getOne(7L);
        Module module1 = moduleDAO.getOne(8L);
        Module module2 = moduleDAO.getOne(9L);
        Module module3 = moduleDAO.getOne(10L);
        Module module4 = moduleDAO.getOne(11L);
*/


/*
        for (Module module : moduleList) {
            switch (module.getName()) {
                case "JAVA":
                case "Python":
                    module.setCourse(courseBackEnd);
                    moduleListBackEnd.add(module);
                    break;
                case "HTML":
                case "CSS":
                    module.setCourse(courseFrontEnd);
                    moduleListFrontEnd.add(module);
                    break;
                case "GitHub":
                    module.setCourse(courseFullStack);
                    moduleListFullStack.add(module);

            }
        }

        courseFrontEnd.setModules(moduleListFrontEnd);
        courseBackEnd.setModules(moduleListBackEnd);
        courseFullStack.setModules(moduleListFullStack);

        courseDAO.updateOne(courseFrontEnd);
        courseDAO.updateOne(courseBackEnd);
        courseDAO.updateOne(courseFullStack);*/


        //testing login module with hidden characters for password -- only runs in cmd via maven !!
        //LoginService.login();


        // Launching the swing GUI application


        try {
            //new LoginPage();

 /*         Grade grade1 = new Grade();

            grade1.setGradeValue(BigDecimal.valueOf(9));
            grade1.setExam(examDAO.getOne(90L));
            grade1.setPerson(personDAO.getOne(23));

            gradeDAO.createOne(grade1);

*/
            // calling ExamService method to check functioning of requested feature
            ExamService es = new ExamService();
            es.outputExam(12L);


        } catch (Exception e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        } finally {
            EMFClass.getEMFLuc().close();

        }


    }


}
