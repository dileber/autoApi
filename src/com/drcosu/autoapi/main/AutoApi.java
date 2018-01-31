package com.drcosu.autoapi.main;

import javax.swing.*;

/**
 * Created by DELL on 2017/12/13.
 */
public class AutoApi {
    public JPanel panel1;
    public JTextField textField1;
    public JButton Button;
    public JLabel lable2;
    public JTextField textField2;
    public JLabel lable1;
    public JTextField textField3;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AutoApi");
        frame.setContentPane(new AutoApi().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
