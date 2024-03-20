package sms;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class addAttendance extends JFrame implements ActionListener {
    JLabel l1, l2;
    JTextField gr, attendance;
    JButton display , submit, back;
    JScrollPane sp1;
    JTable details;


    Font f1 = new Font("Tahoma", Font.PLAIN, 22);
    addAttendance(){
        setSize(650,600);

        l1 = new JLabel("Enter GR");
        l1.setBounds(120,60,150,50);
        l1.setFont(f1);
        add(l1);

        gr = new JTextField();
        gr.setBounds(250,60,120,50);
        gr.setFont(f1);
        add(gr);

        display = new JButton("Display");
        display.setBounds(400, 60, 150,50);
        display.addActionListener(this);
        display.setFont(f1);
        add(display);

        sp1 = new JScrollPane();
        sp1.setBounds(70,200,500,80);
        add(sp1);

        details = new JTable();
        details.setRowHeight(40);
        sp1.setViewportView(details);
        details.setFont(f1);

        JTableHeader th1 = details.getTableHeader();
        th1.setFont(f1);
        th1.setBackground(Color.decode("#96e37b"));


        l2 = new JLabel("Update / ADD Attendance");
        l2.setFont(f1);
        l2.setBounds(50,350,300,50);
        add(l2);

        attendance = new JTextField();
        attendance.setBounds(320,350,120,50);
        attendance.setFont(f1);
        add(attendance);

        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setBounds(250,450,200,50);
        submit.setFont(f1);
        add(submit);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("jproject_images/back.png"));
        Image img1 = i1.getImage().getScaledInstance(75,50,Image.SCALE_DEFAULT);
        i1 = new ImageIcon(img1);
        back = new JButton(i1);
        back.setBounds(0,0 , 50,50);
        back.addActionListener(this);
        add(back);

        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        UIManager.put("OptionPane.messageFont", f1); // to set font of MessageDialogue

        String gr = this.gr.getText();

        if(ae.getSource()==display){
            conn c = new conn();
            String query1 = "select gr , name , attendance from studentdetails where gr = '"+gr+"';";
            try{
                ResultSet rs1 = c.s.executeQuery(query1);
                details.setModel(DbUtils.resultSetToTableModel(rs1));
            }catch (Exception e){
                System.out.println(e);
            }
        }

        if(ae.getSource()==submit){
            String attendance = this.attendance.getText();

            conn c = new conn();
            String query2 = "update studentdetails set attendance = '"+attendance+"' where gr = '"+gr+"';";
            try{
                c.s.executeUpdate(query2);
                JOptionPane.showMessageDialog(null, "Attendance updated successfully!");
            }
            catch (Exception e){
                System.out.println(e);
            }
        }

        if(ae.getSource()==back){
            new viewAttendance();
            this.setVisible(false);
        }

    }

    public static void main(String[] args) {
        new addAttendance();
    }
}
