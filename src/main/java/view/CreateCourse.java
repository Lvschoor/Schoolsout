package view;

import dao.CourseDAO;
import model.Course;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateCourse extends JFrame {
    private JPanel createCourse;
    private JButton returnToAdminDashboardButton;
    private JTextField courseNameField;
    private JTextField courseImageURLField;
    private JTextField courseCodeField;
    private JLabel courseDescriptionLabel;
    private JRadioButton YESRadioButton;
    private JRadioButton NORadioButton;
    private JTextPane courseDescriptionField;
    private JButton submitButton;
    private JFrame createCourseFrame;


    public CreateCourse(User user) {

        createCourseFrame = new JFrame("Create Course");
        createCourseFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        createCourseFrame.setPreferredSize(new Dimension(500, 400));
        createCourseFrame.setResizable(false);

        createCourseFrame.add(createCourse);
        createCourseFrame.pack();
        createCourseFrame.setLocationRelativeTo(null);
        createCourseFrame.setVisible(true);


        returnToAdminDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createCourseFrame.dispose();
                new AdminDashboard(user);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CourseDAO courseDAO = new CourseDAO();
                Course course = new Course();
                course.setName(courseNameField.getText());
                course.setCode(courseCodeField.getText());
                course.setImageURL(courseImageURLField.getText());
                course.setActive(YESRadioButton.isSelected());
                course.setDescription(courseDescriptionField.getText());
                courseDAO.createOne(course);
                JOptionPane.showMessageDialog(null, "New course created.");
                createCourseFrame.dispose();
                new AdminDashboard(user);
            }
        });

        returnToAdminDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createCourseFrame.dispose();
                new AdminDashboard(user);
            }
        });
    }
}
