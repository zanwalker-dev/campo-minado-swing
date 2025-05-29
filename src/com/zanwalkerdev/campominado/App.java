package com.zanwalkerdev.campominado;

import com.zanwalkerdev.campominado.model.Board;
import com.zanwalkerdev.campominado.view.ConsoleBoard;

public class App {

    public static void main(String[] args) {

        Board board = new Board(7, 9, 6);
        new ConsoleBoard(board);

    }
}
