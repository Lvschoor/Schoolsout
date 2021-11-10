package view;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DeleteUser extends JFrame{
    private JPanel deleteUser;
    private JComboBox users;
    private JButton confirmYouWantToButton;
    private JButton returnToAdminDashboardButton;
    private JFrame deleteUserFrame;

    UserDAO userDAO = new UserDAO();
    List<User> listOfUsers = new ArrayList<>();

    public DeleteUser(User user){

        deleteUserFrame = new JFrame("Delete user");
        deleteUserFrame.setDefaultCloseOperation((EXIT_ON_CLOSE));
        deleteUserFrame.setPreferredSize(new Dimension(500, 300));
        deleteUserFrame.setResizable(false);

        deleteUserFrame.add(deleteUser);
        deleteUserFrame.pack();

        deleteUserFrame.setLocationRelativeTo(null);
        deleteUserFrame.setVisible(true);

        listOfUsers = userDAO.getAll();
        for(User userForList : listOfUsers){
            users.addItem(userForList.getLogin());

        }

        returnToAdminDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteUserFrame.dispose();
                new AdminDashboard(user);
            }
        });


        confirmYouWantToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                User userToBeDeleted = userDAO.getOne(users.getSelectedItem().toString());
                userDAO.deleteOne(userToBeDeleted);
                JOptionPane.showMessageDialog(null, "User deleted.");
                deleteUserFrame.dispose();
                new AdminDashboard(user);
            }
        });
    }

}
