package view;

import dao.ExamDAO;
import dao.ModuleDAO;
import entities.Exam;
import entities.Module;
import entities.User;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CreateExam extends JFrame {
    private JPanel createExam;
    private JTextField examNameField;
    private JTextPane examDescriptionField;
    private JTextField examWeightField;
    private JTextField examTotalField;
    private JComboBox modules;
    private JButton submitButton;
    private JButton returnToAdminDashboardButton;
    private JXDatePicker datePicker;
    private JLabel examDate;
    private JComboBox exams;
    private JFrame createExamFrame;


    ExamDAO examDAO = new ExamDAO();
    Exam exam =  new Exam();
    Exam examGroup = new Exam();
    ModuleDAO moduleDAO = new ModuleDAO();
    Module module = new Module();
    List<Module> listOfModules = new ArrayList<>();
    List<Exam> listOfExams = new ArrayList<>();


    public CreateExam(User user) {
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

        listOfExams = examDAO.getAll();
        exams.addItem("None");
        for (Exam exam : listOfExams) {
            exams.addItem(exam.getName());
        }

        datePicker.setFormats(new SimpleDateFormat("d.MM.yyyy"));

        modules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String moduleName = String.valueOf(modules.getSelectedItem());
                module = findByName(listOfModules, moduleName);


            }
        });

        exams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String examName = String.valueOf(exams.getSelectedItem());
                examGroup = findExamByName(listOfExams, examName);


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
                System.out.println(examNameField.getText());
                System.out.println("############################################################");
                exam.setName(examNameField.getText());

                LocalDate localDate = datePicker.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                exam.setDate(localDate);
                exam.setDescription(examDescriptionField.getText());
                if (!examWeightField.getText().equals("")) {
                    exam.setWeight(Integer.parseInt(examWeightField.getText()));
                }
                if (!examTotalField.getText().equals("")) {
                    exam.setTotal(Integer.parseInt(examTotalField.getText()));
                }

                if (module.getName() != null) {
                    exam.setModule(module);
                }

                exam.setExamGroup(examGroup);

                examDAO.createOne(exam);
                JOptionPane.showMessageDialog(null, "Exam created.");
                createExamFrame.dispose();
                new AdminDashboard(user);

            }
        });
    }

    public static Module findByName(Collection<Module> modules, String name) {
        return modules.stream().filter(module -> name.equals(module.getName())).findFirst().orElse(null);
    }

    public static Exam findExamByName(Collection<Exam> exams, String name) {
        return exams.stream().filter(exam -> name.equals(exam.getName())).findFirst().orElse(null);
    }
}
