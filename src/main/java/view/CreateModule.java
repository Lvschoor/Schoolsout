package view;

import dao.CourseDAO;
import dao.ModuleDAO;
import model.Course;
import model.Module;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CreateModule extends JFrame {
    private JPanel createModule;
    private JTextField moduleNameField;
    private JTextPane moduleDescriptionField;
    private JButton submitButton;
    private JButton returnToAdminDashboardButton;
    private JLabel courseDescriptionLabel;
    private JComboBox courses;
    private JFrame createModuleFrame;

    CourseDAO courseDAO = new CourseDAO();
    ModuleDAO moduleDAO = new ModuleDAO();
    Module module = new Module();
    Course course = new Course();

    public CreateModule(User user) {

        createModuleFrame = new JFrame("Create Module");
        createModuleFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        createModuleFrame.setPreferredSize(new Dimension(500, 400));
        createModuleFrame.setResizable(false);

        createModuleFrame.add(createModule);
        createModuleFrame.pack();
        createModuleFrame.setLocationRelativeTo(null);
        createModuleFrame.setVisible(true);


        java.util.List<Course> listOfCourses = new ArrayList<>();
        listOfCourses = courseDAO.getAll();
        for (Course courseFromList : listOfCourses) {
            courses.addItem(courseFromList.getName());
        }

        List<Course> finalListOfCourses = listOfCourses;
        courses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String courseName = String.valueOf(courses.getSelectedItem());
                course = findByName(finalListOfCourses, courseName);


            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                module.setName(moduleNameField.getText());
                module.setDescription(moduleDescriptionField.getText());
                if (course.getName() != null) {
                    module.setCourse(course);
                }
                moduleDAO.updateOne(module);

                JOptionPane.showMessageDialog(null, "New module created.");
                createModuleFrame.dispose();
                new AdminDashboard(user);
            }
        });
        returnToAdminDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createModuleFrame.dispose();
                new AdminDashboard(user);
            }
        });
    }

    public static Course findByName(Collection<Course> courses, String name) {
        return courses.stream().filter(course -> name.equals(course.getName())).findFirst().orElse(null);
    }
}
