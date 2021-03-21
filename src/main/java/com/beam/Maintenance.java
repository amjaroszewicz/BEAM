package com.beam;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;


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
        final JFileChooser fc = new JFileChooser();
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

                    //Handle open button action.
                    if (e.getSource() == addFileButton) {
                        int returnVal = fc.showOpenDialog(Maintenance.this);

                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = fc.getSelectedFile();
                            //This is where a real application would open the file.
                            //log.append("Opening: " + file.getName() + "." + newline);
                        } else {
                            //log.append("Open command cancelled by user." + newline);
                        }
                    }
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
