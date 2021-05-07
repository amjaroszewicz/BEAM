package com.beam;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private static final String INDEX_FILE_PATH ="index.json";

//    private static final StartUpService startupService = new StartUpServiceImpl();

    public static void main(String[] args) {
        App mainApp = new App();
    }


    //constructor
    public App(){
        run();
        maint = new Maintenance();
        String indexPath = System.getProperty("user.home") + "/" + INDEX_FILE_PATH;
//        startupService.loadIndex(indexPath);

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
                    resultsList.setListData(new String[0]);
                    String indexFilePath = MaintUtils.getIndexFilePath();
                    String searchText = inputTextbox.getText();
                    String [] terms = searchText.split(" ");
                    List<String> stringList = readIndexFile(indexFilePath);
                    boolean allRadioSelected = allRadio.isSelected();
                    boolean exactRadioSelected = exactRadio.isSelected();
                    boolean anyRadioSelected = anyRadio.isSelected();
                    List<String> result= Collections.EMPTY_LIST;
                    if(allRadioSelected){
                        result = stringList.stream().filter(fileInfo -> {
                            for (String term : terms) {
                                if (!fileInfo.contains(term)) {
                                    return false;
                                }
                            }
                            return true;
                        }).collect(Collectors.toList());

                    }

                    if(exactRadioSelected){
                        result  = stringList.stream().filter(fileInfo ->fileInfo.contains(searchText)).collect(Collectors.toList());
                    }

                    if(anyRadioSelected){
                        result = stringList.stream().filter(fileInfo -> {
                            for (String term : terms) {
                                if (fileInfo.contains(term)) {
                                    return true;
                                }
                            }
                            return false;
                        }).collect(Collectors.toList());
                    }
                    resultsList.setListData(result.toArray(new String[0]));

                }
                ///
            }
        });
    }

    private List<String> readIndexFile(String indexFilePath){
        try {
            return Files.readAllLines(new File(indexFilePath).toPath(), Charset.defaultCharset() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}








