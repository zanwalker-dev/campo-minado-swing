package com.zanwalkerdev.campominado.view;

import com.zanwalkerdev.campominado.model.Camp;
import com.zanwalkerdev.campominado.model.CampEvent;
import com.zanwalkerdev.campominado.model.ObserverCamp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CampButton extends JButton  implements ObserverCamp, MouseListener {

    private final Color BG_1 = new Color(92, 152, 28);
    private final Color BG_MARK = new Color(8, 179, 247);
    private final Color BG_EXPLODE = new Color(180, 66, 68);
    private final Color BORDER_COLOR = new Color(32, 90, 32);
    private final Color BORDER_COLOR_2 = new Color(69, 210, 69);
    private final Color TEXT_GREEN = new Color(0, 100, 0);

    private Camp camp;

    public CampButton(Camp camp) {
        this.camp = camp;
        setContentAreaFilled(false);
        setOpaque(true);

        setBackground(BG_1);
        setBorder(BorderFactory.createLineBorder(BORDER_COLOR));

        addMouseListener(this);

        camp.registerObserver(this);

    }

    @Override
    public void eventHappened(Camp camp, CampEvent event) {
        switch (event) {
            case OPEN:
                applyStyleOpen();
                break;
            case MARK:
                applyStyleMark();
                break;
            case EXPLODE:
                applyStyleExplode();
                break;
            default:
                applyStyleDefault();
        }
    }

    private void applyStyleOpen() {

        if(camp.isMined()) {
            setBackground(BG_EXPLODE);
            setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
            return;
        }

        setBackground(BG_1);
        setBorder(BorderFactory.createLineBorder(BORDER_COLOR_2, 2));

        switch (camp.minesAdjacent()){
            case 1:
                setForeground(Color.WHITE);
                break;
            case 2:
                setForeground(Color.BLUE);
                break;
            case 3:
                setForeground(Color.YELLOW);
            case 4:
            case 5:
            case 6:
                setForeground(Color.RED);
                break;
            default:
                setForeground(Color.PINK);
        }
        String valor = !camp.adjacentSafe() ? camp.minesAdjacent() + "" : "";
        setText(valor);
    }

    private void applyStyleMark() {
        setBackground(BG_MARK);
        setText("");
        setIcon(new ImageIcon(getClass().getResource("/images/mark.png")));
    }

    private void applyStyleExplode() {
        setBackground(BG_EXPLODE);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        setText("X");
    }

    private void applyStyleDefault() {
        setBackground(BG_1);
        setText("");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            System.out.println("botão esquerdo");
            camp.open();
        } else {
            System.out.println("botão direito");
            camp.toggleMark();
        }
    }


    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
