package view;

import dao.CourseDAO;
import model.Course;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UpdateCourse extends JFrame{
    private JButton returnToAdminDashboardButton;
    private JComboBox courses;
    private JPanel updateCourse;
    private JTextField courseNameField;
    private JTextField courseImageURLField;
    private JTextField courseCodeField;
    private JTextPane courseDescriptionField;
    private JLabel courseDescriptionLabel;
    private JRadioButton YESRadioButton;
    private JRadioButton NORadioButton;
    private JButton submitChangesButton;
    private JFrame updateCourseFrame;

    CourseDAO courseDAO = new CourseDAO();


    public UpdateCourse(User user){

        updateCourseFrame = new JFrame("Update Course");
        updateCourseFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        updateCourseFrame.setPreferredSize(new Dimension(500, 400));
        updateCourseFrame.setResizable(false);

        updateCourseFrame.add(updateCourse);
        updateCourseFrame.pack();
        updateCourseFrame.setLocationRelativeTo(null);
        updateCourseFrame.setVisible(true);



        List<Course> listOfCourses = new ArrayList<>();
        listOfCourses = courseDAO.getAll();
        for (Course course : listOfCourses) {
            courses.addItem(course.getName());
        }

        List<Course> finalListOfCourses = listOfCourses;
        courses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String courseName = String.valueOf(courses.getSelectedItem());
                Course course = findByName(finalListOfCourses,courseName);
                Course courseToUpdate = courseDAO.getOne(course.getId());
                courseNameField.setText(courseToUpdate.getName());
                courseCodeField.setText(courseToUpdate.getCode());
                courseImageURLField.setText(courseToUpdate.getImageURL());
                courseDescriptionField.setText(courseToUpdate.getDescription());
                if (courseToUpdate.isActive()){
                    YESRadioButton.setSelected(true);
                } else {
                    NORadioButton.setSelected(true);
                }

            }
        });

        returnToAdminDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateCourseFrame.dispose();
                new AdminDashboard(user);
            }
        });

        submitChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String courseName = String.valueOf(courses.getSelectedItem());
                Course course = findByName(finalListOfCourses,courseName);
                Course courseToUpdate = courseDAO.getOne(course.getId());
                courseToUpdate.setName(courseNameField.getText());
                courseToUpdate.setCode(courseCodeField.getText());
                System.out.println(courseToUpdate.getCode());
                courseToUpdate.setImageURL(courseImageURLField.getText());
                courseToUpdate.setActive(YESRadioButton.isSelected());
                courseToUpdate.setDescription(courseDescriptionField.getText());
                courseDAO.updateOne(courseToUpdate);
                JOptionPane.showMessageDialog(null, "Course updated.");
                updateCourseFrame.dispose();
                new AdminDashboard(user);
            }
        });
    }

    public static Course findByName(Collection<Course> courses, String name) {
        return courses.stream().filter(course -> name.equals(course.getName())).findFirst().orElse(null);
    }
}
