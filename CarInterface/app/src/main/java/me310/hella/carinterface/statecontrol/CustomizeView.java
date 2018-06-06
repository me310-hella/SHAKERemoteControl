package me310.hella.carinterface.statecontrol;

import android.widget.ImageView;

import me310.hella.carinterface.R;

public class CustomizeView extends ControlView {

    public CustomizeView(ImageView imageView) {
        super(imageView);
    }

    @Override
    public ControlView topLeft() {
        return this;
    }

    @Override
    public ControlView topRight(){
        return StateController.getFanView();
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
        imageView.setImageBitmap(decodeSampledBitmapFromResource(resources, R.drawable.main_customize, REQUIRED_IMAGE_WIDTH, REQUIRED_IMAGE_HEIGHT));
        imageView.setImageResource(R.drawable.main_customize);
    }
}
