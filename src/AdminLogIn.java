import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminLogIn extends JFrame{
    JButton logInButton;
    JLabel userNameLabel, passwordLabel, welcomeLabel,logo;
    JTextField textField;
    JPasswordField passwordField;

    String username,password;
    AdminLogIn(){

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from AdminAccount");

            if (resultSet.next()) {
                username = resultSet.getString("UserName");
                password = resultSet.getString("Password");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        setLayout(new GridBagLayout());
        JPanel panel1 = new JPanel(new GridBagLayout());
        panel1.setBackground(Color.WHITE);
        JPanel panel2 = new JPanel(new GridBagLayout());
        panel2.setBackground(new Color(61, 121, 138));


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,50,10,50);


        welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font("Osake",Font.BOLD,45));
        welcomeLabel.setForeground(new Color(61, 121, 138));
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridheight=2;
        panel1.add(welcomeLabel,constraints);


        add(panel1);


        userNameLabel = new JLabel("User Name");
        userNameLabel.setFont(new Font("Osake",Font.BOLD,40));
        userNameLabel.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=0;
        panel2.add(userNameLabel,constraints);


        textField = new JTextField(25);
        constraints.gridx=0;
        constraints.gridy=2;
        panel2.add(textField,constraints);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Osake",Font.BOLD,40));
        passwordLabel.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=4;
        panel2.add(passwordLabel,constraints);


        passwordField = new JPasswordField(25);
        passwordField.setEchoChar('*');
        constraints.gridx=0;
        constraints.gridy=6;
        panel2.add(passwordField,constraints);



        //Log in Button
        logInButton = new JButton("Log In");
        logInButton.setSize(20,15);
        logInButton.setFocusable(false);
        logInButton.setBackground(new Color(218, 218, 213));
        logInButton.setForeground(new Color(61, 121, 138));
        constraints.gridx=0;
        constraints.gridy=8;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        panel2.add(logInButton,constraints);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char [] c = passwordField.getPassword();
                String pass = new String(c);
                String user = textField.getText();

                if(user.isEmpty() || pass.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Please fill in your Username and Password","Requirement",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    if(user.equals(username) && pass.equals(password)){
                        dispose();
                        new AdminPage();
                    }else{
                        JOptionPane.showMessageDialog(null,"Wrong Username or Password","Error",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });



        //Adding panels

        add(panel2);

        getContentPane().setBackground(new Color(61, 121, 138));
        setSize(700,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
