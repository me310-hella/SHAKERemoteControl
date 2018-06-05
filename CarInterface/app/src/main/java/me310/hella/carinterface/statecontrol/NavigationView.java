package me310.hella.carinterface.statecontrol;

import me310.hella.carinterface.R;

public class NavigationView extends ControlView {
    @Override
    public ControlView topLeft() {
        return null;
    }

    @Override
    public ControlView topRight() {
        return null;
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
        return null;
    }

    @Override
    public void show() {
        imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.navigation, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
    }
}
