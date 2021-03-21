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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;


public class Maintenance extends JFrame {
    public JTable table1;
    public JPanel panel1;
    public JButton addFileButton;
    public JButton removeFileButton;
    public JButton refreshFilesButton;
    public JButton mainMenuButton;
    private JLabel NIlabel;


    public static void main(String[] args) {
    }

    //constructor
    public Maintenance(){
        //final JFrame frame = new JFrame("App");
        if(!MaintUtils.checkIndexFile()){
            MaintUtils.createNewIndexFile();
            NIlabel.setVisible(true);
        }
        this.setTitle("Maintenance");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setSize(800, 601);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
        table1.setVisible(true);
        table1.setFillsViewportHeight(true);
        table1.setAutoCreateRowSorter(true);
        final DefaultTableModel dtm=new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        dtm.addColumn("File Name");
        dtm.addColumn("Date Modified");
        dtm.addColumn("Size");
        dtm.addColumn("File Path");
        table1.setModel(dtm);
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
                            // # TO-DO: create loop of indexable words and add to file.
                            try {
                                Path file2 = Paths.get(fc.getSelectedFile().getAbsolutePath());
                                BasicFileAttributes attr =
                                        Files.readAttributes(file2, BasicFileAttributes.class);

                            dtm.addRow(new Object[] {file.getName(),attr.lastModifiedTime(), attr.size() + " bytes",file.getAbsolutePath()});

                            } catch (IOException error) {
                                error.printStackTrace();
                            }


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
