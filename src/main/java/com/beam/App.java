package com.beam;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Locale;

public class App extends JFrame {
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JButton searchButton;
    private JList list1;
    private JButton exitButton;
    private JLabel titleLabel;
    private JPanel aboutPanel;
    private JPanel buttonGroupPanel;
    private JRadioButton exactRadio;
    private JRadioButton anyRadio;
    private JRadioButton allRadio;
    private JButton maintenanceButton;
    private JLabel inputLabel;
    private JTextField inputTextbox;
    private JPanel indexPanel;
    private JLabel indexLabel;
    private JLabel indexNumberLabel;
    private JPanel inputPanel;
    private JPanel bottomPanel;
    private JList resultsList;
    private JLabel aboutLabel;
    private JLabel about2Label;
    private JLabel about3Label;
    private JPanel mainPanel;
    public static JFrame maint=null;





    public static void main(String[] args) {
        App mainApp = new App();
         maint = new Maintenance();
        //mainApp.setVisible(true);


    }
    //constructor
    public App(){
        //final JFrame frame = new JFrame("App");
        this.setTitle("Main Application");
        this.setContentPane(this.tabbedPane1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(800, 601);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        maintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maint.setVisible(true);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Add functionality to search button
                if(inputTextbox.getText().isEmpty()){
                    JOptionPane.showMessageDialog(tabbedPane1,
                            "You must enter something to search for.",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    // Add functionality if search result is not blank
                }
            }
        });

    }






    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}








