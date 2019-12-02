package pl.sebastian;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

public class ReaderFile implements ActionListener {
    JFrame frame;
    JButton open, write;
    JTextArea textArea;

    ReaderFile() {


        frame = new JFrame("ReaderFile");


        textArea = new JTextArea();
        open = new JButton("OPEN");
        open.setBounds(40, 240, 100, 80);
        open.addActionListener(this);
        frame.add(open);
        write = new JButton("SAVE");
        write.setBounds(200, 240, 100, 80);
        write.addActionListener(this);
        frame.add(write);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(30, 40, 280, 100);
        frame.add(scrollPane);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(350, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


    }


    public static void main(String[] args) {
        // write your code here
        new ReaderFile();
    }

    @Override
    public void actionPerformed(ActionEvent e) throws NullPointerException {
        Object o = e.getSource();
        if (o == open) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

                File file = fileChooser.getSelectedFile();

                try {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNext()) {
                        textArea.append(scanner.nextLine() + "\n");
                    }
                } catch (FileNotFoundException ex) {

                }

            }
        } else if (o == write) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

                File file = fileChooser.getSelectedFile();
                try {
                    PrintWriter printWriter = new PrintWriter(file);
                    Scanner scanner = new Scanner(textArea.getText());
                    while (scanner.hasNext()) {
                        printWriter.println(scanner.nextLine() + "\n");
                    }
                    printWriter.close();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }
}