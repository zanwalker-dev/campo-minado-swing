package com.zanwalkerdev.campominado.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;


public class Board implements ObserverCamp {

    private int lines;
    private int columns;
    private int mines;

    private final List<Camp> camps = new ArrayList<>();
    private final List<Consumer<Boolean>> observers = new ArrayList<>();

    public Board(int lines, int columns, int mines) {
        this.lines = lines;
        this.columns = columns;
        this.mines = mines;

        generateCamps();
        associateAdjacents();
        drawMines();
    }

    public void registerObserver(Consumer<Boolean> observer) {
        observers.add(observer);
    }

    private void notifyObservers(boolean result){
        observers.stream()
                .forEach(o -> o.accept(result));
    }

    public void open(int line, int column) {
            camps.stream().filter(c -> c.getLine() == line && c.getColumn() == column)
                    .findFirst()
                    .ifPresent(c -> {c.open();});
    }

    public void toggleMark(int line, int column) {
        camps.stream().filter(c -> c.getLine() == line && c.getColumn() == column)
                .findFirst().ifPresent(c -> {c.toggleMark();});
    }

    private void generateCamps() {
        for (int line = 0; line < this.lines; line++) {
            for (int column = 0; column < this.columns; column++) {
                Camp camp = new Camp(line, column);
                camp.registerObserver(this);
                camps.add(camp);
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
    public void eventHappened(Camp camp, CampEvent event) {
        if(event == CampEvent.EXPLODE){
            showMines();
            notifyObservers(false);
        } else if(objectiveAchieved()) {
            notifyObservers(true);
        }
    }

    private void showMines(){
        camps.stream()
                .filter(c -> c.isMined())
                .forEach(c -> c.setOpened(true));
    }
}
