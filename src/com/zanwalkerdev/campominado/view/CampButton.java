package com.zanwalkerdev.campominado.view;

import com.zanwalkerdev.campominado.model.Camp;
import com.zanwalkerdev.campominado.model.CampEvent;
import com.zanwalkerdev.campominado.model.ObserverCamp;

import javax.swing.*;

public class CampButton extends JButton  implements ObserverCamp {

    public CampButton(Camp camp) {

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
    }

    private void applyStyleMark() {
    }

    private void applyStyleExplode() {
    }

    private void applyStyleDefault() {
    }

}
