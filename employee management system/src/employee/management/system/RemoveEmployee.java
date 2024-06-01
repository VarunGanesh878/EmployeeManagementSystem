package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class RemoveEmployee extends JFrame implements ActionListener {

    Choice choiceEMPID;

    JButton delete, back;


    RemoveEmployee(){

        JLabel empid = new JLabel("EMPID");
        empid.setForeground(Color.red);
        empid.setBounds(20,20,100,30);
        add(empid);

        choiceEMPID = new Choice();
        choiceEMPID.setBounds(150,20,150,20);
        add(choiceEMPID);

        try{
            conn conn = new conn();
            ResultSet resultSet = conn.statement.executeQuery("select * from employee");
            while(resultSet.next()){
                choiceEMPID.add(resultSet.getString("empid"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }

        JLabel name = new JLabel("Name");
        name.setBounds(20,70,100,30);
        name.setFont(new Font("Tamoha",Font.BOLD,15));
        add(name);

        JLabel textname = new JLabel();
        textname.setBounds(150,70,100,30);
        add(textname);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(20,130,100,30);
        phone.setFont(new Font("Tamoha",Font.BOLD,15));
        add(phone);

        JLabel textphone = new JLabel();
        textphone.setBounds(150,130,100,30);
        add(textphone);

        JLabel email = new JLabel("Email");
        email.setBounds(20,180,100,30);
        email.setFont(new Font("Tamoha",Font.BOLD,15));
        add(email);

        JLabel textemail = new JLabel();
        textemail.setBounds(150,180,200,30);
        add(textemail);

        try{
            conn conn = new conn();
            ResultSet resultSet = conn.statement.executeQuery("select * from employee where empid = '"+choiceEMPID.getSelectedItem()+"'");
            while(resultSet.next()){

                textname.setText(resultSet.getString("name"));
                textphone.setText(resultSet.getString("phone"));
                textemail.setText(resultSet.getString("email"));

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        choiceEMPID.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                try{
                    conn conn = new conn();
                    ResultSet resultSet = conn.statement.executeQuery("select * from employee where empid = '"+choiceEMPID.getSelectedItem()+"'");
                    while(resultSet.next()){

                        textname.setText(resultSet.getString("name"));
                        textphone.setText(resultSet.getString("phone"));
                        textemail.setText(resultSet.getString("email"));

                    }

                }catch (Exception E){
                    E.printStackTrace();
                }


            }
        });

        delete = new JButton("DELETE");
        delete.setBounds(50,300,100,30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("BACK");
        back.setBounds(200,300,100,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/rback.png"));
        Image i22 = i11.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i22);
        JLabel imgg = new JLabel(i33);
        imgg.setBounds(0,0,600,400);
        add(imgg);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(400,80,200,200);
        imgg.add(img);






        setSize(650,450);
        setLayout(null);
        setLocation(350,100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == delete) {
            try {
                conn conn = new conn();
                String query = "delete from employee where empid='" + choiceEMPID.getSelectedItem() + "'";
                conn.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Employee Deleted Successfully");
                setVisible(false);
                new Main_class();

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else if (e.getSource() == back) {
                    setVisible(false);
                    new Main_class();
                }
            }




    public static void main(String[] args) {

        new RemoveEmployee();
    }
    }



