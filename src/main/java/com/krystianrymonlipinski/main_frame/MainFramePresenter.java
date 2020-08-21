package com.krystianrymonlipinski.main_frame;

public class MainFramePresenter {

    MainFrameModel mainFrameModel;
    MainFrameView mainFrameView;

    public MainFramePresenter(MainFrameView view) {
        mainFrameModel = new MainFrameModel();
        mainFrameView = view;
    }

    public void onNewGameButtonClicked() {
        System.out.println("START!");
    }

    public void onChosenColorChanged(boolean isWhiteColorSelected) {
        System.out.println(isWhiteColorSelected);
    }

    public void onLoadMoveButtonClicked() {
        mainFrameView.showIncorrectMoveDialog();
    }
}
