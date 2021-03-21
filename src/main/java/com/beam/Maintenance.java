package com.beam;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Maintenance extends JFrame {
    public JTable table1;
    public JPanel panel1;
    public JButton addFileButton;
    public JButton removeFileButton;
    public JButton refreshFilesButton;
    public JButton mainMenuButton;

    public static void main(String[] args) {
    }

    //constructor
    public Maintenance(){
        //final JFrame frame = new JFrame("App");
        this.setTitle("Maintenance");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setSize(600, 401);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Maintenance.this.setVisible(false);
            }
        });
        addFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Add File Functionality
            }
        });
        refreshFilesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Refresh File Functionality
            }
        });
        removeFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Remove File Functionality
            }
        });

    }

}
