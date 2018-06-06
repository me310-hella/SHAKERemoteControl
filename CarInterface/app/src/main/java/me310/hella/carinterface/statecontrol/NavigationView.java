package me310.hella.carinterface.statecontrol;

import android.widget.ImageView;

import me310.hella.carinterface.R;

public class NavigationView extends ControlView {

    public NavigationView(ImageView imageView) {
        super(imageView);
    }

    @Override
    public ControlView topLeft() {
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
        //imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.navigation, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
        imageView.setImageResource(R.drawable.navigation);
    }
}
