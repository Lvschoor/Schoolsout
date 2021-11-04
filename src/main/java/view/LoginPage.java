package view;

import dao.UserDAO;
import entities.User;
import services.LoginService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField userNameField;
    private JPanel login;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel userLabel;
    private JLabel password;
    private JButton createUserButton;
    private JFrame loginFrame;




    public LoginPage() {

        loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation((EXIT_ON_CLOSE));
        loginFrame.setPreferredSize(new Dimension(250, 200));
        loginFrame.setResizable(false);

        loginFrame.add(login);
        loginFrame.pack();

        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (LoginService.loginCheck(userNameField.getText(), String.valueOf(passwordField.getPassword()))) {
                    JOptionPane.showMessageDialog(null, "You logged in");
                    loginFrame.dispose();
                    UserDAO userDAO = new UserDAO();
                    User user = userDAO.getOne(userNameField.getText());
                    if (user.getPerson()==null){
                        new UserDetails(user);
                    } else if(user.getLogin().equals("admin")){
                        new AdminDashboard(user);
                    } else
                        new Dashboard(user);


                } else {
                    JOptionPane.showMessageDialog(null, "Wrong combination of username and password!");
                }
                userNameField.setText("");
                passwordField.setText("");

            }
        });
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loginFrame.dispose();
                new CreateUser();


            }
        });
    }
}
