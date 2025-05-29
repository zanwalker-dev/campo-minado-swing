package com.zanwalkerdev.campominado.view;

import com.zanwalkerdev.campominado.exceptions.ExplosionException;
import com.zanwalkerdev.campominado.exceptions.QuitException;
import com.zanwalkerdev.campominado.model.Board;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class ConsoleBoard {

    private Board board;
    Scanner input = new Scanner(System.in);

    public ConsoleBoard(Board board) {
        this.board = board;

        executeGame();
    }

    private void executeGame() {
        try {
            boolean continueGame = true;

            while (continueGame){
                gameCicle();

                System.out.println("Reiniciar jogo? (S/n) ");
                String res =input.nextLine();
                if("n".equalsIgnoreCase(input.nextLine())){
                    continueGame = false;
                } else {
                    board.resetGame();
                }
            }
        } catch (QuitException e) {
            System.out.println("Até logo!");
        } finally {
            input.close();
        }
    }

    private void gameCicle() {

        try{

            while(!board.objectiveAchieved()){ //Enquanto objetivo não for alcançado segue o loop
                System.out.println(board); // renderiza o tabuleiro.

                String playerInput = captureInput("Digite (linha, coluna): ");

                Iterator<Integer> lc = Arrays.stream(playerInput.split(","))
                        .map( e -> Integer.parseInt(e.trim()) )
                        .iterator();

                playerInput = captureInput("1 - Abrir / 2 - Marcar");

                if("1".equals(playerInput)){
                    board.open(lc.next(), lc.next());
                } else if("2".equals(playerInput)){
                    board.toggleMark(lc.next(), lc.next());
                }
            }
            System.out.println("Você ganhou!!!<3");
        } catch (ExplosionException e){
            System.out.println(board);
            System.out.println("BOOOOM! Você perdeu! x.x");
        }
    }

    private String captureInput(String text) {
        System.out.print(text);
        String playerInput = input.nextLine();

        if("".equals(playerInput)) {
            System.out.println("Digite um valor de 0 a 8 para coluna e linha. Ex: 3,3");
        }

        if("sair".equalsIgnoreCase(playerInput)){
            throw new QuitException();
        }
        return playerInput;
    }
}
