package com.beam;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * App Class
 *
 * This class is used to handle the Main Application window and all relevant methods.
 * @author Jesse Aitken, Michael Hauser, Mile Limehouse, Ashwin Srivastava. Alexander Crawford.
 *
 */

public class App extends JFrame implements Runnable{
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
    }
    //constructor
    public App(){
        run();
        maint = new Maintenance();
    }
    public void run(){
        //Method calls below are for testing.
        //MaintUtils.deleteIndexFile();
        //MaintUtils.deleteJsonFile();
        //Set Properties for Main window
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.setTitle("Main Application");
        this.setContentPane(this.tabbedPane1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(800, 601);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        MaintUtils.checkVersion(this);

        //Set exit action for exitButton
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //Set action for maintenanceButton
        //Displays maintenance window
        maintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maint.setVisible(true);
            }
        });
        //Set action for "Search" button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //Add functionality to search button
            //Alerts user if they click search with blank text field
                    if(inputTextbox.getText().isEmpty()){
                        JOptionPane.showMessageDialog(tabbedPane1,
                                "You must enter something to search for.",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        // Add functionality if search result is not blank
                    }
            ///
            }
        });
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}








