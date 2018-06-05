package me310.hella.carinterface.statecontrol;

import me310.hella.carinterface.R;

public class FanControlView extends ControlView {


    public FanControlView() {
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
    public ControlView middleLeft() {
        return null;
    }

    @Override
    public ControlView middleRight() {
        return null;
    }

    @Override
    public ControlView bottomLeft() {
        return null;
    }

    @Override
    public ControlView bottomRight() {
        return this;

    }

    @Override
    public void show() {
        imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.fan, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));

    }
}
