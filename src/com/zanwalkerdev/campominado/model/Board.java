package com.zanwalkerdev.campominado.model;

import com.zanwalkerdev.campominado.exceptions.ExplosionException;
import com.zanwalkerdev.campominado.exceptions.QuitException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class Board {

    private int lines;
    private int columns;
    private int mines;

    private final List<Camp> camps = new ArrayList<>();

    public Board(int lines, int columns, int mines) {
        this.lines = lines;
        this.columns = columns;
        this.mines = mines;

        generateCamps();
        associateAdjacents();
        drawMines();
    }

    public void open(int line, int column) {
        try {
            camps.stream().filter(c -> c.getLine() == line && c.getColumn() == column)
                    .findFirst().ifPresent(c -> {c.open();});
        } catch (ExplosionException e) {
            camps.forEach(c -> c.setOpened(true));

            throw e;
        }
    }

    public void toggleMark(int line, int column) {
        camps.stream().filter(c -> c.getLine() == line && c.getColumn() == column)
                .findFirst().ifPresent(c -> {c.toggleMark();});
    }

    private void generateCamps() {
        for (int line = 0; line < this.lines; line++) {
            for (int column = 0; column < this.columns; column++) {
                camps.add(new Camp(line, column));
            }
        }
    }

    private void associateAdjacents() {
        for(Camp c1: camps) {
            for(Camp c2: camps) {
                c1.addAdjacent(c2);
            }
        }
    }

    private void drawMines() {
        long armedMines = 0;
        Predicate<Camp> mined = c -> c.isMined();

        do{
            int random = (int) (Math.random() * camps.size());
            camps.get(random).toMine();
            armedMines = camps.stream().filter(mined).count();
        } while (armedMines < this.mines);
    }

    public boolean objectiveAchieved(){
        return camps.stream().allMatch(c -> c.objectiveAchieved());
    }

    public void resetGame(){
        camps.stream().forEach(c -> c.reset());
        drawMines();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for(int c = 0; c < columns; c++){
            sb.append(" ");
            sb.append(c);
            sb.append(" ");
        }
        sb.append("\n");

        int i = 0;
        for(int line = 0; line < this.lines; line++){
            sb.append(line);
            sb.append(" ");
            for(int column = 0; column < this.columns; column++){
                sb.append(" ");
                sb.append(camps.get(i));
                sb.append(" ");
                i++;
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
