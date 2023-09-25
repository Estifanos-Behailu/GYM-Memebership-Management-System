import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame{
    JButton addCustomer,addTrainer,listCustomers,listTrainers,payment,trainerAgreement,security;
    JLabel title,addCustomerLabel,addTrainerLabel,listCustomersLabel,listTrainersLabel,paymentLabel,trainerAgreementLabel,securityLabel;
    AdminPage(){
        setLayout(null);


        addCustomer = new JButton(new ImageIcon("Images/AddUser.png"));
        addCustomer.setBounds(300,200,200,200);
        addCustomerLabel = new JLabel("Add Member");
        addCustomerLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        addCustomerLabel.setBounds(320,350,200,200);
        addCustomerLabel.setForeground(Color.WHITE);
        add(addCustomer);
        add(addCustomerLabel);
        addCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddCustomer();
            }
        });

        listCustomers = new JButton(new ImageIcon("Images/List.png"));
        listCustomers.setBounds(600,200,200,200);
        listCustomersLabel= new JLabel("List Members");
        listCustomersLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        listCustomersLabel.setBounds(620,350,200,200);
        listCustomersLabel.setForeground(Color.WHITE);
        add(listCustomers);
        add(listCustomersLabel);
        listCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Members();
            }
        });

        addTrainer = new JButton(new ImageIcon("Images/AddUser.png"));
        addTrainer.setBounds(900,200,200,200);
        addTrainerLabel = new JLabel("Add Trainer");
        addTrainerLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        addTrainerLabel.setBounds(925,350,200,200);
        addTrainerLabel.setForeground(Color.WHITE);
        add(addTrainer);
        add(addTrainerLabel);
        addTrainer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddTrainer();
            }
        });

        listTrainers = new JButton(new ImageIcon("Images/List.png"));
        listTrainers.setBounds(1200,200,200,200);
        listTrainersLabel= new JLabel("List Trainer");
        listTrainersLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        listTrainersLabel.setBounds(1230,350,200,200);
        listTrainersLabel.setForeground(Color.WHITE);
        add(listTrainers);
        add(listTrainersLabel);
        listTrainers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Trainers();
            }
        });


        payment = new JButton(new ImageIcon("Images/Payment.png"));
        payment.setBounds(450,600,200,200);
        paymentLabel = new JLabel("Payment");
        paymentLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        paymentLabel.setBounds(500,750,200,200);
        paymentLabel.setForeground(Color.WHITE);
        add(payment);
        add(paymentLabel);
        payment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Payment();
            }
        });


        trainerAgreement = new JButton(new ImageIcon("Images/Assign.png"));
        trainerAgreement.setBounds(750,600,200,200);
        trainerAgreementLabel = new JLabel("Trainer Agreement");
        trainerAgreementLabel.setFont(new Font("Papyrus",Font.BOLD,20));
        trainerAgreementLabel.setBounds(760,750,200,200);
        trainerAgreementLabel.setForeground(Color.WHITE);
        add(trainerAgreement);
        add(trainerAgreementLabel);
        trainerAgreement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TrainerAgreement();
            }
        });

        security = new JButton(new ImageIcon("Images/Change.png"));
        security.setBounds(1050,600,200,200);
        securityLabel = new JLabel("Security");
        securityLabel.setFont(new Font("Papyrus",Font.BOLD,25));
        securityLabel.setBounds(1090,750,200,200);
        securityLabel.setForeground(Color.WHITE);
        add(security);
        add(securityLabel);
        security.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Security();
            }
        });


        title = new JLabel("The Gym");
        title.setFont(new Font("Osake",Font.BOLD,100));
        title.setBounds(600,0,500,200);
        title.setForeground(Color.WHITE);
        add(title);


        setSize(1700,1000);
        setResizable(false);
        getContentPane().setBackground(new Color(61, 121, 138));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

}
