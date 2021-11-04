package view;

import dao.CourseDAO;
import dao.PersonDAO;
import entities.Course;
import entities.Person;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Dashboard extends JFrame {
    private JPanel dashboard;
    private JComboBox courses;
    private JButton changeAccountDetailsButton;
    private JButton chooseCourseButton;
    private JTextField userField;
    private JLabel userLabel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JButton confirmButton;
    private JButton exitButton;
    private JFrame dashboardFrame;

    public Dashboard(User user){

        dashboardFrame = new JFrame("Dashboard");
        dashboardFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        dashboardFrame.setPreferredSize(new Dimension(400, 300));
        dashboardFrame.setResizable(false);

        dashboardFrame.add(dashboard);
        dashboardFrame.pack();
        dashboardFrame.setLocationRelativeTo(null);
        dashboardFrame.setVisible(true);

        confirmButton.setVisible(false);

        userField.setText(user.getLogin());
        firstNameField.setText(user.getPerson().getFirstname());
        lastNameField.setText(user.getPerson().getFamilyname());

        CourseDAO courseDAO = new CourseDAO();
        List<Course> listOfCourses = new ArrayList<>();
        listOfCourses = courseDAO.getAll();
        for (Course course : listOfCourses) {
            courses.addItem(course.getName());
        }

        changeAccountDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dashboardFrame.dispose();
                new UserDetails(user);
            }
        });
        chooseCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                courses.setVisible(true);

            }
        });
        List<Course> finalListOfCourses = listOfCourses;
        courses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String courseName = String.valueOf(courses.getSelectedItem());
                Course course = findByName(finalListOfCourses,courseName);
                Person person = user.getPerson();
                person.setCourse(course);
                PersonDAO personDAO = new PersonDAO();
                personDAO.updateOne(person);
                confirmButton.setVisible(true);



            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Chosen course registered.");
                confirmButton.setVisible(false);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dashboardFrame.dispose();
                new LoginPage();
            }
        });
    }
    public static Course findByName(Collection<Course> courses, String name) {
        return courses.stream().filter(course -> name.equals(course.getName())).findFirst().orElse(null);
    }
}