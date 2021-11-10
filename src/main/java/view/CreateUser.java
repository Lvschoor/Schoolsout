package view;

import dao.UserDAO;
import model.Person;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUser extends JFrame {
    private JPanel createUser;
    private JTextField userNameField;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JButton createUserButton;
    private JFrame createUserFrame;

    public JFrame getCreateUserFrame() {
        return createUserFrame;
    }

    public CreateUser() {

        createUserFrame = new JFrame("Create new user");
        createUserFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        createUserFrame.setPreferredSize(new Dimension(300, 250));
        createUserFrame.setResizable(false);

        createUserFrame.add(createUser);
        createUserFrame.pack();

        createUserFrame.setLocationRelativeTo(null);
        createUserFrame.setVisible(true);


        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UserDAO userDAO = new UserDAO();
                User user = new User();
                if (userNameField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter username!");
                } else {
                    if (userDAO.getOne(userNameField.getText())!= null){
                        JOptionPane.showMessageDialog(null, "Username already exists!");
                    } else {

                    if (String.valueOf(passwordField1.getPassword()).equals(String.valueOf(passwordField2.getPassword()))) {

                        user.setLogin(userNameField.getText());
                        user.setPasswordhash(String.valueOf(passwordField2.getPassword()));
                        user.setActive(true);
                        user.setPerson(new Person());
                        userDAO.createOne(user);
                        createUserFrame.dispose();
                        new LoginPage();


                        JOptionPane.showMessageDialog(null, "New user created.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Passwords don't match!!");
                    }}
                }


            }
        });
    }
}
