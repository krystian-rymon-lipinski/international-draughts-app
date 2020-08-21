package com.krystianrymonlipinski.main_frame;

public class MainFramePresenter {

    public void onNewGameButtonClicked() {
        System.out.println("START!");
    }

    public void onChosenColorChanged(boolean isWhiteColorSelected) {
        System.out.println(isWhiteColorSelected);
    }

    public void onLoadMoveButtonClicked() {
        System.out.println("LOAD!");
    }
}
