package com.zanwalkerdev.campominado.model;

import com.zanwalkerdev.campominado.exceptions.ExplosionException;

import java.util.ArrayList;
import java.util.List;

public class Camp {

    private final int line;
    private final int column;

    private boolean mined; //false por padrão
    private boolean opened; //false por padrão
    private boolean marked;

    private final List<Camp> adjacentList = new ArrayList<>();

    public Camp(int line, int column){
        this.line = line;
        this.column = column;
    }

    /* Logica para adicionar vizinhos, para vizinhos na horizontal e vertical o valor
    absoluto deve ser 1 para vizinhos na diagonal o valor absoluto deverá ser 0 */
    boolean addAdjacent(Camp adjacent){

        boolean lineDiff = line != adjacent.line;
        boolean columnDiff = column != adjacent.column;
        boolean diagonal = lineDiff && columnDiff;

        int detlaLine = Math.abs(line - adjacent.line);
        int detlaColumn = Math.abs(column - adjacent.column);
        int deltaGeral = detlaLine + detlaColumn;

        if(deltaGeral == 1 && !diagonal){
            adjacentList.add(adjacent);
            return true;
        } else if(deltaGeral == 2 && diagonal){
            adjacentList.add(adjacent);
            return true;
        } else {
            return false;
        }
    }

    /* Logica para proteger campo marcado */
    void toggleMark(){
        if(!opened){
            marked = !marked;
        }
    }

    /* Logica para abrir campo */
    boolean open(){
        if(!opened && !marked){
            opened = true;

            if(mined){
                throw new ExplosionException();
            }

            if(adjacentSafe()){
                adjacentList.forEach(Camp::open);
            }
            return true;
        } else {
            return false;
        }
    }

    /* Verifica se um campo adjacente é seguro*/
    boolean adjacentSafe(){
       return adjacentList.stream().noneMatch(a -> a.mined);
    }

    void toMine(){
        mined = true;
    }

    void setOpened(boolean opened) {
        this.opened = opened;
    }

    public boolean isMined() {
        return mined;
    }

    public boolean isMarked(){
        return marked;
    }

    public boolean isOpened(){
        return opened;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    boolean objectiveAchieved(){
        boolean unveiled = !mined && opened;
        boolean safe = mined && marked;
        return unveiled || safe;
    }

    long minesAdjacent(){
        return  adjacentList.stream().filter(a -> a.mined).count();
    }

    void reset() {
        opened = false;
        mined = false;
        marked = false;
    }

    @Override
    public String toString() {
        if(marked){
            return "P";
        } else if( opened && mined){
            return "*";
        } else if(opened && minesAdjacent() > 0){
            return Long.toString(minesAdjacent());
        } else if(opened) {
            return " ";
        } else {
            return "#";
        }
    }
}
