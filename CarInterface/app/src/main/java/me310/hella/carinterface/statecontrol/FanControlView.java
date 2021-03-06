package me310.hella.carinterface.statecontrol;

import android.widget.ImageView;

import me310.hella.carinterface.R;

public class FanControlView extends ControlView {

    public boolean activated = false;

    public FanControlView(ImageView imageView) {
        super(imageView);
    }

    @Override
    public ControlView topLeft() {
        if (activated) {

            activated = false;

        } else {

            activated = true;
        }
        return this;
    }

    @Override
    public ControlView middleLeft() {
        return this;
    }

    @Override
    public ControlView middleRight() {
        return this;
    }

    @Override
    public ControlView bottomLeft() {
        return this;
    }

    @Override
    public ControlView bottomRight() {
        return this;

    }

    @Override
    public void show() {
        imageView.setImageResource(R.drawable.fan);

    }
}
