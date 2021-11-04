package view;

import dao.CourseDAO;
import dao.ModuleDAO;
import entities.Course;
import entities.Module;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UpdateModule extends JFrame {
    private JComboBox modules;
    private JTextField moduleNameField;
    private JComboBox courses;
    private JTextPane moduleDescriptionField;
    private JButton submitButton;
    private JButton returnToAdminDashboardButton;
    private JPanel updateModule;
    private JFrame updateModuleFrame;

    CourseDAO courseDAO = new CourseDAO();
    ModuleDAO moduleDAO = new ModuleDAO();
    Module module = new Module();
    Course course = new Course();
    List<Course> listOfCourses = new ArrayList<>();
    List<Module> listOfModules = new ArrayList<>();


    public UpdateModule(User user) {

        updateModuleFrame = new JFrame("Update Module");
        updateModuleFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        updateModuleFrame.setPreferredSize(new Dimension(500, 400));
        updateModuleFrame.setResizable(false);

        updateModuleFrame.add(updateModule);
        updateModuleFrame.pack();
        updateModuleFrame.setLocationRelativeTo(null);
        updateModuleFrame.setVisible(true);


        listOfModules = moduleDAO.getAll();
        for (Module module : listOfModules) {
            modules.addItem(module.getName());
        }


        listOfCourses = courseDAO.getAll();
        for (Course course : listOfCourses) {
            courses.addItem(course.getName());
        }

        List<Module> finalListOfModules = listOfModules;
        modules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String moduleName = String.valueOf(modules.getSelectedItem());
                Module module = findModuleByName(finalListOfModules, moduleName);
                Module moduleToUpdate = moduleDAO.getOne(module.getId());
                moduleNameField.setText(moduleToUpdate.getName());
                moduleDescriptionField.setText(moduleToUpdate.getDescription());
                courses.setSelectedItem(module.getCourse().getName());
            }
        });


        returnToAdminDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateModuleFrame.dispose();
                new AdminDashboard(user);
            }
        });
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String moduleName = String.valueOf(modules.getSelectedItem());
                Module module = findModuleByName(finalListOfModules, moduleName);
                Module moduleToUpdate = moduleDAO.getOne(module.getId());
                moduleToUpdate.setName(moduleNameField.getText());
                moduleToUpdate.setDescription((moduleDescriptionField.getText()));
                String courseName = String.valueOf(courses.getSelectedItem());
                Course course = findCourseByName(listOfCourses, courseName);
                moduleToUpdate.setCourse(course);
                JOptionPane.showMessageDialog(null, "Module updated.");
                moduleDAO.updateOne(moduleToUpdate);
                updateModuleFrame.dispose();
                new AdminDashboard(user);
            }
        });
    }

    public static Module findModuleByName(Collection<Module> modules, String name) {
        return modules.stream().filter(module -> name.equals(module.getName())).findFirst().orElse(null);
    }
    public static Course findCourseByName(Collection<Course> courses, String name) {
        return courses.stream().filter(course -> name.equals(course.getName())).findFirst().orElse(null);
    }
}
