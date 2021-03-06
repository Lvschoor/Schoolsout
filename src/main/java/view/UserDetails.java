package view;

import dao.PersonDAO;
import dao.UserDAO;
import model.Gender;
import model.Person;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDetails extends JFrame {
    private JPanel userDetails;
    private JTextField firstName;
    private JTextField lastName;
    private JButton submitButton;
    private JRadioButton maleRadioButton;
    private JRadioButton otherRadioButton;
    private JRadioButton femaleRadioButton;
    private ButtonGroup genderButtonGroup;
    private JFrame userDetailsFrame;

    UserDAO userDAO = new UserDAO();
    PersonDAO personDAO = new PersonDAO();
    Person person;

    public UserDetails(User user) {
        userDetailsFrame = new JFrame("User details");
        userDetailsFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        userDetailsFrame.setPreferredSize(new Dimension(300, 250));
        userDetailsFrame.setResizable(false);

        userDetailsFrame.add(userDetails);
        userDetailsFrame.pack();
        userDetailsFrame.setLocationRelativeTo(null);
        userDetailsFrame.setVisible(true);

        maleRadioButton.setActionCommand("Male");
        femaleRadioButton.setActionCommand("Female");
        otherRadioButton.setActionCommand("Other");

        person = personDAO.getOne(user.getPerson().getId());
        if (user.getPerson().getFirstname() != null) {

            firstName.setText(userDAO.getOne(user.getLogin()).getPerson().getFirstname());
            lastName.setText(userDAO.getOne(user.getLogin()).getPerson().getFamilyname());
            if (userDAO.getOne(user.getLogin()).getPerson().getGender() == Gender.MALE) {
                maleRadioButton.setSelected(true);
            } else if (userDAO.getOne(user.getLogin()).getPerson().getGender() == Gender.FEMALE) {
                femaleRadioButton.setSelected(true);
            } else otherRadioButton.setSelected(true);

        }


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                person.setFirstname(firstName.getText());
                person.setFamilyname(lastName.getText());
                if (maleRadioButton.isSelected()) {
                    person.setGender(Gender.MALE);
                } else if (femaleRadioButton.isSelected()) {
                    person.setGender(Gender.FEMALE);
                } else {
                    person.setGender(Gender.OTHER);
                }
                user.setPerson(person);
                userDAO.updateOne(user);

                userDetailsFrame.dispose();
                new Dashboard(user);
            }
        });
    }

}
