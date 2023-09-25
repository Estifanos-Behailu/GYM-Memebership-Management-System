import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Security extends JFrame{

    JLabel oldPasswordLabel,newPasswordLabel,confirmPasswordLabel,title;
    JTextField oldPasswordField;
    JPasswordField newPasswordField,confirmPasswordField;
    JButton changeButton,backButton;

    Security(){
        setLayout(null);

        title = new JLabel("Change Password");
        title.setFont(new Font("Osake",Font.BOLD,40));
        title.setForeground(Color.WHITE);
        title.setBounds(150,30,1000,75);
        add(title);


        oldPasswordLabel = new JLabel("Old Password");
        oldPasswordLabel.setFont(new Font("Papyrus",Font.BOLD,20));
        oldPasswordLabel.setForeground(Color.WHITE);
        oldPasswordLabel.setBounds(270,110,300,20);
        add(oldPasswordLabel);

        oldPasswordField = new JTextField(20);
        oldPasswordField.setBounds(260,150,150,25);
        add(oldPasswordField);


        newPasswordLabel = new JLabel("New Password");
        newPasswordLabel.setFont(new Font("Papyrus",Font.BOLD,20));
        newPasswordLabel.setForeground(Color.WHITE);
        newPasswordLabel.setBounds(270,210,300,20);
        add(newPasswordLabel);


        newPasswordField = new JPasswordField(20);
        newPasswordField.setBounds(260,250,150,25);
        add(newPasswordField);

        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(new Font("Papyrus",Font.BOLD,20));
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setBounds(250,310,300,20);
        add(confirmPasswordLabel);

        confirmPasswordField= new JPasswordField(20);
        confirmPasswordField.setBounds(260,350,150,25);
        add(confirmPasswordField);


        changeButton = new JButton("Change");
        changeButton.setForeground(new Color(61, 121, 138));
        changeButton.setBackground(Color.white);
        changeButton.setFont(new Font("Papyrus",Font.BOLD,25));
        changeButton.setFocusable(false);
        changeButton.setBounds(260,425,150,30);
        add(changeButton);

        backButton = new JButton("Back");
        backButton.setForeground(new Color(61, 121, 138));
        backButton.setBackground(Color.white);
        backButton.setFocusable(false);
        backButton.setBounds(20,20,75,25);
        add(backButton);




        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String old = oldPasswordField.getText();

                char [] n = newPasswordField.getPassword();
                String newP = new String(n);

                char [] c = confirmPasswordField.getPassword();
                String confirm = new String((c));

                if(old.isEmpty()||newP.isEmpty()||confirm.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Please fill in all the forms","Error",JOptionPane.WARNING_MESSAGE);
                }else if(newP.contains(" ")) {
                    JOptionPane.showMessageDialog(null, "Can't have a space in your Password", "Error", JOptionPane.WARNING_MESSAGE);
                }else if(!newP.equals(confirm)){
                    JOptionPane.showMessageDialog(null,"New Password Doesn't match","Error",JOptionPane.WARNING_MESSAGE);
                }else if(old.equals(newP)){
                    JOptionPane.showMessageDialog(null,"New Password can't be the same as the old password","Error",JOptionPane.WARNING_MESSAGE);
                }else{
                    boolean match=true;
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("Select * from AdminAccount");
                        if(resultSet.next()) {
                            String oldPass = resultSet.getString("Password");
                            if(oldPass.equals(old)){
                                PreparedStatement pst = connection.prepareStatement("UPDATE AdminAccount SET password = ? ");
                                pst.setString(1,newP);
                                pst.executeUpdate();
                                JOptionPane.showMessageDialog(null,"Password Changed","Error",JOptionPane.WARNING_MESSAGE);
                                dispose();
                                new AdminLogIn();
                            }else{
                                JOptionPane.showMessageDialog(null,"Wrong Old Password","Error",JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    } catch (SQLException se) {
                        throw new RuntimeException(se);
                    }
                }
            }
        });




        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminPage();
            }
        });





        getContentPane().setBackground(new Color(61, 121, 138));
        setSize(700,550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
