package com.zanwalkerdev.campominado.view;

import com.zanwalkerdev.campominado.model.Board;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    public BoardPanel(Board board){

        setLayout(new GridLayout(board.getLines(), board.getColumns()));

        int total = board.getLines() * board.getColumns();

        board.forEach(camp -> add(new CampButton(camp)));

    }
}
