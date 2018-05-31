package me310.hella.carinterface.statecontrol;

import android.widget.Button;

import java.util.List;

public class MainControlView extends ControlView {




    public MainControlView(List<Button> buttons) {
        super(buttons);
    }

    @Override
    public ControlView topLeft() {
        return new FanControlView(buttons);
    }

    @Override
    public ControlView topRight() {
       return this;

    }

    @Override
    public ControlView bottomRight() {
        return this;

    }

    @Override
    public void show() {
        topLeftButton.setText("Fan Control");
    }
}
