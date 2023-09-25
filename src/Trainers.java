import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Trainers extends JFrame {
    private int ID,age;
    private String firstName,lastName,gender,mobileNumber,SID;
    private JTable table;
    private DefaultTableModel tableModel,searchTableModel;
    private JPanel panel;
    private JButton editButton,deleteButton,searchButton,clearButton,backButton;

    private JLabel searchLabel;
    private JTextField searchField;

    private JLabel logo;
    private boolean searchButtonClicked = false;

    public Trainers() {
        loadPage();
    }

    public void loadPage(){
        setLayout(null);

        logo = new JLabel("Trainers");
        logo.setFont(new Font("Osake",Font.BOLD,75));
        logo.setForeground(Color.WHITE);
        logo.setBounds(670,30,1000,200);
        add(logo);

        searchLabel = new JLabel("ID : ");
        searchLabel.setBounds(650,250,50,25);
        searchField = new JTextField(20);
        searchField.setBounds(750,250,200,25);
        searchButton = new JButton(new ImageIcon("Images/search.png"));
        searchButton.setBackground(Color.WHITE);
        searchButton.setBounds(1000,250,25,25);
        clearButton = new JButton(new ImageIcon("Images/reset.png"));
        clearButton.setBackground(Color.WHITE);
        clearButton.setBounds(1050,250,25,25);

        backButton = new JButton("Back");
        backButton.setBounds(50,50,75,25);
        backButton.setBackground(Color.white);
        backButton.setFocusable(false);
        backButton.setForeground(new Color(61, 121, 138));

        searchLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        searchLabel.setForeground(Color.WHITE);






        add(searchLabel);
        add(searchField);
        add(searchButton);
        add(clearButton);
        add(backButton);


        panel = new JPanel(null);
        editButton = new JButton(new ImageIcon("edit.png"));
        editButton.addActionListener(e->edit());
        editButton.setBounds(0,0,95,30);
        editButton.setBackground(Color.WHITE);
        deleteButton = new JButton(new ImageIcon("delete.png"));
        deleteButton.setBounds(95,0,95,30);
        deleteButton.addActionListener(e-> delete());
        deleteButton.setBackground(Color.WHITE);
        panel.add(editButton);
        panel.add(deleteButton);


        //Load Table
        tableModel = new DefaultTableModel(new Object[]{"ID","First Name","Last Name","Gender","Mobile Number","Age","Action"},0);
        table = new JTable(tableModel);
        table.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRenderer());
        table.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor());
        table.setRowHeight(30);

        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Trainer");
            while(resultSet.next()) {
                ID = resultSet.getInt(1);
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                gender = resultSet.getString("gender");
                mobileNumber = resultSet.getString("mobileNumber");
                age=resultSet.getInt(6);

                tableModel.addRow(new Object[]{ID,firstName,lastName,gender,mobileNumber,age,panel});
            }
        }catch (SQLException se) {
            throw new RuntimeException(se);
        }



        table.setBackground(new Color(61, 121, 138));
        table.setForeground(Color.WHITE);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(200,400,1300,300);
        add(sp);



        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButtonClicked = true;
                SID = searchField.getText();
                searchField.setEditable(false);
                if(SID.isEmpty()||(!SID.matches("[0-9]+"))){
                    JOptionPane.showMessageDialog(null, "Please Enter a proper ID number", "Error", JOptionPane.WARNING_MESSAGE);
                }else{
                    try{
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("select * from Trainer where TID = "+SID);
                        while(resultSet.next()) {
                            int searchID = resultSet.getInt(1);
                            String searchFirstName = resultSet.getString("firstName");
                            String searchLastName = resultSet.getString("lastName");
                            String searchGender = resultSet.getString("gender");
                            String searchMobileNumber = resultSet.getString("mobileNumber");
                            int searchAge = resultSet.getInt(6);


                            searchTableModel = new DefaultTableModel(new Object[]{"ID","First Name","Last Name","Gender","Mobile Number","Age","Action"},0);
                            table.setModel(searchTableModel);
                            table.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRenderer());
                            table.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor());
                            table.setRowHeight(30);
                            searchTableModel.addRow(new Object[]{searchID, searchFirstName, searchLastName, searchGender, searchMobileNumber, searchAge, panel});
                        }
                    }catch (SQLException se) {
                        throw new RuntimeException(se);
                    }
                }
            }
        });


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButtonClicked =false;
                searchField.setText("");
                searchField.setEditable(true);
                table.setModel(tableModel);
                table.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRenderer());
                table.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor());
                table.setRowHeight(30);
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






    //Deletes Trainers
    public void delete() {
        int row = 0;
        boolean noAgreement = true;
        if(searchButtonClicked){
            for(int i = 0; i<tableModel.getRowCount();i++){
                String str = String.valueOf(tableModel.getValueAt(i,0));
                if(str.equals(SID)){
                    row = i;
                    break;
                }
            }
        }else{
            row = table.getSelectedRow();
        }

        int ans = JOptionPane.showConfirmDialog(null, "Are you sure you want to deleted the selected row?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (ans == 0) {
            int ID = (int) tableModel.getValueAt(row, 0);
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
                PreparedStatement statement = connection.prepareStatement("DELETE FROM trainer WHERE TID = ?");
                statement.setInt(1, ID);
                statement.executeUpdate();
            } catch (SQLIntegrityConstraintViolationException si){
                noAgreement = false;
                JOptionPane.showMessageDialog(null,"Please remove the trainer's training agreements Before deleting","Error",JOptionPane.WARNING_MESSAGE);
            } catch (SQLException se) {
                throw new RuntimeException(se);
            }
            if(searchButtonClicked && noAgreement){
                tableModel.removeRow(row);
                table.setModel(tableModel);
                table.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRenderer());
                table.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor());
                table.setRowHeight(30);
            }else if(noAgreement){
                tableModel.removeRow(row);
            }
        }

    }


    //Edit Trainer's information
    public void edit(){
        JFrame frame = new JFrame("Edit");
        frame.setUndecorated(true);
        JButton editButton,cancelButton;

        frame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        int row = 0;
        if(searchButtonClicked){
            for(int i = 0; i<tableModel.getRowCount();i++){
                String str = String.valueOf(tableModel.getValueAt(i,0));
                if(str.equals(SID)){
                    row = i;
                    break;
                }
            }
        }else{
            row = table.getSelectedRow();
        }

        JLabel TrainerIdLabel = new JLabel("Trainer ID :    ");
        TrainerIdLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        TrainerIdLabel.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=2;
        frame.add(TrainerIdLabel,constraints);


        JLabel idLabel = new JLabel(String.valueOf(tableModel.getValueAt(row, 0)));
        idLabel.setForeground(Color.WHITE);
        idLabel.setFont(new Font("Papyrus",Font.BOLD,30));
        constraints.gridx=1;
        constraints.gridy=2;
        frame.add(idLabel,constraints);

        JLabel firstNameLabel = new JLabel("First Name :    ");
        firstNameLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        firstNameLabel.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=4;
        frame.add(firstNameLabel,constraints);

        JTextField firstNameField = new JTextField((String)tableModel.getValueAt(row, 1),60);
        constraints.gridx=1;
        constraints.gridy=4;
        frame.add(firstNameField,constraints);

        JLabel lastNameLabel = new JLabel("Last Name :    ");
        lastNameLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        lastNameLabel.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=6;
        frame.add(lastNameLabel,constraints);

        JTextField lastNameField = new JTextField((String)tableModel.getValueAt(row, 2),60);
        constraints.gridx=1;
        constraints.gridy=6;
        frame.add(lastNameField,constraints);

        JLabel genderLabel=new JLabel("Gender :    ");
        genderLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        genderLabel.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=8;
        frame.add(genderLabel,constraints);

        JComboBox gen = new JComboBox();
        gen.addItem("Male");
        gen.addItem("Female");
        gen.setSelectedItem((String) tableModel.getValueAt(row,3));
        constraints.gridx=1;
        constraints.gridy=8;
        frame.add(gen,constraints);

        JLabel numberLabel = new JLabel("Mobile Number :    ");
        numberLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        numberLabel.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=10;
        frame.add(numberLabel,constraints);

        JTextField numberField = new JTextField((String)tableModel.getValueAt(row, 4),60);
        constraints.gridx=1;
        constraints.gridy=10;
        frame.add(numberField,constraints);

        JLabel ageLabel = new JLabel("Age :    ");
        ageLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        ageLabel.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=12;
        frame.add(ageLabel,constraints);

        JTextField ageField = new JTextField(String.valueOf(tableModel.getValueAt(row, 5)),60);
        constraints.gridx=1;
        constraints.gridy=12;
        frame.add(ageField,constraints);

        JLabel space1 =new JLabel(" ");
        constraints.gridx=1;
        constraints.gridy=14;
        frame.add(space1,constraints);

        JLabel space2 =new JLabel(" ");
        constraints.gridx=1;
        constraints.gridy=16;
        frame.add(space2,constraints);

        editButton = new JButton("Edit");
        editButton.setBackground(new Color(221, 232, 233));
        editButton.setForeground(new Color(61, 121, 138));
        editButton.setFont(new Font("Papyrus",Font.BOLD,20));
        editButton.setSize(200,100);
        editButton.setFocusable(false);
        constraints.gridx=1;
        constraints.gridy=18;
        constraints.fill=GridBagConstraints.BOTH;
        frame.add(editButton,constraints);

        int finalRow = row;

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ID =  Integer.parseInt(idLabel.getText());
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String sex =String.valueOf(gen.getSelectedItem());
                String mobileNumber = numberField.getText();
                String ageStr = ageField.getText();
                int age=0;
                boolean noErrors;

                if (firstName.isEmpty() || lastName.isEmpty() || sex.isEmpty() || mobileNumber.isEmpty() || ageStr.isEmpty()) {
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
                } else {
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
                        PreparedStatement statement = connection.prepareStatement("UPDATE Trainer SET firstName=?,lastName=?,gender=?,mobileNumber=?,age=? WHERE TID = ?");
                        statement.setString(1, firstName);
                        statement.setString(2, lastName);
                        statement.setString(3, sex);
                        statement.setString(4, mobileNumber);
                        statement.setInt(5, age);
                        statement.setInt(6,ID);
                        statement.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Trainer Edited Successfully", "Successful", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    } catch (SQLException se) {
                        throw new RuntimeException(se);
                    }
                    tableModel.setValueAt(ID, finalRow,0);
                    tableModel.setValueAt(firstName, finalRow,1);
                    tableModel.setValueAt(lastName, finalRow,2);
                    tableModel.setValueAt(sex, finalRow,3);
                    tableModel.setValueAt(mobileNumber, finalRow,4);
                    tableModel.setValueAt(age, finalRow,5);
                }
            }
        });

        JLabel space3 =new JLabel(" ");
        constraints.gridx=1;
        constraints.gridy=20;
        frame.add(space3,constraints);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(221, 232, 233));
        cancelButton.setForeground(new Color(61, 121, 138));
        cancelButton.setFont(new Font("Papyrus",Font.BOLD,20));
        cancelButton.setSize(200,100);
        cancelButton.setFocusable(false);
        constraints.gridx=1;
        constraints.gridy=22;
        constraints.fill=GridBagConstraints.BOTH;
        frame.add(cancelButton,constraints);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });


        frame.setSize(800, 500);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(61, 121, 138));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }




    //Overrides cell properties to show the panel
    private class TableActionCellRenderer extends DefaultTableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return panel;
        }
    }


    //Overrides cell properties to make button clickable
    private class TableActionCellEditor extends DefaultCellEditor{
        public TableActionCellEditor() {
            super(new JCheckBox());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }
    }

}
