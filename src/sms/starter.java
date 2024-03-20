package sms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class starter extends JFrame implements ActionListener {

    JButton login;

    starter(){
        setSize(800,600);

        JLabel l1 = new JLabel("Student Monitoring System");
        l1.setBounds(180,50,500,100);
        l1.setFont(new Font("Tahoma", Font.BOLD, 32));
        add(l1);

         login = new JButton("LOGIN");
        login.setBounds(600,450,150,80);
        login.addActionListener(this);
        add(login);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("jproject_images/first.jpg"));
        Image editedimg1 = i1.getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT);
        i1 = new ImageIcon(editedimg1);
        JLabel lb1 = new JLabel(i1);
        lb1.setBounds(0,0,800,600);
        add(lb1);

        setUndecorated(true);
        setLayout(null);
        setLocationRelativeTo(null);//takes the frame to center
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==login){
            new login();
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new starter();
    }
}
