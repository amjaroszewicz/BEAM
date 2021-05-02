package com.beam;

/**
 * App Class
 *
 * This class is used to handle the Main Application window and all relevant methods.
 * @author Jesse Aitken, Michael Hauser, Mile Limehouse, Ashwin Srivastava. Alexander Crawford.
 *
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                App mainApp = new App();
                maint = new Maintenance();
            }
        });




    }
    //constructor
    public App(){
        //Set Properties for Main window
        //MaintUtils.deleteIndexFile();
        //MaintUtils.deleteJsonFile();
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
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        System.exit(0);
                    }
                });

            }
        });
        //Set action for maintenanceButton
        //Displays maintenance window
        maintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        maint.setVisible(true);
                    }
                });

            }
        });
        //Set action for "Search" button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Add functionality to search button
                //Alerts user if they click search with blank text field

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
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

                ///
            }
        });

    }






    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}








