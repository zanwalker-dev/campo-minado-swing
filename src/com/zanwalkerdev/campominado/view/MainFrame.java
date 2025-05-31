package com.zanwalkerdev.campominado.view;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(){
        setTitle("Campo Minado");
        setSize(690, 438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);


    }

    public static void main(String[] args) {

        new MainFrame();
    }

}
