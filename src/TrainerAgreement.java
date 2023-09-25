import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TrainerAgreement extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel membersFirstNameLabel,membersLastNameLabel,membersGenderLabel,searchMemberLabel,trainerFirstNameLabel,trainerLastNameLabel,trainerGenderLabel,searchTrainerLabel,title;
    private JTextField membersFirstNameField,membersLastNameField,membersGenderField,searchMemberField,trainerFirstNameField,trainerLastNameField,trainerGenderField,searchTrainerField;
    private JButton setAgreementButton,removeAgreementButton,backButton,searchMemberButton,searchTrainerButton,clearMemberSearchField,clearTrainerSearchField;
    String memberID="",trainerID="";
    TrainerAgreement(){

        setLayout(null);


        title = new JLabel("Trainer Agreement");
        title.setFont(new Font("Osake",Font.BOLD,75));
        title.setForeground(Color.WHITE);
        title.setBounds(420,30,1000,100);
        add(title);


        tableModel = new DefaultTableModel(new Object[]{"Member ID","Member's First Name","Member's Last Name","Member's Gender","Trainer ID","Trainer's First Name","Trainer's Last Name","Trainer's Gender"},0);

        table = new JTable(tableModel);
        table.setBackground(new Color(61, 121, 138));
        table.setForeground(Color.WHITE);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(100,200,1500,250);
        add(sp);


        searchMemberLabel = new JLabel("Member ID : ");
        searchMemberLabel.setFont(new Font("Papyrus",Font.BOLD,20));
        searchMemberLabel.setForeground(Color.WHITE);
        searchMemberLabel.setBounds(200,550,300,20);
        add(searchMemberLabel);


        searchMemberField = new JTextField();
        searchMemberField.setBounds(330,550,100,25);
        add(searchMemberField);


        searchMemberButton = new JButton(new ImageIcon("Images/search.png"));
        searchMemberButton.setBackground(Color.WHITE);
        searchMemberButton.setBounds(450,550,25,25);
        add(searchMemberButton);

        clearMemberSearchField = new JButton(new ImageIcon("Images/reset.png"));
        clearMemberSearchField.setBackground(Color.WHITE);
        clearMemberSearchField.setBounds(495,550,25,25);
        add(clearMemberSearchField);


        membersFirstNameLabel = new JLabel("First Name: ");
        membersFirstNameLabel.setFont(new Font("Papyrus",Font.BOLD,20));
        membersFirstNameLabel.setForeground(Color.WHITE);
        membersFirstNameLabel.setBounds(200,600,300,20);
        add(membersFirstNameLabel);



        membersFirstNameField = new JTextField();
        membersFirstNameField.setEditable(false);
        membersFirstNameField.setBounds(330,600,300,20);
        add(membersFirstNameField);



        membersLastNameLabel = new JLabel("Last Name:");
        membersLastNameLabel.setFont(new Font("Papyrus",Font.BOLD,20));
        membersLastNameLabel.setForeground(Color.WHITE);
        membersLastNameLabel.setBounds(200,650,300,20);
        add(membersLastNameLabel);



        membersLastNameField = new JTextField();
        membersLastNameField.setEditable(false);
        membersLastNameField.setBounds(330,650,300,20);
        add(membersLastNameField);




        membersGenderLabel = new JLabel("Gender: ");
        membersGenderLabel.setFont(new Font("Papyrus",Font.BOLD,20));
        membersGenderLabel.setForeground(Color.WHITE);
        membersGenderLabel.setBounds(200,700,300,20);
        add(membersGenderLabel);



        membersGenderField = new JTextField();
        membersGenderField.setEditable(false);
        membersGenderField.setBounds(330,700,300,20);
        add(membersGenderField);



        searchTrainerLabel = new JLabel("Trainer ID : ");
        searchTrainerLabel.setFont(new Font("Papyrus",Font.BOLD,20));
        searchTrainerLabel.setForeground(Color.WHITE);
        searchTrainerLabel.setBounds(700,550,300,20);
        add(searchTrainerLabel);


        searchTrainerField = new JTextField();
        searchTrainerField.setBounds(830,550,100,25);
        add(searchTrainerField);

        searchTrainerButton = new JButton(new ImageIcon("Images/search.png"));
        searchTrainerButton.setBackground(Color.WHITE);
        searchTrainerButton.setBounds(950,550,25,25);
        add(searchTrainerButton);


        clearTrainerSearchField = new JButton(new ImageIcon("Images/reset.png"));
        clearTrainerSearchField.setBackground(Color.WHITE);
        clearTrainerSearchField.setBounds(995,550,25,25);
        add(clearTrainerSearchField);



        trainerFirstNameLabel = new JLabel("First Name: ");
        trainerFirstNameLabel.setFont(new Font("Papyrus",Font.BOLD,20));
        trainerFirstNameLabel.setForeground(Color.WHITE);
        trainerFirstNameLabel.setBounds(700,600,300,20);
        add(trainerFirstNameLabel);


        trainerFirstNameField = new JTextField();
        trainerFirstNameField.setEditable(false);
        trainerFirstNameField.setBounds(830,600,300,20);
        add(trainerFirstNameField);



        trainerLastNameLabel = new JLabel("Last Name: ");
        trainerLastNameLabel.setFont(new Font("Papyrus",Font.BOLD,20));
        trainerLastNameLabel.setForeground(Color.WHITE);
        trainerLastNameLabel.setBounds(700,650,300,20);
        add(trainerLastNameLabel);


        trainerLastNameField = new JTextField();
        trainerLastNameField.setEditable(false);
        trainerLastNameField.setBounds(830,650,300,25);
        add(trainerLastNameField);



        trainerGenderLabel = new JLabel("Gender: ");
        trainerGenderLabel.setFont(new Font("Papyrus",Font.BOLD,20));
        trainerGenderLabel.setForeground(Color.WHITE);
        trainerGenderLabel.setBounds(700,700,300,20);
        add(trainerGenderLabel);

        trainerGenderField = new JTextField();
        trainerGenderField.setEditable(false);
        trainerGenderField.setBounds(830,700,300,20);
        add(trainerGenderField);


        backButton = new JButton("Back");
        backButton.setBackground(Color.white);
        backButton.setForeground(new Color(61, 121, 138));
        backButton.setFocusable(false);
        backButton.setBounds(50,50,75,25);
        add(backButton);


        setAgreementButton = new JButton("Set Agreement");
        setAgreementButton.setBackground(Color.white);
        setAgreementButton.setForeground(new Color(61, 121, 138));
        setAgreementButton.setFont(new Font("Papyrus",Font.BOLD,25));
        setAgreementButton.setFocusable(false);
        setAgreementButton.setBounds(1250,620,300,35);
        add(setAgreementButton);


        removeAgreementButton = new JButton("Remove Agreement");
        removeAgreementButton.setBackground(Color.white);
        removeAgreementButton.setForeground(new Color(61, 121, 138));
        removeAgreementButton.setFont(new Font("Papyrus",Font.BOLD,25));
        removeAgreementButton.setFocusable(false);
        removeAgreementButton.setBounds(1250,670,300,35);
        add(removeAgreementButton);


        //populates the table with all the agreements
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
            Statement statement = connection.createStatement();
            String query = "SELECT M.CID,M.firstName,M.lastName,M.gender,T.TID,T.firstName,T.lastName,T.gender " +
                    "FROM Members AS M " +
                    "INNER JOIN trains as tr ON M.CID = tr.CID " +
                    "INNER JOIN Trainer as T ON tr.TID = T.TID";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                int MID = resultSet.getInt(1);
                String MFN = resultSet.getString(2);
                String MLN = resultSet.getString(3);
                String MG = resultSet.getString(4);
                int TID = resultSet.getInt(5);
                String TFN = resultSet.getString(6);
                String TLN = resultSet.getString(7);
                String TG = resultSet.getString(8);

                tableModel.addRow(new Object[]{MID,MFN,MLN,MG,TID,TFN,TLN,TG});
            }
        }catch (SQLException se) {
            throw new RuntimeException(se);
        }



        searchMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = searchMemberField.getText();
                if(ID.isEmpty()||(!ID.matches("[0-9]+"))){
                    JOptionPane.showMessageDialog(null, "Please Enter a proper ID number", "Error", JOptionPane.WARNING_MESSAGE);
                }else {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("Select * from members where CID = "+ID);
                        while (resultSet.next()){
                            memberID = ID;
                            membersFirstNameField.setText(resultSet.getString("firstName"));
                            membersLastNameField.setText(resultSet.getString("lastName"));
                            membersGenderField.setText(resultSet.getString("gender"));
                        }
                    } catch (SQLException se) {
                        throw new RuntimeException(se);
                    }
                }
            }
        });



        searchTrainerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = searchTrainerField.getText();
                if(ID.isEmpty()||(!ID.matches("[0-9]+"))){
                    JOptionPane.showMessageDialog(null, "Please Enter a proper ID number", "Error", JOptionPane.WARNING_MESSAGE);
                }else {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("Select * from Trainer where TID = "+ID);
                        while (resultSet.next()){
                            trainerID=ID;
                            trainerFirstNameField.setText(resultSet.getString("firstName"));
                            trainerLastNameField.setText(resultSet.getString("lastName"));
                            trainerGenderField.setText(resultSet.getString("gender"));

                        }
                    } catch (SQLException se) {
                        throw new RuntimeException(se);
                    }
                }
            }
        });


        setAgreementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean noAgreement = true;
                if(memberID.isEmpty()||(!memberID.matches("[0-9]+"))||trainerID.isEmpty()||(!trainerID.matches("[0-9]+"))){
                    JOptionPane.showMessageDialog(null, "Please Fill all the Field Properly", "Error", JOptionPane.WARNING_MESSAGE);
                }else {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("Select * from trains");
                        while (resultSet.next()) {
                            String MID = resultSet.getString("CID");
                            String TID = resultSet.getString("TID");
                            if (MID.equals(memberID)) {
                                noAgreement = false;
                                JOptionPane.showMessageDialog(null,"The member has an agreement with TID : "+TID+" Please remove that agreement to add a new one!");
                                break;
                            }
                        }
                        if (noAgreement) {
                            PreparedStatement pst = connection.prepareStatement("Insert into trains values (?,?)");
                            pst.setInt(1,Integer.parseInt(memberID));
                            pst.setInt(2,Integer.parseInt(trainerID));
                            pst.executeUpdate();

                            String MFN = membersFirstNameField.getText();
                            String MLN = membersLastNameField.getText();
                            String MG = membersGenderField.getText();
                            String TFN = trainerFirstNameField.getText();
                            String TLN = trainerLastNameField.getText();
                            String TG = trainerGenderField.getText();

                            tableModel.addRow(new Object[]{memberID,MFN,MLN,MG,trainerID,TFN,TLN,TG});
                        }


                    } catch (SQLException se) {
                        throw new RuntimeException(se);
                    }
                }
            }
        });

        removeAgreementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean Agreement = false;
                if(memberID.isEmpty()||(!memberID.matches("[0-9]+"))){
                    JOptionPane.showMessageDialog(null, "Please fill in All member information properly", "Error", JOptionPane.WARNING_MESSAGE);
                }else {
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "MYsql");
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("Select * from trains");
                        while (resultSet.next()) {
                            String MID = resultSet.getString("CID");
                            if (MID.equals(memberID)) {
                                Agreement = true;
                                break;
                            }
                        }
                        if (Agreement) {
                            int ans = JOptionPane.showConfirmDialog(null,"Are you sure you want to remove agreement?","Remove",JOptionPane.YES_NO_OPTION);
                            if(ans==0){
                                PreparedStatement pst = connection.prepareStatement("Delete from trains where CID = ?");
                                pst.setInt(1,Integer.parseInt(memberID));
                                pst.executeUpdate();

                                for(int i = 0; i<tableModel.getRowCount();i++){
                                    String str = String.valueOf(tableModel.getValueAt(i,0));
                                    if(str.equals(memberID)){
                                        tableModel.removeRow(i);
                                        break;
                                    }
                                }

                            }

                        }else{
                            JOptionPane.showMessageDialog(null,"The member has no agreement");
                        }

                    } catch (SQLException se) {
                        throw new RuntimeException(se);
                    }
                }
            }
        });

        clearMemberSearchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                membersFirstNameField.setText("");
                membersLastNameField.setText("");
                membersGenderField.setText("");
                searchMemberField.setText("");
                memberID="";
            }
        });

        clearTrainerSearchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trainerFirstNameField.setText("");
                trainerLastNameField.setText("");
                trainerGenderField.setText("");
                searchTrainerField.setText("");
                trainerID="";
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
