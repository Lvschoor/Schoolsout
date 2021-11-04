package view;

import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame{
    private JPanel adminDashboard;
    private JButton createCourseButton;
    private JButton updateCourseButton;
    private JButton createModuleButton;
    private JButton updateModuleButton;
    private JButton createExamButton;
    private JButton updateExamButton;
    private JButton logoutButton;
    private JButton deleteUserButton;
    private JFrame adminDashboardFrame;


    public AdminDashboard(User user){
        adminDashboardFrame = new JFrame("Admin dashboard");
        adminDashboardFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        adminDashboardFrame.setPreferredSize(new Dimension(400, 300));
        adminDashboardFrame.setResizable(false);

        adminDashboardFrame.add(adminDashboard);
        adminDashboardFrame.pack();
        adminDashboardFrame.setLocationRelativeTo(null);
        adminDashboardFrame.setVisible(true);


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                adminDashboardFrame.dispose();
                new LoginPage();
            }
        });
        createCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                adminDashboardFrame.dispose();
                new CreateCourse(user);

            }
        });
        updateCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                adminDashboardFrame.dispose();
                new UpdateCourse(user);
            }
        });
        createModuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                adminDashboardFrame.dispose();
                new CreateModule(user);
            }
        });
        updateModuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                adminDashboardFrame.dispose();
                new UpdateModule(user);
            }
        });
        createExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                adminDashboardFrame.dispose();
                new CreateExam(user);
            }
        });
        updateExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                adminDashboardFrame.dispose();
                new UpdateExam(user);
            }
        });
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                adminDashboardFrame.dispose();
                new DeleteUser(user);
            }
        });
    }
}
