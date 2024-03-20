package sms;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;

public class viewDetails extends JFrame {

    JScrollPane sp1;
    JTable details;
    viewDetails(){
        setSize(1000,700);
        sp1 = new JScrollPane();
        sp1.setBounds(50,100,900,400);
        add(sp1);

        details = new JTable();
        details.setFont(new Font("Tahoma", Font.PLAIN, 18));
        details.setRowHeight(26);

        sp1.setViewportView(details);

        JTableHeader tb1 = details.getTableHeader();
        tb1.setFont(new Font("Tahoma", Font.BOLD, 22));
        tb1.setBackground(Color.CYAN);

        getDetails();

        details.getColumnModel().getColumn(0).setMinWidth(150);
        details.getColumnModel().getColumn(1).setMaxWidth(40);
        details.getColumnModel().getColumn(3).setMaxWidth(120);
        details.getColumnModel().getColumn(5).setMinWidth(150);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    void getDetails(){
//        code to fetch the data from MYSQl
        conn c = new conn();
        String query = "select name, gr, bldgrp, dob, address, aadhar, gender, phoneno from studentdetails;";
        try{
            ResultSet rs =  c.s.executeQuery(query);
            details.setModel(DbUtils.resultSetToTableModel(rs));

        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new viewDetails();
    }
}
