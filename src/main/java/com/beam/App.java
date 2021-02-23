package com.beam;
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Hello world!
 *
 */
public class App
{
    private JTabbedPane tabbedPane1;
    private JTextField textField1;

    private JTextArea enterASearchTermTextArea;
    private JPanel exit1;
    private JTextArea searchEngine10TextArea;
    private JRadioButton allTermsRadioButton;
    private JRadioButton anyTermsRadioButton;
    private JRadioButton exactPhraseRadioButton;
    private JTable table1;
    private TableModel tablemodel;



    public App() {
        TableModel tableModel= table1.getModel();
        JTable jtable = new JTable();
        exit1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.exit(0);
            }
        });
    }


    public static void main( String[] args )
    {

        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().tabbedPane1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize ( 800, 601 );
        frame.setLocationRelativeTo ( null );
        frame.setVisible(true);


    }



}








