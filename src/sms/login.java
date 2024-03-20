package sms;
import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener {

   JButton signin, cancel;
   JTextField username;
   JPasswordField password;
    login(){
        setSize(600,400);

        JLabel l1 = new JLabel("Username");
        l1.setBounds(60,80,150,50);
        l1.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l1);

        JLabel l2 = new JLabel("Password");
        l2.setBounds(60,160, 150,50);
        l2.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(l2);

        username = new JTextField();
        username.setBounds(250, 80, 150,40);
        username.setFont(new Font("Segeo", Font.PLAIN, 24));
        add(username);

         password = new JPasswordField();
        password.setBounds(250,160, 150,40);
        add(password);

         signin = new JButton("Sign In");
        signin.setBounds(480,280,80,50);
        signin.addActionListener(this);
        add(signin);


         cancel = new JButton("Cancel");
        cancel.setBounds( 40,280,100,50);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);

        add(cancel);

        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==signin){
            String username = this.username.getText();
            String password = String.valueOf(this.password.getPassword());

            conn c = new conn();
            String query = "select username,password from login where username = '"+username+"' and password = '"+password+"'";
            try{
               ResultSet rs =  c.s.executeQuery(query);
               if(rs.next()){
                   JOptionPane.showMessageDialog(null, "Login Successfull!");
                   new options();
                   setVisible(false);
               }
               else{
                   JOptionPane.showMessageDialog(null, "Invalid Credentials!!");
               }
            }catch (Exception e){

            }
        }

        if(ae.getSource()== cancel){

            System.exit(0);// it will close the app
        }


    }
    public static void main(String[] args) {
        new login();
    }
}
