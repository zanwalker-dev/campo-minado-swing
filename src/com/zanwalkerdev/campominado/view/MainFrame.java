package com.zanwalkerdev.campominado.view;

import com.zanwalkerdev.campominado.model.Board;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(){

        Board board = new Board(16,30, 50);
        BoardPanel boardPanel = new BoardPanel(board);

        add(boardPanel);

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
