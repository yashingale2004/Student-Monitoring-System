package sms;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class viewMarks extends JFrame implements ActionListener{
    JTabbedPane tabs,tabspass,tabsfail;
    JScrollPane sp1, sp2,sppass1,sppass2,spfail1,spfail2;
    JTable sem1, sem2,psem1,psem2,fsem1,fsem2;
    JButton addMarks1,pass,refresh;
    JLabel l1,l2;

    Font f1 = new Font("Tahoma", Font.PLAIN, 19);
    Font f2 = new Font("Tahoma", Font.PLAIN, 18);

    viewMarks(){
        setSize(1000,800);

        tabs = new JTabbedPane();
        tabs.setBounds(300,100,650,600);
        tabs.setFont(f1);
        add(tabs);

        sem1 = new JTable();

        JTableHeader tb1 = sem1.getTableHeader();
        tb1.setFont(new Font("Tahoma", Font.BOLD, 22));

        sem2 = new JTable();
        JTableHeader tb2 = sem2.getTableHeader();
        tb2.setFont(new Font("Tahoma", Font.BOLD, 22));

        sp1 = new JScrollPane();
        sp1.setViewportView(sem1);

        sp2 = new JScrollPane();
        sp2.setViewportView(sem2);

        getDetails();

        tabs.addTab("SEM 1", sp1);
        tabs.addTab("SEM 2", sp2);

        addMarks1 = new JButton("Add/Update");
        addMarks1.setBounds(60,120,150,50);
        addMarks1.setFont(f1);
        addMarks1.addActionListener(this);
        add(addMarks1);

        pass = new JButton("Pass/Fail");
        pass.setBounds(60,300,150,50);
        pass.setFont(f1);
        add(pass);
        pass.addActionListener(this);

        refresh = new JButton("Refresh");
        refresh.setBounds(60,480,150,50);
        refresh.setFont(f1);
        refresh.addActionListener(this);
        add(refresh);

        l1 = new JLabel("Passed Students");
        l1.setBounds(250,40,200,50);
        l1.setFont(f1);
        add(l1);
        l1.setVisible(false);


        l2 = new JLabel("Failed Students");
        l2.setBounds(250,400,200,50);
        l2.setFont(f1);
        add(l2);
        l2.setVisible(false);

        sppass1 = new JScrollPane();
        psem1 = new JTable();
        JTableHeader tb3 = psem1.getTableHeader();
        tb3.setFont(new Font("Tahoma", Font.BOLD, 22));
        sppass1.setViewportView(psem1);

        sppass2 = new JScrollPane();
        psem2 = new JTable();
        JTableHeader tb4 = psem2.getTableHeader();
        tb4.setFont(new Font("Tahoma", Font.BOLD, 22));
        sppass2.setViewportView(psem2);

        spfail1 = new JScrollPane();
        fsem1 = new JTable();
        JTableHeader tb5 = fsem1.getTableHeader();
        tb5.setFont(new Font("Tahoma", Font.BOLD, 22));
        spfail1.setViewportView(fsem1);

        spfail2 = new JScrollPane();
        fsem2 = new JTable();
        JTableHeader tb6 = fsem2.getTableHeader();
        tb6.setFont(new Font("Tahoma", Font.BOLD, 22));
        spfail2.setViewportView(fsem2);

        tabspass= new JTabbedPane();
        tabspass.setBounds(250,80,675,300);
        tabspass.setFont(f1);
        add(tabspass);
        tabspass.setVisible(false);

        tabsfail = new JTabbedPane();
        tabsfail.setBounds(250,440,675,300);
        tabsfail.setFont(f1);
        add(tabsfail);
        tabsfail.setVisible(false);

        tabspass.addTab("Sem1",sppass1);
        tabspass.addTab("Sem2",sppass2);
        tabspass.setFont(f1);
        tabspass.setVisible(false);

        tabsfail.addTab("Sem1",spfail1);
        tabsfail.addTab("Sem2",spfail2);
        tabsfail.setFont(f1);
        tabsfail.setVisible(false);

        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void getDetails(){
        conn c = new conn();

//        sem1 marks fetching code
        String query1 = "select gr , name , english , maths , science ,total1 from studentdetails;";
        try{
           ResultSet rs1 = c.s.executeQuery(query1);
           sem1.setModel(DbUtils.resultSetToTableModel(rs1));
        }catch (Exception e){
            System.out.println(e);
        }

//        sem 2 marks fetching code
        String query2 = "select gr , name, marathi , history, geography ,total2 from studentdetails;";
        try{
            ResultSet rs2 = c.s.executeQuery(query2);
            sem2.setModel(DbUtils.resultSetToTableModel(rs2));
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    public void actionPerformed(ActionEvent ae){
        UIManager.put("OptionPane.messageFont",f2);
        if (ae.getSource()==addMarks1){
            sms.addMarks markstemp = new addMarks();
        }
        if (ae.getSource()==pass){
            String cutoff= JOptionPane.showInputDialog("Enter the Cutoff marks : ");

            //sem1 and sem 2 passed student
            conn c = new conn();
            String qr1 = "select gr , name , english , maths , science , total1 from studentdetails where total1>='"+cutoff+"';";
            String qr2 = "select gr , name , history , geography , marathi , total2 from studentdetails where total2>='"+cutoff+"';";

            //SEM1 AND SEM2 FAILED STUDENT

            String qr3 = "select gr , name , english , maths , science , total1 from studentdetails where total1 < '"+cutoff+"';";
            String qr4 = "select gr , name , history , geography , marathi , total2 from studentdetails where total2 < '"+cutoff+"';";

            try {
                ResultSet rs10 = c.s.executeQuery(qr1);
                psem1.setModel(DbUtils.resultSetToTableModel(rs10));

                ResultSet rs20 = c.s.executeQuery(qr2);
                psem2.setModel(DbUtils.resultSetToTableModel(rs20));

                ResultSet rs30= c.s.executeQuery(qr3);
                fsem1.setModel(DbUtils.resultSetToTableModel(rs30));

                ResultSet rs40 = c.s.executeQuery(qr4);
                fsem2.setModel(DbUtils.resultSetToTableModel(rs40));

                tabs.setVisible(false);
                l1.setVisible(true);
                l2.setVisible(true);
                tabspass.setVisible(true);
                tabsfail.setVisible(true);

            }
            catch(Exception e){
                System.out.println(e);

            }
        }
        if (ae.getSource()== refresh){
            getDetails();
            tabs.setVisible(true);
            tabspass.setVisible(false);
            tabsfail.setVisible(false);
            l1.setVisible(false);
            l2.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new viewMarks();
    }
}