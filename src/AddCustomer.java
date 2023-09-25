import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddCustomer extends JFrame {

    JLabel memberIdLabel,idLabel,firstNameLabel,lastNameLabel,genderLabel,numberLabel,ageLabel,logoLabel,titleLabel;
    JTextField firstNameField,lastNameField,numberField,ageField;

    JButton addButton,cancelButton;

    JComboBox gender;


    AddCustomer() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        logoLabel = new JLabel(new ImageIcon("Images/AddUser.png"));
        constraints.gridx=0;
        constraints.gridy=0;
        add(logoLabel,constraints);


        titleLabel = new JLabel("Add Member");
        titleLabel.setFont(new Font("Osake",Font.BOLD,75));
        titleLabel.setForeground(Color.WHITE);
        constraints.gridx=1;
        constraints.gridy=0;
        add(titleLabel,constraints);


        memberIdLabel = new JLabel("Member ID :    ");
        memberIdLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        memberIdLabel.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=2;
        add(memberIdLabel,constraints);


        idLabel = new JLabel();
        idLabel.setForeground(Color.WHITE);
        constraints.gridx=1;
        constraints.gridy=2;
        add(idLabel,constraints);


        //auto generates the ID Number
        try {
            int id;
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select max(CID) from members");
            if (resultSet.next()){
                id=resultSet.getInt(1)+1;
                idLabel.setText(String.valueOf(id));
                idLabel.setFont(new Font("Papyrus",Font.BOLD,30));
            }
        } catch (SQLException se) {
            throw new RuntimeException(se);
        }


        firstNameLabel = new JLabel("First Name :    ");
        firstNameLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        firstNameLabel.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=4;
        add(firstNameLabel,constraints);

        firstNameField = new JTextField(60);
        constraints.gridx=1;
        constraints.gridy=4;
        add(firstNameField,constraints);

        lastNameLabel = new JLabel("Last Name :    ");
        lastNameLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        lastNameLabel.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=6;
        add(lastNameLabel,constraints);

        lastNameField = new JTextField(60);
        constraints.gridx=1;
        constraints.gridy=6;
        add(lastNameField,constraints);

        genderLabel=new JLabel("Gender :    ");
        genderLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        genderLabel.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=8;
        add(genderLabel,constraints);

        gender = new JComboBox<String>();
        gender.addItem("Male");
        gender.addItem("Female");
        constraints.gridx=1;
        constraints.gridy=8;
        add(gender,constraints);

        numberLabel = new JLabel("Mobile Number :    ");
        numberLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        numberLabel.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=10;
        add(numberLabel,constraints);

        numberField = new JTextField(60);
        constraints.gridx=1;
        constraints.gridy=10;
        add(numberField,constraints);

        ageLabel = new JLabel("Age :    ");
        ageLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        ageLabel.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=12;
        add(ageLabel,constraints);

        ageField = new JTextField(60);
        constraints.gridx=1;
        constraints.gridy=12;
        add(ageField,constraints);



        JLabel space1 =new JLabel(" ");
        constraints.gridx=1;
        constraints.gridy=14;
        add(space1,constraints);


        JLabel space2 =new JLabel(" ");
        constraints.gridx=1;
        constraints.gridy=16;
        add(space2,constraints);


        addButton = new JButton("Add");
        addButton.setBackground(new Color(221, 232, 233));
        addButton.setForeground(new Color(61, 121, 138));
        addButton.setFont(new Font("Papyrus",Font.BOLD,20));
        addButton.setSize(200,100);
        addButton.setFocusable(false);
        constraints.gridx=1;
        constraints.gridy=18;
        constraints.fill=GridBagConstraints.BOTH;
        add(addButton,constraints);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMembers();
            }
        });




        JLabel space3 =new JLabel(" ");
        constraints.gridx=1;
        constraints.gridy=20;
        add(space3,constraints);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(221, 232, 233));
        cancelButton.setForeground(new Color(61, 121, 138));
        cancelButton.setFont(new Font("Papyrus",Font.BOLD,20));
        cancelButton.setSize(200,100);
        cancelButton.setFocusable(false);
        constraints.gridx=1;
        constraints.gridy=22;
        constraints.fill=GridBagConstraints.BOTH;
        add(cancelButton,constraints);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminPage();
            }
        });

        setSize(1700, 1000);
        setResizable(false);
        getContentPane().setBackground(new Color(61, 121, 138));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void addMembers(){
        int ID =  Integer.parseInt(idLabel.getText());
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String gen =String.valueOf(gender.getSelectedItem());
        String mobileNumber = numberField.getText();
        String ageStr = ageField.getText();
        int age=0;
        boolean noErrors = true;

        if (firstName.isEmpty() || lastName.isEmpty() || gen.isEmpty() || mobileNumber.isEmpty() || ageStr.isEmpty()) {
                noErrors=false;
                JOptionPane.showMessageDialog(null, "Please fill in All The forms", "Error", JOptionPane.WARNING_MESSAGE);
        }else if (!firstName.matches("[a-zA-Z]+")) {
                noErrors = false;
                JOptionPane.showMessageDialog(null, "Please enter a valid First Name", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (!lastName.matches("[a-zA-Z]+")) {
                noErrors = false;
                JOptionPane.showMessageDialog(null, "Please enter a valid Last Name", "Error", JOptionPane.WARNING_MESSAGE);
        } else if ((!mobileNumber.matches("[0-9]+")) || mobileNumber.length() != 10) {
                noErrors = false;
                JOptionPane.showMessageDialog(null, "Please enter a valid Mobile Number", "Error", JOptionPane.WARNING_MESSAGE);
        } else if (!ageStr.matches("[0-9]+")) {
                noErrors = false;
                JOptionPane.showMessageDialog(null, "Please enter a valid Age", "Error", JOptionPane.WARNING_MESSAGE);
        }else{
                age = Integer.parseInt(ageStr);
                if (age < 13 || age > 50) {
                    noErrors = false;
                    JOptionPane.showMessageDialog(null, "Age should be between 13 - 50", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    noErrors = true;
                }
        }

        if(noErrors){
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
                PreparedStatement statement = connection.prepareStatement("insert into members values (?,?,?,?,?,?)");
                statement.setInt(1,ID);
                statement.setString(2, firstName);
                statement.setString(3, lastName);
                statement.setString(4, gen);
                statement.setString(5, mobileNumber);
                statement.setInt(6, age);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Member Added Successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new AdminPage();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
