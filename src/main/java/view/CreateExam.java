package view;

import dao.ExamDAO;
import dao.ModuleDAO;
import entities.Exam;
import entities.Module;
import entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CreateExam extends JFrame{
    private JPanel createExam;
    private JTextField examNameField;
    private JTextPane examDescriptionField;
    private JTextField examDateField;
    private JTextField examWeightField;
    private JTextField examTotalField;
    private JComboBox modules;
    private JButton submitButton;
    private JButton returnToAdminDashboardButton;
    private JFrame createExamFrame;


    ExamDAO examDAO = new ExamDAO();
    Exam exam = new Exam();
    ModuleDAO moduleDAO = new ModuleDAO();
    Module module = new Module();
    List<Module> listOfModules = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");


    public CreateExam(User user){
        createExamFrame = new JFrame("Create Exam");
        createExamFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        createExamFrame.setPreferredSize(new Dimension(500, 400));
        createExamFrame.setResizable(false);

        createExamFrame.add(createExam);
        createExamFrame.pack();
        createExamFrame.setLocationRelativeTo(null);
        createExamFrame.setVisible(true);

        listOfModules = moduleDAO.getAll();
        for (Module module : listOfModules) {
            modules.addItem(module.getName());
        }

        modules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String moduleName = String.valueOf(modules.getSelectedItem());
                module = findByName(listOfModules, moduleName);


            }
        });

        returnToAdminDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createExamFrame.dispose();
                new AdminDashboard(user);
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                exam.setName(examNameField.getText());
                LocalDate localDate = LocalDate.parse(examDateField.getText(),formatter);
                exam.setDate(localDate);
                exam.setDescription(examDescriptionField.getText());
                exam.setWeight(Integer.parseInt(examWeightField.getText()));
                exam.setTotal(Integer.parseInt(examTotalField.getText()));
                exam.setModule(module);
                examDAO.updateOne(exam);
                JOptionPane.showMessageDialog(null, "Exam created.");
                createExamFrame.dispose();
                new AdminDashboard(user);

            }
        });
    }
    public static Module findByName(Collection<Module> modules, String name) {
        return modules.stream().filter(module -> name.equals(module.getName())).findFirst().orElse(null);
    }
}
