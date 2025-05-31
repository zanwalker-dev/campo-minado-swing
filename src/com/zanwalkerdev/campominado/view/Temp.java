package com.zanwalkerdev.campominado.view;

import com.zanwalkerdev.campominado.model.Board;

public class Temp {

    public static void main(String[] args) {

        Board board = new Board(3,3, 9);
        board.toggleMark(0,0);
        board.toggleMark(0,1);
        board.toggleMark(0,2);
        board.toggleMark(1,0);
        board.toggleMark(1,1);
        board.toggleMark(1,2);
        board.toggleMark(2,0);
        board.toggleMark(2,1);
        board.toggleMark(2,2);
    }
}
