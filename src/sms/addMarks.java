package sms;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class addMarks extends JFrame implements ActionListener {

    int labW = 120, labH = 50;
    int txtW = 70, txtH = 50;

    JLabel l1;
    JTextField gr;
    JButton display, update1, update2;
    JTabbedPane tabs;
    JPanel p1, p2;
    JScrollPane sp1 , sp2;
    JTable sem1 , sem2;
    JTextField english , maths , science, history , geography , marathi;


    Font f1 = new Font("Tahoma", Font.PLAIN, 22);
    Font f2 = new Font("Tahoma", Font.BOLD, 24);

    addMarks(){
        setSize(650,800);

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

        tabs = new JTabbedPane();
        tabs.setBounds(40,160,550,550);
        tabs.setFont(f2);
        add(tabs);

        p1 = new JPanel();
        p1.setLayout(null);

        p2 = new JPanel();
        p2.setLayout(null);

        tabs.addTab("SEM 1", p1);
        tabs.addTab("SEM 2", p2);

        sem1 = new JTable();
        sem1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        sem1.setRowHeight(30);

        sem2 = new JTable();
        sem2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        sem2.setRowHeight(30);

        JTableHeader tb1 = sem1.getTableHeader();
        tb1.setFont(f1);

        JTableHeader tb2 = sem2.getTableHeader();
        tb2.setFont(f1);

        sp1 = new JScrollPane();
        sp1.setBounds(30,40,470,80);
        p1.add(sp1);

        sp2 = new JScrollPane();
        sp2.setBounds(30,40,470,80);
        p2.add(sp2);

        sp1.setViewportView(sem1);
        sp2.setViewportView(sem2);

        JLabel l1 = new JLabel("English");
        l1.setBounds(50,180,labW,labH);
        l1.setFont(f1);
        p1.add(l1);

        english = new JTextField();
        english.setBounds(300,180,txtW,txtH);
        english.setFont(f1);
        p1.add(english);

        JLabel l2 = new JLabel("Maths");
        l2.setBounds(50,240,labW,labH);
        l2.setFont(f1);
        p1.add(l2);

        maths = new JTextField();
        maths.setBounds(300,240,txtW, txtH);
        maths.setFont(f1);
        p1.add(maths);

        JLabel l3 = new JLabel("Science");
        l3.setBounds(50,300,labW,labH);
        l3.setFont(f1);
        p1.add(l3);

        science = new JTextField();
        science.setBounds(300,300,txtW, txtH);
        science.setFont(f1);
        p1.add(science);

        update1 = new JButton("Update");
        update1.setBounds(200,400,150,50);
        update1.setFont(f1);
        update1.addActionListener(this);
        p1.add(update1);

        JLabel l4 = new JLabel("Marathi");
        l4.setBounds(50,180,labW, labH);
        l4.setFont(f1);
        p2.add(l4);

        marathi = new JTextField();
        marathi.setBounds(300,180, txtW, txtH);
        marathi.setFont(f1);
        p2.add(marathi);

        JLabel l5 = new JLabel("History");
        l5.setBounds(50,240, txtW, txtH);;
        l5.setFont(f1);
        p2.add(l5);

        history = new JTextField();
        history.setBounds(300,240,txtW,txtH);
        history.setFont(f1);
        p2.add(history);

        JLabel l6 = new JLabel("Geography");
        l6.setBounds(50,300, labW,labH);
        l6.setFont(f1);
        p2.add(l6);

        geography = new JTextField();
        geography.setBounds(300,300, txtW, txtH);
        geography.setFont(f1);
        p2.add(geography);

        update2 = new JButton("Update");
        update2.setBounds(200,400,150,50);
        update2.setFont(f1);
        update2.addActionListener(this);
        p2.add(update2);




        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == display){
            String gr = this.gr.getText();

            conn c = new conn();
//            query to fetch the marks of sem1
            String query1 = "select name , english , maths , science from studentdetails where gr= '"+gr+"';";
            try {
                ResultSet rs1 =c.s.executeQuery(query1);
                sem1.setModel(DbUtils.resultSetToTableModel(rs1));

            }catch (Exception e){
                System.out.println(e);
            }

//            query to fetch the marks of sem2
            String query2 = "select name , marathi , history , geography from studentdetails where gr = '"+gr+"';";
            try {
                ResultSet rs2 = c.s.executeQuery(query2);
                sem2.setModel(DbUtils.resultSetToTableModel(rs2));
            }catch (Exception e){
                System.out.println(e);
            }

        }

        if(ae.getSource()==update1){
            String gr =  this.gr.getText();

            String english = this.english.getText();
            String maths = this.maths.getText();
            String science = this.science.getText();

            conn c = new conn();
            String query = "update studentdetails set english = '"+english+"', maths = '"+maths+"', science = '"+science+"' where gr = '"+gr+"';";
            try {
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"SEM 1 Data updated successfully!");
            }catch (Exception e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Some error occured :(");
            }
        }

        if(ae.getSource() == update2){
            String gr = this.gr.getText();

            String  marathi = this.marathi.getText();
            String history = this.history.getText();
            String geography = this.geography.getText();

            conn c = new conn();
            String query = "update studentdetails set marathi = '"+marathi
                    +"', history = '"+history
                    +"', geography = '"+geography+"' where gr = '"+gr+"';";

            try{
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "SEM 2 marks updated successfully!");
            }catch (Exception e){
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Some error occured :(");
            }

        }

    }

    public static void main(String[] args) {
        new addMarks();
    }
}
