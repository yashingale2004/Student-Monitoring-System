package sms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class options extends JFrame implements ActionListener {

    JButton attendance , marks , details;
    options(){
        setSize(1000,300);

        details = new JButton("details");
        details.setBounds(180,100,200, 80);
        details.addActionListener(this);
        add(details);

        attendance = new JButton("Attendance");
        attendance.setBounds(400,100,200,80);
        attendance.addActionListener(this);
        add(attendance);

        marks = new JButton("Marks");
        marks.setBounds(620,100,200,80);
        marks.addActionListener(this);
        add(marks);

        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==attendance){
            new viewAttendance();
            this.setVisible(false);
        }
        if(ae.getSource()==marks){
            new viewMarks();
            this.setVisible(false);
        }
        if(ae.getSource()==details){
            new viewDetails();
            this.setVisible(false);
        }

    }

    public static void main(String[] args) {
        new options();
    }
}
