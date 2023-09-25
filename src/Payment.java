import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel searchLabel,firstNameLabel,lastNameLabel,monthLabel,amountLabel,title;
    private JTextField searchField,firstNameField,lastNameField;
    private JComboBox months;
    private JButton searchButton,payButton,backButton;
    private String currentMonth="";

    public Payment (){
        title = new JLabel("Payment");
        searchLabel = new JLabel("ID : ");
        searchField = new JTextField();
        searchButton = new JButton("Search");
        firstNameLabel = new JLabel("First Name : ");
        firstNameField = new JTextField();
        firstNameField.setEditable(false);
        lastNameLabel = new JLabel("Last Name : ");
        lastNameField = new JTextField();
        lastNameField.setEditable(false);
        monthLabel = new JLabel("Month : ");
        String [] m = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November"," December"};
        months = new JComboBox(m);
        months.setSelectedItem(null);
        amountLabel = new JLabel("Amount : 5000");
        payButton = new JButton("Pay");
        backButton = new JButton("Back");
        tableModel = new DefaultTableModel(new Object[]{"Month","Amount"},0);
        table = new JTable(tableModel);



        setLayout(null);

        title.setFont(new Font("Osake",Font.BOLD,75));
        title.setForeground(Color.WHITE);
        title.setBounds(670,30,1000,200);
        add(title);

        searchLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setBounds(100,300,100,25);
        add(searchLabel);

        searchField.setBounds(160,300,100,25);
        add(searchField);

        searchButton = new JButton(new ImageIcon("Images/search.png"));
        searchButton.setBackground(Color.WHITE);
        searchButton.setBounds(270,300,25,25);
        add(searchButton);


        firstNameLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        firstNameLabel.setForeground(Color.WHITE);
        firstNameLabel.setBounds(100,350,300,25);
        add(firstNameLabel);

        firstNameField.setBounds(100,400,300,25);
        add(firstNameField);

        lastNameLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        lastNameLabel.setForeground(Color.WHITE);
        lastNameLabel.setBounds(100,450,300,25);
        add(lastNameLabel);

        lastNameField.setBounds(100,500,300,25);
        add(lastNameField);

        monthLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        monthLabel.setForeground(Color.WHITE);
        monthLabel.setBounds(100,550,300,25);
        add(monthLabel);

        months.setBounds(100,600,300,25);
        add(months);


        amountLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setBounds(100,650,300,25);
        add(amountLabel);

        payButton.setBackground(Color.white);
        payButton.setForeground(new Color(61, 121, 138));
        payButton.setFont(new Font("Papyrus",Font.BOLD,25));
        payButton.setFocusable(false);
        payButton.setBounds(100,750,300,30);
        add(payButton);

        backButton = new JButton("Back");
        backButton.setBackground(Color.white);
        backButton.setForeground(new Color(61, 121, 138));
        backButton.setFocusable(false);
        backButton.setBounds(50,50,75,25);
        add(backButton);

        table.setBackground(new Color(61, 121, 138));
        table.setForeground(Color.WHITE);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(500,300,1100,500);
        add(sp);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID=searchField.getText();
                if(ID.isEmpty()||(!ID.matches("[0-9]+"))){
                    JOptionPane.showMessageDialog(null, "Please Enter a proper ID number", "Error", JOptionPane.WARNING_MESSAGE);
                }else {
                    Connection connection = null;
                    try{
                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("select * from payment where ID = " + ID);
                        while(resultSet.next()) {
                            String month = resultSet.getString("month");
                            String amount = resultSet.getString("amount");
                            tableModel.addRow(new Object[]{month,amount});
                        }
                    }catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    try {
                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
                        Statement statement = connection.createStatement();
                        ResultSet rs = statement.executeQuery("select * from members where CID = " + ID);
                        while (rs.next()) {
                            String fname = rs.getString("firstName");
                            String lname = rs.getString("lastName");
                            firstNameField.setText(fname);
                            lastNameField.setText(lname);
                        }
                    }catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            }
        });

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID=searchField.getText();
                currentMonth = (String)months.getSelectedItem();
                if(currentMonth.isEmpty()||ID.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please Enter all the fields", "Error", JOptionPane.WARNING_MESSAGE);
                }else {
                    boolean noError=true;
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("select * from payment where ID = " + ID);
                        while (resultSet.next()) {
                            String month = resultSet.getString("month");
                            if (month.equals(currentMonth)){
                                String msg = "The payment for "+currentMonth+" has already been paid";
                                JOptionPane.showMessageDialog(null,msg,"Paid",JOptionPane.INFORMATION_MESSAGE);
                                noError=false;
                                break;
                            }
                        }

                        if(noError){
                            PreparedStatement pst = connection.prepareStatement("insert into payment values(?,?,?)");
                            pst.setInt(1,Integer.parseInt(ID));
                            pst.setString(2,currentMonth);
                            pst.setInt(3,5000);
                            pst.executeUpdate();
                            tableModel.addRow(new Object[]{currentMonth,5000});
                            JOptionPane.showMessageDialog(null,"Payment added successfully","Successful",JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            new AdminPage();
                        }
                    }catch (SQLException se) {
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



        setSize(1700, 1000);
        setResizable(false);
        getContentPane().setBackground(new Color(61, 121, 138));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
