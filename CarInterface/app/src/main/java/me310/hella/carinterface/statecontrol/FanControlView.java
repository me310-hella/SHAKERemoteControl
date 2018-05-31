package me310.hella.carinterface.statecontrol;

import android.widget.Button;

import java.util.List;

public class FanControlView extends ControlView {


    public FanControlView(List<Button> buttons) {
        super(buttons);
    }

    @Override
    public ControlView topLeft() {
        if (activated) {
            topLeftButton.setBackgroundColor(defaultColor);
            activated = false;

        } else {
            topLeftButton.setBackgroundColor(activatedColor);
            activated = true;
        }
        return this;
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
        System.out.println("Show fan control");
        topLeftButton.setText("On/Off");

    }
}
