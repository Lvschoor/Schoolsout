package view;

import dao.ExamDAO;
import dao.ModuleDAO;
import model.Exam;
import model.Module;
import model.User;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class UpdateExam extends JFrame {
    private JPanel updateExam;
    private JComboBox exams;
    private JTextField examDateField;
    private JTextField examWeightField;
    private JTextField examTotalField;
    private JComboBox modules;
    private JTextPane examDescriptionField;
    private JButton submitChangesButton;
    private JButton returnToAdminDashboardButton;
    private JTextField examNameField;
    private JXDatePicker datePicker;
    private JFrame updateExamFrame;

    ModuleDAO moduleDAO = new ModuleDAO();
    Module module = new Module();
    List<Module> listOfModules = new ArrayList<>();
    Exam exam = new Exam();
    ExamDAO examDAO = new ExamDAO();
    List<Exam> listOfExams = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");


    public UpdateExam(User user) {

        updateExamFrame = new JFrame("Update Exam");
        updateExamFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        updateExamFrame.setPreferredSize(new Dimension(500, 400));
        updateExamFrame.setResizable(false);

        updateExamFrame.add(updateExam);
        updateExamFrame.pack();
        updateExamFrame.setLocationRelativeTo(null);
        updateExamFrame.setVisible(true);

        listOfModules = moduleDAO.getAll();
        for (Module module : listOfModules) {
            modules.addItem(module.getName());
        }

        listOfExams = examDAO.getAll();
        for (Exam exam : listOfExams) {
            exams.addItem(exam.getName());
        }
        datePicker.setFormats(new SimpleDateFormat("d.MM.yyyy"));

        exams.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String examName = String.valueOf(exams.getSelectedItem());
                exam = findExamByName(listOfExams, examName);
                Exam examToUpdate = examDAO.getOne(exam.getId());
                examNameField.setText(examName);
                if (examToUpdate.getDate()!=null){
                    datePicker.setDate(Date.from(examToUpdate.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                }
                examDescriptionField.setText(examToUpdate.getDescription());
                examWeightField.setText(String.valueOf(examToUpdate.getWeight()));
                examTotalField.setText(String.valueOf(examToUpdate.getTotal()));
                if (examToUpdate.getModule()!=null){modules.setSelectedItem(examToUpdate.getModule().getName());}


            }
        });

        returnToAdminDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                updateExamFrame.dispose();
                new AdminDashboard(user);
            }
        });
        submitChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String examName = String.valueOf(exams.getSelectedItem());
                exam = findExamByName(listOfExams, examName);
                Exam examToUpdate = examDAO.getOne(exam.getId());

                String moduleName = String.valueOf(modules.getSelectedItem());
                module = findModuleByName(listOfModules,moduleName);

                examToUpdate.setName(examNameField.getText());
                examToUpdate.setDescription(examDescriptionField.getText());
                LocalDate localDate = datePicker.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                examToUpdate.setDate(localDate);
                if (!examWeightField.getText().equals("")){
                    examToUpdate.setWeight(Integer.parseInt(examWeightField.getText()));
                }
                if (!examTotalField.getText().equals("")){
                    examToUpdate.setTotal(Integer.parseInt(examTotalField.getText()));
                }

                if (module.getName() != null){
                    examToUpdate.setModule(module);
                }

                examDAO.updateOne(examToUpdate);

                JOptionPane.showMessageDialog(null, "Exam updated.");
                updateExamFrame.dispose();
                new AdminDashboard(user);

            }
        });
    }

    public static Exam findExamByName(Collection<Exam> exams, String name) {
        return exams.stream().filter(exam -> name.equals(exam.getName())).findFirst().orElse(null);
    }

    public static Module findModuleByName(Collection<Module> modules, String name) {
        return modules.stream().filter(module -> name.equals(module.getName())).findFirst().orElse(null);
    }

}
